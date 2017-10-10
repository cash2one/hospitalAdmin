package com.jumper.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.HospitalWithdrawals;
import com.jumper.hospital.service.HospitalWithdrawalsService;
import com.jumper.hospital.utils.Consts;

@Controller
@RequestMapping("withdrawals")
public class HospitalWithdrawalsController extends BaseController{

	@Autowired
	private HospitalWithdrawalsService hospitalWithdrawalsService;
	
	@RequestMapping("getWithdrawalsList")
	public String getWithdrawalsList(ModelMap model,Page<HospitalWithdrawals> page){
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		
		Page<HospitalWithdrawals> pageData=hospitalWithdrawalsService.getWithdrawalsList(page,hospitalInfo.getId());
		model.put("pageData", pageData);
		//配置管理员列表  前端界面控制
		pageLocation(model, Consts.HOSPITAL_MODULE, "getWithdrawalsList", "getWithdrawalsList");
		return "hospital/withdrawalsList";
	}
}
