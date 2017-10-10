package com.jumper.hospital.service.impl;
/**
 * 远程监控医院Service实现类
 * @author rent
 * @date 2015-10-10
 */
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.MonitorHospitalDao;
import com.jumper.hospital.entity.MonitorHospital;
import com.jumper.hospital.enums.Week;
import com.jumper.hospital.service.MonitorHospitalService;
import com.jumper.hospital.utils.TimeUtils;

@Service
public class MonitorHospitalServiceImpl extends BaseServiceImpl<MonitorHospital, Integer> implements MonitorHospitalService {

	private static final Logger logger = Logger.getLogger(MonitorHospitalServiceImpl.class);
	@Autowired
	private MonitorHospitalDao monitorHospitalDaoImpl;
	
	@Override
	public BaseDao<MonitorHospital, Integer> getBaseDAO() {
		return null;
	}

	@Override
	public MonitorHospital findMonitorHospitalByHospitalId(Integer hospitalId) {
		try {
			return monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(hospitalId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(TimeUtils.getCurrentTime("")+"--ServiceImpl通过医院ID查询远程监控医院异常!异常信息:"+e.getMessage());
			return null;
		}
	}

	@Override
	public void saveHospital(MonitorHospital hospitalInfo) {
		monitorHospitalDaoImpl.persist(hospitalInfo);
	}

	@Override
	public boolean editMonitorHospital(Integer id, Integer startDay, Integer endDay, String startTime, String endTime) {
		try {
			MonitorHospital mHospital = monitorHospitalDaoImpl.get(id);
			mHospital.setStartDay(Week.getWeekByInteger(startDay));
			mHospital.setEndDay(Week.getWeekByInteger(endDay));
			mHospital.setStartTime(startTime);
			mHospital.setEndTime(endTime);
			monitorHospitalDaoImpl.save(mHospital);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateScoringMethod(Integer id, Integer scoringMethod) {
		try {
			MonitorHospital mHospital =findMonitorHospitalByHospitalId(id);
			mHospital.setScoringMethod(scoringMethod);
			monitorHospitalDaoImpl.save(mHospital);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
