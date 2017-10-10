package com.jumper.hospital.service;

import com.jumper.hospital.entity.UsersBookHusbandInfo;

public interface UsersBookHusbandInfoService extends
		BaseService<UsersBookHusbandInfo, Integer> {

	/**
	 * 根据孕妇id查询丈夫基本信息
	 * @param id
	 * @return
	 */
	UsersBookHusbandInfo findUsersBookHusbandInfo(int id);

}
