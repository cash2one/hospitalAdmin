package com.jumper.hospital.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalDescription;
import com.jumper.hospital.entity.HospitalDoctorNotice;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.HospitalMajorInfo;
import com.jumper.hospital.entity.HospitalSubordinate;
import com.jumper.hospital.entity.MonitorHospital;
import com.jumper.hospital.entity.UserExtraInfo;
import com.jumper.hospital.entity.UserInfo;
import com.jumper.hospital.service.AdminService;
import com.jumper.hospital.service.HospitalDescriptionService;
import com.jumper.hospital.service.HospitalDoctorInfoService;
import com.jumper.hospital.service.HospitalDoctorNoticeService;
import com.jumper.hospital.service.HospitalDoctorTitleService;
import com.jumper.hospital.service.HospitalInfoService;
import com.jumper.hospital.service.HospitalMajorInfoService;
import com.jumper.hospital.service.HospitalSubordinateService;
import com.jumper.hospital.service.MonitorHospitalService;
import com.jumper.hospital.service.MonitorOperateLogServices;
import com.jumper.hospital.service.UserExtraInfoService;
import com.jumper.hospital.service.UserInfoService;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.utils.BeanUtils;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.HBSmsCodeUtils;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.utils.MD5EncryptUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.VoHospitalAutoComplete;
import com.jumper.hospital.vo.visit.Msg;

import com.jumper.common.web.ReturnMsg;
/**
 * 医院管理
 * @author tanqing
 */
@Controller
@RequestMapping(value = "/hos")
public class HospitalController extends BaseController{
	@Autowired
	private  MonitorOperateLogServices monitorOperateLogServices;
	@Autowired
	private HospitalDescriptionService hospitalDescriptionServiceImpl;
	@Autowired
	private HospitalInfoService hospitalInfoServiceImpl;
	@Autowired
	HospitalDoctorInfoService hospitalDoctorInfoService;
	@Autowired
	private HospitalMajorInfoService hospitalMajorInfoService;
	@Autowired
	private HospitalSubordinateService hospitalSubordinateService;//从属关系表数据业务
	@Autowired
	private HospitalDoctorNoticeService hospitalDoctorNoticeService;
	@Autowired
	private HospitalDoctorTitleService hospitalDoctorTitleService;
	@Autowired
	private MonitorHospitalService monitorHospitalServiceImpl;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserExtraInfoService userExtraInfoService;
	
	
	private static final Logger logger = Logger.getLogger(HospitalController.class);
	
