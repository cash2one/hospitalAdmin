package com.jumper.hospital.dao.impl;
/**
 * 用户体温测试记录Dao实现类
 * @author rent
 * @date 2015-09-21
 */
import java.sql.Timestamp;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.UserTemperatureRecordDao;
import com.jumper.hospital.entity.UserTemperatureRecord;

@Repository
public class UserTemperatureRecordDaoImpl extends BaseDaoImpl<UserTemperatureRecord, Integer> implements UserTemperatureRecordDao {

	@Override
	public Page<UserTemperatureRecord> getUserTemperatureWithPage( UserTemperatureRecord temper, Page<UserTemperatureRecord> page,
			Timestamp startTime, Timestamp endTime) {
		Criteria c = createCriteria();
		if(temper != null){
			c.add(Restrictions.eq("userInfo", temper.getUserInfo()));
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
		}
		return findPageByCriteria(page, c);	
	}

}
