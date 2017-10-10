package com.jumper.hospital.service;

import com.jumper.hospital.entity.UsersBookFirstCheckInfo;

public interface UsersBookFirstCheckInfoService extends
		BaseService<UsersBookFirstCheckInfo, Integer> {

	/**
	 * 根据user_id查询初次产检信息表
	 * @param id
	 * @return
	 */
	UsersBookFirstCheckInfo findUsersBookFirstCheckInfo(int id);

}
