package com.jumper.hospital.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.Role;
import com.jumper.hospital.service.AdminService;
import com.jumper.hospital.service.HospitalInfoService;
import com.jumper.hospital.service.RoleService;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.utils.MD5EncryptUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.VOAdmin;
import com.jumper.hospital.vo.VOBaseInfo;
/**
 * 医生管理员 管理   备注：这个类里面的方法已经废弃了，都没有用到
 * @author tanqing
 */
@Controller
@RequestMapping(value = "/doctor")
public class DoctorManagerController extends BaseController{
	@Autowired
	private AdminService adminService;
	@Autowired
	private HospitalInfoService hospitalInfoService;
	@Autowired
	private RoleService roleService;
	/**
	 * 医生管理员列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/doctorManagerList")
	public String doctorManagerList(ModelMap model,String keywords){
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		Integer hospId=hospitalInfo.getId();
		//获取该医院所有的医生管理员信息 带分页
		Page<Admin> pages = this.adminService.getAdminByHospId(hospId,keywords);
		List<Admin> doctors = pages.getResult();
		//model到页面显示
		model.put("doctors", doctors);
		model.put("keywords", keywords);
		//配置管理员列表  前端界面控制
		pageLocation(model, Consts.HOSPITAL_MODULE, "manager", null);
		return "hospital/doctorManagerList";
	}
	/**
	 * 添加/删除 管理员信息
	 * @param id
	 * @param name
	 * @param email
	 * @param username
	 * @param loginFailureCount
	 * @param isLocked
	 * @param isEnabled
	 * @param password
	 * @param lockTime
	 * @param roleId
	 * @param hospId
	 * @return
	 */
	@RequestMapping(value= "/doctorAddOrEdit")
	public String doctorAddOrEdit(String id, String name,String email,String username,String loginFailureCount,
			String isLocked,String isEnabled,String password,String lockTime,String[] roleId,String hospId){
		Admin admin = new Admin();
		boolean flag=true;
		if(StringUtils.isNumeric(id) && StringUtils.isNotBlank(id)){
			admin.setId(Integer.parseInt(id));
			flag=false;
		}
		if(StringUtils.isNotBlank(name)){
			admin.setName(name);
		}
		if(StringUtils.isNotBlank(email)){
			admin.setEmail(email);
		}
		if(StringUtils.isNotBlank(username)){
			admin.setUsername(username);
		}
		if(StringUtils.isNumeric(isLocked)){
			if(isLocked.equals("1")){//锁定
				admin.setIsLocked(true);
				if(StringUtils.isNotBlank(lockTime)){
					admin.setLockedDate(TimeUtils.getTimestampDate(lockTime, "yyyy-MM-dd HH:mm:ss"));
				}else{
					admin.setLockedDate(TimeUtils.getCurrentTime());
				}
			}else{
				admin.setIsLocked(false);
			}
		}
		if(StringUtils.isNumeric(isEnabled)){
			if(isEnabled.equals("1")){//启用
				admin.setIsEnabled(true);
			}else{
				admin.setIsEnabled(false);
			}
		}
		if(roleId.length > 0){
			Set<Role> roles = new LinkedHashSet<Role>();
			for(String r:roleId){
				Role role = this.roleService.get(Integer.parseInt(r));
				roles.add(role);
			}
			admin.setRoles(roles);
		}
		if(StringUtils.isNumeric(hospId)){
			HospitalInfo hospitalInfo = this.hospitalInfoService.get(Integer.parseInt(hospId));
			admin.setHospitalInfo(hospitalInfo);
		}
		if(StringUtils.isNotBlank(password)){
			if(flag){
				String pwd = MD5EncryptUtils.getMd5Value(password);
				admin.setPassword(pwd);
			}else{
				admin.setPassword(password);
			}
		}
		if(StringUtils.isNotBlank(loginFailureCount) && StringUtils.isNotBlank(loginFailureCount)){
			admin.setLoginFailureCount(Integer.parseInt(loginFailureCount));
		}else{
			admin.setLoginFailureCount(0);
		}
		//保存或者更新
		this.adminService.save(admin);
		return "redirect:/hospital/doctorManagerList";
	}

	/**
	 * 删除对应id的记录
	 * @param id
	 */
	@RequestMapping(value = "/delete")
	public String delete(String id){
		if(StringUtils.isNumeric(id)){
			Integer idInt = Integer.parseInt(id);
			if(0 != idInt){
				this.adminService.delete(idInt);
				return "redirect:/doctor/doctorManagerList";
			}
		}
		return null;
	}
	/**
	 * 管理员 编辑  ajax与后台交互
	 * @param id
	 * @throws IOException
	 */
	@RequestMapping(value="/ajaxDoctors")
	public void ajaxDoctors(String id) throws IOException{
		List<Object> list = new ArrayList<Object>();
		List<Object> roleList = new ArrayList<Object>();
		HashMap<String, Object> map = new HashMap<String,Object>();
		//所有角色 转换成VO
		List<Role> roles = this.roleService.findAll();
		for(Role r:roles){
			VOBaseInfo vo = new VOBaseInfo();
			vo.setId(r.getId());
			vo.setName(r.getName());
			roleList.add(vo);
		}
		if(StringUtils.isNotBlank(id)){ //修改--返回查询到的数据
			if(StringUtils.isNumeric(id)){
				int	idInt=Integer.parseInt(id);
				Admin admin = this.adminService.get(idInt);
				VOAdmin voAdmin = this.converToVOAdmin(admin);
				map.put("admin", voAdmin);
			}
		}else{ //添加--返回所有医院及角色的名称
			//所有医院
			List<HospitalInfo> hospitals = this.hospitalInfoService.findAll();
			//转换成VO
			for(HospitalInfo hosp:hospitals){
				VOBaseInfo vo = new VOBaseInfo();
				vo.setId(hosp.getId());
				vo.setName(hosp.getName());
				list.add(vo);
			}
			map.put("hospitals", list);
		}
		map.put("roles", roleList);
		//将集合转换为Json格式数据
		JsonUtils.render(getResponse(), map);
	}
	
	/**
	 * vo转换
	 * @param hd
	 * @return
	 */
	public VOAdmin converToVOAdmin(Admin admin){
		VOAdmin vo = new VOAdmin();
		String[] strs = new String[3];
		Integer[] ints = new Integer[3];
		vo.setId(admin.getId());
		vo.setEmail(admin.getEmail());
		vo.setIsEnabled(admin.getIsEnabled());
		vo.setIsLocked(admin.getIsLocked());
		vo.setUsername(admin.getUsername());
		vo.setPassword(admin.getPassword());
		vo.setName(admin.getName());
		vo.setLoginFailureCount(admin.getLoginFailureCount());
		if(null != admin.getLockedDate()){
			vo.setLockedDate(TimeUtils.convertTime(admin.getLockedDate(), "yyyy-MM-dd HH:mm:ss"));
		}
		if(null != admin.getLoginDate()){
			vo.setLoginDate(TimeUtils.convertTime(admin.getLoginDate(), "yyyy-MM-dd HH:mm:ss"));
		}
		vo.setHospId(admin.getHospitalInfo().getId());
		vo.setHospName(admin.getHospitalInfo().getName());
		int i=0;
		for(Role r: admin.getRoles()){
			ints[i]=r.getId();
			strs[i]=r.getName();
			i++;
		}
		vo.setRoleIds(ints);
		vo.setRoleNames(strs);
		return vo;
	}
}
