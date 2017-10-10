package com.jumper.hospital.dao.impl;
/**
 * 线下课程预约信息Dao实现类
 * @author rent
 * @date 2016-01-25
 */
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.SchoolCourseAppointDao;
import com.jumper.hospital.entity.SchoolCourseAppoint;
import com.jumper.hospital.entity.SchoolOfflineCourse;
import com.jumper.hospital.enums.SchoolAppointState;
import com.jumper.hospital.enums.SchoolSignState;
import com.jumper.hospital.vo.school.VoMonthAppointResult;

@Repository
public class SchoolCourseAppointDaoImpl extends BaseDaoImpl<SchoolCourseAppoint, Integer> implements SchoolCourseAppointDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<VoMonthAppointResult> findAppointSignResult(Integer hospitalId, Date startTime, Date endTime) {
		String sqlString = "select DATE_FORMAT(app.appoint_time, '%Y-%m-%d') date, count(*) appoint, sum(app.sign_state) sign " +
				"from school_course_appoint app, school_course_detail detail where (detail.course_state=0 or detail.course_state=2) and (app.appoint_state=0 or app.appoint_state=2) and app.hospital_id=? and app.appoint_time >= ? and app.appoint_time <= ? and app.course_detail_id = detail.id " +
				"group by DATE_FORMAT(app.appoint_time, '%Y-%m-%d') order by app.add_time asc";
		SQLQuery query = createSqlQuery(sqlString, new Object[]{hospitalId, startTime, endTime});
		query.addScalar("date", StandardBasicTypes.STRING);
		query.addScalar("appoint", StandardBasicTypes.INTEGER);
		query.addScalar("sign", StandardBasicTypes.INTEGER);
		query.setResultTransformer(Transformers.aliasToBean(VoMonthAppointResult.class));
		List<VoMonthAppointResult> list = query.list();
		return list;
	}

	@Override
	public VoMonthAppointResult findMonthAppointSign(Integer hospitalId, Date startTime, Date endTime) {
		String sqlString = "select count(*) appoint, sum(sign_state) sign " +
			"from school_course_appoint app, school_course_detail detail where (detail.course_state=0 or detail.course_state=2) and (app.appoint_state=0 or app.appoint_state=2) and app.hospital_id=? and app.appoint_time >= ? and app.appoint_time <= ? and app.course_detail_id = detail.id";
		SQLQuery query = createSqlQuery(sqlString, new Object[]{hospitalId, startTime, endTime});
		query.addScalar("appoint", StandardBasicTypes.INTEGER);
		query.addScalar("sign", StandardBasicTypes.INTEGER);
		query.setResultTransformer(Transformers.aliasToBean(VoMonthAppointResult.class));
		VoMonthAppointResult data = (VoMonthAppointResult) query.uniqueResult();
		return data;
	}

	@Override
	public VoMonthAppointResult findAppointSignByCourseId(Integer hospitalId, Integer detailId) {
		String sqlString = "select count(*) appoint, sum(sign_state) sign " +
			"from school_course_appoint app where (app.appoint_state=0 or app.appoint_state=2) and app.hospital_id=? and app.course_detail_id = ?";
		SQLQuery query = createSqlQuery(sqlString, new Object[]{hospitalId, detailId});
		query.addScalar("appoint", StandardBasicTypes.INTEGER);
		query.addScalar("sign", StandardBasicTypes.INTEGER);
		query.setResultTransformer(Transformers.aliasToBean(VoMonthAppointResult.class));
		VoMonthAppointResult data = (VoMonthAppointResult) query.uniqueResult();
		return data;
	}

	@Override
	public Page<SchoolCourseAppoint> findAppointListByDetailId(Page<SchoolCourseAppoint> page, Integer detailId,
			SchoolAppointState appointState, SchoolSignState signState, String searchKey) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("courseDetailId", detailId));
		if(appointState != null){
			c.add(Restrictions.eq("appointState", appointState));
		}
		if(signState != null){
			c.add(Restrictions.eq("signState", signState));
		}
		if(StringUtils.isNotEmpty(searchKey)){
			c.add(Restrictions.or(
				Restrictions.eq("appointOrderId", searchKey),
				Restrictions.eq("appointUserName", searchKey),
				Restrictions.like("appointUserPhone", searchKey, MatchMode.START)
			));
		}
		c.addOrder(Order.desc("addTime"));
		return findPageByCriteria(page, c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchoolCourseAppoint> findAppointListByDetailId(Integer detailId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("courseDetailId", detailId));
		c.addOrder(Order.desc("addTime"));
		return c.list();
	}

	@Override
	public int bathcPay(String ids) {
		String hql="update SchoolCourseAppoint a set a.payState=1 where a.id in("+ids+")";
		Session session=this.getSession();
		Transaction trans=session.beginTransaction();
		Query queryupdate=session.createQuery(hql);
		int ret=queryupdate.executeUpdate();
		trans.commit();
		session.flush();
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchoolCourseAppoint> getOppointList(Integer id) {
	   String hql="from SchoolCourseAppoint where courseDetailId=? group by appointUserId ";
	   Query query = this.getSession().createQuery(hql);
	   query.setParameter(0, id);
	   return query.list();
	}

	@Override
	public Long getAppointCount(Integer courseId) {
		Criteria c=createCriteria();
		c.add(Restrictions.eq("courseDetailId", courseId));
		c.add(Restrictions.eq("appointState",SchoolAppointState.预约成功));
		Long count= (Long) c.setProjection(Projections.rowCount()).uniqueResult();
		return count;
	}

	@Override
	public boolean checkHasOutOfDateAppointToChange() {
		String sql = "select count(*) from school_course_appoint a, school_course_detail d where a.course_detail_id = d.id and date_add(d.course_date, interval TIME_TO_SEC(str_to_date(d.start_time,'%H:%i')) second) <= now() and a.appoint_state = 0;";
		SQLQuery query = createSqlQuery(sql, new Object[]{});
		int count = ((BigInteger) query.uniqueResult()).intValue();
		return count > 0 ? true : false;
	}
	
	@Override
	public Integer changeOutOfDateAppoint() {
		String sql = "update school_course_appoint a, school_course_detail d set a.appoint_state = 3 where a.course_detail_id = d.id and date_add(d.course_date, interval TIME_TO_SEC(str_to_date(d.start_time,'%H:%i')) second) <= now() and a.appoint_state = 0;";
		return executeNonQuerySql(sql);
	}

	@Override
	public Integer updateAppointCourseInfo(SchoolOfflineCourse course) {
		String nativeSql = "update school_course_appoint a, school_course_detail d set a.appoint_course_name = '"+course.getCourseName()+"' , a.appoint_course_doctor = '"+course.getCourseDoctor()+"' where a.course_detail_id = d.id and a.appoint_state=0 and d.course_id = "+course.getId()+";";
		return executeNonQuerySql(nativeSql);
	}

	@Override
	public Integer updateAppointByCourseDetail(Integer courseDetailId) {
		String sql = "update school_course_appoint set appoint_state = 4 where course_detail_id  = "+courseDetailId+";";
		return executeNonQuerySql(sql);
	}

	@Override
	public Long getSignCount(Integer detailId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("courseDetailId", detailId));
		c.add(Restrictions.eq("signState",SchoolSignState.已签到));
		Long count= (Long) c.setProjection(Projections.rowCount()).uniqueResult();
		return count;
	}

}
