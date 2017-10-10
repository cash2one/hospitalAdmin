package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.entity.MonitorHospitalServiceTime;

public interface MonitorHospitalServiceTimeDao extends BaseDao<MonitorHospitalServiceTime, Integer> {
	/**
	 * 获取当前医院的排班表，按照时间顺序显示
	 * @param hospitalId
	 * @return
	 */
	List<MonitorHospitalServiceTime> getHospitalDutyRoster(Integer hospitalId);
	
	
	
}
