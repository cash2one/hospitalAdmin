package com.jumper.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalTodayRecommendDao;
import com.jumper.hospital.entity.HospitalTodayRecommend;
import com.jumper.hospital.service.HospitalTodayRecommendService;

@Service
public class HospitalTodayRecommendServiceImpl extends
		BaseServiceImpl<HospitalTodayRecommend, Integer> implements
		HospitalTodayRecommendService {
	
	@Autowired
	private HospitalTodayRecommendDao hospitalTodayRecommendDao;

	@Override
	public BaseDao<HospitalTodayRecommend, Integer> getBaseDAO() {
		return hospitalTodayRecommendDao;
	}

	@Override
	public HospitalTodayRecommend findhospitalTodayRecommend(Integer id) {
		return hospitalTodayRecommendDao.findhospitalTodayRecommend(id);
	}


}
