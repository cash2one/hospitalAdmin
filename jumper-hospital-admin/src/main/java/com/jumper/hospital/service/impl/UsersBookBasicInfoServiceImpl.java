package com.jumper.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.UsersBookBasicInfoDao;
import com.jumper.hospital.entity.UsersBookBasicInfo;
import com.jumper.hospital.service.UsersBookBasicInfoService;

@Service
public class UsersBookBasicInfoServiceImpl extends
		BaseServiceImpl<UsersBookBasicInfo, Integer> implements
		UsersBookBasicInfoService {
	@Autowired
	private UsersBookBasicInfoDao usersBookBasicInfoDao;

	@Override
	public BaseDao<UsersBookBasicInfo, Integer> getBaseDAO() {
		return usersBookBasicInfoDao;
	}

	@Override
	public Page<UsersBookBasicInfo> findPregnantBookList(
			Page<UsersBookBasicInfo> page, String keyWords,Integer hospitalId) {
		return usersBookBasicInfoDao.findPregnantBookList(page,keyWords,hospitalId);
	}

	@Override
	public UsersBookBasicInfo findUsersBookBasicInfo(int id) {
		return usersBookBasicInfoDao.findUsersBookBasicInfo(id);
	}

	@Override
	public void updateUsersBookBasicInfo(Integer id, int id2) {
		usersBookBasicInfoDao.updateUsersBookBasicInfo(id,id2);
	}


}
