package com.jumper.hospital.service;

import com.jumper.hospital.entity.PayOrderInfo;

public interface PayOrderInfoService extends BaseService<PayOrderInfo, Integer> {

	
	/**
	 * 获取已支付订单的支付信息
	 * @param orderNo
	 * @return
	 */
	public PayOrderInfo getPayOrderInfoValid(String orderNo);
}
