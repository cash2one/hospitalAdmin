package com.jumper.hospital.dao.impl;
/**
 * 线上课程签到Dao实现类
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.SchoolOnlineSignDao;
import com.jumper.hospital.entity.SchoolOnlineSign;

@Repository
public class SchoolOnlineSignDaoImpl extends BaseDaoImpl<SchoolOnlineSign, Integer> implements SchoolOnlineSignDao {

	@Override
	public Integer getSignCountByCourseId(Integer courseId) {
		String queryString = "select count(*) from school_online_sign s where s.sign_course_id=?";
		SQLQuery query = createSqlQuery(queryString, new Object[]{courseId});
		Number count = (Number) query.uniqueResult();
		return count.intValue();
	}

	@Override
	public Page<SchoolOnlineSign> findCourseSignList(Page<SchoolOnlineSign> page, Integer courseId, String key) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("signCourseId", courseId));
		if(StringUtils.isNotEmpty(key)){
			c.add(Restrictions.or(
				Restrictions.eq("signUserName", key),
				Restrictions.eq("signUserPhone", key)
			));
		}
		c.addOrder(Order.desc("signTime"));
		return findPageByCriteria(page, c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchoolOnlineSign> getCourseSignList(Integer courseId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("signCourseId", courseId));
		c.addOrder(Order.desc("signTime"));
		return c.list();
	}
	
}
