package com.jumper.hospital.dao;

import com.jumper.hospital.entity.UsersBookHusbandInfo;

public interface UsersBookHusbandInfoDao extends
		BaseDao<UsersBookHusbandInfo, Integer> {


	/**
	 * 根据孕妇id查询丈夫基本信息
	 * @param id
	 * @return
	 */
	UsersBookHusbandInfo findUsersBookHusbandInfo(int id);

}
