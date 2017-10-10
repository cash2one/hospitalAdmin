package com.jumper.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.UserExtraInfoDao;
import com.jumper.hospital.entity.UserExtraInfo;
import com.jumper.hospital.service.UserExtraInfoService;

/**
 * UserExtraInfoService实现类
 * @author admin
 * 2016-8-3
 */
@Service
public class UserExtraInfoServiceImpl extends BaseServiceImpl<UserExtraInfo, Integer> implements UserExtraInfoService {

	@Autowired
	private UserExtraInfoDao UserExtraInfoDaoImpl;
		
	@Override
	public BaseDao<UserExtraInfo, Integer> getBaseDAO() {
		return UserExtraInfoDaoImpl;
	}

	@Override
	public UserExtraInfo findByUserId(Integer userId) {
		return UserExtraInfoDaoImpl.findByUserId(userId);
	}

}
