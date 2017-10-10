package com.jumper.hospital.controller;
/**
 * 控制器基类
 * @author rent
 * @date 2015-10-16
 */
import java.io.InputStream;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jumper.hospital.Comparator.AuthorityComparator;
import com.jumper.hospital.base.Progress;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.Authority;
import com.jumper.hospital.entity.Role;
import com.jumper.hospital.service.AdminService;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.DesEncryptUtils;
import com.jumper.hospital.utils.FastdfsUpload;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.vo.VoAdminEdit;

@Controller
@RequestMapping("/base")
public class BaseController {

	@Autowired
	private AdminService adminServiceImpl;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	@ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
	        this.request = request;
	        this.response = response;
	        
	        String requestType = request.getHeader("X-Requested-With");
			if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
				return;
			}
	        Admin admin = getLoginAdminInfo();
	        if(admin != null && admin.getHospitalInfo() != null){
	        	 this.request.setAttribute("hospitalInfo", admin.getHospitalInfo());
	        	 this.request.setAttribute("filePath", Consts.BASE_FILE_URL);

	         
	        	 String param =Consts.COMMUNITY_HOSPITAL_URL + DesEncryptUtils.Encrypt(admin.getUsername()+"/"+admin.getPassword(), DesEncryptUtils.KeyStr);
	        	 request.getSession().setAttribute("loginParam", param);
	        }
	        this.request.setAttribute("v", Consts.VERSION);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public HttpServletRequest getRequest(){
		return this.request;
	}
	
	public HttpServletResponse getResponse(){
		return this.response;
	}
	
	/**
	 * 获取当前登录管理员信息
	 * @return
	 */
	public Admin getLoginAdminInfo(){
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser == null || currentUser.getPrincipal() == null){
			return null;
		}
		String user = currentUser.getPrincipal().toString();
		Admin admin = adminServiceImpl.findByUsername(user);
		if(admin != null){
			return admin;
		}
		return null;
	}

	public int getRoleAuthority(Admin admin)
    {
	    Set<Role> rolesSet = admin.getRoles();
        return rolesSet.iterator().next().getId();
    }

	/**
	 * 文件上传方法，单个文件上传
	 * @param request
	 * @param response
	 * @param file
	 */
	@RequestMapping("upload")
	public void fileUpload(HttpServletRequest request, HttpServletResponse response, MultipartFile file){
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	        MultipartFile upload_file = multipartRequest.getFile("file");
	        String name = upload_file.getOriginalFilename();
	        InputStream stream = upload_file.getInputStream();
	        String filePath = FastdfsUpload.upoladFile(name, stream);
	        
	        response.getWriter().write(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("unauthorized")
	public String test(){
		return "main/index";
	}
	
	@RequestMapping("login")
	public String loginPage(){
		return "common/login";
	}
	
	@RequestMapping("login_out")
	public String login_out(){
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isAuthenticated()){
			currentUser.logout();
		}
		return "redirect:/base/login";
	}
	
	@RequestMapping("admin")
	public void getAdminInfo(){
		Admin admin = getLoginAdminInfo();
		VoAdminEdit voAdmin = VoAdminEdit.getVoAdmin(admin);
		JsonUtils.render(response, voAdmin);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("main")
	public String main(){
		Admin admin = getLoginAdminInfo();
		if(admin != null){
			Set<Role> roles = admin.getRoles();
			Set<Authority> auths = new TreeSet<Authority>(new AuthorityComparator());
			if(roles != null && roles.size() > 0){
				for(Role role : roles){
					auths.addAll(role.getAuthorities());
				}
			}
			if(auths != null && auths.size() > 0){
				for(Authority auth : auths){
					if(auth.getParents() == null && StringUtils.isNotEmpty(auth.getUrl())){
						return "redirect:"+auth.getUrl();
					}
				}
			}
		}
		return "redirect:/base/unauthorized";
	}
	
	/**
	 * 页面定位方法
	 * @param map
	 * @return
	 */
	public ModelMap pageLocation(ModelMap model, String module, String menu, String submenu){
		model.put(Consts.MODULE, module);
		model.put(Consts.MENU, menu);
		model.put(Consts.SUBMENU, submenu);
		return model;
	}
	
	@RequestMapping("testUp")
	public String testUpload(){
		return "main/home";
	}
	
	@RequestMapping("home")
	public String mainHome(){
		return "main/home";
	}
	
	@RequestMapping("progress")
	public void getFileUploadProgress(HttpServletRequest request){
		Progress status = (Progress) request.getSession().getAttribute(Consts.PROGRESS);
		if(status == null){
			JsonUtils.render(getResponse(), "{}");
		}
		JsonUtils.render(getResponse(), status);
	}
	
}