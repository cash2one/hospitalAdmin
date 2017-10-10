package com.jumper.hospital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalNoticeDao;
import com.jumper.hospital.entity.HospitalNotice;
import com.jumper.hospital.service.HospitalNoticeService;

@Service
public class HospitalNoticeServiceImpl extends
		BaseServiceImpl<HospitalNotice, Integer> implements
		HospitalNoticeService {
	
	@Autowired
	private HospitalNoticeDao hospitalNoticeDao;

	@Override
	public BaseDao<HospitalNotice, Integer> getBaseDAO() {
		return hospitalNoticeDao;
	}

	@Override
	public Page<HospitalNotice> findNoticeList(Page<HospitalNotice> page,
			Integer id) {
		return hospitalNoticeDao.findNoticeList(page,id);
	}

	@Override
	public List<HospitalNotice> findNotices(int hospitalId) {
		return hospitalNoticeDao.findNotices(hospitalId);
	}
	
	
}
