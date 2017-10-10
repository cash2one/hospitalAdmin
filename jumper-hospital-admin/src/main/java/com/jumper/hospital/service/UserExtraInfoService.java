package com.jumper.hospital.service;

import com.jumper.hospital.entity.UserExtraInfo;
/**
 * 用户额外信息Service
 * @author admin
 * 2016-8-3
 */
public interface UserExtraInfoService extends BaseService<UserExtraInfo,Integer>{
	
	/**
	 * 根据用户id查
	 * @param userId
	 * @return
	 */
	public  UserExtraInfo findByUserId(Integer userId);
	
}
