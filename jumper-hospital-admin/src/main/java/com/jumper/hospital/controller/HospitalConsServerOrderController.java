package com.jumper.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalConsServiceOrder;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.service.HospitalConsServiceOrderService;
import com.jumper.hospital.utils.Consts;

@Controller
@RequestMapping("consServerOrder")
public class HospitalConsServerOrderController extends BaseController{

	@Autowired
	private HospitalConsServiceOrderService hospitalConsServiceOrderService;
	
	/**
	 * 查询余额结算详情
	 * @param model
	 * @param page
	 * @param state
	 * @param withdrawalsId
	 * @return
	 */
	@RequestMapping("getConsServiceOrderList")
	public String getConsServiceOrderList(ModelMap model,Page<HospitalConsServiceOrder> page,
			@RequestParam(value="state",required=false,defaultValue="-1")Integer state,  //订单状态(没有用到)
			@RequestParam(value="withdrawalsId",required=false,defaultValue="-1")Integer withdrawalsId){//月提现Id
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		
		Page<HospitalConsServiceOrder> pageData=hospitalConsServiceOrderService.getConsServiceOrderList(page,hospitalInfo.getId(),state,withdrawalsId);
		model.put("pageData", pageData);
		model.put("state", state);
		model.put("withdrawalsId", withdrawalsId);
		//配置管理员列表  前端界面控制
		pageLocation(model, Consts.HOSPITAL_MODULE, "getWithdrawalsList", "getConsServiceOrderList");
		return "hospital/consServerOrderList";
	}
	/**
	 * 查询医院未结算的详情
	 * @return
	 */
	@RequestMapping("getUnBlanceServiceOrderList")
	public  String getUnBlanceServiceOrderList(ModelMap model,Page<HospitalConsServiceOrder> page,
			@RequestParam(value="startTime",required=false,defaultValue="")String startTime,
			@RequestParam(value="endTime",required=false,defaultValue="")String endTime){
		
	    //获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
	    HospitalInfo hospitalInfo = admin.getHospitalInfo();
	    Page<HospitalConsServiceOrder> pageData=hospitalConsServiceOrderService.getUnBlanceServiceOrderList(page,hospitalInfo.getId(),startTime,endTime);
	    model.put("pageData", pageData);
	    model.put("startTime", startTime);
	    model.put("endTime", endTime);
		//配置管理员列表  前端界面控制
		pageLocation(model, Consts.HOSPITAL_MODULE, "getWithdrawalsList", "getUnBlanceServiceOrderList");
		return "hospital/unBalacedConsServerOrderList";
	}
	
}
