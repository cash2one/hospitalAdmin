package com.jumper.hospital.dao;
/**
 * 线下课程预约信息Dao
 * @author rent
 * @date 2016-01-25
 */
import java.util.Date;
import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.SchoolCourseAppoint;
import com.jumper.hospital.entity.SchoolOfflineCourse;
import com.jumper.hospital.enums.SchoolAppointState;
import com.jumper.hospital.enums.SchoolSignState;
import com.jumper.hospital.vo.school.VoMonthAppointResult;

public interface SchoolCourseAppointDao extends BaseDao<SchoolCourseAppoint, Integer> {

	/**
	 * 查询预约及签到状态
	 * @param hospitalId 医院ID
	 * @param startTime 开始时间
	 * @param EndTime 结束时间
	 * @return
	 */
	public List<VoMonthAppointResult> findAppointSignResult(Integer hospitalId, Date startTime, Date endTime);
	/**
	 * 查询一个月的总量
	 * @param hospitalId
	 * @param startTime
	 * @param EndTime
	 * @return
	 */
	public VoMonthAppointResult findMonthAppointSign(Integer hospitalId, Date startTime, Date endTime);
	/**
	 * 查询一个课程的预约及签到量
	 * @param hospitalId
	 * @param detailId
	 * @return
	 */
	public VoMonthAppointResult findAppointSignByCourseId(Integer hospitalId, Integer detailId);
	/**
	 * 查询某个课程安排的预约详情列表
	 * @param page
	 * @param detailId
	 * @param appointState
	 * @param signState
	 * @param searchKey
	 * @return
	 */
	public Page<SchoolCourseAppoint> findAppointListByDetailId(Page<SchoolCourseAppoint> page, Integer detailId,
			SchoolAppointState appointState, SchoolSignState signState, String searchKey);
	/**
	 * 查询某个课程安排的预约详情列表 - 不分页
	 * @param detailId
	 * @return
	 */
	public List<SchoolCourseAppoint> findAppointListByDetailId(Integer detailId);
	public int bathcPay(String ids);
	/**
	 * 查看所有预约对象
	 * @param id 
	 * @return
	 */
	public List<SchoolCourseAppoint> getOppointList(Integer id);
	/**
	 * 查询出指定课程预约的人数
	 * @param courseId
	 * @return
	 */
	public Long getAppointCount(Integer courseId);
	/**
	 * 处理已过期的预约信息，检测是否已过期数据
	 * @return
	 */
	public boolean checkHasOutOfDateAppointToChange();
	/**
	 * 处理预约已过期的课程
	 * @return
	 */
	public Integer changeOutOfDateAppoint();
	/**
	 * 更新课程信息的时候将预约信息一并更新
	 * @param course
	 * @return
	 */
	public Integer updateAppointCourseInfo(SchoolOfflineCourse course);
	/**
	 * 课程取消则将预约该课程的用户状态重置为已取消
	 * @param courseDetailId
	 * @return
	 */
	public Integer updateAppointByCourseDetail(Integer courseDetailId);
	/**
	 * 查询课程的签到总量
	 * @param detailId
	 * @return
	 */
	public Long getSignCount(Integer detailId);
}
