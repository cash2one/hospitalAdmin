package com.jumper.hospital.dao;

import com.jumper.hospital.entity.MonitorAdmin;


public interface MonitorAdminDao extends BaseDao<MonitorAdmin, Integer> {


	/**
	 * 查询是否存在
	 */
	public boolean existMonitorAdmin(String mobile);
}
