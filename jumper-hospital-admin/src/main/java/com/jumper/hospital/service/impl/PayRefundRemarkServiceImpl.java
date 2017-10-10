package com.jumper.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.PayRefundRemarkDao;
import com.jumper.hospital.entity.PayRefundRemark;
import com.jumper.hospital.service.PayRefundRemarkService;

@Service
public class PayRefundRemarkServiceImpl extends BaseServiceImpl<PayRefundRemark, Integer> implements PayRefundRemarkService {

	@Autowired
	private PayRefundRemarkDao payRefundRemarkDaoImpl;
	
	@Override
	public BaseDao<PayRefundRemark, Integer> getBaseDAO() {
		return payRefundRemarkDaoImpl;
	}
}
