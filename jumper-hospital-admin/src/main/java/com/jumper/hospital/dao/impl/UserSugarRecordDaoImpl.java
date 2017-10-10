package com.jumper.hospital.dao.impl;
/**
 * 血糖测试数据Dao实现类
 * @author rent
 * @date 2015-09-21
 */
import java.sql.Timestamp;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.UserSugarRecordDao;
import com.jumper.hospital.entity.UserInfo;
import com.jumper.hospital.entity.UserSugarRecord;

@Repository
public class UserSugarRecordDaoImpl extends BaseDaoImpl<UserSugarRecord, Integer> implements UserSugarRecordDao {

	@Override
	public UserSugarRecord findByUserId(Integer userId) {
	 try {
			UserInfo user = new UserInfo();
			user.setId(userId);
			Criteria c = createCriteria();
			c.add(Restrictions.eq("userInfo", user));
			c.setMaxResults(1);
			UserSugarRecord	info = (UserSugarRecord) c.uniqueResult();
			return info;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public int getCount(Integer userId) {
		int count=0;
		if(null != userId){
			String countSql = "SELECT COUNT(*) FROM user_sugar_record WHERE user_id="+userId;
			count = executeCountSql(countSql);
		}
		return count;
	}

	@Override
	public Page<UserSugarRecord> findByConditionWithPage(UserSugarRecord sugar, Page<UserSugarRecord> page,Timestamp staTime,Timestamp endTime) {
		if(null != sugar){
			Criteria c = createCriteria();
			c.add(Restrictions.eq("userInfo", sugar.getUserInfo()));
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
