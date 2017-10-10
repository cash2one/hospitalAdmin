package com.jumper.hospital.dao.impl;
/**
 * 胎动测试数据Dao实现类
 * @author rent
 * @date 2015-09-21
 */
import java.sql.Timestamp;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.UserFetalmoveRecordDao;
import com.jumper.hospital.entity.UserFetalmoveRecord;
@Repository
public class UserFetalmoveRecordDaoImpl extends BaseDaoImpl<UserFetalmoveRecord, Integer> implements UserFetalmoveRecordDao {

	@Override
	public Page<UserFetalmoveRecord> getFetalmoveRecordWithPage( UserFetalmoveRecord fetalmove, Page<UserFetalmoveRecord> page,
			Timestamp startTime, Timestamp endTime) {
		if(null != fetalmove ){
			Criteria c = createCriteria();
			c.add(Restrictions.eq("userInfo", fetalmove.getUserInfo()));
			if(null != startTime && null != startTime){
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
