package com.jumper.hospital.service;
/**
 * 课程表详情Service
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.SchoolCourseDetail;
import com.jumper.hospital.enums.SchoolCourseState;
import com.jumper.hospital.vo.school.VoDateCoursePlanData;
import com.jumper.hospital.vo.school.VoMonthSchedule;

public interface SchoolCourseDetailService extends BaseService<SchoolCourseDetail, Integer> {

	/**
	 * 获取某医院当前月份的课程表信息
	 * @param hospitalId 医院ID
	 * @param date 时间
	 * @return List<VoMonthSchedule>
	 */
	public List<VoMonthSchedule> findCourseDetailByMonth(Integer hospitalId, String date);
	/**
	 * 查询一个月的总数据
	 * @param hospitalId 医院ID
	 * @param date 时间参数
	 * @return
	 */
	public VoMonthSchedule findMonthCountDetail(Integer hospitalId, String date);
	/**
	 * 查询当天课程安排列表
	 * @param hospitalId 医院ID
	 * @param date 时间参数
	 * @param state 课程状态
	 * @param key 关键字查询
	 * @return List<VoDateCoursePlanData>
	 */
	public List<VoDateCoursePlanData> findTodayCourseList(Integer hospitalId, String date, SchoolCourseState state, String key);
	/**
	 * 添加课程表课程安排
	 * @param hospitalId
	 * @param courseId
	 * @param date
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public String addCourseDetail(Integer hospitalId, Integer courseId, String date, String startTime, String endTime);
	/**
	 * 取消课程
	 * @param id
	 * @return
	 */
	public String courseCancel(Integer id);
	/**
	 * 获取某天课程生成PDF信息
	 * @param hospitalId 医院ID
	 * @param date 当天课程信息 
	 * @return PDF文件地址
	 */
	public String findCoursePlanByDayForPDF(HttpServletRequest request, HospitalInfo hospital, String date);
	/**
	 * 检测课程是否被安排，如果被安排则不能删除
	 * @param courseId 课程ID
	 * @return 
	 */
	public boolean checkCourseHasPlan(Integer courseId);
	/**
	 * 更改过期课程状态
	 * @return
	 */
	public Integer changeOutDateRecord();
	
	/**
	 * 删除课程
	 * @param id
	 * @return
	 */
	public String deleteCourse(Integer id);
}
