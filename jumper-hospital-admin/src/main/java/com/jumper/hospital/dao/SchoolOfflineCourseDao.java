package com.jumper.hospital.dao;
/**
 * 线下课程Dao
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.SchoolOfflineCourse;

public interface SchoolOfflineCourseDao extends BaseDao<SchoolOfflineCourse, Integer> {

	/**
	 * 查询该医院的线下课程库
	 * @param hospitalId 医院ID
	 * @param page 分页对象
	 * @param searchKey 查找条件
	 * @return page 分页信息
	 */
	public Page<SchoolOfflineCourse> getCoursesByHospital(Integer hospitalId, Page<SchoolOfflineCourse> page, String searchKey);
	/**
	 * 查询课程信息
	 * @param hospitalId 医院ID
	 * @param searchKey 关键字
	 * @return
	 */
	public List<SchoolOfflineCourse> searchCourse(Integer hospitalId, String searchKey);
	/**
	 * 通过
	 * @param hospitalId
	 * @return
	 */
	public SchoolOfflineCourse getCourseList(Integer hospitalId);
	/**
	 * 通过课程名称和医院Id来查询课程对象是否存在
	 * @param courseName
	 * @param hospitalId
	 * @param courseId 
	 * @return
	 */
	public SchoolOfflineCourse getCourseByCourseName(String courseName,
			Integer hospitalId, Integer courseId);
}