	/**
	 * 医院资料
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hospitalInfo")
	public String hospitalInfo(ModelMap model){
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		int id = hospitalInfo.getId();
		//获得医院科室的描述
		List<HospitalDescription> officeDescs = hospitalDescriptionServiceImpl.getHospDescByType(id, 0);
		//将医院描述信息model到前台页面
		model.addAttribute("filePath", Consts.BASE_FILE_URL);
		model.addAttribute("officeDescs", officeDescs);
		model.addAttribute("hospitalInfo", hospitalInfo);
		//配置医院信息  前端界面控制
		pageLocation(model, Consts.HOSPITAL_MODULE, "data", null);
		return "hospital/hospitalInfo";
	}
	
	/**
	 * 医院从属机构
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hospitalSubordinate")
	public String hospitalSubordinateList(ModelMap model,Page<HospitalSubordinate> page){
		try{
			//获取当前用户信息
			Admin admin = getLoginAdminInfo();
			//根据医院ID 查询从属列表
			Page<HospitalSubordinate> page2 = hospitalSubordinateService.getHospitalSubordinateList(admin.getHospitalInfo().getId(), page);
			List<HospitalInfo> subordinates = hospitalSubordinateService.getNotSubordinates(admin.getHospitalInfo());
			model.put("subordinates", subordinates);//查询未从属
			model.put("admin", admin);//查询用户权限
			model.put("page", page2);//列表内容
			pageLocation(model, Consts.HOSPITAL_MODULE, Consts.HOSPITAL_SUBMENU_SUBORDINATE, null);
		}catch (Exception e) {
			logger.error("异常信息:",e);
		}
		return "hospital/subordinate";
	}
	/**
	 * 操作用户(1:取消从属)
	 * @param id 医院ID
	 */
	@RequestMapping("cancel")
	public void operateAdmin(Integer id){
		try {
			if(id == null){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			//获取当前用户信息
			Admin admin = getLoginAdminInfo();
			//根据选择的医院ID 删除从属表数据
			hospitalSubordinateService.delete(id);
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	/**
	 * 根据从属医院ID 建立从属关系
	 * @param subordinatesId
	 */
	@RequestMapping("subordinates")
	public void getRoles(Integer[] subordinates){
		Msg msg = new Msg();
		try {
			//获取当前用户信息
			Admin admin = getLoginAdminInfo();
			if(admin == null){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			if(admin.getId() != null){
				if(subordinates != null && subordinates.length > 0){
					for(Integer r_id : subordinates){
						HospitalSubordinate entity=new HospitalSubordinate();
						entity.setHospitalId(admin.getHospitalInfo());
						entity.setSubordinateId(hospitalInfoServiceImpl.get(r_id));
						hospitalSubordinateService.save(entity);
					}
				}
				JsonUtils.render(getResponse(), Consts.SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	@RequestMapping("update")
	public void hospitalUpdate(HospitalInfo info){
		try {
			if(info == null || info.getId() == null){
				JsonUtils.render(getResponse(), "paramsError");
				return;
			}
			HospitalInfo targetObject = hospitalInfoServiceImpl.get(info.getId());
			BeanUtils.copy(info, targetObject);
			if(StringUtils.isBlank(targetObject.getHospitalKey())){
				targetObject.setHospitalKey(MD5EncryptUtils.getMd5Value(targetObject.getName()).substring(8, 17));
			}
			hospitalInfoServiceImpl.save(targetObject);
			
			JsonUtils.render(getResponse(), "success");
			//添加日志===>修改医院信息
			Admin admin = getLoginAdminInfo();
			monitorOperateLogServices.addOperateLog(admin, "医院资料", "修改", "医院资料", "修改医院信息");
			
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), "error");
		}
	}
	
	@RequestMapping("descUpdate")
	public void descUpdate(HospitalDescription desc){
		try {
			if(desc == null){
				JsonUtils.render(getResponse(), "paramsError");
				return;
			}
			if(desc.getId() != null){
				HospitalDescription targetDesc = hospitalDescriptionServiceImpl.get(desc.getId());
				BeanUtils.copy(desc, targetDesc);
				hospitalDescriptionServiceImpl.save(targetDesc);
				JsonUtils.render(getResponse(), "success");
				
				//添加日志===>修改科室描述信息
				Admin admin = getLoginAdminInfo();
				monitorOperateLogServices.addOperateLog(admin, "医院资料", "修改", "科室描述", "修改科室描述信息");
				return;
			}else{
				desc.setAddTime(TimeUtils.getCurrentTime());
				desc.setHospitalId(getLoginAdminInfo().getHospitalInfo());
				desc.setType(0);
				hospitalDescriptionServiceImpl.save(desc);
				JsonUtils.render(getResponse(), "success");
				//添加日志===>删除科室描述信息
				Admin admin = getLoginAdminInfo();
				monitorOperateLogServices.addOperateLog(admin, "医院资料", "新增", "科室描述", "添加科室描述信息");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), "error");
		}
	}
	
	@RequestMapping("delete")
	public void deleteDesc(Integer id){
		try {
			if(id == null){
				JsonUtils.render(getResponse(), "paramsError");
			}
			hospitalDescriptionServiceImpl.delete(id);
			JsonUtils.render(getResponse(), "success");
			
			//添加日志===>删除科室描述信息
			Admin admin = getLoginAdminInfo();
			monitorOperateLogServices.addOperateLog(admin, "医院资料", "删除", "科室描述", "删除科室描述信息");
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), "error");
		}
	}
	
	@RequestMapping("find")
	public void findHospital(String q) throws UnsupportedEncodingException{
		String queryParam = new String(q.getBytes("iso8859-1"),"UTF-8");
		List<VoHospitalAutoComplete> list = hospitalInfoServiceImpl.findAutoCompleteList(queryParam);
		if(ArrayUtils.isNotEmpty(list)){
			JsonUtils.render(getResponse(), list);
		}
	}
	
	/**
	 * 跳转体重管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping("weight")
	public String hospitalWeightService(ModelMap model){
		Admin admin = getLoginAdminInfo();
		HospitalInfo info = admin.getHospitalInfo();
		model.put("isWeight", info.getIsWeight());
		pageLocation(model, Consts.HOSPITAL_MODULE, Consts.HOSPITAL_MENU_SERVICE, Consts.HOSPITAL_SUBMENU_WEIGHT);
		return "hospital/weightService";
	}
	
	/**
	 * 体重管理服务的开通与关闭
	 */
	@RequestMapping("weightOperate")
	public void oeprateWeight(Integer state){
		try {
			if(state == null){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return ;
			}
			Admin admin = getLoginAdminInfo();
			HospitalInfo info = admin.getHospitalInfo();
			if(state == 1){
				String result = hospitalInfoServiceImpl.operateWeight(state, info);
				JsonUtils.render(getResponse(), result);
			}else{
				info.setIsWeight(false);
				info.setIsBlood(false);
				hospitalInfoServiceImpl.persist(info);
				JsonUtils.render(getResponse(), Consts.SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	/**
	 * 跳转孕妇学校服务页面
	 * @param model
	 * @return
	 */
	@RequestMapping("school")
	public String hospitalSchoolService(ModelMap model){
		Admin admin = getLoginAdminInfo();
		HospitalInfo info = admin.getHospitalInfo();
		model.put("isSchool", info.getIsSchool());
		model.put("isVideo", info.getIsVideo());
		model.put("isClass", info.getIsClass());
		pageLocation(model, Consts.HOSPITAL_MODULE, Consts.HOSPITAL_MENU_SERVICE, Consts.HOSPITAL_SUBMENU_SCHOOL);
		return "hospital/schoolService";
	}
	
	/**
	 * 孕妇学校服务的开通与关闭
	 */
	@RequestMapping("schoolOperate")
	public void oeprateSchool(Integer state){
		try {
			if(state == null){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return ;
			}
			Admin admin = getLoginAdminInfo();
			HospitalInfo info = admin.getHospitalInfo();
			String result = hospitalInfoServiceImpl.operateSchool(state, info);
			JsonUtils.render(getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	/**
	 * 跳转孕妇学校服务页面
	 * @param model
	 * @return
	 */
	@RequestMapping("classroom")
	public String hospitalClassService(ModelMap model){
		Admin admin = getLoginAdminInfo();
		HospitalInfo info = admin.getHospitalInfo();
		model.put("isClass", info.getIsClass());
		pageLocation(model, Consts.HOSPITAL_MODULE, Consts.HOSPITAL_MENU_SERVICE, Consts.HOSPITAL_SUBMENU_CLASS);
		return "hospital/classService";
	}
	
	
	/**
	 * 课堂服务的开通与关闭
	 */
	@RequestMapping("classOperate")
	public void oeprateClass(Integer state){
		try {
			if(state == null){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return ;
			}
			Admin admin = getLoginAdminInfo();
			HospitalInfo info = admin.getHospitalInfo();
			String result = hospitalInfoServiceImpl.operateClass(state, info);
			JsonUtils.render(getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	/**
	 * 开通or关 闭网络诊室
	 * @param model
	 * @return
	 */
	@RequestMapping("network")
	public String hospitalNetWork(ModelMap model,HttpServletRequest request){
		try{
			//得到诊室的状态
			String stateStr=request.getParameter("isnetwork");
			Integer state=Integer.parseInt(stateStr);
			Admin admin = getLoginAdminInfo();
			//得到医院的信息
			HospitalInfo info = admin.getHospitalInfo();
			model.put("isNetwork", info==null?false:info.getIsNetwork());
			pageLocation(model, Consts.HOSPITAL_MODULE, Consts.HOSPITAL_MENU_SERVICE, Consts.HOSPITAL_SUBMENU_NETWORK_INTE);
			String result = hospitalInfoServiceImpl.networkClass(state, info);
			
			/**如果状态是 1操作成功就返回到 已开通页面*/
			if(state == 1 && Consts.SUCCESS.equals(result)){
				 List<HospitalMajorInfo>  majorInfosList=this.hospitalMajorInfoService.findHosMajors(info.getId());
				Collections.sort(majorInfosList, new ComparatorUtils()); //根据开通状态排序
				model.put("majorInfosList",majorInfosList);
				return "hospital/provisioning";
			}
			return "hospital/networkService";
		}catch (Exception e) {
			logger.error("异常信息："+e);
			return  null;
		}
	}
	/**
	 * 跳转网络诊室服务是否开通页面monitor
	 * */
	@RequestMapping(value="networkInte")
	public String networkClassServiceGet(ModelMap model,HttpServletRequest request){
		try{
			String stateStr=request.getParameter("isnetwork");
			Integer state=-1;
			if(StringHelper.isNotEmpty(stateStr)){
				state=Integer.parseInt(stateStr);
			}
			
			Admin admin = getLoginAdminInfo();
			//得到医院的信息
			HospitalInfo info = admin.getHospitalInfo();
			pageLocation(model, Consts.HOSPITAL_MODULE, Consts.HOSPITAL_MENU_SERVICE, Consts.HOSPITAL_SUBMENU_NETWORK_INTE);
			if(info.getIsNetwork()){
				List<HospitalMajorInfo> majorInfosList =null;
				//默认查询 全部科室
				if(state!=-1){
					majorInfosList=this.hospitalMajorInfoService.findHosMajorsByIsnetwork(info.getId(),state);
				}else{
					majorInfosList=this.hospitalMajorInfoService.findHosMajors(info.getId());
				}
				Collections.sort(majorInfosList, new ComparatorUtils()); //根据开通状态排序
				model.put("majorInfosList",majorInfosList);
				model.put("state",state);
				return "hospital/provisioning";
			}
			return "hospital/networkService";
		}catch (Exception e) {
			logger.error("异常信息："+e);
			return  null;
		}
		
	}
	
	/**
	 * 根据医院id or 关联科室id 查排班信息
	 * */
	@RequestMapping("findschedulingByMajorID")
	public ModelAndView findschedulingByMajorID(ModelMap model,HttpServletRequest request){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try{
			
			//得到诊室的状态
			String stateStr=request.getParameter("isnetwork");
			Integer state=Integer.parseInt(stateStr);
			Admin admin = getLoginAdminInfo();
			//得到医院的信息
			HospitalInfo info = admin.getHospitalInfo();
			model.put("isNetwork", info==null?false:info.getIsNetwork());
			pageLocation(model, Consts.HOSPITAL_MODULE, Consts.HOSPITAL_MENU_SERVICE, Consts.HOSPITAL_SUBMENU_NETWORK_INTE);
			//如果是关闭服务需要先查询是否有排班信息如果有 开放的排班信息则不可以关闭
			if(state==0){
				List<Object> list = hospitalInfoServiceImpl.findschedulingByMajorID(null,info.getId(),this.getDate());
				if(list!=null && list.size()>0){
					 dataMap.put("success", false);
					 dataMap.put("errormessage", "关闭服务失败，部分科室有未完成的排班，请先停止科室问诊工作");
				}else{
					dataMap.put("success", true);
				}
				
			}else{
				dataMap.put("success", true);
			}
		    
		    
		}catch (Exception e) {
			dataMap.put("success", false);
			dataMap.put("errormessage", "系统异常");
		}
		return new ModelAndView(new MappingJackson2JsonView(),dataMap);
	}
	
	

	/**
	 * 根据医院id,和科室id开通OR关闭 网络诊室
	 * */
	@RequestMapping("openOrCloseMajorById")
	public ModelAndView openOrCloseMajorById(HttpServletRequest request){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try{
			Admin admin = getLoginAdminInfo();
			//得到医院的信息
			HospitalInfo info = admin.getHospitalInfo();
			//得到科室id
			String idstr=request.getParameter("ids");
			String status=request.getParameter("status");//1 开通 0关闭
			Integer id=Integer.parseInt(idstr);
			Integer hospitalid= info.getId();
			if("0".equals(status)){
				List<Object> list = hospitalInfoServiceImpl.findschedulingByMajorID(id,hospitalid,this.getDate());
				if(list!=null && list.size()>0){
					 dataMap.put("success", false);
					 dataMap.put("errormessage", "当前科室有未完成的排班，关闭服务失败");
					 return new ModelAndView(new MappingJackson2JsonView(),dataMap);
				}
			}
			
			//开通操作
			String result = hospitalMajorInfoService.openMajorNetwork(id,status,hospitalid,admin.getId());
			
			if(Consts.SUCCESS.equals(result)){
				 dataMap.put("success", true);
			}else{
				 dataMap.put("success", false);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("异常信息"+e);
			dataMap.put("success", false);
		}
		return new ModelAndView(new MappingJackson2JsonView(),dataMap);
	}
	
	private String getDate() throws ParseException{
		 String nowDate=null;
		 Date date = new Date();  
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		 nowDate = sdf.format(date); 
		 
		 Calendar cal = Calendar.getInstance(); 
	     cal.setTime(sdf.parse(nowDate));  
	     cal.add(Calendar.DAY_OF_YEAR, -1);  
	     nowDate=sdf.format(cal.getTime());

		return nowDate;  
	}
	
	
	/**
	 * 开通服务和没开通服务的排序
	 * @author Administrator
	 *
	 **/
	 class ComparatorUtils implements Comparator<HospitalMajorInfo>{
		public int compare(HospitalMajorInfo o1, HospitalMajorInfo o2) {
			Integer one=o1.getIsnetwork();
			Integer two=o2.getIsnetwork();
			return one.compareTo(two);
		}
		 
	 }
	
	/**
	 * 跳转体重管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping("personalPrivacy")
	public String personalPrivacyService(ModelMap model){
		Admin admin = getLoginAdminInfo();
		HospitalInfo info = admin.getHospitalInfo();
		//model.put("isMobile", info.getIsMobile());
		pageLocation(model, Consts.HOSPITAL_MODULE, Consts.HOSPITAL_MENU_SERVICE, Consts.HOSPITAL_SUBMENU_PRIVACY);
		return "hospital/personalPrivacyService";
	}
	
	/**
	 * 个人信息隐私设置，设置是否屏蔽用户手机号码
	 */
	@RequestMapping("personPrivacyOperate")
	public void oepratePersonPrivacy(Integer state){
		try {
			if(state == null){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return ;
			}
			Admin admin = getLoginAdminInfo();
			HospitalInfo info = admin.getHospitalInfo();
			if(state == 1){
				String result = hospitalInfoServiceImpl.oepratePersonPrivacy(state, info);
				JsonUtils.render(getResponse(), result);
			}else{
				//info.setIsMobile(false);
				hospitalInfoServiceImpl.persist(info);
				JsonUtils.render(getResponse(), Consts.SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
	/**
	 * 设备租赁服务开关页面
	 */
	@RequestMapping("lease")
	public String deviceLease(ModelMap model){
		Admin admin = getLoginAdminInfo();
		HospitalInfo info = admin.getHospitalInfo();
		model.put("isLease", info.getIsLease());
		pageLocation(model, Consts.HOSPITAL_MODULE, Consts.HOSPITAL_MENU_SERVICE, Consts.HOSPITAL_SUBMENU_LEASE);
		return "hospital/leaseService";
	}
	
	/**
	 * 设备租赁服服务的开通与关闭
	 */
	@RequestMapping("leaseOperate")
	public void leaseOperate(Integer state){
		try {
			if(state == null){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return ;
			}
			Admin admin = getLoginAdminInfo();
			HospitalInfo info = admin.getHospitalInfo();
			String result = hospitalInfoServiceImpl.operateLease(state, info);
			JsonUtils.render(getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	@RequestMapping("doctorNotice")
	public String doctorNotice(){
		return "hospital/doctorNotice";
	}
	@RequestMapping("getAllDoctor")
	@ResponseBody
	public ReturnMsg<List<HospitalDoctorNotice>> getAllDoctor(String name){
		Admin admin = getLoginAdminInfo();
		Integer hospitalId = admin.getHospitalInfo().getId();
		List<Admin> doctors = new ArrayList<Admin>();
		List<HospitalDoctorNotice> doctorNotices = hospitalDoctorNoticeService.findAll(null,hospitalId);
		if(StringUtils.isBlank(name)){
			doctors = adminService.findAllDoctor(hospitalId);
		}else{
			doctors = adminService.findDoctorByName(name,hospitalId);
		}
		if(doctors == null || doctors.size() == 0){
			return new ReturnMsg<List<HospitalDoctorNotice>>(ReturnMsg.SUCCESS,null,null);
		}
		List<HospitalDoctorNotice> doctorNotice = new ArrayList<HospitalDoctorNotice>();
		for(Admin h:doctors){
			HospitalDoctorNotice hd = new HospitalDoctorNotice();
			hd.setName(h.getName());
			hd.setPhone(h.getMobile());
			hd.setUsername(h.getUsername());
			hd.setStatus(1);
			if(hospitalDoctorTitleService.findById(h.getTitleid())==null){
				hd.setTitleId(null);
			}else{
				hd.setTitleId(hospitalDoctorTitleService.findById(h.getTitleid()).getName());
			}
			if(doctorNotices != null){		//判断医生是否在已有的电话名单中，若在的话给id标记为1；
				Boolean flag = isContain(h.getUsername(),doctorNotices);
				if(flag){
					hd.setId(1);
				}
			}
			doctorNotice.add(hd);
		}
		return new ReturnMsg<List<HospitalDoctorNotice>>(ReturnMsg.SUCCESS,doctorNotice);
	}
	
	public Boolean isContain(String username,List<HospitalDoctorNotice> hs){
		for(HospitalDoctorNotice h:hs){
			if(h.getUsername().equals(username)){
				return true;
			}
		}
		return false;
	}
	
	@RequestMapping("getDoctorNotice")
	@ResponseBody
	public ReturnMsg<List<HospitalDoctorNotice>> getDoctorNotice(String flag){
		Admin admin = getLoginAdminInfo();
		Integer hospitalId = admin.getHospitalInfo().getId();
		//若点击弹框取消或关掉时执行
		if(StringUtils.isBlank(flag)){
			hospitalDoctorNoticeService.deleteDoctor(1,hospitalId);
			hospitalDoctorNoticeService.updateStatusByNewStatus(0, 2, hospitalId);
		}
		//加载状态不等于0的数据
		List<HospitalDoctorNotice> doctorNotice =  hospitalDoctorNoticeService.findAllisnotZero(0,hospitalId);
		return new ReturnMsg<List<HospitalDoctorNotice>>(ReturnMsg.SUCCESS,doctorNotice);
	}
	@RequestMapping("getDoctorNoticeByStatus")
	@ResponseBody
	public ReturnMsg<List<HospitalDoctorNotice>> getDoctorNoticeByStatus(Integer status){
		Admin admin = getLoginAdminInfo();
		Integer hospitalId = admin.getHospitalInfo().getId();
		hospitalDoctorNoticeService.deleteDoctor(1,hospitalId);
		hospitalDoctorNoticeService.updateStatusByNewStatus(0, 2, hospitalId);
		List<HospitalDoctorNotice> doctorNotice =  hospitalDoctorNoticeService.findAll(status,hospitalId);
		return new ReturnMsg<List<HospitalDoctorNotice>>(ReturnMsg.SUCCESS,doctorNotice);
	}
	
	@RequestMapping("deleteDoctorNoticeByUsername")
	@ResponseBody
	public ReturnMsg<String> deleteDoctorNoticeByUsername(String username){
		Admin admin = getLoginAdminInfo();
		Integer hospitalId = admin.getHospitalInfo().getId();
		if(StringUtils.isBlank(username)){
			return new ReturnMsg<String>(ReturnMsg.FAIL,"username is none!",null);
		}
		HospitalDoctorNotice hospitalDoctorNotice = hospitalDoctorNoticeService.getHospitalDoctorNotice(username, hospitalId);
		if(hospitalDoctorNotice == null){
			return new ReturnMsg<String>(ReturnMsg.FAIL,"记录不存在",null);
		}
		if(hospitalDoctorNotice.getStatus() == 1){
			hospitalDoctorNoticeService.deleteDoctorByPhone(username,hospitalId);
		}else{
			hospitalDoctorNoticeService.updateStatusByUsername(username, 0, hospitalId);
		}
		
		//添加日志===> 修改短信通知名单
		monitorOperateLogServices.addOperateLog(admin, "服务设置", "修改", "胎心预警", "短信通知名单");
		return new ReturnMsg<String>(ReturnMsg.SUCCESS,"删除成功",null);
	}
	
	@RequestMapping("addDoctorNotice")
	@ResponseBody
	public ReturnMsg<String> addDoctorNotice(HospitalDoctorNotice hospitalDoctorNotice){
		Admin admin = getLoginAdminInfo();
		Integer hospitalId = admin.getHospitalInfo().getId();
		if(StringUtils.isBlank(hospitalDoctorNotice.getName())){
			return new ReturnMsg<String>(ReturnMsg.FAIL,"name is none!",null);
		}
		if(StringUtils.isBlank(hospitalDoctorNotice.getPhone())){
			return new ReturnMsg<String>(ReturnMsg.FAIL,"phone is none!",null);
		}
		if(StringUtils.isBlank(hospitalDoctorNotice.getTitleId())){
			return new ReturnMsg<String>(ReturnMsg.FAIL,"title is none!",null);
		}
		if(StringUtils.isBlank(hospitalDoctorNotice.getUsername())){
			return new ReturnMsg<String>(ReturnMsg.FAIL,"username is none!",null);
		}
		hospitalDoctorNotice.setStatus(1);
		hospitalDoctorNotice.setHospitalId(hospitalId);
		hospitalDoctorNoticeService.save(hospitalDoctorNotice);
		//添加日志===> 修改短信通知名单
		monitorOperateLogServices.addOperateLog(admin, "服务设置", "修改", "胎心预警", "短信通知名单");
		return new ReturnMsg<String>(ReturnMsg.SUCCESS,"添加成功",null);
	}
	
	@RequestMapping("updateDoctorNoticeStatus")
	@ResponseBody
	public ReturnMsg<String> updateDoctorNoticeStatus(){
		Admin admin = getLoginAdminInfo();
		Integer hospitalId = admin.getHospitalInfo().getId();
		hospitalDoctorNoticeService.deleteDoctor(0, hospitalId);
		hospitalDoctorNoticeService.updateStatus(2,hospitalId);
		return new ReturnMsg<String>(ReturnMsg.SUCCESS,null,null);
	}
	
	 /**
	  * 查询、发送短信接口的方法
	  * @param request
	  * @return
	  */
	@RequestMapping("getDoctorNoticeInfo")
	@ResponseBody
	public ReturnMsg<List<HospitalDoctorNotice>> getDoctorNoticeInfo(HttpServletRequest request, Integer userId,
			Integer hospitalId) {
		// 判断用户id不为空
		if (userId == null) {
			return new ReturnMsg<List<HospitalDoctorNotice>>(ReturnMsg.FAIL, "用户id不能为空");
		} else {
			// 判断医院id不为空
			if (hospitalId == null) {
				return new ReturnMsg<List<HospitalDoctorNotice>>(ReturnMsg.FAIL, "医院id不能为空");
			} else {
				// 判断用户是否存在
				UserInfo userInfo = userInfoService.findUserInfoById(userId);
				if (userInfo == null) {
					return new ReturnMsg<List<HospitalDoctorNotice>>(ReturnMsg.FAIL, "该用户id未查到数据");
				} else {
					// 判断医院是否存在
					List<HospitalDoctorNotice> doctorNotice = hospitalDoctorNoticeService.findAll(null, hospitalId);
					if (null == doctorNotice || doctorNotice.size() == 0) {
						return new ReturnMsg<List<HospitalDoctorNotice>>(ReturnMsg.FAIL, "该医院id未查到数据");
					} else {
						// 判断该医院是否开通短信通知
						MonitorHospital monitorHospital = monitorHospitalServiceImpl.findMonitorHospitalByHospitalId(hospitalId);
						//根据用户id查询医院name
						HospitalInfo hospitalInfo= hospitalInfoServiceImpl.get(hospitalId);
						Integer isOpen = monitorHospital.getOpenMsg();
						if (isOpen == 0) {
							return new ReturnMsg<List<HospitalDoctorNotice>>(ReturnMsg.FAIL, "当前医院没有开通短信通知");
						} else {
							// 用户、医院都存在并且医院开通了短信通知
							// 异常持续时间
							Integer abnormalTime = monitorHospital.getAbnormalTime();
							//查询用户的真实姓名
							UserExtraInfo userExtraInfo = userExtraInfoService.findByUserId(userId);
							String realName = userExtraInfo.getRealName();//用户真实姓名
							String phone = userExtraInfo.getContactPhone();//用户的联系方式
							String mobile = userInfo.getMobile();//用户的注册电话
							//如果用户的真实姓名和联系方式都不为空，展示真实姓名+联系方式
							if(realName != null &&  !"".equals(realName) && phone != null &&  !"".equals(phone)){
								realName = realName +",联系方式为： " +phone;
								//如果真实姓名和联系方式都为空，展示用户的注册电话
							}else if(realName == null || "".equals(realName) && phone == null || "".equals(phone)){
								realName = mobile;
								//如果真实名或者联系方式有一个为空展示真实姓名或联系方式
							}else if(realName == null || "".equals(realName) || phone == null || "".equals(phone)){
								realName = realName + phone +",";
							
							}
							String content = "您好，当前胎心监护的病人：" + realName + "持续" + abnormalTime + "分钟胎心异常，请您重点关注处理";// 短信内容
							for (HospitalDoctorNotice hospitalDoctorNotice : doctorNotice) {
								
								boolean b = HBSmsCodeUtils.sendSmsMsgCommon(content, 
										hospitalDoctorNotice.getPhone(), 
										hospitalInfo.getName(), hospitalId);
								logger.info("请求参数："+content+hospitalDoctorNotice.getPhone()+hospitalInfo.getName()+hospitalId);
								if(b == true){
									logger.info("请求参数："+content+hospitalDoctorNotice.getPhone()+hospitalInfo.getName()+hospitalId+"发送成功！");
								}else if(b == false){
									logger.info("请求参数："+content+hospitalDoctorNotice.getPhone()+hospitalInfo.getName()+hospitalId+"发送失败！");
								}
							}
							return new ReturnMsg<List<HospitalDoctorNotice>>(ReturnMsg.SUCCESS, doctorNotice);
						}
					}
				}
			}
		}
	}
}
