package com.jumper.hospital.controller;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jumper.hospital.utils.AccessBuildUtils;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.JsonUtils;


/**
 * 设备租赁
 * @author fangxilin
 */
@Controller
@RequestMapping(value = "/lease")
public class DeviceLeaseController extends BaseController{
	@Resource
	protected LinkedHashMap<String, Object> staticPath;// 静态资源
	
	/**
	 * 设备租赁订单入口
	 * @createTime 2016-10-24,上午9:06:47
	 * @createAuthor fangxilin
	 * @return
	 */
	@RequestMapping("leaseOrder")
	public String leaseOrder(ModelMap model){
		Integer hospitalId = getLoginAdminInfo().getHospitalInfo().getId();
		String name = getLoginAdminInfo().getName();//操作人取医生name
		String method = "leaseOrder";
		
		pageLocation(model, Consts.USER_MODULE, Consts.HOSPITAL_MENU_DEVICE, "leaseOrder");
		model.put("deviceUrl", Consts.DEVICE_URL);
		model.put("method", method);
		model.put("hospitalId", hospitalId);
		model.put("username", name);//订单需要用到操作人
		return "deviceLease/deviceLeasePage";
	}
	
	/**
	 * 设备业务入口
	 * @createTime 2016-10-24,上午9:06:47
	 * @createAuthor fangxilin
	 * @return
	 */
	@RequestMapping("deviceService")
	public String deviceService(ModelMap model){
		Integer hospitalId = getLoginAdminInfo().getHospitalInfo().getId();
		String method = this.getRequest().getParameter("method");
		if("deviceInfo".equals(method)){
			pageLocation(model, Consts.HOSPITAL_MODULE, Consts.HOSPITAL_MENU_DEVICE, Consts.HOSPITAL_SUBMENU_DEVICEINFO);
		}else if("marketingSet".equals(method)){
			pageLocation(model, Consts.HOSPITAL_MODULE, Consts.HOSPITAL_MENU_DEVICE, Consts.HOSPITAL_SUBMENU_MARKETINGSET);
		}else{
			method = "error";
		}
		model.put("deviceUrl", Consts.DEVICE_URL);
		model.put("method", method);
		model.put("hospitalId", hospitalId);
		return "deviceLease/deviceLeasePage";
	}
	
	/**
	 * 生成Token
	 * @version 1.0
	 * @createTime 2016-10-25,上午9:18:52
	 * @updateTime 2016-10-25,上午9:18:52
	 * @createAuthor fangxilin
	 */
	@RequestMapping("generatorToken")
	public void generatorToken(){
		String token = AccessBuildUtils.createWebAccessToken(MapUtils.getString(staticPath, "token", "80f752400a7d49a3b351f5fbbf9f6555"), 
				MapUtils.getString(staticPath, "objectId", "2"), 
				MapUtils.getInteger(staticPath, "userCate", 10));
		JsonUtils.renderString(getResponse(), token);
		return;
	}
}
