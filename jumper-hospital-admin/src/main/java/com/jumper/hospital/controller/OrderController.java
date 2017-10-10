package com.jumper.hospital.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalConsServiceOrder;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.MonitorHospital;
import com.jumper.hospital.entity.MonitorOrder;
import com.jumper.hospital.service.HospitalConsServiceOrderService;
import com.jumper.hospital.service.MonitorHospitalService;
import com.jumper.hospital.service.MonitorOrderService;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {
	@Autowired
	private HospitalConsServiceOrderService hospitalConsServiceOrderService;
	
	@Autowired
	private MonitorOrderService monitorOrderService;
	
	@Autowired
	private MonitorHospitalService monitorHospitalService;
	
	@RequestMapping("/visitOrder")
	public String visitOrder(ModelMap model,Page<HospitalConsServiceOrder> page){
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		Page<HospitalConsServiceOrder> pageData = hospitalConsServiceOrderService.findVisitOrderList(page,hospitalInfo.getId());
		int count = pageData.getResult().size();
		Double profit = monitorOrderService.getSumTrueCost(hospitalInfo.getId());
		model.addAttribute("count", count);
		model.addAttribute("visitOrderList", pageData);
		model.addAttribute("profits", profit);
		pageLocation(model, "user", "orderList", "visitOrder");
		return "hospital/visitOrderList";
		
	}
	
	@RequestMapping("/monitorOrder")
	public String monitorOrder(ModelMap model,Page<MonitorOrder> page){
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		Page<MonitorOrder> pageData = new Page<MonitorOrder>();
		pageData = monitorOrderService.findMonitorOrderList(page,hospitalInfo.getId(),null,null,null);
		MonitorHospital mHospital = monitorHospitalService.findMonitorHospitalByHospitalId(hospitalInfo.getId());
		Double profits = null ;
		if(mHospital!=null){
			profits = monitorOrderService.getSumMoney(hospitalInfo.getId());
		}
		int count = 0 ;
		if(pageData!=null){
			count = pageData.getResult().size();
		}
		model.addAttribute("count", count);
		model.addAttribute("monitorOrderList", pageData);
		model.addAttribute("profits", profits);
		pageLocation(model, "order", "orderList", "remoteOrder");
		return "hospital/monitorOrderList";
	}
	/**
	 * 获取总收益
	 * @param sql
	 * @return
	 */
	public Double getProfits(String sql){
		return monitorOrderService.getProfits(sql);
	}
	
	/**
	 * 医院问诊条件查询
	 */
	@RequestMapping("/searchVisitOrder")
	public String findByConds(ModelMap model,Page<HospitalConsServiceOrder> page,HttpServletRequest req){
		String searchKey = req.getParameter("searchKey");
		String startTime = req.getParameter("startTime");
		String endTime = req.getParameter("endTime");
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		Page<HospitalConsServiceOrder> pageData = hospitalConsServiceOrderService.findVisitOrderListByConds(page, hospitalInfo.getId(),searchKey,startTime,endTime);
		Double profit = monitorOrderService.getSumTrueCostByConds(hospitalInfo.getId(),searchKey,startTime,endTime);
		int count = pageData.getResult().size();
		model.addAttribute("count", count);
		model.addAttribute("visitOrderList", pageData);
		model.addAttribute("profits", profit);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		pageLocation(model, "user", "orderList", "visitOrder");
		return "hospital/visitOrderList";
	}
	
	/**
	 * 条件查询远程监控订单
	 */
	@RequestMapping("/searchMonitorOrder")
	public String searchMonitorOrder(ModelMap model,Page<MonitorOrder> page,HttpServletRequest req){
		String searchKey = req.getParameter("searchKey");
		String startTime = req.getParameter("startTime");
		String endTime = req.getParameter("endTime");
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		Page<MonitorOrder> pageData = monitorOrderService.findMonitorOrderList(page, hospitalInfo.getId(),searchKey,startTime,endTime);
		MonitorHospital mHospital = monitorHospitalService.findMonitorHospitalByHospitalId(hospitalInfo.getId());
		Double profit = null;
		if(mHospital!=null){
			profit = monitorOrderService.getSumMoneyByConds(hospitalInfo.getId(),searchKey,startTime,endTime);
		}
		model.addAttribute("count", pageData.getResult().size());
		model.addAttribute("monitorOrderList", pageData);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("profits", profit);
		pageLocation(model, "order", "orderList", "remoteOrder");
		return "hospital/monitorOrderList";
	}
}
