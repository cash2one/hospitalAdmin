package com.jumper.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.UsersBookFirstCheckInfoDao;
import com.jumper.hospital.entity.UsersBookFirstCheckInfo;
import com.jumper.hospital.service.UsersBookFirstCheckInfoService;

@Service
public class UsersBookFirstCheckInfoServiceImpl extends
		BaseServiceImpl<UsersBookFirstCheckInfo, Integer> implements
		UsersBookFirstCheckInfoService {
	@Autowired
	private UsersBookFirstCheckInfoDao usersBookFirstCheckInfoDao;

	@Override
	public BaseDao<UsersBookFirstCheckInfo, Integer> getBaseDAO() {
		return usersBookFirstCheckInfoDao;
	}

	@Override
	public UsersBookFirstCheckInfo findUsersBookFirstCheckInfo(int id) {
		return usersBookFirstCheckInfoDao.findUsersBookFirstCheckInfo(id);
	}


}
