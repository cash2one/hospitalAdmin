package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.entity.MonitorSetting;
import com.jumper.hospital.enums.RemoteType;

public interface MonitorSettingDao extends BaseDao<MonitorSetting, Integer> {

	/**
	 * 根据医院ID和监控查询查询该监控的服务
	 * @param hospitalId 医院ID
	 * @return
	 */
	public List<MonitorSetting> findByHospitalIdAndRemoteType(Integer hospitalId);
	/**
	 * 医院监护服务是否开启
	 * @param hospitalId
	 * @param type
	 * @return
	 */
	public boolean checkMonitorIsOpen(Integer hospitalId, RemoteType type);
}
