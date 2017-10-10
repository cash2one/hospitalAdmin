package com.jumper.hospital.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.service.AdminService;
import com.jumper.hospital.utils.AccessBuildUtils;
import com.jumper.hospital.utils.Const;
import com.jumper.hospital.utils.Consts;

@Controller
@RequestMapping("/weight")
public class WeightNutritionController extends BaseController {
	
	
	@Autowired
	private AdminService adminServiceImpl;
	
	/**
	 * 跳转到体重营养  暂时测试使用
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String newSchool(ModelMap model){

		String accessKey = Const.ACCESS_KEY;
		String objectId = Const.OBJECT_ID;
		String accessToken = AccessBuildUtils.createWebAccessToken(accessKey, objectId, 12);
		Admin admin = getLoginAdminInfo();
		Integer hid=admin.getHospitalInfo().getId();
		String param="?hospitalId="+hid+"&access_token="+accessToken;
		//页面iframe src 地址
		model.addAttribute("url", Const.WEIGHT_NUTRITION_URL+param);
		pageLocation(model, Consts.WEIGHT_NUTRITION_MODULE, Consts.SCHOOL_MENU_LIBRARY, null);
		return "weightNutrition/weightIndex";
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

}
