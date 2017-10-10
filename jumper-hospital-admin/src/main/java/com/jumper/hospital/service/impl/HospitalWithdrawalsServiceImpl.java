package com.jumper.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalWithdrawalsDao;
import com.jumper.hospital.entity.HospitalWithdrawals;
import com.jumper.hospital.service.HospitalWithdrawalsService;

@Service
public class HospitalWithdrawalsServiceImpl extends BaseServiceImpl<HospitalWithdrawals, Integer> implements HospitalWithdrawalsService{

	@Autowired
	private HospitalWithdrawalsDao hospitalWithdrawalsDao;
	
	@Override
	public BaseDao<HospitalWithdrawals, Integer> getBaseDAO() {
		return hospitalWithdrawalsDao;
	}

	@Override
	public Page<HospitalWithdrawals> getWithdrawalsList(Page<HospitalWithdrawals> page, Integer hospitalId) {
		return hospitalWithdrawalsDao.getWithdrawalsList(page,hospitalId);
	}
	
	

}
