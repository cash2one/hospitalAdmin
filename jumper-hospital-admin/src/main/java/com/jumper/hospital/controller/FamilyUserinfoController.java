/*
 * @文件名： FamilyUserinfoController.java
 * @创建人: aaron
 * @创建时间: 2016-1-29
 * @包名： com.jumper.hospital.controller
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
 */
package com.jumper.hospital.controller;

import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.City;
import com.jumper.hospital.entity.District;
import com.jumper.hospital.entity.FamilyDoctorinfo;
import com.jumper.hospital.entity.FamilyExamination;
import com.jumper.hospital.entity.FamilyUserHeartrateRecord;
import com.jumper.hospital.entity.FamilyUserOxygenRecord;
import com.jumper.hospital.entity.FamilyUserPressureRecord;
import com.jumper.hospital.entity.FamilyUserSugarRecord;
import com.jumper.hospital.entity.FamilyUserTemperatureRecord;
import com.jumper.hospital.entity.FamilyUserWeightRecord;
import com.jumper.hospital.entity.FamilyUserinfo;
import com.jumper.hospital.entity.HospitalDoctorMajor;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.Province;
import com.jumper.hospital.service.FamilyDoctorinfoService;
import com.jumper.hospital.service.FamilyExaminationService;
import com.jumper.hospital.service.FamilyQuestionnaireResultsService;
import com.jumper.hospital.service.FamilyUserinfoService;
import com.jumper.hospital.service.HospitalDoctorMajorService;
import com.jumper.hospital.service.HospitalInfoService;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.IDCardValidateUtil;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.utils.MD5EncryptUtils;
import com.jumper.hospital.utils.NumberFormatUtil;
import com.jumper.hospital.utils.TestItemUtil;
import com.jumper.hospital.utils.TimeUtils;

import com.jumper.hospital.vo.familyDoctor.MonitoringResults;
import com.jumper.hospital.vo.familyDoctor.TestItem;
import com.jumper.hospital.vo.familyDoctor.VOFamilyExaminationResult;
import com.jumper.hospital.vo.familyDoctor.VOProvince;

