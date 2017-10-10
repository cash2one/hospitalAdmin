package com.jumper.hospital.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.UserExtraInfoDao;
import com.jumper.hospital.entity.UserExtraInfo;
import com.jumper.hospital.entity.UserInfo;

@Repository
public class UserExtraInfoDaoImpl extends BaseDaoImpl<UserExtraInfo, Integer> implements UserExtraInfoDao {

	@Override
	public UserExtraInfo findByUserId(Integer userId) {
		UserExtraInfo info;
	 try {
			UserInfo user = new UserInfo();
			user.setId(userId);
			Criteria c = createCriteria();
			c.add(Restrictions.eq("userInfo", user));
			c.setMaxResults(1);
			info = (UserExtraInfo) c.uniqueResult();
			return info;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
