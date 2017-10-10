package com.jumper.hospital.service;

import com.jumper.hospital.entity.HospitalTodayRecommend;

public interface HospitalTodayRecommendService extends
		BaseService<HospitalTodayRecommend, Integer> {

	/**
	 * 
	 * @param id
	 * @return
	 */
	HospitalTodayRecommend findhospitalTodayRecommend(Integer id);

}
