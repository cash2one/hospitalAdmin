package com.jumper.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.MonitorHeartrateRecordDao;
import com.jumper.hospital.entity.MonitorHeartrateRecord;
import com.jumper.hospital.service.MonitorHeartrateRecordService;

@Service
public class MonitorHeartrateRecordServiceImpl extends BaseServiceImpl<MonitorHeartrateRecord, Integer> implements MonitorHeartrateRecordService {

	@Autowired
	private MonitorHeartrateRecordDao monitorHeartrateRecordDaoImpl;
	
	@Override
	public BaseDao<MonitorHeartrateRecord, Integer> getBaseDAO() {
		return monitorHeartrateRecordDaoImpl;
	}

}
