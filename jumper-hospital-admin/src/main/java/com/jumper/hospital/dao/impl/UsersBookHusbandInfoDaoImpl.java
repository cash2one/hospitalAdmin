package com.jumper.hospital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.UsersBookHusbandInfoDao;
import com.jumper.hospital.entity.UsersBookHusbandInfo;

@Repository
public class UsersBookHusbandInfoDaoImpl extends BaseDaoImpl<UsersBookHusbandInfo, Integer> implements UsersBookHusbandInfoDao {

	@SuppressWarnings("unchecked")
	@Override
	public UsersBookHusbandInfo findUsersBookHusbandInfo(int id) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("userInfo.id", id));
		List<UsersBookHusbandInfo> list = c.list();
		if(list.size()>1){
			return list.get(0);
		}
		return (UsersBookHusbandInfo) c.uniqueResult();
	}


}
