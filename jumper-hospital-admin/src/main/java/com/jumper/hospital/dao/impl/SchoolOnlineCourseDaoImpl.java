package com.jumper.hospital.dao.impl;
/**
 * 线上课程Dao实现类
 */
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.SchoolOnlineCourseDao;
import com.jumper.hospital.entity.SchoolOnlineCourse;
import com.jumper.hospital.enums.SchoolOnlineState;

@Repository
public class SchoolOnlineCourseDaoImpl extends BaseDaoImpl<SchoolOnlineCourse, Integer> implements SchoolOnlineCourseDao {

	@Override
	public Page<SchoolOnlineCourse> getCoursesByHospital(Integer hospitalId, Page<SchoolOnlineCourse> page, String searchKey, SchoolOnlineState courseState) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", hospitalId));
		if(courseState != null){
			c.add(Restrictions.eq("courseState", courseState));
		}
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

	@Override
	public SchoolOnlineCourse getCourseByCourseName(String courseName,
			Integer hospitalId, Integer courseId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", hospitalId));
		c.add(Restrictions.eq("courseName", courseName));
		if(courseId!=null){
			c.add(Restrictions.ne("id", courseId));
		}
		c.setMaxResults(1);
		SchoolOnlineCourse course=(SchoolOnlineCourse) c.uniqueResult();
		return course;
	}

}
