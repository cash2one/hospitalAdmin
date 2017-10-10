package com.jumper.hospital.dao;
/**
 * 课程表详情Dao
 * @author rent
 * @date 2016-01-25
 */
import java.util.Date;
import java.util.List;

import com.jumper.hospital.entity.SchoolCourseDetail;
import com.jumper.hospital.enums.SchoolCourseState;
import com.jumper.hospital.vo.school.VoDetailCountResult;

public interface SchoolCourseDetailDao extends BaseDao<SchoolCourseDetail, Integer> {

	/**
	 * 获取医院当前月课程表信息
	 * @param hospitalId 医院ID
	 * @param monthStart 开始时间
	 * @param monthEnd 结束时间
	 * @return List<VoDetailCountResult>
	 */
	public List<VoDetailCountResult> findCourseDetailByMonth(Integer hospitalId, Date monthStart, Date monthEnd);
	/**
	 * 查询一个月总的课程表课程量
	 * @param hospitalId 医院ID
	 * @param monthStart 开始时间
	 * @param monthEnd 结束时间
	 * @return Integer 课程总量
	 */
	public Integer findMonthCourseDetail(Integer hospitalId, Date monthStart, Date monthEnd);
	/**
	 * 查询某一天的某家医院课程安排详情
	 * @param hospitalId 医院ID
	 * @param date 时间参数
	 * @param state 课程状态
	 * @param key 关键字搜索
	 * @return
	 */
	public List<SchoolCourseDetail> findCoursePlanByDateAndHospital(Integer hospitalId, Date date, SchoolCourseState state, String key);
	/**
	 * 判断医生在时间上是否会出现冲突
	 * @param hospitalId
	 * @param courseDoctor
	 * @param date
	 * @return
	 */
	public List<SchoolCourseDetail> getCourseByDoctor(Integer hospitalId,
			String courseDoctor, String date);
	/**
	 * 检测课程是否已被安排
	 * @param courseId
	 * @return
	 */
	public boolean checkCourseHasPlan(Integer courseId);
	/**
	 * 检测是否有可用与更新的课程，避免每次只需update语句
	 * @return
	 */
	public boolean checkHasOutDateRecordToChange();
	/**
	 * 更新已过期课程安排
	 * @return Integer 受影响行数
	 */
	public Integer changeOutDateRecord();
	/**
	 * 导出PDF数据(课程为预约中或已过期)
	 * @param hospitalId 医院ID
	 * @param date 时间参数
	 * @return
	 */
	public List<SchoolCourseDetail> findCoursePlanForPDF(Integer hospitalId, Date date);
}
