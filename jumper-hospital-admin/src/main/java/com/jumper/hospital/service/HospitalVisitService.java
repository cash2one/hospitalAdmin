package com.jumper.hospital.service;

import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.HospitalService;

public interface HospitalVisitService extends
		BaseService<HospitalService, Integer> {

	/**
	 * 根据医院id获取服务信息
	 * @param id
	 * @return
	 */
	HospitalService getServiceInfoById(Integer id);

	/**
	 * 添加问诊服务
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
	 * 通过医院id查询问诊服务信息
	 * @param id
	 * @return
	 */
	HospitalService findHospitalService(Integer id);

	/**
	 * 修改服务费用
	 * @param id
	 * @param money
	 */
	void updateMoney(Integer id, double money);


}
