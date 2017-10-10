package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.entity.MonitorSetting;
import com.jumper.hospital.enums.RemoteType;
import com.jumper.hospital.vo.VoMonitorSetting;

public interface MonitorSettingService extends BaseService<MonitorSetting, Integer> {

	/**
	 * 根据医院ID和监控查询查询该监控的服务
	 * @param hospitalId 医院ID
	 * @return
	 */
	public List<VoMonitorSetting> findByHospitalIdAndRemoteType(Integer hospitalId);
	/**
	 * 查看某医院的服务是否开启
	 * @param hospitalId 医院ID
	 * @param type 监护类型
	 * @return
	 */
	public boolean checkMonitorIsOpen(Integer hospitalId, RemoteType type);
	/**
	 * 校验常规监测订单是否已试用完毕
	 * @param hospitalId 医院ID
	 * @param type 监护类型
	 * @return
	 */
	public boolean checkRoutineOrderIsUseFinish(Integer hospitalId, RemoteType type);
}
