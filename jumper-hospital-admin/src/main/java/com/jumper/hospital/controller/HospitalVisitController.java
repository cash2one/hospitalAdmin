package com.jumper.hospital.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalDoctorMajor;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.HospitalMajorInfo;
import com.jumper.hospital.entity.HospitalService;
import com.jumper.hospital.entity.HospitalTodayRecommend;
import com.jumper.hospital.service.HospitalDoctorMajorService;
import com.jumper.hospital.service.HospitalMajorInfoService;
import com.jumper.hospital.service.HospitalTodayRecommendService;
import com.jumper.hospital.service.HospitalVisitService;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.TimeUtils;

/**
 * 医院问诊设置
 * @author gyx
 */
@Controller
@RequestMapping(value = "/hospitalVisit")
public class HospitalVisitController extends BaseController{
	@Autowired
	private HospitalVisitService hospitalVisitService;
	@Autowired
	private HospitalDoctorMajorService hospitalDoctorMajorService;
	@Autowired
	private HospitalMajorInfoService hospitalMajorInfoService;
	@Autowired
	private HospitalTodayRecommendService hospitalTodayRecommendService;
	/**
	 * 医院服务：取医院的所有科室信息显示到页面上
	 * @param model
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/index")
	public String visitInfo(ModelMap model){
		//获取所有的科室信息
//		List<HospitalDoctorMajor> majorList = this.hospitalDoctorMajorService.getMajorList();
		//model.addAttribute("majorList", majorList);
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		model.addAttribute("hospitalInfo", hospitalInfo);
		//获取该医院的科室信息
		List<HospitalMajorInfo> majorInfos = this.hospitalMajorInfoService.findHosMajors(hospitalInfo.getId());
		List<HospitalDoctorMajor> myMajors = new ArrayList<HospitalDoctorMajor>();
		if(majorInfos!=null&&majorInfos.size()>0){
			for (HospitalMajorInfo hospitalMajorInfo : majorInfos) {
				if(hospitalMajorInfo.getMajorId()!=null){
					int id = hospitalMajorInfo.getMajorId();
					HospitalDoctorMajor hospitalDoctorMajor = hospitalDoctorMajorService.findMajorInfoById(id);
					myMajors.add(hospitalDoctorMajor);
				}
				
			}
		}
		
		model.addAttribute("majorInfos", myMajors);
		if(myMajors!=null&&myMajors.size()>0){
			model.addAttribute("major", 1);
		}else{
			model.addAttribute("major", 0);
		}
		//获取当前登陆人员信息
		//医院信息中，是否开通的状态值为1表示已开通
		//把星期放到一个map中，方便存取
		Map<Integer,String> weekList = new HashMap<Integer, String>();
		weekList.put(1,"星期一");
		weekList.put(2,"星期二");
		weekList.put(3,"星期三");
		weekList.put(4,"星期四");
		weekList.put(5,"星期五");
		weekList.put(6,"星期六");
		weekList.put(7,"星期日");
		model.addAttribute("weekList", weekList);
		HospitalService hospitalService = this.hospitalVisitService.findHospitalService(hospitalInfo.getId());
		if((hospitalInfo.getIsConsultant()==null||hospitalInfo.getIsConsultant()==0||hospitalInfo.getIsConsultant()==1)&&hospitalService==null){
			pageLocation(model, Consts.HOSPITAL_MODULE, "service", "hos_visit");
			//未开通跳转到页面1
			return "hospital/hospitalVisits";
		}else{
			//根据医院的id获取当前医院的问诊服务信息，以便在前台进行判断（在医院服务表中）
			//计算结束时间，如果是0点就写为24点显示到页面上
			String times = "";
			if(hospitalService.getEndTime()!=null){
				if(hospitalService.getEndTime().getHours()==0&&hospitalService.getEndTime().getMinutes()==0){
					times = "24:00";
				}else{
					times = TimeUtils.convertTime(hospitalService.getEndTime(), "HH:mm");
				}
			}
			
			model.addAttribute("endTime", times);
			model.addAttribute("hospitalService", hospitalService);
			//根据hospitalId获取医院所开通的科室
			//List<HospitalMajorInfo> majorInfos = this.hospitalMajorInfoService.findHosMajors(hospitalInfo.getId());
			/*String majors = "";
			for(int i=0;i<majorInfos.size();i++){
				majors += majorInfos.get(i).getMajorId()+",";
			}
			model.addAttribute("majors", majors);*/
			pageLocation(model, Consts.HOSPITAL_MODULE, "service", "hos_visit");
			//如果已开通，带着服务信息跳转到页面2
			return "hospital/hospitalVisits_close";
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/openVisit",produces="text/html;charset=UTF-8")
	public String openVisit(HttpServletRequest req){
		double money = Double.parseDouble(req.getParameter("cost"));
		int week_low = Integer.parseInt(req.getParameter("week_low"));
		int week_high = Integer.parseInt(req.getParameter("week_high"));
		String beginTime = req.getParameter("begin_Time");
		String end_Time = req.getParameter("end_Time");
		/*String majorStr = req.getParameter("major");
		String majors[] = majorStr.split(",");
		int major[] = new int[majors.length];
		for(int i=0;i<majors.length;i++){
			major[i] = Integer.parseInt(majors[i]);
		}*/
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		//添加问诊服务信息
		hospitalVisitService.addVisitInfo(money,week_low,week_high,beginTime,end_Time,admin,hospitalInfo);
		//将医院对应的科室添加到对应的数据库表中
		//hospitalMajorInfoService.addMajorInfo(hospitalInfo.getId(), major);
		//String result = "success";
		String result = "success";
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/editMoney")
	public String editMoney(HttpServletRequest req){
		double money = Double.parseDouble(req.getParameter("cost"));
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		hospitalVisitService.updateMoney(hospitalInfo.getId(),money);
		return "success";
	}
	
	//关闭服务：将服务的状态改为关闭，值为0
	@ResponseBody
	@RequestMapping(value="/closeVisit")
	public void closeVisit(HttpServletRequest req,HttpServletResponse resp){
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		HospitalService hospitalService = this.hospitalVisitService.findHospitalService(hospitalInfo.getId());
		hospitalInfo.setIsConsultant(0);
		hospitalService.setHospitalInfo(hospitalInfo);
		hospitalService.setStatus(0);
		hospitalVisitService.edit(hospitalService);
		//查询，如果该医院是今日推荐的医院，要将其删除
		HospitalTodayRecommend hospitalTodayRecommend = hospitalTodayRecommendService.findhospitalTodayRecommend(hospitalInfo.getId());
		if(hospitalTodayRecommend!=null){
			hospitalTodayRecommendService.delete(hospitalTodayRecommend);
		}
//		return "success";
		try {
			resp.getWriter().write("{\"msg\":\"success\",\"openStatus\":0}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return ;
	}
	
	//反复关闭开通服务
	@ResponseBody
	@RequestMapping(value="/openTwice")
	public String openTwice(HttpServletRequest req){
		int week_low = Integer.parseInt(req.getParameter("weekLow"));
		int week_high = Integer.parseInt(req.getParameter("weekHigh"));
		String beginTime = req.getParameter("beginTime");
		String end_Time = req.getParameter("endTime");
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		HospitalService hospitalService = this.hospitalVisitService.findHospitalService(hospitalInfo.getId());
		hospitalInfo.setIsConsultant(1);
		hospitalService.setHospitalInfo(hospitalInfo);
		hospitalService.setStatus(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			hospitalService.setBeginTime(sdf.parse("1970-01-01 " + beginTime));
			hospitalService.setEndTime(sdf.parse("1970-01-01 " + end_Time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		hospitalService.setWeeklow(week_low);
		hospitalService.setWeekhigh(week_high);
//		hospitalVisitService.updateVisit(hospitalService);
		try {
			hospitalVisitService.edit(hospitalService);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

	
	//修改问诊服务时间
	@ResponseBody
	@RequestMapping("/editTime")
	public String editTime(HttpServletRequest req){
		String weekLow = req.getParameter("weekLow");
		String weekHigh = req.getParameter("weekHigh");
		String beginTime = req.getParameter("beginTime");
		String endTime = req.getParameter("endTime");
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		HospitalService hospitalService = this.hospitalVisitService.findHospitalService(hospitalInfo.getId());
		if(StringUtils.isNotEmpty(weekLow)){
			hospitalService.setWeeklow(Integer.parseInt(weekLow));
		}
		if(StringUtils.isNotEmpty(weekHigh)){
			hospitalService.setWeekhigh(Integer.parseInt(weekHigh));
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			if(StringUtils.isNotEmpty(beginTime)){
				hospitalService.setBeginTime(sdf.parse("1970-01-01 "+beginTime));
			}
			if(StringUtils.isNotEmpty(endTime)){
				hospitalService.setEndTime(sdf.parse("1970-01-01 "+endTime));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		hospitalService.setUpdateTime(TimeUtils.getCurrentTime());
		hospitalVisitService.edit(hospitalService);
		return "success";
	}
	
	/*@ResponseBody
	@RequestMapping("/editMajor")
	public String editMajor(HttpServletRequest req){
		String checkedMajor = req.getParameter("checkedMajor");
		String majors[] = checkedMajor.split(",");
		int major[] = new int[majors.length];
		for(int i=0;i<majors.length;i++){
			major[i] = Integer.parseInt(majors[i]);
		}
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		HospitalService hospitalService = this.hospitalVisitService.findHospitalService(hospitalInfo.getId());
		hospitalService.setUpdateTime(TimeUtils.getCurrentTime());
		//更新服务修改时间
		hospitalVisitService.edit(hospitalService);
		List<HospitalMajorInfo> myMajors = hospitalMajorInfoService.findHosMajors(hospitalInfo.getId());
		for(int i=0;i<myMajors.size();i++){
			hospitalMajorInfoService.delete(myMajors.get(i).getId());
		}
		hospitalMajorInfoService.addMajorInfo(hospitalInfo.getId(), major);
		return "success";
	}*/
	
	
}
