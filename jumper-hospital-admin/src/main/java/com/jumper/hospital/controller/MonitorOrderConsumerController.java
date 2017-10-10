package com.jumper.hospital.controller;
/**
 * 监测管理(未完成、已完成报告)controller
 * @author rent
 * @date 2015-11-9
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.AdminDao;
import com.jumper.hospital.dao.HospitalSubordinateDao;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.HospitalSubordinate;
import com.jumper.hospital.entity.MonitorHospital;
import com.jumper.hospital.entity.MonitorOrderConsumer;
import com.jumper.hospital.entity.MonitorOrderConsumerTemp;
import com.jumper.hospital.enums.RemoteType;
import com.jumper.hospital.service.AuthorityService;
import com.jumper.hospital.service.HospitalDoctorInfoService;
import com.jumper.hospital.service.MonitorHospitalService;
import com.jumper.hospital.service.MonitorOrderConsumerService;
import com.jumper.hospital.service.MonitorOrderConsumerTempService;
import com.jumper.hospital.service.MonitorSettingService;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.HttpPost;
import com.jumper.hospital.utils.HttpRequestUtils;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.vo.AutoGradeInfo;
import com.jumper.hospital.vo.VOFhrInfo;
import com.jumper.hospital.vo.VOFhrScore;
import com.jumper.hospital.vo.VOScoringAnalysis;
import com.jumper.hospital.vo.VoFinishedOrder;
import com.jumper.hospital.vo.VoRemoteData;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("report")
public class MonitorOrderConsumerController extends BaseController {

	@Autowired
	private MonitorOrderConsumerService monitorOrderConsumerServiceImpl;
	@Autowired
	private MonitorOrderConsumerTempService monitorOrderConsumerTempServiceImpl;
	@Autowired
	private MonitorSettingService monitorSettingServiceImpl;
	@Autowired
	AuthorityService authorityService;
	@Autowired
	private HospitalSubordinateDao hospitalSubordinateDaoImpl;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private HospitalDoctorInfoService hospitalDoctorInfoService;
	@Autowired
	private MonitorHospitalService monitorHospitalService;
	
	//利用logger获取提示信息
	private final static Logger logger = Logger.getLogger(HospitalConsultController.class);
	/**
	 * 未完成报告
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("audit")
	public String noFinishedOrder(ModelMap model, Page<MonitorOrderConsumer> page){
 		Admin admin = getLoginAdminInfo();
		HospitalInfo info = admin.getHospitalInfo();
		MonitorHospital mHospital= monitorHospitalService.findMonitorHospitalByHospitalId(info.getId());
		String url="xixiang/wait_audit";
		//根据配置来显示 sogc
		if(Integer.valueOf("3").equals(mHospital.getScoringMethod())){
			url="xixiang/sogc_wait_audit";
		//改良Fischer
		}else if(Integer.valueOf("2").equals(mHospital.getScoringMethod())){
			url="xixiang/wait_fischer_audit";
		//1:Kreb's
		}else{
			url="xixiang/wait_audit";
		}
		
		Integer hospitalId = info.getId();
		boolean realTime = monitorSettingServiceImpl.checkMonitorIsOpen(hospitalId, RemoteType.实时监护);
		boolean normal = monitorSettingServiceImpl.checkMonitorIsOpen(hospitalId, RemoteType.常规监护);
		boolean inside = monitorSettingServiceImpl.checkMonitorIsOpen(hospitalId, RemoteType.院内监护);
		if(!realTime && !normal && !inside){
			model.put("open", false);
			pageLocation(model, Consts.REPORT_MODULE, Consts.REPORT_WAIT_AUDIT, null);
			return url;
		}	
		Page<VoRemoteData> pageData = monitorOrderConsumerServiceImpl.findNotFinishOrder(page, hospitalId);
		model.put("open", true);
		model.put("page", pageData);
		model.put("admin", admin);
		pageLocation(model, Consts.REPORT_MODULE, Consts.REPORT_WAIT_AUDIT, null);
		return url;
	}
	
	@RequestMapping("complete")
	public String finishedOrder(ModelMap model, Page<MonitorOrderConsumer> page, String searchKey, String startTime, String endTime ,RemoteType remoteType, Integer selectId,String searchDoctor){
		
		Admin admin  = getLoginAdminInfo();
		//根据输入的searchDoctor在hospital_doctor_info中获取医生的ID
		List<Integer> doctorIds=new ArrayList<Integer>();
		if(null!=searchDoctor&&!"".equals(searchDoctor)){
			List<Integer> ids=hospitalDoctorInfoService.findDoctorIdsByDoctorName(admin.getHospitalInfo().getId(),searchDoctor.trim());
			if(null!=ids){
				doctorIds.addAll(ids);
			}
			//如果为空再判断输入的是否为当前医院的账号，由于设计原因当前的账号在hospital_doctor_info表中没有记录无法关联
			Admin currenthospitalAdmin=adminDao.matchCurrentHospitalAdmin(admin.getHospitalInfo().getId(),searchDoctor.trim());
			if(null!=currenthospitalAdmin){
				doctorIds.add(-1);
			}
		}
		Admin currenthospitalAdmin=adminDao.CurrentHospitalAdmin(admin.getHospitalInfo().getId());
		Page<VoFinishedOrder> pageData = monitorOrderConsumerServiceImpl.findFinishOrderData(page, admin.getHospitalInfo().getId(), searchKey, startTime, endTime,remoteType,selectId,doctorIds,currenthospitalAdmin);
		//查询医院机构信息
		List<HospitalSubordinate> HospitalSubordinateList = hospitalSubordinateDaoImpl.findHospitalSubordinateList(admin.getHospitalInfo().getId());
		//拼接医院机构下拉框
		Set<JSONObject> hospitalInfoSet = new HashSet<JSONObject>();
		JSONObject hospitalInfo= new JSONObject();
		hospitalInfo.put("id", admin.getHospitalInfo().getId());
		hospitalInfo.put("name", admin.getHospitalInfo().getName());
		hospitalInfoSet.add(hospitalInfo);
		for(HospitalSubordinate hospitalSubordinate : HospitalSubordinateList){
			hospitalInfo= new JSONObject();
			hospitalInfo.put("id", hospitalSubordinate.getSubordinateId().getId());
			hospitalInfo.put("name", hospitalSubordinate.getSubordinateId().getName());
			hospitalInfoSet.add(hospitalInfo);
		}
		//控制报告来源（医院名称）列是否显示
		Boolean isNotOne = false;
		if(hospitalInfoSet.size() > 1){
			isNotOne = true;
		}
		
		//添加一个统计有效报告数的统计，暂时只有拥有下级医院的才有该统计功能，一天完成报告只能算一份
		//判断是否为下级医院
		Boolean isSubordinatehospital= hospitalSubordinateDaoImpl.isSubordinatehospital(admin.getHospitalInfo().getId());
		if(isNotOne || isSubordinatehospital ){
			//有效的已完成报告数
			Integer finishOrderCount=monitorOrderConsumerServiceImpl.finishOrderCount(admin.getHospitalInfo().getId(), searchKey, startTime, endTime,remoteType,selectId,doctorIds);
			model.put("finishOrderCount", finishOrderCount);
			
		}
		model.put("isSubordinatehospital", isSubordinatehospital);
		model.put("page", pageData);
		model.put("searchKey", searchKey);
		model.put("startTime", startTime);
		model.put("endTime", endTime);
		model.put("remoteTypes", RemoteType.values());
		model.put("remoteType", remoteType);
		model.put("selectId", selectId);
		model.put("isNotOne", isNotOne);
		model.put("hospitalInfoSet", hospitalInfoSet);
		model.put("searchDoctor", searchDoctor);
		pageLocation(model, Consts.REPORT_MODULE, Consts.REPORT_HAS_FINISH, null);
		return "report/finish";
	}
	
	@RequestMapping("detail")
	public String finishOrderDetail(ModelMap model,Integer id){
		Admin admin = getLoginAdminInfo();
		VoRemoteData consumer = monitorOrderConsumerServiceImpl.findFinishOrderDataDetail(id);
		model.put("consumer", consumer);
		model.put("admin", admin);
		pageLocation(model, Consts.REPORT_MODULE, Consts.REPORT_HAS_FINISH, null);
		//return "xixiang/detail";
		return "report/detail";
	}
	
	@RequestMapping("realTime")
	public String realTimeRemote(ModelMap model, Page<MonitorOrderConsumerTemp> page){
		Admin admin = getLoginAdminInfo();
		page.setPageSize(6);
		Page<VoFinishedOrder> pageData = monitorOrderConsumerTempServiceImpl.findRealTimeOrder(page, admin.getHospitalInfo().getId());
		model.put("page", pageData);
		model.put("admin", admin);
		
		return "xixiang/realtime";
	}
	
	@RequestMapping("checkConsumer")
	@ResponseBody
	public String checkConsumer(Integer consumerId){
		return monitorOrderConsumerServiceImpl.checkConsumerIsValidForChat(consumerId);
	}
	@RequestMapping("xgenerate")
	public void xixiangGenerateReport(Integer id, String remark, Integer offset, String resultItem, Integer source, Integer speed,
			Integer start, Integer end){
		Admin admin = getLoginAdminInfo();
		Integer printType = null;
		String hospitalName = "";
		if(admin!=null){
			HospitalInfo hospitalInfo = admin.getHospitalInfo();
			if(hospitalInfo!=null){
				printType = hospitalInfo.getPrintType().getType();
				hospitalName = hospitalInfo.getName();
			}
		}
        try {
            Boolean flag = monitorOrderConsumerServiceImpl.xGenerateReport(getRequest(), id, remark, admin, offset, resultItem, source, speed, start, end,printType,hospitalName);
            if(flag){
            	JsonUtils.render(getResponse(), Consts.SUCCESS);
            	return;
            }
            JsonUtils.render(getResponse(), Consts.FAILED);
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.render(getResponse(), Consts.FAILED);
        }
	}
	
	/**
	 * 导航是否显示提示，待审核报告的的份数提示，郏县已完成报告的份数，当前医院是否为郏县妇幼
	 */
	@RequestMapping("reportTip")
	@ResponseBody
	public void reportTipMsgTip(){
		//当前医院待审核报告的份数
 		Admin admin = getLoginAdminInfo();
		HospitalInfo info = admin.getHospitalInfo();
		Integer hospitalId =info.getId();
		try{
			Map<String, Object> map=new HashMap<String, Object>();
			//当前医院未完成报告数，包括下级医院
			Integer totalNotFinishOrder = monitorOrderConsumerServiceImpl.countNotFinishOrder(hospitalId);
			if(null!=totalNotFinishOrder){
				map.put("totalNotFinishOrder", totalNotFinishOrder);
			}else{
				map.put("totalNotFinishOrder", 0);
			}
			//从属医院查看未读报告
			Boolean isSubordinatehospital= hospitalSubordinateDaoImpl.isSubordinatehospital(admin.getHospitalInfo().getId());
			
			Integer totalFinishUnredReport=null;
			if(isSubordinatehospital){
				//查询从属医院已经审核但是未读的报告数
				totalFinishUnredReport=monitorOrderConsumerServiceImpl.totalFinishUnRedReport(hospitalId);
				map.put("totalFinishUnredReport", totalFinishUnredReport);
			}else{
				map.put("totalFinishUnredReport", 0);
			}
			//是否显示提示
			if(null!=totalNotFinishOrder &&totalNotFinishOrder>0 || null!=totalFinishUnredReport &&totalFinishUnredReport>0){
				map.put("isShowHeadTip", true);
			}else{
				map.put("isShowHeadTip", false);
			}
			JsonUtils.render(getResponse(), map);
		}catch (Exception e) {
			JsonUtils.render(getResponse(), "系统异常");
		}
	}
	
	//下级医院查看上级已经完成的报告
	@RequestMapping("checkFinishedReport")
	@ResponseBody
	public void checkFinishedReport(Integer reportId){
		try{
			Boolean b= monitorOrderConsumerServiceImpl.updateIsViewed(reportId);
			if (b) {
				JsonUtils.render(getResponse(), "更新成功");
			}else {
				JsonUtils.render(getResponse(), "更新失败");
			}
		}catch (Exception e) {
			JsonUtils.render(getResponse(), "系统异常");
		}
		
	}
	//预警记录列表
	@RequestMapping("getWarnInfo")
	public void getWarnInfo(Integer userId, Integer hospitalId,Integer consumerId){
		try {
			//查询预警记录POST 
			String url = Consts.WARNING_URL+"/warn/getWarnInfo";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("hospitalId", hospitalId);
			map.put("consumerId", consumerId);
			String sendPost = HttpRequestUtils.sendPost(url, net.sf.json.JSONObject.fromObject(map));
			System.out.println(sendPost);
			JsonUtils.render(getResponse(), sendPost);
		} catch (Exception e) {
			logger.info(e);
		}
	}
	//实时列表预警记录
	@RequestMapping("getRealTimeWarnInfo")
	public void getRealTimeWarnInfo(Integer userId, Integer hospitalId,Integer monitorOrderId,Date addTime){
		try {
			//查询预警记录POST 
			String url = Consts.WARNING_URL+"/warn/getWarnInfo";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("hospitalId", hospitalId);
			map.put("monitorOrderId", monitorOrderId);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addTime1 = simpleDateFormat.format(addTime);
			map.put("addTime", addTime1);
			logger.info("请求参数："+net.sf.json.JSONObject.fromObject(map));
			String sendPost = HttpRequestUtils.sendPost(url, net.sf.json.JSONObject.fromObject(map));
			System.out.println(sendPost);
			logger.info("响应参数："+sendPost);
			JsonUtils.render(getResponse(), sendPost);
		} catch (Exception e) {
			logger.info(e);
		}
	}
	
	//添加预警记录
	@RequestMapping("addWarnInfo")
	public void addWarnInfo(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		try {
			//添加预警记录
//			String url = Consts.WARNING_URL+"/addWarnInfo";
			String url = Consts.WARNING_URL+"/warn/addWarnInfo";
			String alarmValue = request.getParameter("alarmValue");
			String lowerLimit = request.getParameter("lowerLimit");
			String upperLimit = request.getParameter("upperLimit");
			String userId = request.getParameter("userId");
			String hospitalId = request.getParameter("hospitalId");
			String monitorOrderId = request.getParameter("monitorOrderId");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("alarmValue", alarmValue);
			map.put("lowerLimit", lowerLimit);
			map.put("upperLimit", upperLimit);
			map.put("userId", userId);
			map.put("hospitalId", hospitalId);
			map.put("monitorOrderId", monitorOrderId);
			String sendPost = HttpRequestUtils.sendPost(url, net.sf.json.JSONObject.fromObject(map));
			System.out.println(sendPost);
			JsonUtils.render(getResponse(), sendPost);
		} catch (Exception e) {
			logger.info(e);
		}
	}
	

	/**
	 * 
	 * @param model
	 * @param recordId monitor_heartrate_record 主键
	 * @param start 开始点
	 * @param end   结束点
	 * @param speed 走纸速度
	 * @return  
	 */
	@RequestMapping("automaticScoring")
	public String automaticScoring(ModelMap model,Integer consumerId,Integer start,Integer end,Integer speed){
		
			Admin admin = getLoginAdminInfo();
			MonitorHospital mHospital= monitorHospitalService.findMonitorHospitalByHospitalId(admin.getHospitalInfo().getId());
			Integer ScoringType=mHospital.getScoringMethod();
			//判断是哪种模式的自动评分
			String url="";
			if(ScoringType.equals(1)){
				url="xixiang/autoScoringKrebs";
				//url= "xixiang/autoScoringFischer";
			}else if(ScoringType.equals(2)){
				url= "xixiang/autoScoringFischer";
			}
			
			VoRemoteData consumer = monitorOrderConsumerServiceImpl.findFinishOrderDataDetail(consumerId);
			
			String startStr=start==null?"":start.toString();
			String endStr=end==null?"":end.toString();
			
			String post3 = HttpPost.doGet(Consts.AUTOSCORE_URL+"/fetalRate/getFhrInfo?recordId="+consumer.getRecordId()+"&type="+ScoringType+"&start="+startStr+"&end="+endStr);
			System.out.println(post3);
			net.sf.json.JSONObject  dataJson=JsonUtils.toJSONObject(post3);
			Integer dateState=  (Integer) dataJson.get("msg");
			VOFhrInfo fhrInfo=null;
			if(Integer.valueOf("1").equals(dateState)){
				 fhrInfo=convertFhrInfo(post3);
			}
			//获取得分评论
			VOScoringAnalysis scoringAnalysis=null;
			if(fhrInfo!=null&&fhrInfo.getVoFhrScore()!=null){
				if(ScoringType.equals(1)){//改良Krebs
					scoringAnalysis= convertScoringAnalysisKrebs(fhrInfo.getVoFhrScore());
				}else{//改良Fischer
					scoringAnalysis= convertScoringAnalysisFischer(fhrInfo.getVoFhrScore());
				}
			}
			
			model.put("scoringAnalysis", scoringAnalysis);
			model.put("fhrInfo", fhrInfo);
			model.put("consumer", consumer);
			model.put("admin", admin);
			model.put("mHospital", mHospital);
			model.put("speed", speed); //走纸速度
			model.put("start", start);//开始点
			model.put("end", end);//结束点
			
			pageLocation(model, Consts.REPORT_MODULE, Consts.REPORT_WAIT_AUDIT, null);
			return url;
	}
	
	
	/**
	 * 是否可以评分
	 * @param model
	 * @param recordId monitor_heartrate_record 主键
	 * @param start 开始点
	 * @param end   结束点
	 * @param speed 走纸速度
	 * @return  
	 */
	@RequestMapping("isAbleAutomaticScor")
	public void isAbleAutomaticScor(ModelMap model,Integer consumerId,String start,String end){
		
			try {
				
			    Admin admin = getLoginAdminInfo();
				MonitorHospital mHospital= monitorHospitalService.findMonitorHospitalByHospitalId(admin.getHospitalInfo().getId());
				Integer ScoringType=mHospital.getScoringMethod();
				
				VoRemoteData consumer = monitorOrderConsumerServiceImpl.findFinishOrderDataDetail(consumerId);
				
				String startStr=start==null?"":start.toString();
				String endStr=end==null?"":end.toString();
				
				String post3 = HttpPost.doGet(Consts.AUTOSCORE_URL+"/fetalRate/getFhrInfo?recordId="+consumer.getRecordId()+"&type="+ScoringType+"&start="+startStr+"&end="+endStr);
				System.out.println(post3);
				net.sf.json.JSONObject  dataJson=JsonUtils.toJSONObject(post3);
				Integer dateState=  (Integer) dataJson.get("msg");
				
				String msgbox=(String) dataJson.get("msgbox");
				
				if(Integer.valueOf("1").equals(Integer.valueOf(dateState))){
					JsonUtils.render(getResponse(), "able");
				}else{
					JsonUtils.render(getResponse(), msgbox);
				}
				
			} catch (Exception e) {
				JsonUtils.render(getResponse(), Consts.FAILED);
				e.printStackTrace();
				logger.error("调用自动评分接口报错");
			}
	}
	
	/**
	 * 根据得分获取评论，改良Fischer
	 * @param voFhrScore
	 * @return
	 */
	private VOScoringAnalysis convertScoringAnalysisFischer(VOFhrScore voFhrScore) {
		
		VOScoringAnalysis scoringAnalysis=new VOScoringAnalysis();
		
		//基线  胎心基线评分 
		if(0==voFhrScore.getBasalScore()){
			scoringAnalysis.setBasalAnalysis("重度过缓（<100）或 重度过速(>180)");
		}else if(1==voFhrScore.getBasalScore()){
			scoringAnalysis.setBasalAnalysis("轻度过缓（100-109）或 轻度过速（161-180）");
		}else if(2==voFhrScore.getBasalScore()){
			scoringAnalysis.setBasalAnalysis("正常(110-160)");
		}
		// bpmVScore; // 变异振幅评分
		if(0==voFhrScore.getBpmVScore()){
			scoringAnalysis.setBpmVarAnalysis("过小（<3）");
		}else if(1==voFhrScore.getBpmVScore()){
			scoringAnalysis.setBpmVarAnalysis("较小（3-5）或  较大（>25）");
		}else if(2==voFhrScore.getBpmVScore()){
			scoringAnalysis.setBpmVarAnalysis("正常（6-25）");
		}
		//freqVScore; // 变异频率评分
		if(0==voFhrScore.getFreqVScore()){
			scoringAnalysis.setFreqVarAnalysis("过低（<2）");
		}else if(1==voFhrScore.getFreqVScore()){
			scoringAnalysis.setFreqVarAnalysis("较低（2-5）");
		}else if(2==voFhrScore.getFreqVScore()){
			scoringAnalysis.setFreqVarAnalysis("正常（>5）");
		}
		//numAccScore; // 胎心率加速评分
		if(0==voFhrScore.getNumAccScore()){
			scoringAnalysis.setNumAccAnalysis("没有加速");
		}else if(1==voFhrScore.getNumAccScore()){
			scoringAnalysis.setNumAccAnalysis("较少（1-2）");
		}else if(2==voFhrScore.getNumAccScore()){
			scoringAnalysis.setNumAccAnalysis("正常（>2）");
		}
		
		//numDecScore; // 胎心率减速评分
		if(0==voFhrScore.getNumDecScore()){
			scoringAnalysis.setNumDecAnalysis("LD,PD,重度VD");
		}else if(1==voFhrScore.getNumDecScore()){
			scoringAnalysis.setNumDecAnalysis("轻度VD>2,或ED>2");
		}else if(2==voFhrScore.getNumDecScore()){
			scoringAnalysis.setNumDecAnalysis("无");
		}
		
		//numFetalMoveScore; // 胎动次数评分  ，改良Fischer 没有胎动数据
/*		if(0==voFhrScore.getNumFetalMoveScore()){
			scoringAnalysis.setNumFetalMoveAnalysis("0");
		}else if(1==voFhrScore.getNumFetalMoveScore()){
			scoringAnalysis.setNumFetalMoveAnalysis("1-4");
		}else if(2==voFhrScore.getNumFetalMoveScore()){
			scoringAnalysis.setNumFetalMoveAnalysis(">=5");
		}*/
		return scoringAnalysis;
	}
	
	/**
	 * 根据得分获取评论，Krebs
	 * @param voFhrScore
	 * @return
	 */
	private VOScoringAnalysis convertScoringAnalysisKrebs(VOFhrScore voFhrScore) {
		
		VOScoringAnalysis scoringAnalysis=new VOScoringAnalysis();
		
		//基线  胎心基线评分 
		if(0==voFhrScore.getBasalScore()){
			scoringAnalysis.setBasalAnalysis("重度过缓（<100）或  重度过速(>180)");
		}else if(1==voFhrScore.getBasalScore()){
			scoringAnalysis.setBasalAnalysis("轻度过缓（100-119） 或  轻度过速（161-180）");
		}else if(2==voFhrScore.getBasalScore()){
			scoringAnalysis.setBasalAnalysis("正常（120-160）");
		}
		// bpmVScore; // 变异振幅评分
		if(0==voFhrScore.getBpmVScore()){
			scoringAnalysis.setBpmVarAnalysis("过小（<5）");
		}else if(1==voFhrScore.getBpmVScore()){
			scoringAnalysis.setBpmVarAnalysis("较小（5-9）或  较大（>25）");
		}else if(2==voFhrScore.getBpmVScore()){
			scoringAnalysis.setBpmVarAnalysis("正常（10-25）");
		}
		//freqVScore; // 变异频率评分
		if(0==voFhrScore.getFreqVScore()){
			scoringAnalysis.setFreqVarAnalysis("过低（<3）");
		}else if(1==voFhrScore.getFreqVScore()){
			scoringAnalysis.setFreqVarAnalysis("较低（3-6）");
		}else if(2==voFhrScore.getFreqVScore()){
			scoringAnalysis.setFreqVarAnalysis("正常（>6）");
		}
		//numAccScore; // 胎心率加速评分
		if(0==voFhrScore.getNumAccScore()){
			scoringAnalysis.setNumAccAnalysis("没有加速");
		}else if(1==voFhrScore.getNumAccScore()){
			scoringAnalysis.setNumAccAnalysis("较少（1-4）");
		}else if(2==voFhrScore.getNumAccScore()){
			scoringAnalysis.setNumAccAnalysis("正常（>=5）");
		}
		
		//numDecScore; // 胎心率减速评分
		if(0==voFhrScore.getNumDecScore()){
			scoringAnalysis.setNumDecAnalysis(">=2");
		}else if(1==voFhrScore.getNumDecScore()){
			scoringAnalysis.setNumDecAnalysis("1");
		}else if(2==voFhrScore.getNumDecScore()){
			scoringAnalysis.setNumDecAnalysis("无或早减");
		}
		
		//numFetalMoveScore; // 胎动次数评分
		if(0==voFhrScore.getNumFetalMoveScore()){
			scoringAnalysis.setNumFetalMoveAnalysis("没有胎动");
		}else if(1==voFhrScore.getNumFetalMoveScore()){
			scoringAnalysis.setNumFetalMoveAnalysis("较少（1-4）");
		}else if(2==voFhrScore.getNumFetalMoveScore()){
			scoringAnalysis.setNumFetalMoveAnalysis("正常（>=5）");
		}
		return scoringAnalysis;
	}

	/**
	 * 将json数据转换成 VOFhrInfo
	 * @param post3
	 * @return
	 */
	 @SuppressWarnings("static-access")
	private VOFhrInfo convertFhrInfo(String post3) {
		 net.sf.json.JSONObject  dataJson=JsonUtils.toJSONObject(post3);
		 VOFhrInfo fhrInfo=new VOFhrInfo();
		 JSONArray data=dataJson.getJSONArray("data");
		 net.sf.json.JSONObject fhrInfoList=data.getJSONObject(0);
		 //转换加速减速数据
		 String vocFhrLocation=  fhrInfoList.getJSONArray("vocFhrLocation").toString();
		 if(vocFhrLocation.length()>0){
			 fhrInfo.setVocFhrLocation(vocFhrLocation);
		 }
		 //转换胎心率评分数据
		 net.sf.json.JSONObject voFhrScoreJsonObj=fhrInfoList.getJSONObject("voFhrScore");
		 if(voFhrScoreJsonObj!=null){
			 VOFhrScore voFhrScore=(VOFhrScore) fhrInfoList.toBean(voFhrScoreJsonObj, VOFhrScore.class);
			 fhrInfo.setVoFhrScore(voFhrScore);
		 }
		 
		 //转换基线数据  baselines
		 String baselinesJsonArray= fhrInfoList.getJSONArray("baselines").toString();
		 if(baselinesJsonArray.length()>0){
			 fhrInfo.setBaselines(baselinesJsonArray);
		 }
		return fhrInfo;
	}
	 
	 /**
	  * 自动评分生成报告
	  * @param id  报告ID
	  * @param remark 医生备注
	  * @param offset  
	  * @param resultItem checkBox 选择框
	  * @param source 评分
	  * @param speed 走纸速度
	  * @param start  打印开始时间
	  * @param end  打印结束时间
	  */
	@RequestMapping("autoScoreGenerate")
	public void autoScorGenerate(Integer id, String remark,  String resultItem, Integer source, Integer speed,
			Integer start, Integer end){

		Admin admin = getLoginAdminInfo();
		Integer printType = null;
		String hospitalName = "";
		if(admin!=null){
			HospitalInfo hospitalInfo = admin.getHospitalInfo();
			if(hospitalInfo!=null){
				printType = hospitalInfo.getPrintType().getType();
				hospitalName = hospitalInfo.getName();
			}
		}
        try {
            Boolean flag = monitorOrderConsumerServiceImpl.autoScorGenerateReport(getRequest(), id, remark, admin, resultItem, source, speed, start, end,printType,hospitalName);
            if(flag){
            	JsonUtils.render(getResponse(), Consts.SUCCESS);
            	return;
            }
            JsonUtils.render(getResponse(), Consts.FAILED);
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.render(getResponse(), Consts.FAILED);
        }
		
	}
	
	/**
	 * 保存评分数据
	 * @param args
	 */
	@RequestMapping("saveAutoScore")
	public void saveAutoScore(HttpServletRequest request,HttpServletResponse response,ModelMap model,AutoGradeInfo autoGradeInfo){
		try {
			//添加自动评分数据
			String url =Consts.AUTOSCORE_URL+"/fetalRate/addAutoGradeInfo";
//			String hospitalId = request.getParameter("hospitalId");
			net.sf.json.JSONObject JsomAutoGradeInfo=JsonUtils.toJSONObject(autoGradeInfo);
			String sendPost = HttpRequestUtils.sendPost(url, JsomAutoGradeInfo);
			System.out.println(sendPost);
			JsonUtils.render(getResponse(), sendPost);
		} catch (Exception e) {
			logger.info(e);
		}
	}

	public static void main(String[] args) {
		 
		 try {
				String url = Consts.WARNING_URL+"/warn/getWarnInfo";
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", 12288);
				map.put("hospitalId", 8569);
				map.put("monitorOrderId", 4129);
//				map.put("addTime", "2017-07-28T15:01:10");
				map.put("addTime", "2017-07-28 15:00:49");
				String sendPost = HttpRequestUtils.sendPost(url, net.sf.json.JSONObject.fromObject(map));
				System.out.println(sendPost);
			} catch (Exception e) {
				logger.info(e);
			}
		/* 
		 try {
				String url = "http://192.168.0.2:8081/monitor/warn/getWarnInfo";
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", 11874);
				map.put("hospitalId", 8569);
				String sendPost = HttpRequestUtils.sendPost(url, net.sf.json.JSONObject.fromObject(map));
				System.out.println(sendPost);
			} catch (Exception e) {
				logger.info(e);
			}*/
		 /*
		 try {
			//添加预警记录
			String url = "http://192.168.0.2:8081/monitor/warn/addWarnInfo";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hospitalId", "");
			map.put("alarmValue", 175);
			map.put("lowerLimit", 120);
			map.put("upperLimit", 185);
			map.put("userId", 11723);
			map.put("monitorOrderId", 12353);
			String sendPost = HttpRequestUtils.sendPost(url, net.sf.json.JSONObject.fromObject(map));
			System.out.println(sendPost);
		} catch (Exception e) {
			logger.info(e);
		}
		*/
		 	 
	}
}
