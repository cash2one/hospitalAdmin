package com.jumper.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.PayOrderInfoDao;
import com.jumper.hospital.entity.PayOrderInfo;
import com.jumper.hospital.service.PayOrderInfoService;

@Service
public class PayOrderInfoServiceImpl extends BaseServiceImpl<PayOrderInfo, Integer> implements PayOrderInfoService {

	
	@Autowired
	private PayOrderInfoDao payOrderInfoDaoImpl;
	
	public BaseDao<PayOrderInfo, Integer> getBaseDAO() {
		return payOrderInfoDaoImpl;
	}
	
	/**
	 * 获取已支付订单的支付信息
	 * @param orderNo
	 * @return
	 */
	public PayOrderInfo getPayOrderInfoValid(String orderNo) {
		return payOrderInfoDaoImpl.getPayOrderInfoValid(orderNo);
	}
}
