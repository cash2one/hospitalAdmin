package com.jumper.hospital.service.impl;
/**
 * 课程表详情Service实现类
 * @author rent
 * @date 2016-01-25
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.SchoolCourseAppointDao;
import com.jumper.hospital.dao.SchoolCourseDetailDao;
import com.jumper.hospital.dao.SchoolOfflineCourseDao;
import com.jumper.hospital.draw.GenerateCourseListPDF;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.SchoolCourseDetail;
import com.jumper.hospital.entity.SchoolOfflineCourse;
import com.jumper.hospital.enums.SchoolCourseState;
import com.jumper.hospital.service.SchoolCourseDetailService;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.utils.BeanUtils;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.QRCodeUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.school.VoDateCoursePlanData;
import com.jumper.hospital.vo.school.VoDetailCountResult;
import com.jumper.hospital.vo.school.VoMonthAppointResult;
import com.jumper.hospital.vo.school.VoMonthSchedule;

@Service
public class SchoolCourseDetailServiceImpl extends BaseServiceImpl<SchoolCourseDetail, Integer> implements SchoolCourseDetailService {

	private static final Logger logger = Logger.getLogger(SchoolCourseDetailServiceImpl.class);
	
	@Autowired
	private SchoolCourseDetailDao schoolCourseDetailDaoImpl;
	@Autowired
	private SchoolCourseAppointDao schoolCourseAppointDaoImpl;
	@Autowired
	private SchoolOfflineCourseDao schoolOfflineCourseDaoImpl;
	
	@Override
	public BaseDao<SchoolCourseDetail, Integer> getBaseDAO() {
		return schoolCourseDetailDaoImpl;
	}

	@Override
	public List<VoMonthSchedule> findCourseDetailByMonth(Integer hospitalId, String date) {
		try {
			Date monthStart = TimeUtils.getMonthStartTime(date);
			Date monthEnd = TimeUtils.getMonthEndTime(date);
			List<VoDetailCountResult> list = schoolCourseDetailDaoImpl.findCourseDetailByMonth(hospitalId, monthStart, monthEnd);
			List<VoMonthAppointResult> appointList = schoolCourseAppointDaoImpl.findAppointSignResult(hospitalId, monthStart, monthEnd);
			/** 查询数据结果处理 **/
			List<VoMonthSchedule> dataList = new ArrayList<VoMonthSchedule>();
			if(ArrayUtils.isNotEmpty(list)){
				for(VoDetailCountResult detail : list){
					VoMonthSchedule schedle = new VoMonthSchedule();
					schedle.setDay(TimeUtils.getDayByDate(detail.getDate()));
					schedle.setCourseCount(detail.getCount());
					boolean flag = false;
					if(ArrayUtils.isNotEmpty(appointList)){
						for(VoMonthAppointResult appoint : appointList){
							if(detail.getDate().equals(appoint.getDate())){
								flag = true;
								schedle.setAppointCount(appoint.getAppoint() == null ? 0 : appoint.getAppoint());
								schedle.setSignCount(appoint.getSign() == null ? 0 : appoint.getSign());
							}
						}
					}
					/** 如果没有预约及签到人数补0 **/
					if(!flag){
						schedle.setAppointCount(0);
						schedle.setSignCount(0);
					}
					dataList.add(schedle);
				}
			}
			return dataList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("获取医院课程表信息出错！错误原因:"+e.getMessage()+",错误医院:"+hospitalId);
		}
		return null;
	}

	@Override
	public VoMonthSchedule findMonthCountDetail(Integer hospitalId, String date) {
		try {
			Date monthStart = TimeUtils.getMonthStartTime(date);
			Date monthEnd = TimeUtils.getMonthEndTime(date);
			Integer courseCount = schoolCourseDetailDaoImpl.findMonthCourseDetail(hospitalId, monthStart, monthEnd);
			VoMonthAppointResult appointData = schoolCourseAppointDaoImpl.findMonthAppointSign(hospitalId, monthStart, monthEnd);
			VoMonthSchedule vms = new VoMonthSchedule();
			if(courseCount != null){
				vms.setCourseCount(courseCount);
				vms.setDay(TimeUtils.getMonthByDate(monthStart));
				if(appointData != null){
					vms.setAppointCount(appointData.getAppoint() == null ? 0 : appointData.getAppoint());
					vms.setSignCount(appointData.getSign() == null ? 0 : appointData.getSign());
				}else{
					vms.setAppointCount(0);
					vms.setSignCount(0);
				}
			}
			return vms;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询一个月的课程表统计总量出错！错误原因:"+e.getMessage()+",错误医院:"+hospitalId);
		}
		return null;
	}

	@Override
	public List<VoDateCoursePlanData> findTodayCourseList(Integer hospitalId, String date, SchoolCourseState state, String key) {
		try {
			Date searchDay = TimeUtils.getTimestampDate(date, Consts.FORMAT_TIME_THREE);
			List<SchoolCourseDetail> list = schoolCourseDetailDaoImpl.findCoursePlanByDateAndHospital(hospitalId, searchDay, state, key);
			List<VoDateCoursePlanData> dataList = convertCoursePlan(list, hospitalId, date);
			return dataList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询某个日期的课程表安排列表出错！错误原因:"+e.getMessage()+",错误医院"+hospitalId);
		}
		return null;
	}

	/**
	 * vo转换
	 * @param list
	 * @param hospitalId
	 * @param date
	 * @return
	 */
	public List<VoDateCoursePlanData> convertCoursePlan(List<SchoolCourseDetail> list, Integer hospitalId, String date){
		List<VoDateCoursePlanData> dataList = new ArrayList<VoDateCoursePlanData>();
		if(ArrayUtils.isNotEmpty(list)){
			for(SchoolCourseDetail detail : list){
				if(detail != null){
					VoDateCoursePlanData vo = new VoDateCoursePlanData();
					BeanUtils.copy(detail, vo);
					SchoolOfflineCourse course = schoolOfflineCourseDaoImpl.get(detail.getCourseId());
					if(course != null){
						vo.setAppointTime(TimeUtils.convertTime(detail.getCourseDate(), Consts.FORMAT_TIME_THREE)+" "+detail.getStartTime());
						vo.setAppointCount(course.getAppointCount());
						/** 获取预约及签到数量 **/
						VoMonthAppointResult result = schoolCourseAppointDaoImpl.findAppointSignByCourseId(hospitalId, detail.getId());
						if(result != null){
							vo.setHasAppoint(result.getAppoint() == null ? 0 : result.getAppoint());
							vo.setHasSign(result.getSign() == null ? 0 : result.getSign());
						}else{
							vo.setHasAppoint(0);
							vo.setHasSign(0);
						}
					
					}
					dataList.add(vo);
				}
			}
		}
		return dataList;
	}

	@Override
	public String addCourseDetail(Integer hospitalId, Integer courseId, String date, String startTime, String endTime) {
		try {
			if(StringUtils.isEmpty(date) || StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime) || courseId == null){
				return Consts.PARAMS_ERROR;
			}
			SchoolOfflineCourse course = schoolOfflineCourseDaoImpl.get(courseId);
			if(course == null){
				return Consts.FAILED;
			}
			//判断添加相同课程的课程表，时间上是否会出现冲突(判断条件：医院的id,老师的姓名，课程日期)
			
			List<SchoolCourseDetail> list=schoolCourseDetailDaoImpl.getCourseByDoctor(hospitalId,course.getCourseDoctor(),date);
		    if(list!=null&&list.size()>0){
			 for(SchoolCourseDetail detail:list){
				Long startTime1=TimeUtils.parseDate(startTime, "HH:mm");
				Long endTime1=TimeUtils.parseDate(endTime, "HH:mm");
				Long startTime2=TimeUtils.parseDate(detail.getStartTime(), "HH:mm");
				Long endTime2=TimeUtils.parseDate(detail.getEndTime(), "HH:mm");
				 //起始时间或结束时间在之前的时间段之内
				if((startTime2<=startTime1&&startTime1<=endTime2)||(startTime2<=endTime1&&endTime1<=endTime2||(startTime1<=startTime2&&endTime1>=endTime2))){
					return Consts.TIME_OVERLAP;
				}
			  }
		    }
		    
			SchoolCourseDetail detail = new SchoolCourseDetail();
			detail.setCourseNo(course.getCourseNo());
			detail.setAddTime(TimeUtils.getCurrentTime());
			detail.setAppointCount(course.getAppointCount());
			detail.setCourseCost(course.getCourseCost());
			detail.setCourseDate(TimeUtils.getTimestampDate(date, Consts.FORMAT_TIME_THREE));
			detail.setCourseDoctor(course.getCourseDoctor());
			detail.setCourseId(courseId);
			detail.setCourseName(course.getCourseName());
			detail.setCourseState(SchoolCourseState.预约中);
			detail.setHospitalId(hospitalId);
			detail.setEndTime(endTime);
			detail.setStartTime(startTime);
			detail.setCourseQrUrl("");
			SchoolCourseDetail data = schoolCourseDetailDaoImpl.persist(detail);
			
			String QRCodePath = QRCodeUtils.CreateQrCode(Consts.COURSE_QR_URL+data.getId());
			data.setCourseQrUrl(QRCodePath);
			schoolCourseDetailDaoImpl.save(data);
			return Consts.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("添加课程表课程安排错误！错误原因:"+e.getMessage()+",错误医院:"+hospitalId);
			return Consts.ERROR;
		}
	}

	@Override
	public String courseCancel(Integer id) {
		try {
			if(id == null || id == 0){
				return Consts.PARAMS_ERROR;
			}
			SchoolCourseDetail detail = schoolCourseDetailDaoImpl.get(id);
			if(detail == null){
				return Consts.FAILED;
			}
			detail.setCourseState(SchoolCourseState.已取消);
			SchoolCourseDetail result = schoolCourseDetailDaoImpl.save(detail);
			if(result != null){
				/** 这里修改预约状态 **/
				Integer count = schoolCourseAppointDaoImpl.updateAppointByCourseDetail(id);
				if(count > 0){
					return Consts.SUCCESS;
				}
			}
			return Consts.FAILED;
		} catch (Exception e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}

	@Override
	public String findCoursePlanByDayForPDF(HttpServletRequest request, HospitalInfo hospital, String date) {
		try {
			Date searchDay = TimeUtils.getTimestampDate(date, Consts.FORMAT_TIME_THREE);
			List<SchoolCourseDetail> list = schoolCourseDetailDaoImpl.findCoursePlanForPDF(hospital.getId(), searchDay);
			if(ArrayUtils.isNotEmpty(list)){
				String filePath = GenerateCourseListPDF.generatePDF(request, hospital.getName(), searchDay, list);
				return filePath;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean checkCourseHasPlan(Integer courseId) {
		return schoolCourseDetailDaoImpl.checkCourseHasPlan(courseId);
	}

	@Override
	public Integer changeOutDateRecord() {
		/** 先检测是否有过期的数据 **/
		boolean flag = schoolCourseDetailDaoImpl.checkHasOutDateRecordToChange();
		if(flag){
			return schoolCourseDetailDaoImpl.changeOutDateRecord();
		}
		return Consts.FALSE;
	}
	
	@Override
	public String deleteCourse(Integer id) {
		try {
			if(id == null || id == 0){
				return Consts.PARAMS_ERROR;
			}
			SchoolCourseDetail detail = schoolCourseDetailDaoImpl.get(id);
			if(detail == null){
				return Consts.FAILED;
			}
			
		   schoolCourseDetailDaoImpl.delete(detail);//无人预约的课程可以直接删除
			return Consts.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}
}
