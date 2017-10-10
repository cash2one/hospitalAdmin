package com.jumper.hospital.dao;
/**
 * 线上课程Dao
 */
import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.SchoolOnlineCourse;
import com.jumper.hospital.enums.SchoolOnlineState;

public interface SchoolOnlineCourseDao extends BaseDao<SchoolOnlineCourse, Integer> {

	/**
	 * 通过ID获取网络课程库内容
	 * @param hospitalId 医院ID
	 * @param page 分页对象
	 * @param searchKey 条件查询
	 * @return 分页信息
	 */
	public Page<SchoolOnlineCourse> getCoursesByHospital(Integer hospitalId, Page<SchoolOnlineCourse> page, String searchKey, SchoolOnlineState courseState);

	/**
	 * 判断线上课程名是否同时存在
	 * @param courseName
	 * @param hospitalId
	 * @param courseId
	 * @return
	 */
	public SchoolOnlineCourse getCourseByCourseName(String courseName,
			Integer hospitalId, Integer courseId);
}
