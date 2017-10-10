package com.jumper.hospital.dao;
/**
 * 线上课程签到Dao
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.SchoolOnlineSign;

public interface SchoolOnlineSignDao extends BaseDao<SchoolOnlineSign, Integer> {

	/**
	 * 根据课程ID获取签到总数
	 * @param courseId
	 * @return
	 */
	public Integer getSignCountByCourseId(Integer courseId);
	/**
	 * 查找课程的签到列表信息
	 * @param courseId
	 * @return
	 */
	public Page<SchoolOnlineSign> findCourseSignList(Page<SchoolOnlineSign> page, Integer courseId, String key);
	/**
	 * 查找课程的签到列表信息 - 不分页
	 * @param courseId
	 * @return
	 */
	public List<SchoolOnlineSign> getCourseSignList(Integer courseId);
}
