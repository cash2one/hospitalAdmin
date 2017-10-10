package com.jumper.hospital.dao;

import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.HospitalService;

public interface HospitalVisitDao extends BaseDao<HospitalService, Integer> {

	/**
	 * 根据医院id获取服务信息
	 * @param id
	 * @return
	 */
	HospitalService getServiceInfo(Integer id);

	/**
	 * 添加服务信息
	 * @param money
	 * @param week_low
	 * @param week_high
	 * @param beginTime
	 * @param end_Time
	 * @param major
	 * @param admin
	 * @param hospitalInfo
	 */
	void addVisitInfo(double money, int week_low, int week_high,
			String beginTime, String end_Time, Admin admin,
			HospitalInfo hospitalInfo);

	/**
	 * 通过医院id查询服务信息
	 * @param id
	 * @return
	 */
	HospitalService findHospitalService(Integer id);

}
