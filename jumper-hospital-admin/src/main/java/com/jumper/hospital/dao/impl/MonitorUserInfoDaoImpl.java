package com.jumper.hospital.dao.impl;
/**
 * 远程监控用户Dao实现类
 * @author rent
 * @date 2015-09-18
 */
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.MonitorUserInfoDao;
import com.jumper.hospital.dao.UserInfoDao;
import com.jumper.hospital.entity.MonitorUserInfo;
import com.jumper.hospital.entity.UserInfo;
import com.jumper.hospital.utils.TimeUtils;

@Repository
public class MonitorUserInfoDaoImpl extends BaseDaoImpl<MonitorUserInfo, Integer> implements MonitorUserInfoDao {

	private static final Logger logger = Logger.getLogger(MonitorUserInfoDaoImpl.class);
	@Autowired
	private UserInfoDao userInfoDaoImpl;
	
	@Override
	public boolean doCheckUserIsBind(Integer userId) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("userId.id", userId));
			c.setProjection(Projections.rowCount());
			Long rowCount = (Long) c.uniqueResult();
			if(rowCount > 0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(TimeUtils.getCurrentTime("")+"--查询用户是否绑定异常！异常信息："+e.getMessage());
		}
		return false;
	}

	@Override
	public MonitorUserInfo doBindUserInfo(String mobile, Integer userId) {
		try {
			if(userId == null || userId == 0 || StringUtils.isEmpty(mobile)){
				logger.error(TimeUtils.getCurrentTime("")+"--绑定APP用户失败，手机或用户为空！");
				return null;
			}
			Criteria c = createCriteria();
			c.add(Restrictions.eq("mobile", mobile));
			c.add(Restrictions.isNull("userId"));
			MonitorUserInfo monitorUserInfo = (MonitorUserInfo) c.uniqueResult();
			if(monitorUserInfo != null){
				UserInfo user = userInfoDaoImpl.get(userId);
				if(user == null){
					logger.error(TimeUtils.getCurrentTime("")+"--绑定APP用户失败，APP用户信息不存在！");
					return null;
				}
				monitorUserInfo.setUserId(user);
				save(monitorUserInfo);
				return monitorUserInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(TimeUtils.getCurrentTime("")+"--绑定APP用户发生异常，异常原因："+e.getMessage());
		}
		return null;
	}

	@Override
	public MonitorUserInfo getMonitorUserInfo(Integer userId) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("userId.id", userId));
			MonitorUserInfo mInfo = (MonitorUserInfo) c.uniqueResult();
			return mInfo;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(TimeUtils.getCurrentTime("")+"--获取监控用户异常！异常原因:"+e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<MonitorUserInfo> findUserAutoComplete(String mobile) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.like("mobile", mobile, MatchMode.START));
			List<MonitorUserInfo> list = c.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Integer> findUserInfoByNameOrMobile(String searchKey) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.or(Restrictions.like("realName", searchKey, MatchMode.START), Restrictions.like("mobile", searchKey, MatchMode.START)));
			c.setProjection(Projections.groupProperty("id"));
			return c.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean doCheckMobileIsExist(String mobile) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("mobile", mobile));
			c.setProjection(Projections.rowCount());
			long count = (Long) c.uniqueResult();
			if(count > 0){
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	@Override
	public MonitorUserInfo findMUSerByMobile(String mobile) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("mobile", mobile));
		c.setMaxResults(1);
		return (MonitorUserInfo) c.uniqueResult();
	}

}
