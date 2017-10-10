package com.jumper.hospital.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.UserBloodFatRecordDao;
import com.jumper.hospital.entity.NewsChanels;
import com.jumper.hospital.entity.UserBloodFatRecord;
import com.jumper.hospital.entity.UserInfo;

@Repository
public class UserBloodFatRecordDaoImpl extends BaseDaoImpl<UserBloodFatRecord, Integer> implements UserBloodFatRecordDao {

	@Override
	public Page<UserBloodFatRecord> getUserBloodFatRecordWithPage( UserBloodFatRecord bloodFat, Page<UserBloodFatRecord> page,
			Timestamp startTime, Timestamp endTime) {
		if(null != bloodFat ){
			Criteria c = createCriteria();
			c.add(Restrictions.eq("userInfo", bloodFat.getUserInfo()));
			if(null != startTime && null != endTime){
				c.add(Restrictions.between("add_time", startTime, endTime));
			}
			if(null == startTime &&  null != endTime ){
				c.add(Restrictions.le("add_time", endTime));
			}
			if(null != startTime && null== endTime){
				c.add(Restrictions.ge("add_time", startTime));
			}
			c.addOrder(Order.desc("add_time"));
			return findPageByCriteria(page, c);	
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserBloodFatRecord> findBloodFatByCondition( UserBloodFatRecord BloodFat, Timestamp startTime, Timestamp endTime) {
		List<UserBloodFatRecord> list = null;
		try {
			if(null != BloodFat ){
			Criteria c = getSession().createCriteria(UserBloodFatRecord.class);   
			c.add(Restrictions.eq("userInfo", BloodFat.getUserInfo()));
			if(null != startTime && null != endTime){
				c.add(Restrictions.between("add_time", startTime, endTime));
			}
			if(null == startTime &&  null != endTime ){
				c.add(Restrictions.le("add_time", endTime));
			}
			if(null != startTime && null== endTime){
				c.add(Restrictions.ge("add_time", startTime));
			}
			c.addOrder(Order.desc("add_time"));
		    list = c.list();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return list ;
		}
		return list;
		
	}
	
	

}