/**
 * 类名称：FamilyUserinfoController 类描述： 创建人：aaron 创建时间：2016-1-29 下午3:22:46
 * 修改人：aaron 修改时间：2016-1-29 下午3:22:46 修改备注：
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/fDoctor")
public class FamilyUserinfoController extends BaseController {
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
	}
	@Autowired
	private FamilyUserinfoService familyUserinfoService;
	 
	@Autowired
	private FamilyDoctorinfoService familyDoctorinfoService;
    @Autowired
    private HospitalInfoService hospitalInfoService;
    @Autowired
    private HospitalDoctorMajorService hospitalDoctorMajorService;
    @Autowired
    private FamilyExaminationService familyExaminationService;
    @Autowired
    FamilyQuestionnaireResultsService familyQuestionnaireResultsService;
	
	
	/**
	 *
	 * waitAudit(该方法用于获取当前医生所属的医院相关的用户的未审核的报告，侧重点是未审核的报告，允许一个用户出现多次)
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws Exception 
	 * @Exception 异常对象
	 */
	private final static Logger logger = Logger.getLogger(FamilyUserinfoController.class);

	@RequestMapping("/waitAudit")
	public String waitAudit(ModelMap model,Page<VOFamilyExaminationResult> page, HttpSession session) throws Exception {
		// 获取当前登陆人员信息
				Admin admin = this.getLoginAdminInfo();
			familyExaminationService.getHospitalNotExamineResultByHospitalId(page, admin);
			model.put("page",page);
			model.put("doctorName", admin.getUsername());
			pageLocation(model, Consts.FAMILY_MODULE,Consts.FAMILY_MENU_WAITAUDIT_REPORT, null);//
			return "fDoctor/waitAuditList";
	}


	/**
	 * 获取医生列表
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/doctor")
	public String doctor(ModelMap model, Page<FamilyDoctorinfo> page,
			String username) throws Exception {
		if (null == username) {
			username = "";
		}
		// 获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		boolean isSuper = false, isManager = false;
		int role = getRoleAuthority(admin);
		if (role == 1) {
			isSuper = true;
		} else if (role == 2) {
			isManager = true;
		}
		// 获取当前账号所在医院对应的省市信息
		HospitalInfo currentHospital = admin.getHospitalInfo();
		City currentCity = currentHospital.getCity();
		Province currentProvince = admin.getHospitalInfo().getProvince();
		model.put("city", currentCity);
		model.put("provice", currentProvince);
		model.put("hospital", currentHospital);
		if (isSuper) {
			// 如果是超级用户的话就返回所有的家庭医生
			familyDoctorinfoService.getAllDoctorinfo(page, username);
		} else {// 那么不是超级管理员就返回当前账号所在的医院对应的家庭医生
		    familyDoctorinfoService.getMyHospitalDoctorinfo(page,username,currentHospital.getId());
		}
		model.put("page", page);
		model.put("username", username);
		// 前端界面控制
		pageLocation(model, Consts.FAMILY_MODULE,
				Consts.FAMILY_MENU_DOCTOR_ACCOUNT, null);
		return "fDoctor/doctorList";
	}

	/**
	 * 添加家庭医生信息
	 *
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addFamilyDoctor")
	public Map<String, Object> addFamilyDoctor(FamilyDoctorinfo familyDoctorinfo)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
	 //检测手机号码是否唯一
		boolean flag1 = familyDoctorinfoService.checkUsername(familyDoctorinfo.getUsername());
		if(!flag1){
			resultMap.put("flag", "error");
			resultMap.put("msg", "家庭医生账号已存在");
			resultMap.put("data", null);
			return resultMap;
		}
		boolean flag2 =familyDoctorinfoService.checkMobile(familyDoctorinfo.getPhone());
		familyDoctorinfo.setAddDate(new Date());
		if(!flag2){
			resultMap.put("flag", "error");
			resultMap.put("msg", "家庭医生号码已存在");
			resultMap.put("data", null);
			return resultMap;
		}
		try {
			String MD5password=MD5EncryptUtils.getMd5Value(familyDoctorinfo.getPassword());
			familyDoctorinfo.setPassword(MD5password);
		    HospitalInfo hospitalInfo = hospitalInfoService.get(familyDoctorinfo.getHospitalId());
		    familyDoctorinfo.setResponsibleArea(familyDoctorinfo.getResponsibleArea()+familyDoctorinfo.getDetailAdd());
		    familyDoctorinfo.setHospitalInfo(hospitalInfo);
		    familyDoctorinfoService.save(familyDoctorinfo);
			resultMap.put("flag", "success");
			resultMap.put("msg", "成功添加家庭医生");
			resultMap.put("data", null);
		} catch (Exception e) {
			logger.info("call method addFamilyDoctor error");
			resultMap.put("flag", "error");
			resultMap.put("msg", "添加家庭医生失败");
			resultMap.put("data", e);
			e.printStackTrace();
		}
		return resultMap;
	}

	@ResponseBody
    @RequestMapping(value = "/updateFamilyDoctor")
    public Map<String, Object> updateFamilyDoctor(
            @RequestParam(value = "param", required = true) String param)
            throws Exception {
		//封装异步提交的参数转化为bean
        FamilyDoctorinfo familyDoctorinfo = json2bean(param, FamilyDoctorinfo.class);
        if("******".equalsIgnoreCase(familyDoctorinfo.getPassword())){
        	familyDoctorinfo.setPassword(null);
        }else{
        	String MD5password=MD5EncryptUtils.getMd5Value(familyDoctorinfo.getPassword());
			familyDoctorinfo.setPassword(MD5password);
        }
        if (null == familyDoctorinfo ||null==familyDoctorinfo.getId()) {
           return  null;
        }//实例化结果集map
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //封装要修改的参数map ,方便在后面拼接update语句
        Map<String, Object> propertyMap = new HashMap<String, Object>();
        try {//获取表单封装的bean ,遍历所有属性字段,收集非空的字段,
            Field[] fields = familyDoctorinfo.getClass().getDeclaredFields();
            for (Field f : fields)
            {
                f.setAccessible(true);
                if(null!=f.get(familyDoctorinfo)){
                	propertyMap.put(f.getName(), f.get(familyDoctorinfo));
                }
            }
            propertyMap.remove("id");//直接remove id
            propertyMap.remove("serialVersionUID");//直接remove serialVersionUID多余字段
            
            //根据id更新数据
            familyDoctorinfoService.updateById(familyDoctorinfo,propertyMap);
            resultMap.put("flag", "success");
            resultMap.put("msg", "成功修改家庭医生");
            resultMap.put("data", null);
        } catch (Exception e) {
        	logger.info("call method updateFamilyDoctor error");
            resultMap.put("flag", "error");
            resultMap.put("msg", "修改家庭医生失败");
            resultMap.put("data", e);
            e.printStackTrace();
        }
        return resultMap;
    }




	@ResponseBody
	@RequestMapping(value = "/getProvinceCityDistrct")
	public Map<String, Object> getProvinceCityDistrct() {
		Map<String, Object> model = new HashMap<String, Object>();
		// 获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();

		// 获取当前账号所在医院对应的省市信息
		HospitalInfo currentHospital = admin.getHospitalInfo();
		City currentCity = currentHospital.getCity();
		Province currentProvince = admin.getHospitalInfo().getProvince();
		// 获取省份数据
		List<com.jumper.hospital.entity.Province> provices = familyUserinfoService
				.getProvices();
		// 根据省份id得到对应的城市数据
		List<City> citys = familyUserinfoService
				.getCitiesByProvinceId(currentProvince.getId());
		// 根据当前的城市id获取对应的县区数据
		List<District> districts = familyUserinfoService
				.getDistrictsByCityId(currentCity.getId());
		model.put("city", currentCity);
		model.put("provice", currentProvince);
		model.put("hospital", currentHospital);
		model.put("citys", citys);
		model.put("provices", provices);
		model.put("districts", districts);
		return model;

	}
/**
 *
 * getCitysByProId(根据省id获取对应的市数据)
 * @param @param id
 * @param @return
 * @return Map<String,Object>
 * @Exception 异常对象
 */
	@ResponseBody
	@RequestMapping("/getCitysByProId")
	public Map<String, Object> getCitysByProId(int id) {
		Map<String, Object> model = new HashMap<String, Object>();

		List<City> list;
		try {
			list = familyUserinfoService.getCitiesByProvinceId(id);
			model.put("flag", "success");
			model.put("msg", "成功获取城市数据");
			model.put("data", list);
		} catch (Exception e) {
			model.put("flag", "error");
			model.put("msg", "获取城市数据失败");
			model.put("data", e.getMessage());
		}
		return model;

	}
	@ResponseBody
	@RequestMapping("/getFamilyDoctorById")
	public Map<String, Object> getFamilyDoctorById(int id) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			Map <String,Object> map = familyDoctorinfoService.getDoctorByid(id);
			model.put("flag", "success");
			model.put("msg", "成功获取医生数据");
			model.put("data", map);
		} catch (Exception e) {
			model.put("flag", "error");
			model.put("msg", "获取医生数据失败");
			model.put("data", e.getMessage());
		}
		return model;
	}
	@ResponseBody
	@RequestMapping("/getDoctorTitles")
	public Map<String, Object> getDoctorTitles() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			List<String> list=familyDoctorinfoService.getDoctorTitles();
			model.put("flag", "success");
			model.put("msg", "成功获取医生职称数据");
			model.put("data", list);
		} catch (Exception e) {
			model.put("flag", "error");
			model.put("msg", "获取医生职称数据失败");
			model.put("data", e.getMessage());
		}
		return model;
	}
	/**
	 *
	 * getDistrctByCityId(根据市id获取对应的区县数据)
	 * @param @param id
	 * @param @return
	 * @return Map<String,Object>
	 * @Exception 异常对象
	 */
	@ResponseBody
	@RequestMapping("/getDistrctByCityId")
	public Map<String, Object> getDistrctByCityId(int id) {
		Map<String, Object> model = new HashMap<String, Object>();

		List<District> list;
		try {
			list = familyUserinfoService.getDistrictsByCityId(id);
			model.put("flag", "success");
			model.put("msg", "成功获取城市数据");
			model.put("data", list);
		} catch (Exception e) {
			model.put("flag", "error");
			model.put("msg", "获取城市数据失败");
			model.put("data", e.getMessage());
		}
		return model;

	}

	//删除医生数据 delFamilyDoctor
	@ResponseBody
    @RequestMapping("/delFamilyDoctor")
    public Map<String, Object> delFamilyDoctor(int id) {
	    Map<String, Object> model = new HashMap<String, Object>();
	    try
        {
            //familyDoctorinfoService.delete(id);
	    	familyDoctorinfoService.deleteById(id);
            model.put("flag", "success");
            model.put("msg", "成功删除家庭医生");
            model.put("data", null);
        } catch (Exception e)
        {
        	logger.info("call method delFamilyDoctor error");
            e.printStackTrace();
            model.put("flag", "error");
            model.put("msg", "删除家庭医生失败");
            model.put("data", e.getMessage());
        }
        return model;
	}
	@ResponseBody
	@RequestMapping("/getAllProvinces")
	public Map<String, Object> getAllProvinces() {
		Map<String, Object> model = new HashMap<String, Object>();
		try
		{
			List<VOProvince> list=familyDoctorinfoService.getAllProvinces();
			model.put("flag", "success");
			model.put("msg", "成功删除家庭医生");
			model.put("data", list);
		} catch (Exception e)
		{
			logger.info("call method getAllProvinces error");
			e.printStackTrace();
			model.put("flag", "error");
			model.put("msg", "删除家庭医生失败");
			model.put("data", e.getMessage());
		}
		return model;
	}
	@ResponseBody
	@RequestMapping("/getHospitalDoctorMajors")
	public Map<String, Object> getHospitalDoctorMajors() {
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		try {
			List<HospitalDoctorMajor> list = hospitalDoctorMajorService.getMajorList();
			resultMap.put("flag", "success");
			resultMap.put("msg", "成功获取医生科室");
			resultMap.put("data", list);
		} catch (Exception e) {
			logger.info("call method getHospitalDoctorMajors error");
			e.printStackTrace();
			resultMap.put("flag", "error");
			resultMap.put("msg", "获取医生科室失败");
			resultMap.put("data", e);
		}
		return resultMap;
	}
