package com.jumper.hospital.controller;
/**
 * 权限管理操作Controller
 * @author rent
 * @date 2015-11-27
 */
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.Authority;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.Role;
import com.jumper.hospital.service.AdminService;
import com.jumper.hospital.service.AuthorityService;
import com.jumper.hospital.service.HospitalInfoService;
import com.jumper.hospital.service.MonitorAdminService;
import com.jumper.hospital.service.RoleService;
import com.jumper.hospital.utils.BeanUtils;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.utils.MD5EncryptUtils;
import com.jumper.hospital.utils.TimeUtils;

@Controller
@RequestMapping("auth")
public class AuthController extends BaseController {

	@Autowired
	private AdminService adminServiceImpl;
	@Autowired
	private HospitalInfoService hospitalInfoServiceImpl;
	@Autowired
	private RoleService roleServiceImpl;
	@Autowired
	private AuthorityService authorityServiceImpl;
	@Autowired
	private MonitorAdminService monitorAdminService;
	
	/**
	 * 主页管理员列表
	 * @return
	 */
	@RequestMapping("index")
	public String adminList(ModelMap model, Page<Admin> page, String searchKey){
		Admin admin = getLoginAdminInfo();
		Integer type = 1; 
		if(admin.getUsername().equalsIgnoreCase("admin")){
			type = 0;
		}
		Page<Admin> adminPage = adminServiceImpl.getAdminList(admin.getHospitalInfo().getId(), type, page, searchKey,null,null);
		model.put("page", adminPage);
		model.put("username", searchKey);
		model.put("admin", admin);
		pageLocation(model, Consts.AUTH_MODULE, Consts.AUTH_MENU_ADMIN_LIST, null);
		return "admin/admin_list";
	}
	
