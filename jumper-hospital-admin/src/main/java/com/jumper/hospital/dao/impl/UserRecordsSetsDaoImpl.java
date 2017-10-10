package com.jumper.hospital.dao.impl;
/**
 * 用户测试记录Dao实现类
 * @author rent
 * @date 2015-09-21
 */
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.UserRecordsSetsDao;
import com.jumper.hospital.entity.UserRecordsSets;
import com.jumper.hospital.enums.UserTestRecordType;
import com.jumper.hospital.utils.TimeUtils;

@Repository
public class UserRecordsSetsDaoImpl extends BaseDaoImpl<UserRecordsSets, Integer> implements UserRecordsSetsDao {

	private static final Logger logger = Logger.getLogger(UserRecordsSetsDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findHeartrateRecord(Integer userId) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("userInfo.id", userId));
			c.add(Restrictions.eq("recordType", UserTestRecordType.FETALHEART.getType()));
			c.add(Restrictions.ge("addTime", TimeUtils.getCurrentStartTime(0, new Date())));
			c.setProjection(Projections.groupProperty("objectId"));
			List<Integer> list = c.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(TimeUtils.getCurrentTime("")+"--查询当前胎心记录异常！异常原因:"+e.getMessage()+",查询用户:"+userId);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findFetalmoveRecord(Integer userId) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("userInfo.id", userId));
			c.add(Restrictions.eq("recordType", UserTestRecordType.FETALMOVEMENT.getType()));
			c.add(Restrictions.ge("addTime", TimeUtils.getCurrentStartTime(0, new Date())));
			c.setProjection(Projections.groupProperty("objectId"));
			List<Integer> list = c.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(TimeUtils.getCurrentTime("")+"--查询当前胎动记录异常！异常原因:"+e.getMessage()+",查询用户:"+userId);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findSugarRecord(Integer userId) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("userInfo.id", userId));
			c.add(Restrictions.eq("recordType", UserTestRecordType.BLOODGLUCOSE.getType()));
			c.add(Restrictions.ge("addTime", TimeUtils.getCurrentStartTime(0, new Date())));
			c.setProjection(Projections.max("data"));
			c.setProjection(Projections.groupProperty("objectId"));
			List<Integer> list = c.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(TimeUtils.getCurrentTime("")+"--查询当前血糖记录异常！异常原因:"+e.getMessage()+",查询用户:"+userId);
		}
		return null;
	}

}
