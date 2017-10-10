package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.entity.PayOrderInfo;

public interface PayOrderInfoDao extends BaseDao<PayOrderInfo, Integer> {

	
	/**
	 * 查询支付订单
	 * @param orderNo
	 * @return
	 */
	public PayOrderInfo getPayOrderInfoByOrderNo(String orderNo);
	
	/**
	 * 查询支付列表信息
	 * @param orderNoList
	 * @return
	 */
	public List<PayOrderInfo> getPayOrderInfoList(List<String> orderNoList);
	
	/**
	 * 
	 * @param orderNo
	 * @return
	 */
	public PayOrderInfo getPayOrderInfoValid(String orderNo);
}
