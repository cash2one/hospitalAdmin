package com.jumper.hospital.dao.impl;
/**
 * 课程表详情Dao实现类
 * @author rent
 * @date 2016-01-25
 */
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.SchoolCourseDetailDao;
import com.jumper.hospital.entity.SchoolCourseDetail;
import com.jumper.hospital.entity.SchoolOfflineCourse;
import com.jumper.hospital.enums.SchoolCourseState;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.school.VoDetailCountResult;

@Repository
public class SchoolCourseDetailDaoImpl extends BaseDaoImpl<SchoolCourseDetail, Integer> implements SchoolCourseDetailDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<VoDetailCountResult> findCourseDetailByMonth(Integer hospitalId, Date monthStart, Date monthEnd) {
		String queryString = "select DATE_FORMAT(course_date, '%Y-%m-%d') date, count(*) count from school_course_detail d " +
				"where (d.course_state = 0 or d.course_state = 2) and d.hospital_id=? and d.course_date >= ? and d.course_date <= ? group by DATE_FORMAT(course_date, '%Y-%m-%d') order by d.add_time asc";
		SQLQuery query = createSqlQuery(queryString, new Object[]{hospitalId, monthStart, monthEnd});
		query.addScalar("date", StandardBasicTypes.STRING);
		query.addScalar("count", StandardBasicTypes.INTEGER);
		query.setResultTransformer(Transformers.aliasToBean(VoDetailCountResult.class));
		List<VoDetailCountResult> list = query.list();
		return list;
	}

	@Override
	public Integer findMonthCourseDetail(Integer hospitalId, Date monthStart, Date monthEnd) {
		String queryString = "select count(*) from school_course_detail d " +
			"where (d.course_state = 0 or d.course_state = 2) and d.hospital_id=? and d.course_date >= ? and d.course_date <= ?";
		SQLQuery query = createSqlQuery(queryString, new Object[]{hospitalId, monthStart, monthEnd});
		Number count = (Number) query.uniqueResult();
		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchoolCourseDetail> findCoursePlanByDateAndHospital(Integer hospitalId, Date date, SchoolCourseState state, String key) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", hospitalId));
		c.add(Restrictions.eq("courseDate", date));
		if(state != null){
			c.add(Restrictions.eq("courseState", state));
		}
		if(StringUtils.isNotEmpty(key)){
			c.add(Restrictions.or(
				Restrictions.eq("courseNo", key), 
				Restrictions.like("courseName", key, MatchMode.ANYWHERE), 
				Restrictions.eq("courseDoctor", key)
			));
		}
		c.addOrder(Order.asc("startTime"));
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchoolCourseDetail> getCourseByDoctor(Integer hospitalId,
			String courseDoctor, String date) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("courseDate", TimeUtils.getTimestampDate(date, Consts.FORMAT_TIME_THREE)));
		c.add(Restrictions.eq("courseDoctor", courseDoctor));
		c.add(Restrictions.eq("hospitalId", hospitalId));
		c.add(Restrictions.eq("courseState",SchoolCourseState.预约中));//新添加课程，医生的课程不能与预约中的课程重复
		return c.list();
	}

	@Override
	public boolean checkCourseHasPlan(Integer courseId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("courseId", courseId));
		c.add(Restrictions.eq("courseState", SchoolCourseState.预约中));
		c.setProjection(Projections.rowCount());
		long count = (Long) c.uniqueResult();
		if(count > 0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean checkHasOutDateRecordToChange() {
		String sql = "select count(*) from school_course_detail where course_state = 0 and date_add(course_date, interval TIME_TO_SEC(str_to_date(start_time,'%H:%i')) second) <= now()";
		SQLQuery query = createSqlQuery(sql, new Object[]{});
		int count = ((BigInteger) query.uniqueResult()).intValue();
		return count > 0 ? true : false;
	}
	
	public Integer changeOutDateRecord(){
		String sql = "update school_course_detail set course_state = 2 where course_state = 0 and date_add(course_date, interval TIME_TO_SEC(str_to_date(start_time,'%H:%i')) second) <= now()";
		return executeNonQuerySql(sql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchoolCourseDetail> findCoursePlanForPDF(Integer hospitalId, Date date) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", hospitalId));
		c.add(Restrictions.eq("courseDate", date));
		c.add(Restrictions.or(Restrictions.eq("courseState", SchoolCourseState.预约中), Restrictions.eq("courseState", SchoolCourseState.已过期)));
		c.addOrder(Order.asc("startTime"));
		return c.list();
	}

	public void updateSchoolCourseDetail(SchoolOfflineCourse so) throws UnsupportedEncodingException {
		String sql="update school_course_detail  set course_name='"+so.getCourseName()+"' ,  course_doctor='"+so.getCourseDoctor()+"' where course_id="+so.getId()+"";
		executeNonQuerySql(sql);	
	}

}