/**
 * 
 * @param id 报告单id
 * @param reason  页面中医生在生成报告单的时候手动填写的备注
 * @param offset   动画插件自动生成的值用于pdf绘制
 * @return
 */
	@ResponseBody
	@RequestMapping("/generate")
	public Map<String, Object> generate(Integer id, String reason, Integer offset) {
		reason=null==reason?"":reason;
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		try {
			Admin admin = this.getLoginAdminInfo();
		   familyExaminationService.saveReport(getRequest(), id, reason, admin.getUsername(), offset);
		   this.setResultMap("success","成功生成报告单", null, resultMap);
		} catch (Exception e) {
			logger.info("call method generate error");
			e.printStackTrace();
			this.setResultMap("error","生成报告单失败", e.getMessage(), resultMap);
		}
		return resultMap;
	}


//------------------------用户部分-------------------------------------------------------------------------
	/** 家庭医生的用户列表信息
	 * @throws Exception */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/userinfo")
	public String userinfo(ModelMap model,Page<Map> page,String condition) throws Exception {
		if(null==condition)condition="";
				// 获取当前登陆人员信息
				Admin admin = this.getLoginAdminInfo();
				boolean isSuper = false;
				int role = getRoleAuthority(admin);
				if (role == 1) {
					isSuper = true;
				}
				if(isSuper){//如果是超级管理员就获取所有的用户数据
				    familyUserinfoService.getAllFamilyUserInfos(page , condition);
				}else{//否则获取当前账号所在的医院对应的用户数据
					Integer hospitalId = admin.getHospitalInfo().getId();
				    familyUserinfoService.getFamilyUserInfo4hospitalId(page,hospitalId,condition);
				}
				for (int i = 0; i < page.getResult().size(); i++) {
					Map map = page.getResult().get(i);
					
					if( null!=map.get("pregancyDay")){//如果预产期不为空
						Timestamp t=(Timestamp) map.get("pregancyDay");
						int[] week = TimeUtils.getPregnantWeek(new Date(t.getTime()));
						map.put("pregnantWeek",week[0]);
					}
				}
		model.put("page", page);
		model.put("condition", condition);
		// 前端界面控制
		pageLocation(model, Consts.FAMILY_MODULE,Consts.FAMILY_MENU_USERINFO_LIST, null);
		return "fDoctor/userinfo";
	}
	
	/** 报告列表简单展示
	 * @throws Exception */
	@RequestMapping("/viewDetail")
	public String viewDetail(ModelMap model,HttpServletRequest request ,Integer id) throws Exception {
			try {
				FamilyUserinfo familyUserinfo = familyUserinfoService.get(id);
				//通过id 获取报告标识中当前id的用户的已完成报告数据
				List<Map<String, Object>> list=familyExaminationService.getListByUserid(familyUserinfo.getId());
				List<Map<String, Object>> report = familyQuestionnaireResultsService.getQuestionReport(familyUserinfo.getId());
				if(null!=report && report.size()>0){
					Map<String, Object> map = report.get(0);//pregnantWeek
					if(null!=familyUserinfo.getPregancyDay()){
						int[] pregnantWeek = TimeUtils.getPregnantWeek(familyUserinfo.getPregancyDay(), 
								new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("addTime").toString()+":00"));
						map.put("pregnantWeek", "孕"+pregnantWeek[0]+"周"+pregnantWeek[1]+"天");
					}
					map.put("state",familyUserinfo.getState());
					map.put("resultUrl", Consts.BASE_FILE_URL+map.get("resultUrl"));
					model.put("report", report.get(0));
				}
				
				Date pregancyDay = familyUserinfo.getPregancyDay();
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map_ = list.get(i);
					String count="第"+NumberFormatUtil.foematInteger( i+1)+"次检查";
					map_.put("count", count);
					Date addTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map_.get("addTime").toString());
					if(null!=pregancyDay){
						int[] pregnantWeek = TimeUtils.getPregnantWeek(pregancyDay, addTime);
						map_.put("pregnantWeek","孕"+pregnantWeek[0]+"周"+pregnantWeek[1]+"天");
					} 
					map_.put("addTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map_.get("addTime").toString()));
				}
				model.put("id", id);
				if(null!=pregancyDay)model.put("gestationalWeek", TimeUtils.getPregnantWeek(pregancyDay)[0]+"周");//设置用户当前孕周
				model.put("userinfo", familyUserinfo);
				model.put("examinations", list);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				// 前端界面控制
				pageLocation(model, Consts.FAMILY_MODULE,Consts.FAMILY_MENU_USERINFO_LIST, null);
				return "fDoctor/viewDetail";
		 
	}
	
	/** 报告单展示
	 * @throws Exception */
	@RequestMapping("/rePortDetail")
	public String rePortDetail(ModelMap model,HttpServletRequest request ,Integer id) throws Exception {
		FamilyExamination familyExamination = familyExaminationService.get(id);
		Integer userid = familyExamination.getUserid();
		//该map存储的是监测项目的 项目名和对应的监测项目id
		Map<String,Integer> map=familyExaminationService.getExamineRecords(familyExamination.getId(),familyExamination.getAddTime());
		
		FamilyUserinfo familyUserinfo = familyUserinfoService.get(userid);
		//报告单中的用户数据
		// 检测概况数据
		List<MonitoringResults> resultList=new ArrayList<MonitoringResults>();//结果集list
		familyExaminationService.getHeartRecord(userid, map, resultList);//--获取胎心展示结果
		familyExaminationService.getWeightRecord(userid, map, resultList);//--获取体重展示结果
		familyExaminationService.getOxygenRecord(userid, map, resultList);//--获取血氧展示结果
		familyExaminationService.getPressureRecord(userid, map, resultList);//---获取血压展示结果
		familyExaminationService.getSugarRecord(userid, map, resultList);//--获取血糖展示结果
		familyExaminationService.getTemperatureRecord(userid, map, resultList);//--获取体温展示结果
		// 孕周
		if(null!=familyUserinfo.getPregancyDay()){
			int[] pregnantWeek = TimeUtils.getPregnantWeek(familyUserinfo.getPregancyDay(), familyExamination.getAddTime());
			model.put("gestationalWeek", pregnantWeek[0]);
		}else{
			model.put("gestationalWeek", 0);
		}
		// 胎心pdf
		if("".equals(familyExamination.getReportPdfUrl())|| null== familyExamination.getReportPdfUrl()){
			model.put("pdfurl", null);
		}else{
			String url =Consts.BASE_FILE_URL+ familyExamination.getReportPdfUrl();
			model.put("pdfurl", url);
		}
		model.put("id", id);
		model.put("familyExamination",familyExamination);
		model.put("fe", familyUserinfo);
		model.put("result", resultList);
		pageLocation(model, Consts.FAMILY_MODULE,Consts.FAMILY_MENU_USERINFO_LIST, null);
		return "fDoctor/rePortDetail";
	}
	
	/** 报告单详细数据展示
	 * @throws Exception */
	@RequestMapping("/dataDetail")
	public String dataDetail(ModelMap model,HttpServletRequest request ,Integer id) throws Exception {
			FamilyExamination familyExamination = familyExaminationService.get(id);
			Integer userid = familyExamination.getUserid();
			//Timestamp checkTime = familyExamination.getCheckTime();
			FamilyUserinfo familyUserinfo = familyUserinfoService.get(userid);

			//该map存储的是监测项目的 项目名和对应的监测项目id
			Map<String,Integer> map=familyExaminationService.getExamineRecords(familyExamination.getId(),familyExamination.getAddTime());
			
			// 检测数据
			List<MonitoringResults> resultList=new ArrayList<MonitoringResults>();//结果集list
			FamilyUserHeartrateRecord heartRecord = familyExaminationService.getHeartRecord(userid, map, resultList);//--获取胎心展示结果
			FamilyUserWeightRecord weightRecord = familyExaminationService.getWeightRecord(userid, map, resultList);//--获取体重展示结果
			FamilyUserOxygenRecord oxygenRecord = familyExaminationService.getOxygenRecord(userid, map, resultList);//--获取血氧展示结果
			FamilyUserPressureRecord pressureRecord = familyExaminationService.getPressureRecord(userid, map, resultList);//---获取血压展示结果
			FamilyUserSugarRecord sugarRecord = familyExaminationService.getSugarRecord(userid, map, resultList);//--获取血糖展示结果
			FamilyUserTemperatureRecord temperatureRecord = familyExaminationService.getTemperatureRecord(userid, map, resultList);//--获取体温展示结果
			
			
			if(null!=heartRecord){
				//音频文件路径
				String music=(null==heartRecord.getRawFiles()?"":Consts.BASE_FILE_URL+heartRecord.getRawFiles());
				//胎动json value
				String fetalMoveData =(null==heartRecord.getFetalMoveFiles()?"":heartRecord.getFetalMoveFiles());
				//胎心动画数据
				String heardata=(null==heartRecord.getRecordFiles()?"":JsonUtils.getJsonFileStr(Consts.BASE_FILE_URL+heartRecord.getRecordFiles()));
				
				//胎心测试开始时间
				Date appTestTime = heartRecord.getAppTestTime();
				//胎心测试结束时间
				String heartEndTime = TimeUtils.calculateTime(new Timestamp(appTestTime.getTime()),heartRecord.getTestTime() );//开始测试时间
				model.put("heardata", heardata);
				model.put("fetalMoveData", fetalMoveData);
				model.put("music", music);
				model.put("heartTestStart", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(appTestTime));
				model.put("heartTestend", heartEndTime);
				model.put("testTime", TimeUtils.getTimeStrBySeconds(heartRecord.getTestTime() ));
			}
			
			model.put("id", id);
			model.put("heart", heartRecord);
			model.put("oxygen", oxygenRecord);
			model.put("weight", weightRecord);
			model.put("pressure", pressureRecord);
			model.put("sugar", sugarRecord);
			model.put("temperature", temperatureRecord);
			Map<String, TestItem> initMap = TestItemUtil.getInitMap();
			model.put("init", initMap);
			// 孕周
			
			if(null!=familyUserinfo.getPregancyDay()){
				int[] pregnantWeek = TimeUtils.getPregnantWeek(familyUserinfo.getPregancyDay(), familyExamination.getAddTime());
				model.put("gestationalWeek", pregnantWeek[0]);
			}
			model.put("consumer",familyExamination);
			model.put("user", familyUserinfo);
			
			pageLocation(model, Consts.FAMILY_MODULE,Consts.FAMILY_MENU_USERINFO_LIST, null);
			return "fDoctor/dataDetail";
	}
	
	@ResponseBody
	@RequestMapping("/getFamilyUser")
	public Object getFamilyUser(Integer id) {
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		try {
			 FamilyUserinfo userinfo = familyUserinfoService.get(id);
			String address= familyUserinfoService.getProvinceCityByUserInfo(userinfo);
			if(null!=userinfo.getLastPeriod()){
				resultMap.put("lastPeriod", new SimpleDateFormat("yyyy-MM-dd").format(userinfo.getLastPeriod()));
			}else{
				resultMap.put("lastPeriod", "");
			}
			resultMap.put("address",address);
			resultMap.put("flag", "success");
			resultMap.put("msg", "获取用户数据成功");
			resultMap.put("data", userinfo);
			 return  resultMap;
		} catch (Exception e) {
			logger.info("call getFamilyUser  error");
			e.printStackTrace();
			return this.setResultMap("error","获取用户失败", e.getMessage(), resultMap);
		}
	}
	@ResponseBody
	@RequestMapping("/updateFamilyUser")
	public Map<String, Object> updateFamilyUser(FamilyUserinfo userinfo,String address) {
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		try {
			 //检测手机号码
			FamilyUserinfo dbUserinfo = familyUserinfoService.get(userinfo.getId());
			if(!(userinfo.getMobile().equals(dbUserinfo.getMobile()))){
				Boolean flag= familyUserinfoService.checkMobile(userinfo.getMobile());
				if(!flag){
					this.setResultMap("error","手机号码重复",null, resultMap);
					return resultMap;
				}
			}
			dbUserinfo.setName(userinfo.getName());
			dbUserinfo.setAge(userinfo.getAge());
			 
			dbUserinfo.setLastPeriod(userinfo.getLastPeriod());
			dbUserinfo.setMobile(userinfo.getMobile());
			dbUserinfo.setDetailAdd(userinfo.getDetailAdd());//手动输入部分
			if(StringUtils.isNotBlank(userinfo.getDetailAdd())){
				dbUserinfo.setJoinAdd(address+dbUserinfo.getDetailAdd());//完整的拼接地址
				if(dbUserinfo.getJoinAdd().length()>200){
					this.setResultMap("error","地址长度不能超过200", null, resultMap);
				}
			}else{dbUserinfo.setJoinAdd(address+"");}
			
			
			if(null!=userinfo.getLastPeriod()){
				dbUserinfo.setPregancyDay(getPregancyDay(userinfo.getLastPeriod()));
			}else{
				dbUserinfo.setPregancyDay(null);
				dbUserinfo.setLastPeriod(null);
			}
			if(StringUtils.isNotBlank(userinfo.getIdentity())){
				if(userinfo.getIdentity().indexOf("x")>-1)return this.setResultMap("error", "x不能小写", null, resultMap);
		        IDCardValidateUtil cc = new IDCardValidateUtil();
		        String validate= cc.IDCardValidate(userinfo.getIdentity());
		        if(validate.length()>0)return this.setResultMap("error", validate, null, resultMap);
		        dbUserinfo.setIdentity(userinfo.getIdentity());
			}else{
				 dbUserinfo.setIdentity("");
			}
			if(null!=userinfo.getHeight()){
				dbUserinfo.setHeight(userinfo.getHeight());
			}else{dbUserinfo.setHeight(null);}
			if(StringUtils.isNotBlank(userinfo.getLinkMan())){
				dbUserinfo.setLinkMan(userinfo.getLinkMan());
			}else{dbUserinfo.setLinkMan("");}
			if(StringUtils.isNotBlank(userinfo.getLinkTel())){
				dbUserinfo.setLinkTel(userinfo.getLinkTel());
			}else{dbUserinfo.setLinkTel("");}
 
			familyUserinfoService.save(dbUserinfo);
			this.setResultMap("success","更新成功", null, resultMap);
		} catch (Exception e) {
			logger.info("call updateFamilyUser  error");
			e.printStackTrace();
			this.setResultMap("error","更新用户失败", e.getMessage(), resultMap);
		}
		return resultMap;
	}

	 

	public Map<String, Object> setResultMap(String flag,String msg,Object data,Map<String, Object> resultMap){
		logger.info("resultMap null");
		resultMap.put("flag", flag);
		resultMap.put("msg", msg);
		resultMap.put("data", data);
		return resultMap;
	}


	/**
	 * json2bean(在使用异步提交json时问题多多,在前端提交json字符串后,手动的把json字符串转为对象)
	 */
	private <T> T json2bean(String param, Class<T> class1) throws Exception {
		param = URLDecoder.decode(param, "UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(param, class1);
	}
	/**
	 * 根据末次月经时间计算预产期
	 * @param lastPeriod 末次月经时间
	 * @return 
	 */
	public static Date getPregancyDay(Date d1){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar Calendarborn = Calendar.getInstance();
			Calendarborn.setTime(d1);
			Calendarborn.add(Calendar.DAY_OF_MONTH, 280);
			int bornyear = Calendarborn.get(Calendar.YEAR);
			int bornmonth = 1 + Calendarborn.get(Calendar.MONTH);
			int bornday = Calendarborn.get(Calendar.DAY_OF_MONTH);
			String borndayString = (String.valueOf(bornyear) + "-" + String.valueOf(bornmonth) + "-" + String.valueOf(bornday));
			return sdf.parse(borndayString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
