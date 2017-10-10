package com.jumper.hospital.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.MonitorOperateLog;
import com.jumper.hospital.service.MonitorOperateLogServices;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.LogSerchCondition;
import com.jumper.hospital.vo.VOMonitorOperateLog;

/**
 * 操作日志记录
 */
@Controller
@RequestMapping("operateLog")
public class MonitorOperateLogController extends BaseController {
	
	
//	@Autowired
//	private  MonitorOperateLogDao MonitorOperateLogDao;
	
	@Autowired
	private MonitorOperateLogServices monitorOperateLogServices;
	
	/**
	 *添加操作日志 
	 */
	@RequestMapping("addOperateLog")
	@ResponseBody
	public String addOperateLog(VOMonitorOperateLog vo){
		try{
			Admin admin = getLoginAdminInfo();
			Integer hospitalId= admin.getHospitalInfo().getId();
			Timestamp operateTime= new Timestamp(System.currentTimeMillis());
			vo.setOperateTime(operateTime);
			vo.setHospitalId(hospitalId);
			vo.setMonitorAdminId(admin.getId());
			vo.setUserName(admin.getUsername());
			vo.setName(admin.getName());
			monitorOperateLogServices.addOperateLog(vo);
			return "success";
		}catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	/**
	 * 展示登陆日志
	 * @param model
	 * @param page 分页信息
	 * @param searchKey 搜索关键字
	 * @param searchType 搜索的类型  0：操作者字段搜索   1：操作对象字段搜索
	 * @param logType 日志类型  0:登陆日志   1:操作日志
	 * @param beginT 开始时间
	 * @param endT   结束时间
	 * @param quickQuery 快速查询按钮标志
	 * @return
	 */
	@RequestMapping("showOperateLog")
	public String showOperateLog(ModelMap model,Page<MonitorOperateLog> page,String searchKey,String searchType, Integer logType,String beginT,String endT,String quickQuery){
		
 		Date beginTime=null;
		Date endTime=null;
		if(beginT!=null&&!"".endsWith(beginT)){
			beginTime= TimeUtils.getString2Date(beginT, "yyyy-MM-dd");
		}
		if(endT!=null&&!"".endsWith(endT)){
			endTime= TimeUtils.getString2Date(endT, "yyyy-MM-dd");
		}
		
		//快速查询按钮条件
		if(quickQuery!=null&&!"".endsWith(quickQuery)){
			LogSerchCondition logSerchCondition=Condition(quickQuery);
			beginTime=logSerchCondition.getBeginTime();
			endTime=logSerchCondition.getEndTime();
			beginT=null;
			endT=null;
		}
		
		Admin admin = getLoginAdminInfo();
		Integer hospitalInfoId=admin.getHospitalInfo().getId();
		Page<VOMonitorOperateLog> pageData= monitorOperateLogServices.findLogPageByCondition(page,searchKey,searchType,logType,beginTime,endTime,hospitalInfoId);
		model.put("logType", logType);
		model.put("beginTime", beginT);
		model.put("endTime", endT);
		model.put("searchType", searchType);
		model.put("searchKey", searchKey);
		model.put("pageData", pageData);
		model.put("quickQuery", quickQuery);
		
		pageLocation(model, Consts.HOSPITAL_MODULE, Consts.HOSPITAL_OPERATE_LOG, null);
		return "operateLog/operateLogList";
	}
	
	public LogSerchCondition Condition(String quickQuery){
		LogSerchCondition condition=new LogSerchCondition();
		//0:昨天 ,1:今天，2:本周,3:本月 4:所有
		if("0".equals(quickQuery)){
			condition.setBeginTime(TimeUtils.getCurrentStartTime(-1, new Date()));
			condition.setEndTime(TimeUtils.getCurrentStartTime(0, new Date()));
		}else if("1".equals(quickQuery)){
			condition.setBeginTime(TimeUtils.getCurrentStartTime(0, new Date()));
			condition.setEndTime(TimeUtils.getCurrentStartTime(1, new Date()));
		}else if("2".equals(quickQuery)){
			 // 获得本周一0点时间  
	        Calendar cal = Calendar.getInstance();  
	        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  
	        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
	        condition.setBeginTime(cal.getTime());
	        //获取当前天晚上时间
	        condition.setEndTime(TimeUtils.getCurrentStartTime(1, new Date()));
		}else if("3".equals(quickQuery)){
			 //获取本月第一天0点
		     Calendar cal = Calendar.getInstance();  
		     cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  
		     cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));  
		     condition.setBeginTime(cal.getTime());
		     //获取当前天晚上时间
		     condition.setEndTime(TimeUtils.getCurrentStartTime(1, new Date()));
		}else if("4".equals(quickQuery)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = "2017-3-1";
			Date date=null;
			try {
				 date=sdf.parse(strDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 condition.setEndTime(date);
			 condition.setEndTime(TimeUtils.getCurrentStartTime(1, new Date()));
		}
		return condition;
	}
	
	public static void main(String[] args) {
		//System.out.println(TimeUtils.getCurrentStartTime(0, new Date()));
		//System.out.println(TimeUtils.getCurrentStartTime(1, new Date()));
		
//	     Calendar cal = Calendar.getInstance();  
//	     cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  
//	     cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));  
//         System.out.println(cal.getTime());
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String strDate = "2017-3-1";
//		Date date=null;
//		try {
//			 date=sdf.parse(strDate);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(date);
		
		
	}
	
	
}
