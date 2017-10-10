package com.jumper.hospital.dao.impl;
/**
 * 血氧、心率测试数据Dao实现类
 * @author rent
 * @date 2015-09-21
 */
import java.sql.Timestamp;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.UserOxygenRecordDao;
import com.jumper.hospital.entity.UserOxygenRecord;

@Repository
public class UserOxygenRecordDaoImpl extends BaseDaoImpl<UserOxygenRecord, Integer> implements UserOxygenRecordDao {

	@Override
	public Page<UserOxygenRecord> getUserOxygenRecordWithPage( UserOxygenRecord oxy, Page<UserOxygenRecord> page,
			Timestamp startTime, Timestamp endTime) {
		if(null != oxy){
			Criteria c = createCriteria();
			c.add(Restrictions.eq("userInfo", oxy.getUserInfo()));
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
