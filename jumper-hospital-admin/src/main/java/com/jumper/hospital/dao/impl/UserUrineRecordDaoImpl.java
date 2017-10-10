package com.jumper.hospital.dao.impl;

import java.sql.Timestamp;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.UserUrineRecordDao;
import com.jumper.hospital.entity.UserUrineRecord;

@Repository
public class UserUrineRecordDaoImpl extends BaseDaoImpl<UserUrineRecord, Integer> implements UserUrineRecordDao {

	@Override
	public Page<UserUrineRecord> getUrineByConditionWithPage( UserUrineRecord urine, Page<UserUrineRecord> page,
			Timestamp staTime, Timestamp endTime) {
		if(urine != null){
			Criteria c = createCriteria();
			c.add(Restrictions.eq("userInfo", urine.getUserInfo()));
			if(null != staTime && null != endTime){
				c.add(Restrictions.between("add_time", staTime, endTime));
			}
			if(null == staTime &&  null != endTime ){
				c.add(Restrictions.le("add_time", endTime));
			}
			if(null != staTime && null== endTime){
				c.add(Restrictions.ge("add_time", staTime));
			}
			c.addOrder(Order.desc("add_time"));
			return findPageByCriteria(page, c);	
		}
		return null;
	}

}
