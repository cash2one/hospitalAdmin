package com.jumper.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.UsersBookHusbandInfoDao;
import com.jumper.hospital.entity.UsersBookHusbandInfo;
import com.jumper.hospital.service.UsersBookHusbandInfoService;

@Service
public class UsersBookHusbandInfoServiceImpl extends
		BaseServiceImpl<UsersBookHusbandInfo, Integer> implements
		UsersBookHusbandInfoService {
	@Autowired 
	private UsersBookHusbandInfoDao usersBookHusbandInfoDao;
	@Override
	public BaseDao<UsersBookHusbandInfo, Integer> getBaseDAO() {
		return usersBookHusbandInfoDao;
	}
	@Override
	public UsersBookHusbandInfo findUsersBookHusbandInfo(int id) {
		return usersBookHusbandInfoDao.findUsersBookHusbandInfo(id);
	}


}