	/**
	 * 操作用户(1:解锁，2:禁用，3:启用)
	 * @param id 管理员ID
	 * @param type 操作类型
	 */
	@RequestMapping("operate")
	public void operateAdmin(Integer id, Integer type){
		try {
			if(id == null || type == null){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			Admin admin = adminServiceImpl.get(id);
			switch (type) {
				case 1:/** 解锁 **/
					admin.setIsLocked(false);
					break;
				case 2:/** 禁用 **/
					admin.setIsEnabled(false);
					break;
				case 3:/** 启用 **/
					admin.setIsEnabled(true);
					break;
				default:
					break;
			}
			Admin info = adminServiceImpl.save(admin);
			if(info != null){
				JsonUtils.render(getResponse(), Consts.SUCCESS);
			}
			JsonUtils.render(getResponse(), Consts.FAILED);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	/**
	 * 编辑管理员信息
	 * @param admin
	 * @param hospitalId
	 */
	@RequestMapping("edit")
	public void editAdmin(Admin admin, Integer hospitalId){
		try {
			if(admin == null){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			if(admin.getId() != null){
				String password = "";
				Admin targetAdmin = adminServiceImpl.get(admin.getId());
				if(!admin.getPassword().equalsIgnoreCase(targetAdmin.getPassword())){
					password = MD5EncryptUtils.getMd5Value(admin.getPassword());
				}
				BeanUtils.copy(admin, targetAdmin);
				if(StringUtils.isNotEmpty(password)){
					targetAdmin.setPassword(password);
				}
				adminServiceImpl.save(targetAdmin);
			}else{
				Set<Role> role = new HashSet<Role>();
				if(getLoginAdminInfo().getUsername().equalsIgnoreCase("admin")){
					if(hospitalId == null){
						JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
					}
					HospitalInfo info = hospitalInfoServiceImpl.get(hospitalId);
					admin.setHospitalInfo(info);
					admin.setIsFather(true);
					role.add(roleServiceImpl.get(2));
				}else{
					admin.setHospitalInfo(getLoginAdminInfo().getHospitalInfo());
					admin.setIsFather(false);
					role.add(roleServiceImpl.get(3));
				}
				String password = MD5EncryptUtils.getMd5Value(admin.getPassword());
				admin.setPassword(password);
				admin.setIsEnabled(true);
				admin.setIsLocked(false);
				admin.setRoles(role);
				admin.setLoginFailureCount(0);
				admin.setEmail("admin@jumper.com");
				admin.setAddTime(TimeUtils.getCurrentTime());
				admin.setMobile(admin.getMobile());
				adminServiceImpl.save(admin);
			}
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	/**
	 * 角色列表
	 * @param page 分页信息
	 * @param model 数据绑定
	 * @param searchKey 关键字查询
	 * @return
	 */
	@RequestMapping("roleList")
	public String getRoleList(Page<Role> page, ModelMap model, String searchKey){
		Page<Role> pageData = roleServiceImpl.getRolePage(page, searchKey);
		model.put("page", pageData);
		model.put("username", searchKey);
		pageLocation(model, Consts.AUTH_MODULE, Consts.AUTH_MENU_ROLE_LIST, null);
		return "admin/role_list";
	}
	
	/**
	 * 添加编辑角色信息
	 * @return
	 */
	@RequestMapping("roleEdit")
	public void roleEdit(Role role, ModelMap model){
		try {
			if(role.getId() == null){
				role.setIsSystem(false);
				roleServiceImpl.save(role);
			}else{
				Role targetRole = roleServiceImpl.get(role.getId());
				BeanUtils.copy(role, targetRole);
				roleServiceImpl.save(targetRole);
			}
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	/**
	 * 角色权限列表
	 * @param model
	 * @param role
	 * @return
	 */
	@RequestMapping("roleAuthority")
	public String roleAuthority(ModelMap model, Integer role){
		if(role == null){
			return "redirect:/auth/roleList";
		}
		Role roleInfo = roleServiceImpl.get(role);
		List<Authority> list = authorityServiceImpl.getParentList();
		model.put("role", roleInfo);
		model.put("list", list);
		pageLocation(model, Consts.AUTH_MODULE, Consts.AUTH_MENU_ROLE_LIST, null);
		return "admin/role_authority";
	}
	
	/**
	 * 编辑角色权限
	 * @param role_id
	 * @param auth_list
	 * @return
	 */
	@RequestMapping("saveRoleAuth")
	public String addRoleAuthoritySave(int role_id , Integer[] auth_list){
		try {
			if(role_id != 0){
				Role role = roleServiceImpl.get(role_id);
				if(auth_list != null && auth_list.length > 0){
					Set<Authority> dataList = authorityServiceImpl.getSetByIds(auth_list);
					role.setAuthorities(dataList);
					roleServiceImpl.save(role);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/auth/roleList";
	}
	
	/**
	 * 权限列表
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("authList")
	public String authorityList(Page<Authority> page, ModelMap model, Integer parent){
		Page<Authority> pageData = null;
		if(parent == null){
			pageData = authorityServiceImpl.getParentAuthority(page);
		}else{
			pageData = authorityServiceImpl.getChildAuthority(page, parent);
		}
		model.put("page", pageData);
		model.put("parent", parent);
		pageLocation(model, Consts.AUTH_MODULE, Consts.AUTH_MENU_MODULE_LIST, null);
		return "admin/auth_list";
	}
	
	@RequestMapping("authEdit")
	public void authEdit(Authority auth, Integer parent){
		try {
			if(auth.getId() == null){
				if(parent != null){
					Authority p = authorityServiceImpl.get(parent);
					auth.setParents(p);
				}
				authorityServiceImpl.save(auth);
			}else{
				Authority targetAuth = authorityServiceImpl.get(auth.getId());
				BeanUtils.copy(auth, targetAuth);
				authorityServiceImpl.save(targetAuth);
			}
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	@RequestMapping("paymentRoles")
	@ResponseBody
	public Map<Integer, String> getPaymentRoleList(HttpServletResponse response, Integer used){
		try {
			Map<Integer, String> map = roleServiceImpl.getRoleByUsed(used);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("payEdit")
	public void addPaymentAdmin(Admin admin, Integer roleId){
		try {
			if(admin == null || roleId == null){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			admin.setAddTime(TimeUtils.getCurrentTime());
			admin.setHospitalInfo(hospitalInfoServiceImpl.get(49));
			admin.setIsEnabled(true);
			admin.setIsFather(true);
			admin.setIsLocked(false);
			admin.setIsHospitalNst(false);
			admin.setLoginFailureCount(0);
			admin.setPassword(MD5EncryptUtils.getMd5Value(admin.getPassword()));
			admin.setEmail("");
			
			boolean mobiles=monitorAdminService.existMonitorAdmin(admin.getMobile());
			if(mobiles==true){
				JsonUtils.render(getResponse(), Consts.MOBILE_EXIST);
				return;
			}
			admin.setMobile(admin.getMobile());
			Role role = roleServiceImpl.get(roleId);
			Set<Role> roles = new HashSet<Role>();
			roles.add(role);
			admin.setRoles(roles);
			adminServiceImpl.save(admin);
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	/**
	 * 
		 * @author yanghz
		 * @date 2016-8-15 下午5:35:52
		 * @Description: 根据角色id查权限
		 * @param @param roleId
		 * @param @return
		 * @return String    返回类型 
		 * @throws
	 */
	@RequestMapping("getAuthorByrole")
	public void getAuthorByRole(Integer roleId){
		String authorState = "no";
			try {
				if(roleId != null){
					Role roleInfo = roleServiceImpl.get(roleId);
					if(roleInfo != null){
						Set<Authority> authorities = roleInfo.getAuthorities();
						if(authorities != null && authorities.size() > 0){
							for (Authority authority : authorities) {
								if(authority != null && authority.getId().equals(1000)){
									authorState = "yes";
									
								}
							}
							JsonUtils.render(getResponse(), authorState);
							return;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
