package com.jumper.hospital.service;
/**
 * 线下课程库Service
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.SchoolOfflineCourse;

public interface SchoolOfflineCourseService extends BaseService<SchoolOfflineCourse, Integer> {

	/**
	 * 查询该医院的线下课程库
	 * @param hospitalId 医院ID
	 * @param page 分页对象
	 * @param searchKey 查找条件
	 * @return page 分页信息
	 */
	public Page<SchoolOfflineCourse> getCoursesByHospital(Integer hospitalId, Page<SchoolOfflineCourse> page, String searchKey);
	/**
	 * 添加线下课程信息
	 * @param course 
	 * @param hospitalId
	 * @return
	 */
	public boolean addOffLineCourse(SchoolOfflineCourse course, Integer hospitalId, Integer costType);
	/**
	 * 搜索课程信息
	 * @param hospitalId 医院ID
	 * @param searchKey 关键字
	 * @return
	 */
	public List<SchoolOfflineCourse> searchCourse(Integer hospitalId, String searchKey);
	/**
	 * 通过医院id来查询医院的课程列表
	 * @param id
	 * @return 
	 */
	public SchoolOfflineCourse getCourseList(Integer hospitalId);
	/**
	 * 通过课程名称和医院Id来查询课程对象是否存在
	 * @param courseName
	 * @param hospitalId
	 * @param integer 
	 * @return
	 */
	public SchoolOfflineCourse getCourseByCourseName(String courseName,
			Integer hospitalId, Integer integer);
}
