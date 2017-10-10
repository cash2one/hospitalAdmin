package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.entity.MonitorHospitalServiceTime;
import com.jumper.hospital.vo.VOMonitorHospitalServiceTime;

public interface MonitorHospitalServiceTimeService extends BaseService<MonitorHospitalServiceTime, Integer> {
	
	/**
	 * 初始化医院服务时间
	 * @param hospitalId
	 */
	public void initServiceTime(Integer hospitalId);
	
	/**
	 * 获取当前医院的排班时间列表
	 * @param id
	 * @return
	 */
	public List<VOMonitorHospitalServiceTime> getServiceTime(Integer hospitalId);

}
