package com.jumper.hospital.dao;

import com.jumper.hospital.entity.UserExtraInfo;

public interface UserExtraInfoDao extends BaseDao<UserExtraInfo, Integer> {

	/**
	 * 根据用户id查询
	 * @param userId
	 * @return
	 */
	public  UserExtraInfo findByUserId(Integer userId);
}
