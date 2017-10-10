package com.jumper.hospital.dao;

import com.jumper.hospital.entity.HospitalTodayRecommend;

public interface HospitalTodayRecommendDao extends
		BaseDao<HospitalTodayRecommend, Integer> {

	/**
	 * 查看该医院是否为今日推荐医院
	 * @param id
	 * @return
	 */
	HospitalTodayRecommend findhospitalTodayRecommend(Integer id);
	
}
