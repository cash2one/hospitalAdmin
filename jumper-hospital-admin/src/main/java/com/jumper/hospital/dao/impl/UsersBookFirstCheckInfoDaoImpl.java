package com.jumper.hospital.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.UsersBookFirstCheckInfoDao;
import com.jumper.hospital.entity.UsersBookFirstCheckInfo;

@Repository
public class UsersBookFirstCheckInfoDaoImpl extends
		BaseDaoImpl<UsersBookFirstCheckInfo, Integer> implements
		UsersBookFirstCheckInfoDao {

	@Override
	public UsersBookFirstCheckInfo findUsersBookFirstCheckInfo(int id) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("userInfo.id", id));
		if(c.list()!=null&&c.list().size()>0){
			return (UsersBookFirstCheckInfo) c.list().get(0);
		}
		return new UsersBookFirstCheckInfo();
	}

}
