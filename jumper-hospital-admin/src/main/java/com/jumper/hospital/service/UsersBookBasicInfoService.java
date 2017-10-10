package com.jumper.hospital.service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UsersBookBasicInfo;

public interface UsersBookBasicInfoService extends
		BaseService<UsersBookBasicInfo, Integer> {

	/**
	 * 获取孕期档案信息列表
	 * @param page
	 * @param keyWords
	 * @param integer 
	 * @return
	 */
	Page<UsersBookBasicInfo> findPregnantBookList(
			Page<UsersBookBasicInfo> page, String keyWords, Integer hospitalId);

	/**
	 * 根据用户id获取孕妇基本信息表
	 * @param id
	 * @return
	 */
	UsersBookBasicInfo findUsersBookBasicInfo(int id);

	/**
	 * 将档案状态改为未提交
	 * @param id
	 * @param id2
	 */
	void updateUsersBookBasicInfo(Integer id, int id2);
	
}
