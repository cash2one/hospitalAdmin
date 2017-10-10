package com.jumper.hospital.controller;
/**
 * 孕妇学校controller后台所有操作将在该contrller中完成
 * 本controller不负责任何逻辑操作，仅用于跳转，若添加业务代码，请分发到各个service处理
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.CommonDictionary;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.SchoolCommonDoctor;
import com.jumper.hospital.entity.SchoolOfflineCourse;
import com.jumper.hospital.entity.SchoolOnlineCourse;
import com.jumper.hospital.enums.Dictionary;
import com.jumper.hospital.enums.DoctorTitle;
import com.jumper.hospital.service.CommonDictionaryService;
import com.jumper.hospital.service.SchoolCommonDoctorService;
import com.jumper.hospital.service.SchoolCourseDetailService;
import com.jumper.hospital.service.SchoolOfflineCourseService;
import com.jumper.hospital.service.SchoolOnlineCourseService;
import com.jumper.hospital.utils.AccessBuildUtils;
import com.jumper.hospital.utils.Const;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.JsonUtils;

@Controller
@RequestMapping("school")
public class SchoolLibraryController extends BaseController {

	@Autowired
	private SchoolOfflineCourseService schoolOfflineCourseServiceImpl;
	@Autowired
	private SchoolOnlineCourseService schoolOnlineCourseServiceImpl;
	@Autowired
	private CommonDictionaryService commonDictionaryServiceImpl;
	@Autowired
	private SchoolCommonDoctorService schoolCommonDoctorServiceImpl;
	@Autowired
	private SchoolCourseDetailService schoolCourseDetailServiceImpl;
	/**
	 * 跳转到新版的孕妇学校
	 * @param model
	 * @param page
	 * @param libraryType
	 * @param searchKey
	 * @param libraryTypeTemp
	 * @return
	 */
	@RequestMapping("library2")
	public String newSchool(ModelMap model){

		String accessKey = Const.ACCESS_KEY;
		String objectId = Const.OBJECT_ID;
		String accessToken = AccessBuildUtils.createWebAccessToken(accessKey, objectId, 12);
		Admin admin = getLoginAdminInfo();
		Integer hid=admin.getHospitalInfo().getId();
		String param="?hospitalId="+hid+"&access_token="+accessToken;
		//页面iframe src 地址
		model.addAttribute("url", Const.SCHOOL_URL+param);
		pageLocation(model, Consts.SCHOOL_MODULE, Consts.SCHOOL_MENU_LIBRARY, null);
		return "school/library/schoolIndex";
	}
	
	
	
	/**
	 * 线下、网络课程主页
	 * @param model
	 * @param page
	 * @param libraryType
	 * @param searchKey
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("library")
	public String libraryIndex(ModelMap model, Page page, Integer libraryType, String searchKey,
			@RequestParam(value="libraryTypeTemp",required=false,defaultValue="0")Integer libraryTypeTemp){
		
		/*	if(type != libraryType && page != null){
			page.setPageNo(1);
			searchKey = "";
		}
		 */
		if(libraryType!=libraryTypeTemp){//如果切换了线下课程或网络课程，则取消搜索字段
			searchKey = "";
		}
		if(libraryType == null){
			libraryType = 0;
		}
		Admin admin = getLoginAdminInfo();
		//判断是否已经开通了孕妇学校
		model.put("isSchool",  admin.getHospitalInfo().getIsSchool());
		if(libraryType == 0){
			Page<SchoolOfflineCourse> pageData = schoolOfflineCourseServiceImpl.getCoursesByHospital(admin.getHospitalInfo().getId(), page, searchKey);
			model.put("offLine", pageData);
		}else{
			Page<SchoolOnlineCourse> pageData = schoolOnlineCourseServiceImpl.getCoursesByHospital(admin.getHospitalInfo().getId(), page, searchKey, null);
			model.put("onLine", pageData);
		}
		model.put("searchKey", searchKey);
		model.put("libraryType", libraryType);
		pageLocation(model, Consts.SCHOOL_MODULE, Consts.SCHOOL_MENU_LIBRARY, null);
		return "school/library/index";
	}
	
	/**
	 * 跳转到编辑页
	 * @param model 
	 * @param libraryType 课程类型(线下、网络)
	 * @param recordId 记录ID，用于编辑
	 * @param operateType 操作类型(0:查看，1:新增，2:编辑)
	 * @return
	 */
	@RequestMapping("offEdit")
	public String offLineCourseEdit(ModelMap model, Integer libraryType, Integer recordId, Integer operateType){
		operateType = operateType == null ? 0 : operateType;
		model.put("operateType", operateType);
		pageLocation(model, Consts.SCHOOL_MODULE, Consts.SCHOOL_MENU_LIBRARY, null);
		Admin admin = getLoginAdminInfo();
		HospitalInfo hospital = admin.getHospitalInfo();
		List<CommonDictionary> commonAddress = commonDictionaryServiceImpl.getByRelationIdAndType(hospital.getId(), Dictionary.常用地址.ordinal());
		List<CommonDictionary> commonNotice = commonDictionaryServiceImpl.getByRelationIdAndType(hospital.getId(), Dictionary.注意事项.ordinal());
		List<SchoolCommonDoctor> commonDoctor = schoolCommonDoctorServiceImpl.getSchoolCommonDoctorByHospital(hospital.getId());
		model.put("commonAddress", commonAddress);
		model.put("commonNotice", commonNotice);
		model.put("commonDoctor", commonDoctor);
		model.put("doctorTitle", DoctorTitle.values());
		
		//如果首次添加课程，怎么默认地址为医院地址，如果不是首次添加课程，则默认地址为上次添加课程使用的地址
		String address=null;
		SchoolOfflineCourse courseTemp = schoolOfflineCourseServiceImpl.getCourseList(admin.getHospitalInfo().getId());
		if(courseTemp != null){
			//默认地址为上次添加的地址
			address=courseTemp.getCourseAddress();
            //上一次课程简介  
            model.addAttribute("lastCourseDesc", courseTemp.getCourseDesc());
            //上一次注意事项
            model.addAttribute("lastCourseNotice", courseTemp.getCourseNotice());
		}else{
			//默认地址为医院的地址
			address=admin.getHospitalInfo().getAddress();
		}
		
		model.addAttribute("address", address);
		/** 线下课程 **/
		if(libraryType == 0){
			if(recordId != null && recordId != 0){
				
				SchoolOfflineCourse course = schoolOfflineCourseServiceImpl.get(recordId);
				model.put("course", course);
			}
			return "school/library/offEdit";
		}else{
			if(recordId != null && recordId != 0){
				SchoolOnlineCourse course = schoolOnlineCourseServiceImpl.get(recordId);
				model.put("course", course);
				
			}
			
			return "school/library/onEdit";
		}
	}
	/**
	 * 判断课程名称是否已经存在
	 * @param courseName 课程名
	 * @param courseId  课程id
	 */
	@RequestMapping("compareCourseName")
	public void getCourseName(String courseName,@RequestParam(value="courseId",required=false)Integer courseId){
		try{
			Admin admin = getLoginAdminInfo();
			HospitalInfo hospital = admin.getHospitalInfo();
			SchoolOfflineCourse courseTemp=schoolOfflineCourseServiceImpl.getCourseByCourseName(courseName, hospital.getId(),courseId);
			if(courseTemp!=null){
				JsonUtils.render(getResponse(), Consts.REPEAT);
				return;
			}else{
				JsonUtils.render(getResponse(), Consts.SUCCESS);
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
			
		}
		
	}
	/**
	 * 判断线上课程名是否同时存在
	 * @param courseName
	 * @param courseId
	 */
	@RequestMapping("compareOnlineCourse")
	public void getOnlineCourseName(String courseName,@RequestParam(value="courseId",required=false)Integer courseId){
		try{
			Admin admin = getLoginAdminInfo();
			HospitalInfo hospital = admin.getHospitalInfo();
			SchoolOnlineCourse courseTemp=schoolOnlineCourseServiceImpl.getCourseByCourseName(courseName, hospital.getId(),courseId);
			if(courseTemp!=null){
				JsonUtils.render(getResponse(), Consts.REPEAT);
				return;
			}else{
				JsonUtils.render(getResponse(), Consts.SUCCESS);
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
			
		}
	}
	
	/**
	 * 编辑下线课程
	 * @param course
	 * @param model
	 * @return
	 */
	@RequestMapping("offSave")
	public void offLineSave(SchoolOfflineCourse course, Integer costType){
	
		try {
			Admin admin = getLoginAdminInfo();
			HospitalInfo hospital = admin.getHospitalInfo();
			//如果用户没有上传图片的话，那么给他一张默认图片
			if(StringUtils.isEmpty(course.getCourseImage())){
				course.setCourseImage(Consts.DEFAULT_IMAGE);
			}
			
			//判断是否出现重复的课程名称
		/*	SchoolOfflineCourse courseTemp=schoolOfflineCourseServiceImpl.getCourseByCourseName(course.getCourseName(), hospital.getId(),course.getId());
			if(courseTemp!=null){
				JsonUtils.render(getResponse(), Consts.REPEAT);
				return;
			}
		*/	
			boolean flag = schoolOfflineCourseServiceImpl.addOffLineCourse(course, hospital.getId(), costType);
			if(flag){
				JsonUtils.render(getResponse(), Consts.SUCCESS);
				return;
			}
			JsonUtils.render(getResponse(), Consts.FAILED);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	/**
	 * 编辑网络课程
	 * @param course
	 */
	@RequestMapping("onSave")
	public void onSave(SchoolOnlineCourse course){
		try {
			Admin admin = getLoginAdminInfo();
			HospitalInfo hospital = admin.getHospitalInfo();
			if(StringUtils.isEmpty(course.getCourseImage())){
				course.setCourseImage(Consts.DEFAULT_ONLINE_IMAGE);//添加默认图片
			}
			boolean flag = schoolOnlineCourseServiceImpl.editOnLineCourse(course, hospital.getId());
			if(flag){
				JsonUtils.render(getResponse(), Consts.SUCCESS);
				return;
			}
			JsonUtils.render(getResponse(), Consts.FAILED);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	/**
	 * 常用医生模板添加医生
	 */
	@RequestMapping("addCommonDoctor")
	public void addCommonDoctor(String doctorName, String doctorTitle){
		try {
			String name = new String(doctorName.getBytes("iso-8859-1"), "utf-8");
			String title = new String(doctorTitle.getBytes("iso-8859-1"),"utf-8");
			if(StringUtils.isEmpty(name)){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			Admin admin = getLoginAdminInfo();
			HospitalInfo info = admin.getHospitalInfo();
			boolean flag = schoolCommonDoctorServiceImpl.checkDoctorHasExist(name, title, info.getId());
			if(flag){
				JsonUtils.render(getResponse(), Consts.REPEAT);
				return;
			}
			SchoolCommonDoctor doctor = schoolCommonDoctorServiceImpl.addSchoolCommonDoctor(name, title, info.getId());
			if(doctor == null){
				JsonUtils.render(getResponse(), Consts.FAILED);
				return;
			}
			JsonUtils.render(getResponse(), doctor);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	@RequestMapping("editCommonDoctor")
	public void editCommonDoctor(Integer id, String doctorTitle){
		try {
			if(id == null || id == 0 || StringUtils.isEmpty(doctorTitle)){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			String title = new String(doctorTitle.getBytes("iso-8859-1"),"utf-8");
			SchoolCommonDoctor doctor = schoolCommonDoctorServiceImpl.get(id);
			doctor.setDoctorTitle(title);
			SchoolCommonDoctor result = schoolCommonDoctorServiceImpl.save(doctor);
			if(result == null){
				JsonUtils.render(getResponse(), Consts.FAILED);
				return;
			}
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	/**
	 * 删除常用医生
	 * @param id
	 */
	@RequestMapping("deleteCommonDoctor")
	public void deleteCommonDoctor(Integer id){
		try {
			if(id == null){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			schoolCommonDoctorServiceImpl.delete(id);
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	/**
	 * 添加字典信息
	 * @param type
	 * @param description
	 */
	@RequestMapping("addCommonAddress")
	public void addCommonAddress(Integer type, String description){
		try {
			String desc = new String(description.getBytes("iso-8859-1"), "utf-8");
			if(type == null || StringUtils.isEmpty(description)){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
		
			Admin admin = getLoginAdminInfo();
			HospitalInfo hospital = admin.getHospitalInfo();
			//先判断已经添加过多少条注意事项，若超过5条则不允许在添加了
			Integer count=commonDictionaryServiceImpl.getCommonDictionayCount(type,hospital.getId());
			if(count>=5){
				JsonUtils.render(getResponse(), Consts.OVER_LARGEST);
				return ;
			}
			CommonDictionary cd = commonDictionaryServiceImpl.addCommonDictionary(type, desc, hospital.getId());
			if(cd == null){
				JsonUtils.render(getResponse(), Consts.FAILED);
				return;
			}
			JsonUtils.render(getResponse(), cd);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	/**
	 * 删除字典内容
	 * @param id
	 */
	@RequestMapping("deleteCommonDictionary")
	public void deleteCommonDictionary(Integer id){
		try {
			if(id == null){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			commonDictionaryServiceImpl.delete(id);
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	/**
	 * 删除课程
	 * @param recordId
	 * @return
	 */
	@RequestMapping("courseDelete")
	public void courseDelete(Integer recordId, Integer libraryType){
		try {
			if(recordId == null || libraryType == null){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			if(libraryType == 0){
				boolean flag = schoolCourseDetailServiceImpl.checkCourseHasPlan(recordId);
				if(flag){
					JsonUtils.render(getResponse(), Consts.EXIST);
					return;
				}
				schoolOfflineCourseServiceImpl.delete(recordId);
			}else{
				schoolOnlineCourseServiceImpl.delete(recordId);
			}
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	@RequestMapping("onDetail")
	public String onLineCourseDetail(ModelMap model, Integer id){
		SchoolOnlineCourse course = schoolOnlineCourseServiceImpl.get(id);
		model.put("course", course);
		return "school/library/onDetail";
	}
	
}
