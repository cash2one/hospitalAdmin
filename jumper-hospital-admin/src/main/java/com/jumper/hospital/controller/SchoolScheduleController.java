package com.jumper.hospital.controller;
/**
 * 课程表相关业务Controller
 * @author rent
 * @date 2016-02-15
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.SchoolCourseAppoint;
import com.jumper.hospital.entity.SchoolCourseDetail;
import com.jumper.hospital.entity.SchoolOfflineCourse;
import com.jumper.hospital.entity.SchoolOnlineCourse;
import com.jumper.hospital.entity.SchoolOnlineSign;
import com.jumper.hospital.enums.SchoolAppointState;
import com.jumper.hospital.enums.SchoolCourseState;
import com.jumper.hospital.enums.SchoolOnlineState;
import com.jumper.hospital.enums.SchoolSignState;
import com.jumper.hospital.service.SchoolCourseAppointService;
import com.jumper.hospital.service.SchoolCourseDetailService;
import com.jumper.hospital.service.SchoolOfflineCourseService;
import com.jumper.hospital.service.SchoolOnlineCourseService;
import com.jumper.hospital.service.SchoolOnlineSignService;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.ExportExcelUtils;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.school.VoAppointList;
import com.jumper.hospital.vo.school.VoDateCoursePlanData;
import com.jumper.hospital.vo.school.VoMonthAppointResult;
import com.jumper.hospital.vo.school.VoMonthSchedule;
import com.jumper.hospital.vo.school.VoOnlineCourseData;
import com.jumper.hospital.vo.school.VoOnlineSignData;

@Controller
@RequestMapping("schedule")
public class SchoolScheduleController extends BaseController {

	@Autowired
	private SchoolCourseDetailService schoolCourseDetailServiceImpl;
	@Autowired
	private SchoolOfflineCourseService schoolOfflineCourseServiceImpl;
	@Autowired
	private SchoolCourseAppointService schoolCourseAppointServiceImpl;
	@Autowired
	private SchoolOnlineCourseService schoolOnlineCourseServiceImpl;
	@Autowired
	private SchoolOnlineSignService schoolOnlineSignServiceImpl;
	/**
	 * 线下课程课程表
	 * @param model 
	 * @param date 时间参数
	 * @return
	 */
	@RequestMapping("offLine")
	public String offLineSchedule(ModelMap model, String date){
		Admin admin = getLoginAdminInfo();
		VoMonthSchedule monthData = schoolCourseDetailServiceImpl.findMonthCountDetail(admin.getHospitalInfo().getId(), date);//月份总量
		List<VoMonthSchedule> list = schoolCourseDetailServiceImpl.findCourseDetailByMonth(admin.getHospitalInfo().getId(), date);//月份每天的数据集
		Integer firstDay = TimeUtils.getWeekOfMonthFirstDay(date);//当前月的第一天是星期几
		Integer year = TimeUtils.getYearByDate(date);//获取当前年份
		Integer maxDay = TimeUtils.getMaxDayOfMonth(date);//获取当前月的总天数
		model.put("data", list);
		model.put("day", firstDay);
		model.put("year", year);
		model.put("maxDay", maxDay);
		model.put("monthData", monthData);
		pageLocation(model, Consts.SCHOOL_MODULE, Consts.SCHOOL_MENU_SCHEDULE, Consts.SCHOOL_SUBMENU_OFFLINE);
		return "school/schedule/offSchedule";
	}
	/**
	 * 某一天的课程表详情
	 * @param model
	 * @param date
	 * @param state
	 * @param key
	 * @return
	 */
	@RequestMapping("editCourse")
	public String editCourse(ModelMap model, String date, SchoolCourseState state, String key){
		Admin admin = getLoginAdminInfo();
		List<VoDateCoursePlanData> list = schoolCourseDetailServiceImpl.findTodayCourseList(admin.getHospitalInfo().getId(), date, state, key);
		model.put("data", list);
		model.put("dateString", date);
		model.put("date", TimeUtils.getTimestampDate(date, Consts.FORMAT_TIME_THREE));
		model.put("courseState", SchoolCourseState.values());
		model.put("key", key);
		model.put("currentState", state);
		pageLocation(model, Consts.SCHOOL_MODULE, Consts.SCHOOL_MENU_SCHEDULE, Consts.SCHOOL_SUBMENU_OFFLINE);
		return "school/schedule/editCourse";
	}
	/**
	 * 搜索课程信息
	 * @param key 关键字
	 */
	@RequestMapping("courseSearch")
	public void searchCourse(String key){
		try {
			String searchKey = new String(key.getBytes("iso-8859-1"), "UTF-8");
			Admin admin = getLoginAdminInfo();
			List<SchoolOfflineCourse> data = schoolOfflineCourseServiceImpl.searchCourse(admin.getHospitalInfo().getId(), searchKey);
			JsonUtils.render(getResponse(), data);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	/**
	 * 安排课程，此处未做时间校验
	 * @param date
	 * @param courseId
	 * @param startTime
	 * @param endTime
	 */
	@RequestMapping("addCourseDetail")
	public void addCourseDetail(String date, Integer courseId, String startTime, String endTime){
		Admin admin = getLoginAdminInfo();
		String result = schoolCourseDetailServiceImpl.addCourseDetail(admin.getHospitalInfo().getId(), courseId, date, startTime, endTime);
		JsonUtils.render(getResponse(), result);
	}
	/**
	 * 在取消课程之前通过后台来查询预约人数
	 * @param courseId
	 */
	@RequestMapping("getAppointCount")
	public void getAppointCount(Integer courseId){
		Long appointCount=schoolCourseAppointServiceImpl.getAppointCount(courseId);
		JsonUtils.render(getResponse(), appointCount);
	}
	
	/**
	 * 取消课程安排
	 * @param id
	 */
	@RequestMapping("courseCancel")
	public void courseCancel(@RequestParam(value="id",required=true)Integer id){
		String result = schoolCourseDetailServiceImpl.deleteCourse(id);
		JsonUtils.render(getResponse(), result);
	}
	/**
	 * 下载签到码
	 * @param id
	 */
	@RequestMapping("downQR")
	public void downloadQRCode(Integer id){
		if(id == null){
			return;
		}
		SchoolCourseDetail detail = schoolCourseDetailServiceImpl.get(id);
		if(detail == null){
			return;
		}
		String fileName = TimeUtils.convertTime(detail.getCourseDate(), Consts.FORMAT_TIME_THREE) + "-" + detail.getId() + "." + detail.getCourseQrUrl().split("\\.")[1];
		HttpServletResponse response = getResponse();
		response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
        ServletOutputStream out;

        try {
        	URL url = new URL(Consts.BASE_FILE_URL+detail.getCourseQrUrl());
        	URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            out = response.getOutputStream();

            int byteread = 0;
            byte[] buffer = new byte[1024];
     	   	while ((byteread = inputStream.read(buffer)) != -1) {
     	   		out.write(buffer, 0, byteread);
     	   	}
            inputStream.close();
            out.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
	 * 课程表PDF下载
	 * @param date
	 */
	@RequestMapping("downCList")
	public void downloadCourseList(String date){
		if(StringUtils.isEmpty(date)){
			return;
		}
		Admin admin = getLoginAdminInfo();
		String filePath = schoolCourseDetailServiceImpl.findCoursePlanByDayForPDF(getRequest(), admin.getHospitalInfo(), date);
		
		if(StringUtils.isNotEmpty(filePath)){
			HttpServletResponse response = getResponse();
			response.setContentType("multipart/form-data");
	        response.setHeader("Content-Disposition", "attachment;fileName="+date+".pdf");
	        ServletOutputStream out;

	        try {
	        	File file = new File(filePath);
	            InputStream inputStream = new FileInputStream(file);
	            out = response.getOutputStream();

	            int byteread = 0;
	            byte[] buffer = new byte[1024];
	     	   	while ((byteread = inputStream.read(buffer)) != -1) {
	     	   		out.write(buffer, 0, byteread);
	     	   	}
	            inputStream.close();
	            out.close();
	            out.flush();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	        	/** 最后将临时文件删除 **/
	        	File file = new File(filePath);
	        	if(file.exists()){
	        		file.delete();
	        	}
	        }
		}
	}
	/**
	 * 课程预约列表信息
	 * @param model
	 * @param detailId
	 * @param page
	 * @param appointState
	 * @param signState
	 * @param searchKey
	 * @return
	 */
	@RequestMapping("appoints")
	public String appointIndex(ModelMap model, Integer detailId, Page<SchoolCourseAppoint> page, SchoolAppointState appointState,
			SchoolSignState signState, String searchKey){
		Page<SchoolCourseAppoint> pageData = schoolCourseAppointServiceImpl.findAppointAndSignListByDetailId(page, detailId, appointState, signState, searchKey);
		SchoolCourseDetail detail = schoolCourseDetailServiceImpl.get(detailId);
		VoMonthAppointResult result = schoolCourseAppointServiceImpl.findAppointSignByCourseId(getLoginAdminInfo().getHospitalInfo().getId(), detailId);
		//查询出所有预约课程的费用
		List<SchoolCourseAppoint> appointList=page.getResult();
		List<Integer> costList=schoolCourseAppointServiceImpl.getCostList(appointList);
		model.addAttribute("costArray", costList.toArray());
		model.put("detailId", detailId);
		model.put("appointState", appointState);
		model.put("signState", signState);
		model.put("searchKey", searchKey);
		model.put("detail", detail);
		model.put("data", pageData);
		model.put("result", result);
		model.put("courseDate", TimeUtils.convertTime(detail.getCourseDate(), Consts.FORMAT_TIME_THREE)+" "+detail.getStartTime()+"-"+detail.getEndTime());
		model.put("appointStateAll", SchoolAppointState.values());
		model.put("signStateAll", SchoolSignState.values());
		pageLocation(model, Consts.SCHOOL_MODULE, Consts.SCHOOL_MENU_SCHEDULE, Consts.SCHOOL_SUBMENU_OFFLINE);
		return "school/schedule/appointList";
	}
	/**
	 * 批量缴费
	 * @param ids（多个id用逗号隔开的字符串）
	 */
	@RequestMapping("batchPay")
	public void batchPay(String ids){
		String result = schoolCourseAppointServiceImpl.batchPay(ids);
		JsonUtils.render(getResponse(), result);
	}
	/**
	 * 批量退费
	 * @param ids
	 */
	@RequestMapping("batchBack")
	public void batchBack(String ids){
		String result = schoolCourseAppointServiceImpl.batchBack(ids);
		JsonUtils.render(getResponse(), result);
	}
	/**
	 * 批量推送
	 * @param ids
	 */
	@RequestMapping("batchPush")
	public void batchPush(String ids, String content, 
			@RequestParam(value="pushType",required=false,defaultValue="1")Integer pushType,//默认短信推送
			@RequestParam(value="courseId",required=false,defaultValue="-1")Integer courseId){
		
	   String result = schoolCourseAppointServiceImpl.batchPush(content, ids, pushType);
	       //如果传入了课程 ，则说明需要取消课程操作
	 		if(result.equals(Consts.SUCCESS) && courseId != null && courseId.intValue() != -1){
	 			try{
	 				schoolCourseDetailServiceImpl.courseCancel(courseId);//取消课程（删除课程）
	 			}catch(RuntimeException e){
	 				e.printStackTrace();
	 				JsonUtils.render(getResponse(), Consts.ERROR);
	 			}
	 		}
	
		JsonUtils.render(getResponse(),  result);
	}
	/**
	 * 导出预约Excel表格
	 * @param detailId
	 */
	@RequestMapping("exportExcel")
	public void exportExcel(Integer detailId){
		SchoolCourseDetail detail = schoolCourseDetailServiceImpl.get(detailId);
		long appointCount = schoolCourseAppointServiceImpl.getAppointCount(detailId);
		long signCount = schoolCourseAppointServiceImpl.getSignCount(detailId);
		List<VoAppointList> dataList = schoolCourseAppointServiceImpl.getExportAppointData(detailId);
		dataList.add(new VoAppointList("签到总数："+signCount+"人"));
		dataList.add(new VoAppointList("预约总数："+appointCount+"人"));
		
		ExportExcelUtils<VoAppointList> eeu = new ExportExcelUtils<VoAppointList>();
		String[] headers = {"课程名称","授课医师","学员姓名","孕周","手机号码","预约时间","预约状态","签到时间","签到状态","缴费状态"};
		try {
			String filePath = getRequest().getSession().getServletContext().getRealPath("/")+"temp"+File.separator+"孕妇学校 - 预约列表.xls";
			OutputStream out = new FileOutputStream(filePath);
			eeu.exportExcel(TimeUtils.convertTime(detail.getCourseDate(), "yyyy年MM月dd日")+" - "+detail.getCourseName(), headers, dataList, out);
			out.close();
			
			if(StringUtils.isNotEmpty(filePath)){
				HttpServletResponse response = getResponse();
				response.setContentType("multipart/form-data");
		        response.setHeader("Content-Disposition", "attachment;fileName=user_appoint_list.xls");
		        ServletOutputStream sOut;

		        try {
		        	File file = new File(filePath);
		            InputStream inputStream = new FileInputStream(file);
		            sOut = response.getOutputStream();

		            int byteread = 0;
		            byte[] buffer = new byte[1024];
		     	   	while ((byteread = inputStream.read(buffer)) != -1) {
		     	   	sOut.write(buffer, 0, byteread);
		     	   	}
		            inputStream.close();
		            sOut.close();
		            sOut.flush();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } finally {
		        	/** 最后将临时文件删除 **/
		        	File file = new File(filePath);
		        	if(file.exists()){
		        		file.delete();
		        	}
		        }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 网络课程课程库首页
	 * @param model
	 * @param page
	 * @param searchKey
	 * @param state
	 * @return
	 */
	@RequestMapping("onLine")
	public String libraryIndex(ModelMap model, Page<SchoolOnlineCourse> page, String searchKey, SchoolOnlineState state){
		Admin admin = getLoginAdminInfo();
		Page<VoOnlineCourseData> pageData = schoolOnlineCourseServiceImpl.getOnlineCourseSchedule(admin.getHospitalInfo().getId(), page, searchKey, state);
		model.put("onLine", pageData);
		model.put("searchKey", searchKey);
		model.put("currentState", state);
		model.put("courseState", SchoolOnlineState.values());
		pageLocation(model, Consts.SCHOOL_MODULE, Consts.SCHOOL_MENU_SCHEDULE, Consts.SCHOOL_SUBMENU_ONLINE);
		return "school/schedule/onSchedule";
	}
	/**
	 * 网络课程上架下架操作
	 * @param courseId
	 * @param state
	 */
	@RequestMapping("operate")
	public void onLineCourseOperate(Integer courseId, Integer state){
		String result = schoolOnlineCourseServiceImpl.operateCourse(courseId, state);
		JsonUtils.render(getResponse(), result);
	}
	/**
	 * 线上课程签到详情
	 * @param page
	 * @param model
	 * @param id
	 * @param key
	 * @return
	 */
	@RequestMapping("signDetail")
	public String signDetail(Page<SchoolOnlineSign> page, ModelMap model, Integer id, String key){
		Page<SchoolOnlineSign> pageData = schoolOnlineSignServiceImpl.findCourseSignList(page, id, key);
		SchoolOnlineCourse course = schoolOnlineCourseServiceImpl.get(id);
		Integer count = schoolOnlineSignServiceImpl.getSignCountByCourseId(id);
		model.put("data", pageData);
		model.put("course", course);
		model.put("id", id);
		model.put("key", key);
		model.put("count", count);
		pageLocation(model, Consts.SCHOOL_MODULE, Consts.SCHOOL_MENU_SCHEDULE, Consts.SCHOOL_SUBMENU_ONLINE);
		return "school/schedule/onSign";
	}
	/**
	 * 导出线上课程签到excel表格
	 * @param id
	 */
	@RequestMapping("exportOnlineExcel")
	public void exportOnlineExcel(Integer id){
		SchoolOnlineCourse course = schoolOnlineCourseServiceImpl.get(id);
		Integer signCount = schoolOnlineSignServiceImpl.getSignCountByCourseId(id);
		List<VoOnlineSignData> list = schoolOnlineSignServiceImpl.getCourseSignList(id, course);
		list.add(new VoOnlineSignData("签到总数："+signCount+"人"));
		
		ExportExcelUtils<VoOnlineSignData> eeu = new ExportExcelUtils<VoOnlineSignData>();
		String[] headers = {"课程名称","授课医师","学员姓名","孕周","手机号码","签到时间"};
		try {
			String filePath = getRequest().getSession().getServletContext().getRealPath("/")+"temp"+File.separator+"孕妇学校 - 签到列表.xls";
			OutputStream out = new FileOutputStream(filePath);
			eeu.exportExcel(course.getCourseName(), headers, list, out);
			out.close();
			
			if(StringUtils.isNotEmpty(filePath)){
				HttpServletResponse response = getResponse();
				response.setContentType("multipart/form-data");
		        response.setHeader("Content-Disposition", "attachment;fileName=online_sign.xls");
		        ServletOutputStream sOut;

		        try {
		        	File file = new File(filePath);
		            InputStream inputStream = new FileInputStream(file);
		            sOut = response.getOutputStream();

		            int byteread = 0;
		            byte[] buffer = new byte[1024];
		     	   	while ((byteread = inputStream.read(buffer)) != -1) {
		     	   		sOut.write(buffer, 0, byteread);
		     	   	}
		            inputStream.close();
		            sOut.close();
		            sOut.flush();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } finally {
		        	/** 最后将临时文件删除 **/
		        	File file = new File(filePath);
		        	if(file.exists()){
		        		file.delete();
		        	}
		        }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询出课程预约的所有人的id
	 * @param id
	 */
	@RequestMapping("getOppointIds")
	public void getOppointIds(Integer id){
		
     		String ids=schoolCourseAppointServiceImpl.getOppointIds(id);
     		JsonUtils.render(getResponse(), ids);
	}
}
