package com.jumper.hospital.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.Authority;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.HospitalMajorInfo;
import com.jumper.hospital.entity.Role;
import com.jumper.hospital.service.AdminService;
import com.jumper.hospital.service.HospitalInfoService;
import com.jumper.hospital.service.HospitalMajorInfoService;
import com.jumper.hospital.service.MonitorAdminService;
import com.jumper.hospital.service.MonitorOperateLogServices;
import com.jumper.hospital.service.RoleService;
import com.jumper.hospital.utils.BeanUtils;
import com.jumper.hospital.utils.Const;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.utils.MD5EncryptUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.VoAdminAddResult;
import com.jumper.hospital.vo.visit.Msg;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController {

	@Autowired
	private AdminService adminServiceImpl;
	@Autowired
	private RoleService roleServiceImpl;
	@Autowired
	private HospitalInfoService hospitalInfoServiceImpl;
	@Autowired
	private HospitalMajorInfoService hospitalMajorInfoService;
	@Autowired 
	private MonitorAdminService monitorAdminServiceImpl;
	@Autowired
	private MonitorOperateLogServices monitorOperateLogServices;
	
	private static final Logger logger = Logger.getLogger(AdminController.class);
	/**
	 * 首页获取管理员列表，Admin管理员获取所有医院管理员，医院管理员获取改医院医生信息
	 * @param model 
	 * @param page 分页对象
	 * @param username 用户名查询
	 * @return
	 */
	@RequestMapping("index")
	public String getAdminList(ModelMap model, Page<Admin> page, HttpServletRequest request,String username,Integer hospitalMajorid,Integer titleid){
		try{
			Admin admin = getLoginAdminInfo();
			Integer type = 1;
			if(admin.getUsername().equalsIgnoreCase("admin")){
				type = 0;
			}

			Page<Admin> adminPage = adminServiceImpl.getAdminList(admin.getHospitalInfo().getId(), type, page, username,hospitalMajorid,titleid);

			//医院信息
			HospitalInfo info=admin.getHospitalInfo(); 
			//待过滤查询职称
			List<Object> hospitalDoctorTitle=adminServiceImpl.getHospitalDoctorTitleList(info.getId());
			//查询科室
			List<HospitalMajorInfo> majorInfosList=this.hospitalMajorInfoService.findHosMajors(info.getId());
			model.put("majorInfosList", majorInfosList);
			model.put("hospitalDoctorTitle", hospitalDoctorTitle);
			//重置密码的按钮是否显示
			if(StringUtils.isNotBlank(Const.RESET_USER_ID)){
				String[] split = Const.RESET_USER_ID.split(",");
				boolean flag=false;
				for (String str : split) {
					if(StringUtils.isNotBlank(str)&&Integer.parseInt(str) == admin.getId()){
						flag=true;
						break;
					}
				}
				if(flag){
					model.put("resetPas", true);
				}else{
					model.put("resetPas", false);
				}
			}else{
				model.put("resetPas", false);
			}
			//查询角色
			List<Role> roles = roleServiceImpl.getNotSystemRoles();
			List<Role> tempRole = new ArrayList<Role>();
			admin.getHospitalDoctorTitle();
			System.out.println(admin.getHospitalInfo().getId());
			//义务角色逻辑添加begin
			if(Integer.valueOf(Const.YWHOSPITAL_ID).equals(admin.getHospitalInfo().getId())){//如果为义乌和孕期诊所权限，只要两个角色
				for(int i=0;i<roles.size();i++){
					if(Integer.valueOf(Consts.YWFY_ROLE_ID).equals(roles.get(i).getId())){
						//roles.remove(i);
						tempRole.add(roles.get(i));
					}
				}
			}
			//如果不是义乌，去掉义乌的角色权限   &&!
			else{
				for(int i=0;i<roles.size();i++){
					if(!Integer.valueOf(Consts.YWFY_ROLE_ID).equals(roles.get(i).getId())){
						//roles.remove(i);
						tempRole.add(roles.get(i));
					}
				}
			}
			//义乌医院角色逻辑添加 end
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date parse = sdf.parse(Const.CLOUD_VISIT_TIME);
			long addTime = admin.getAddTime().getTime();//6
			long upTime = parse.getTime();//5
			if(addTime < upTime && type.equals(1)){
				roles = removeRoles(roles);
			}
			
			
			model.put("page", adminPage);
			model.put("username", username);
			model.put("hospitalMajorid", hospitalMajorid);
			model.put("titleid", titleid);
			model.put("admin", admin);
			//天使医院可以看见所有角色
			if("admin".equals(admin.getUsername())){
				model.put("roles", roles);
			}else{
				model.put("roles", tempRole);
			}
			pageLocation(model, Consts.HOSPITAL_MODULE, Consts.HOSPITAL_SUBMENU_ADMIN, null);
		}catch (Exception e) {
			logger.error("异常信息:",e);
		}
		
		return "admin/index";
	}
	
	/**
	 * 重置用户密码
	 * @param id 用户id
	 */
	@RequestMapping("resetPas")
	public void resetPas(Integer id){
		logger.info("---------- resetPas id:"+id);
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			//得到当前登陆用户信息
			Admin admin = getLoginAdminInfo();
			
			//配置文件有没有值
			if(StringUtils.isBlank(Const.RESET_USER_ID)){
				map.put("msgBox", "配置文件错误!RESET_USER_ID 异常!");
				map.put("msg", "0");
				JsonUtils.render(getResponse(), map);
			}
			//是否有权限重置密码
			String[] split = Const.RESET_USER_ID.split(",");
			boolean flag=false;
			for (String str : split) {
				if(StringUtils.isNotBlank(str)&&Integer.parseInt(str) == admin.getId()){
					flag=true;
					break;
				}
			}
			if(flag){
				//重置的密码默认123456
				String password = MD5EncryptUtils.getMd5Value("123456");
				adminServiceImpl.resetPas(id,password);
				map.put("msgBox", "重置密码成功!默认密码123456！");
				map.put("msg", "1");
				JsonUtils.render(getResponse(), map);
			}else{
				map.put("msgBox", "用户没有权限重置密码!");
				map.put("msg", "0");
				JsonUtils.render(getResponse(), map);
			}
		} catch (Exception e) {
			map.put("msgBox", "操作失败!请联系相关人员处理!");
			map.put("msg", "0");
			JsonUtils.render(getResponse(), map);
			logger.error("---------- resetPas id:"+id,e);
		}
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
			Admin admin1 = getLoginAdminInfo();
			switch (type) {
				case 1:/** 解锁 **/
					admin.setIsLocked(false);
					monitorOperateLogServices.addOperateLog(admin1, "医生列表", "解锁", "医生账号", "解锁"+admin.getUsername()+"账号");
					break;
				case 2:/** 禁用 **/
					admin.setIsEnabled(false);
					//添加日志===>禁用账号
					monitorOperateLogServices.addOperateLog(admin1, "医生列表", "禁用", "医生账号", "禁用"+admin.getUsername()+"账号");
					break;
				case 3:/** 启用 **/
					admin.setIsEnabled(true);
					//添加日志===>启用账号
					monitorOperateLogServices.addOperateLog(admin1, "医生列表", "启用", "医生账号", "启用"+admin.getUsername()+"账号");
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
	
	@RequestMapping("edit")
	public void editAdmin(Admin admin, Integer hospitalId, Integer[] role, Integer visitorRole, String doctorWorkNum,Boolean isHospitalNst ){
		Msg msg = new Msg();
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
				Set<Role> roles = new HashSet<Role>();
				if(role != null && role.length > 0){
					for(Integer r_id : role){
						roles.add(roleServiceImpl.get(r_id));
					}
				}
				targetAdmin.setRoles(roles);
				
				if(null!=isHospitalNst){
					targetAdmin.setIsHospitalNst(isHospitalNst);
					//根据手机号码去匹配设置手机APP端的医生是否开通孕期诊所的权限
					if(null!=admin.getMobile()){
						adminServiceImpl.updateIsHospitalNst(targetAdmin,true);
					}
				}else{
					targetAdmin.setIsHospitalNst(false);
					if(null!=admin.getMobile()){
						adminServiceImpl.updateIsHospitalNst(targetAdmin,false);
					}
				}
				
				targetAdmin.setDoctormajor_id(this.getDoctorMajorIdByMajorInfoId(admin.getHospitalMajorid()));
				//创建建海云随访账号 去掉  或一直返回null
				msg = adminServiceImpl.edit(targetAdmin,visitorRole,doctorWorkNum);
				if(msg!= null){
					logger.info("msg"+msg.toString());
					Map<String , Object> map = new HashMap<String, Object>();
					if(msg.getMsg() != null && msg.getMsg() == 0){
						map.put("msg", msg.getMsg());
						map.put("msgBox", "编辑管理员信息成功！该账户手机端绑定云随访账号的账户名为：" + msg.getMsgBox() + "，初始密码为当前密码。");
					}else if (msg.getMsg() != null && msg.getMsg() == -2){
						map.put("msg", msg.getMsg());
						map.put("msgBox", "工号不存在！");
					}else if(msg.getMsg() != null && msg.getMsg() == -3){
						map.put("msg", msg.getMsg());
						map.put("msgBox", "该工号已被绑定！");
					}else if(msg.getMsg() != null && msg.getMsg() == 29){
						map.put("msg", msg.getMsg());
						map.put("msgBox", "该医院未和建海合作");
					}else if(msg.getMsg() != null && msg.getMsg() == 1){
						map.put("msg", msg.getMsg());
						map.put("msgBox", "编辑管理员信息成功！");
					}else if(msg.getMsg() != null && msg.getMsg() == 2){
						map.put("msg", msg.getMsg());
						map.put("msgBox", "编辑管理员信息失败:随访角色修改失败！");
					}else {
						map.put("msgBox", "创建失败");
						logger.info("绑定建海账号失败:" + msg.getMsgBox());
					}
					JsonUtils.render(getResponse(), map);
				}else{
					logger.info("msg is null!");
					JsonUtils.render(getResponse(), Consts.SUCCESS);
					
					//添加日志===>修改医院账号
					Admin admin1 = getLoginAdminInfo();
					monitorOperateLogServices.addOperateLog(admin1, "医生列表", "修改", "医生账号", "修改医生"+ targetAdmin.getUsername()+"账号信息");
				}
				//adminServiceImpl.save(targetAdmin);
			}else{
				if(getLoginAdminInfo().getUsername().equalsIgnoreCase("admin")){
					if(hospitalId == null){
						JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
					}
					HospitalInfo info = hospitalInfoServiceImpl.get(hospitalId);
					admin.setHospitalInfo(info);
					admin.setIsFather(true);
				}else{
					admin.setHospitalInfo(getLoginAdminInfo().getHospitalInfo());
					admin.setDoctormajor_id(this.getDoctorMajorIdByMajorInfoId(admin.getHospitalMajorid()));
					admin.setIsFather(false);
				}
				Set<Role> roles = new HashSet<Role>();
				if(role != null && role.length > 0){
					for(Integer r_id : role){
						roles.add(roleServiceImpl.get(r_id));
					}
				}
				String password = MD5EncryptUtils.getMd5Value(admin.getPassword());
				admin.setPassword(password);
				admin.setIsEnabled(true);
				admin.setIsLocked(false);
				admin.setRoles(roles);
				admin.setLoginFailureCount(0);
				admin.setEmail("admin@jumper.com");
				admin.setAddTime(TimeUtils.getCurrentTime());
				
				if(null!=isHospitalNst){
					admin.setIsHospitalNst(isHospitalNst);
					//根据手机号码去匹配设置手机APP端的医生是否开通孕期诊所的权限，如果没有创建一个
					adminServiceImpl.updateIsHospitalNst(admin,true);
				}else{
					admin.setIsHospitalNst(false);
				}
				
				msg = adminServiceImpl.saveAdmin(admin,visitorRole,doctorWorkNum);
				if(msg != null){
					logger.info("msg"+msg.toString());
					Map<String , Object> map = new HashMap<String, Object>();
					if(msg.getMsg() != null && msg.getMsg() == 0){
						map.put("msg", msg.getMsg());
						map.put("msgBox", "新增管理员信息成功！该账户手机端绑定云随访账号的账户名为：" + msg.getMsgBox() + "，初始密码为当前密码。");
					}else if (msg.getMsg() != null && msg.getMsg() == -2){
						map.put("msg", msg.getMsg());
						map.put("msgBox", "工号不存在！");
					}else if(msg.getMsg() != null && msg.getMsg() == -3){
						map.put("msg", msg.getMsg());
						map.put("msgBox", "该工号已被绑定！");
					}else if(msg.getMsg() != null && msg.getMsg() == 29){
						map.put("msg", msg.getMsg());
						map.put("msgBox", "该医院未和建海合作");
					}else {
						map.put("msgBox", "创建失败");
						logger.info("绑定建海账号失败:" + msg.getMsgBox());
					}
					JsonUtils.render(getResponse(), map);
				}else{
					logger.info("msg is null!");
					JsonUtils.render(getResponse(), Consts.SUCCESS);
					
					//添加日志===>添加医院账号
					Admin admin1 = getLoginAdminInfo();
					monitorOperateLogServices.addOperateLog(admin1, "医生列表", "添加", "医生账号", "添加医生"+ admin.getUsername()+"账号信息");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	@RequestMapping("change")
	public void adminChange(Admin admin, String oldPass){
		try {
			if(StringUtils.isEmpty(oldPass)){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			if(admin.getId() == null){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			String password = "";
			Admin targetAdmin = adminServiceImpl.get(admin.getId());
			
			String oldPasswordMd5 = MD5EncryptUtils.getMd5Value(oldPass);
			if(!oldPasswordMd5.equalsIgnoreCase(targetAdmin.getPassword())){
				JsonUtils.render(getResponse(), Consts.OLD_PASS_ERROR);
				return;
			}
			
			if(!admin.getPassword().equalsIgnoreCase(targetAdmin.getPassword())){
				password = MD5EncryptUtils.getMd5Value(admin.getPassword());
			}
			BeanUtils.copy(admin, targetAdmin);
			if(StringUtils.isNotEmpty(password)){
				targetAdmin.setPassword(password);
			}
			adminServiceImpl.save(targetAdmin);
			Subject currentUser = SecurityUtils.getSubject();
			if(currentUser.isAuthenticated()){
				currentUser.logout();
			}
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	/**
	 * 运营后台创建账号需要调用的接口
	 * @param hospitalId
	 * @param userName
	 * @param passWord
	 * @param name
	 * @param mobile
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public void remoteAdd(Integer hospitalId, String userName, String passWord, String name ,String mobile){
		try {
			Admin admin = new Admin();
			Set<Role> role = new HashSet<Role>();
			if(hospitalId == null || StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)){
				JsonUtils.render(getResponse(), new VoAdminAddResult(false, "参数错误！"));
				return;
			}
			boolean flag = adminServiceImpl.usernameExists(userName);
			if(flag){
				JsonUtils.render(getResponse(), new VoAdminAddResult(false, "用户名已被占用！"));
				return;
			}
			boolean isOpen = adminServiceImpl.checkHospitalHasOpen(hospitalId);
			if(isOpen){
				JsonUtils.render(getResponse(), new VoAdminAddResult(false, "医院只能添加一个管理员账号！"));
				return;
			}
			boolean isPhone = monitorAdminServiceImpl.existMonitorAdmin(mobile);
			if(isPhone){
				JsonUtils.render(getResponse(), new VoAdminAddResult(false, "您的手机号码已经存在！"));
				return;
			}
			HospitalInfo info = hospitalInfoServiceImpl.get(hospitalId);
			admin.setHospitalInfo(info);
			admin.setUsername(userName);
			admin.setIsFather(true);
			admin.setName(name);
			role.add(roleServiceImpl.get(2));
			String password = MD5EncryptUtils.getMd5Value(passWord);
			admin.setPassword(password);
			admin.setIsEnabled(true);
			admin.setIsLocked(false);
			admin.setRoles(role);
			admin.setAddTime(TimeUtils.getCurrentTime());
			admin.setLoginFailureCount(0);
			admin.setEmail("admin@jumper.com");
			admin.setMobile(mobile);
			admin.setIsHospitalNst(false);
			
			adminServiceImpl.save(admin);
			info.setIsRemote(1);
			info.setIsDoctorNst(false);
			hospitalInfoServiceImpl.save(info);
			JsonUtils.render(getResponse(), new VoAdminAddResult(true, "添加成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), new VoAdminAddResult(false, "处理异常！"));
		}
	}
	
	@RequestMapping("roles")
	public void getRoles(Integer adminId){
		//权限匹配状态
		Boolean isCloudAuthor = Boolean.FALSE;
		try {
			if(adminId != null && adminId != 0){
				Admin admin = adminServiceImpl.get(adminId);
				if(admin != null){
					Set<Role> roles = admin.getRoles();
					if(roles != null && roles.size() > 0){
						List<Integer> roleIds = new ArrayList<Integer>();
						Map<String, Object> roleAuthor = new HashMap<String, Object>();
						for(Role role : roles){
							if(role != null){
								//根绝角色id获取对应的权限
								String authorState = getAuthorByRole(role.getId());
								if(authorState.equals("yes")){
									isCloudAuthor = Boolean.TRUE;
								}
								roleIds.add(role.getId());
							}
						}
						roleAuthor.put("roleIds", roleIds);
						roleAuthor.put("author",isCloudAuthor);
						JsonUtils.render(getResponse(), roleAuthor);
						return;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//以前的已有账户不显示云随访的角色
	public List<Role> removeRoles(List<Role> roles){
			for(Role role : roles){
				if(role != null){
					//根绝角色id获取对应的权限
					String authorState = getAuthorByRole(role.getId());
					if(authorState.equals("yes")){
						roles.remove(role);
						return roles;
					}
				}
			}
		return roles;
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
	public String getAuthorByRole(Integer roleId){
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
									return authorState;
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return authorState;
	}
	
	/**
	 * 根据科室关联表id 得到科室ID
	 * @param adminId
	 * @return
	 */
	public Integer getDoctorMajorIdByMajorInfoId(Integer majorInfoId){
		Integer doctorMajorId = null;
		try {
			if(majorInfoId != null){
				List<Integer> list=adminServiceImpl.getDoctorMajorIdByMajorInfoId(majorInfoId);
				doctorMajorId=list.get(0);
			}
		} catch (Exception e) {
			doctorMajorId=0;
			e.printStackTrace();
		}
		return doctorMajorId;
	}
	
	/*根据输入的医生名称模糊查询医生  暂时不要下拉模糊查询
	@RequestMapping("getDoctors")
	public void getAdminByHospId( @RequestParam("doctorName") String doctorName) {
		
		if(StringUtils.isEmpty(doctorName)){
			JsonUtils.render(getResponse(), new VoAdminAddResult(false, "没有数据返回"));
			return ;
		}
		
		Page<Admin> doctorPage=null;
		List<VOAdmin> doctorList=new LinkedList<VOAdmin>();
		try {
				Admin admin = getLoginAdminInfo();
				doctorPage = adminServiceImpl.getAdminByHospId(admin
						.getHospitalInfo().getId(), doctorName);
				
				for(Admin alist:doctorPage.getResult()){
					VOAdmin vOAdmin=new VOAdmin();
					vOAdmin.setId(alist.getId());
					vOAdmin.setUsername(alist.getUsername());
					vOAdmin.setName(alist.getName());
					doctorList.add(vOAdmin);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				JsonUtils.render(getResponse(), new VoAdminAddResult(false, "处理异常！"));
		}
		JsonUtils.render(getResponse(), doctorList);
	}*/
	
}
