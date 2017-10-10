package com.jumper.hospital.dao.impl;
/**
 * 线下课程Dao实现类
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.SchoolOfflineCourseDao;
import com.jumper.hospital.entity.SchoolOfflineCourse;

@Repository
public class SchoolOfflineCourseDaoImpl extends BaseDaoImpl<SchoolOfflineCourse, Integer> implements SchoolOfflineCourseDao {

	@Override
	public Page<SchoolOfflineCourse> getCoursesByHospital(Integer hospitalId, Page<SchoolOfflineCourse> page, String searchKey) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", hospitalId));
		if(StringUtils.isNotEmpty(searchKey)){
			c.add(Restrictions.or(
					Restrictions.eq("courseNo", searchKey), 
					Restrictions.like("courseName", searchKey), 
					Restrictions.eq("courseDoctor", searchKey)
			));
		}
		c.addOrder(Order.desc("id"));
		return findPageByCriteria(page, c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchoolOfflineCourse> searchCourse(Integer hospitalId, String searchKey) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", hospitalId));
		if(StringUtils.isNotEmpty(searchKey)){
			c.add(Restrictions.or(
					Restrictions.eq("courseNo", searchKey), 
					Restrictions.like("courseName", searchKey, MatchMode.ANYWHERE), 
					Restrictions.eq("courseDoctor", searchKey)
			));
		}
		c.addOrder(Order.desc("id"));
		return c.list();
	}

	@Override
	public SchoolOfflineCourse getCourseList(Integer hospitalId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", hospitalId));
		c.setMaxResults(1);
		c.addOrder(Order.desc("id"));
		return (SchoolOfflineCourse) c.uniqueResult();
	}

	@Override
	public SchoolOfflineCourse getCourseByCourseName(String courseName,
			Integer hospitalId,Integer courseId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", hospitalId));
		c.add(Restrictions.eq("courseName", courseName));
		if(courseId!=null){
			c.add(Restrictions.ne("id", courseId));
		}
		c.setMaxResults(1);
		SchoolOfflineCourse course=(SchoolOfflineCourse) c.uniqueResult();
		return course;
	}
	
	
}
