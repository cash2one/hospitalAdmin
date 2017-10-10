package com.jumper.hospital.dao.impl;
/**
 * 血压测试数据Dao实现类
 * @author remt
 * @date 2015-09-21
 */
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.UserPressureRecordDao;
import com.jumper.hospital.entity.UserPressureRecord;

@Repository
public class UserPressureRecordDaoImpl extends BaseDaoImpl<UserPressureRecord, Integer> implements UserPressureRecordDao {

	@Override
	public Page<UserPressureRecord> findByConditionWithPage( UserPressureRecord pressure, Page<UserPressureRecord> page,
			Timestamp staTime, Timestamp endTime) {
		if(null != pressure){
			Criteria c = createCriteria();
			c.add(Restrictions.eq("userInfo", pressure.getUserInfo()));
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

	@Override
	public int getCount(UserPressureRecord pressure) {
		int count = 0;
		if(pressure.getUserInfo() != null){
			Integer userId = pressure.getUserInfo().getId();
			String countSql = "SELECT COUNT(*) FROM user_pressure_record WHERE user_id="+userId;
			count = executeCountSql(countSql);
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPressureRecord> findByCondition( UserPressureRecord pressure, Timestamp staTime, Timestamp endTime) {
		List<UserPressureRecord> list  = null;
		if(null != pressure){
			Criteria c = createCriteria();
			c.add(Restrictions.eq("userInfo", pressure.getUserInfo()));
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
			list =  c.list();
		}
		return list;
	}

}