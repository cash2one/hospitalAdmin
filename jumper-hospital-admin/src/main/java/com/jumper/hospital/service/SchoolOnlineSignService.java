package com.jumper.hospital.service;
/**
 * 线上课程签到Service
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.SchoolOnlineCourse;
import com.jumper.hospital.entity.SchoolOnlineSign;
import com.jumper.hospital.vo.school.VoOnlineSignData;

public interface SchoolOnlineSignService extends BaseService<SchoolOnlineSign, Integer> {

	/**
	 * 根据课程ID获取该课程的总签到数
	 * @param courseId 课程ID
	 * @return
	 */
	public Integer getSignCountByCourseId(Integer courseId);
	/**
	 * 查询网络课程签到列表
	 * @param courseId
	 * @return
	 */
	public Page<SchoolOnlineSign> findCourseSignList(Page<SchoolOnlineSign> page, Integer courseId, String key);
	/**
	 * 查询网络课程签到列表 - 不分页
	 * @param courseId
	 * @return
	 */
	public List<VoOnlineSignData> getCourseSignList(Integer courseId, SchoolOnlineCourse course);
}
