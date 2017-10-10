package com.jumper.hospital.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalVisitDao;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.HospitalService;
import com.jumper.hospital.service.HospitalVisitService;
import com.jumper.hospital.utils.TimeUtils;

@Service
public class HospitalVisitServiceImpl extends BaseServiceImpl<HospitalService, Integer> implements
		HospitalVisitService {
	@Autowired
	private HospitalVisitDao hospitalVisitDao;
	@Override
	public BaseDao<HospitalService, Integer> getBaseDAO() {
		return hospitalVisitDao;
	}
	
	@Override
	public HospitalService getServiceInfoById(Integer id) {
		return get(id);
		
	}

	@Override
	public void addVisitInfo(double money, int week_low, int week_high,
			String beginTime, String end_Time, Admin admin,
			HospitalInfo hospitalInfo) {
		hospitalVisitDao.addVisitInfo(money,week_low,week_high,beginTime,end_Time,admin,hospitalInfo);
	}

	@Override
	public HospitalService findHospitalService(Integer id) {
		return hospitalVisitDao.findHospitalService(id);
	}

	@Override
	public void updateMoney(Integer id, double money) {
		HospitalService hospitalService = hospitalVisitDao.findHospitalService(id);
		hospitalService.setServicecost(money);
		hospitalService.setUpdateTime(TimeUtils.getCurrentTime());
		this.edit(hospitalService);
	}

}
