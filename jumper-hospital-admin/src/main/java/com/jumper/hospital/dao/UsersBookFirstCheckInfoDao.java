package com.jumper.hospital.dao;

import com.jumper.hospital.entity.UsersBookFirstCheckInfo;

public interface UsersBookFirstCheckInfoDao extends
		BaseDao<UsersBookFirstCheckInfo, Integer> {

	/**
	 * 根据用户id查询初次产检信息表
	 * @param id
	 * @return
	 */
	UsersBookFirstCheckInfo findUsersBookFirstCheckInfo(int id);

}
