package com.jumper.hospital.service;
import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.SchoolOnlineCourse;
import com.jumper.hospital.enums.SchoolOnlineState;
import com.jumper.hospital.vo.school.VoOnlineCourseData;

public interface SchoolOnlineCourseService extends BaseService<SchoolOnlineCourse, Integer> {

	/**
	 * 通过ID获取网络课程库内容
	 * @param hospitalId 医院ID
	 * @param page 分页对象
	 * @param searchKey 条件查询
	 * @return 分页信息
	 */
	public Page<SchoolOnlineCourse> getCoursesByHospital(Integer hospitalId, Page<SchoolOnlineCourse> page, String searchKey, SchoolOnlineState courseState);
	/**
	 * 编辑网络课程
	 * @param course 数据对象
	 * @param hospitalId 医院ID
	 * @return true | false
	 */
	public boolean editOnLineCourse(SchoolOnlineCourse course, Integer hospitalId);
	/**
	 * 获取网络课程课程表信息
	 * @param hospitalId 医院ID
	 * @param page 分页信息
	 * @param searchKey 关键字
	 * @param courseState 课程状态
	 * @return
	 */
	public Page<VoOnlineCourseData> getOnlineCourseSchedule(Integer hospitalId, Page<SchoolOnlineCourse> page, String searchKey, SchoolOnlineState courseState);
	/**
	 * 操作课程
	 * @param courseId
	 * @param state
	 * @return
	 */
	public String operateCourse(Integer courseId, Integer state);
	/**
	 * 判断线上课程名是否重复存在
	 * @param courseName
	 * @param id
	 * @param courseId
	 * @return
	 */
	public SchoolOnlineCourse getCourseByCourseName(String courseName,
			Integer id, Integer courseId);
}
