package com.jumper.hospital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.PregnantCourseNewsDao;
import com.jumper.hospital.entity.PregnantCourseNews;

@Repository
public class PregnantCourseNewsDaoImpl extends BaseDaoImpl<PregnantCourseNews, Integer> implements PregnantCourseNewsDao {

	@Override
	public Integer deleteByNewsId(String ids) {
		String sql = "delete from pregnant_course_news where news_id in (?)";
		SQLQuery sqlQuery = createSqlQuery(sql, ids);
		int result = sqlQuery.executeUpdate();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PregnantCourseNews> getByNewsId(Integer newsId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("newsId", newsId));
		return c.list();
	}

	@Override
	public Integer deleteByPregnantId(Integer newsId, Integer pId) {
		String sql = "delete from pregnant_course_news where news_id=? and pregnant_id=?";
		SQLQuery sqlQuery = createSqlQuery(sql, new Object[]{newsId, pId});
		int result = sqlQuery.executeUpdate();
		return result;
	}

}
