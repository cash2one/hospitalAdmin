package com.jumper.hospital.dao.impl;

import java.sql.Timestamp;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.UserEcgRecordDao;
import com.jumper.hospital.entity.UserEcgRecord;

@Repository
public class UserEcgRecordDaoImpl extends BaseDaoImpl<UserEcgRecord,Integer> implements UserEcgRecordDao{

	@Override
	public Page<UserEcgRecord> getEcgByConditionWithPage(UserEcgRecord ecg, Page<UserEcgRecord> page, Timestamp staTime, Timestamp endTime) {
		if(ecg != null){
			Criteria c = createCriteria();
			c.add(Restrictions.eq("userInfo", ecg.getUserInfo()));
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
