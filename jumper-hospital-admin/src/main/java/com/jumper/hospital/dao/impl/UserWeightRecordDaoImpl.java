package com.jumper.hospital.dao.impl;
/**
 * 用户体重测试记录Dao实现类
 * @author rent
 * @date 2015-09-21
 */
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.UserWeightRecordDao;
import com.jumper.hospital.entity.UserWeightRecord;

@Repository
public class UserWeightRecordDaoImpl extends BaseDaoImpl<UserWeightRecord, Integer> implements UserWeightRecordDao {

	@Override
	public int getCount(Integer userId) {
		int count=0;
		if(null != userId){
			String countSql = "SELECT COUNT(*) FROM user_weight_record WHERE user_id="+userId;
			count = executeCountSql(countSql);
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserWeightRecord> findByCondition(UserWeightRecord userWeightRecord,Timestamp startTime,Timestamp endTime) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("userInfo", userWeightRecord.getUserInfo()));
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
		List<UserWeightRecord> list = c.list();
		return list;
	}

	@Override
	public Page<UserWeightRecord> getUserWeightRecordWithPage(Page<UserWeightRecord> page,UserWeightRecord weightRecord,Timestamp startTime,Timestamp endTime) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("userInfo", weightRecord.getUserInfo()));
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

}
