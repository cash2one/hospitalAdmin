package com.jumper.hospital.dao.impl;
/**
 * 胎动测试数据Dao实现类
 * @author rent
 * @date 2015-09-21
 */
import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.UserHeartrateRecordDao;
import com.jumper.hospital.entity.UserHeartrateRecord;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

@Repository
public class UserHeartrateRecordDaoImpl extends BaseDaoImpl<UserHeartrateRecord, Integer> implements UserHeartrateRecordDao {

	@Override
	public Page<UserHeartrateRecord> getUserHeartrateRecorWithPage( UserHeartrateRecord userHeartrateRecord,
			Page<UserHeartrateRecord> page, Timestamp startTime, Timestamp endTime) {
		if(null != userHeartrateRecord){
			Criteria c = createCriteria();
			c.add(Restrictions.eq("userInfo", userHeartrateRecord.getUserInfo()));
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

}
