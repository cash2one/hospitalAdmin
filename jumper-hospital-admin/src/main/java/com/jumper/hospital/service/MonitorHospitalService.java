package com.jumper.hospital.service;
import com.jumper.hospital.entity.MonitorHospital;

public interface MonitorHospitalService extends BaseService<MonitorHospital, Integer> {

	/**
	 * 通过医院ID查询医院开通的远程监控信息
	 * @param hospitalId 医院信息
	 * @return MonitorHospital
	 */
	public MonitorHospital findMonitorHospitalByHospitalId(Integer hospitalId);
	
	/**
	 * 添加医院
	 * @param hospitalInfo
	 */
	public void saveHospital(MonitorHospital hospitalInfo);
	
	/**
	 * 更新医院服务时间
	 * @param id
	 * @param startDay
	 * @param endDay
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean editMonitorHospital(Integer id, Integer startDay, Integer endDay, String startTime, String endTime);
	/**
	 * 设置评分方式
	 * @param id
	 * @param scoringMethod
	 * @return
	 */
	public boolean updateScoringMethod(Integer id, Integer scoringMethod);
}
