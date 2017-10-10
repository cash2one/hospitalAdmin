package com.jumper.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.MonitorAdminDao;
import com.jumper.hospital.service.MonitorAdminService;

@Service
public class MonitorAdminServiceImpl implements MonitorAdminService {
	@Autowired
	private MonitorAdminDao monitorAdminDao;

	/**
	 * 查询是否存在
	 */
	public boolean existMonitorAdmin(String mobile){
		return monitorAdminDao.existMonitorAdmin(mobile);
	}

}
