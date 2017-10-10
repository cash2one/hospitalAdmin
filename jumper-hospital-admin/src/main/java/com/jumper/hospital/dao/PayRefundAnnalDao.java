package com.jumper.hospital.dao;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.PayRefundAnnal;

public interface PayRefundAnnalDao extends BaseDao<PayRefundAnnal, Integer> {

	
	/**
	 * 获取退款单列表
	 * @param page
	 * @param hospitalId
	 * @return
	 */
	public Page<PayRefundAnnal> findPayRefundAnnalList(Page<PayRefundAnnal> page, Integer hospitalId, String startTime, String endTime);
	
	/**
	 * 查找订单的退款或申诉记录
	 * @param orderId
	 * @param refundType
	 * @return
	 */
	public PayRefundAnnal findPayRefund(Integer orderId, String refundType);
	
	public void savePayRefundAnnal(PayRefundAnnal annl);
}
