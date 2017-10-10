package com.jumper.hospital.service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.PayRefundAnnal;
import com.jumper.hospital.entity.PayRefundRemark;

public interface PayRefundAnnalService extends BaseService<PayRefundAnnal, Integer> {

	
	/**
	 * 分页获取退款列表
	 * @param page
	 * @param hospitalId
	 * @return
	 */
	public Page<PayRefundAnnal> findPayRefundAnnalList(Page<PayRefundAnnal> page, Integer hospitalId, String startTime, String endTime);
	
	/**
	 * 审核退费申请
	 * @param status
	 * @param reason
	 * @return
	 */
	public String validPayRefundAnnal(Integer id, Integer status, PayRefundRemark remark);
	
	/**
	 * 查找订单的退款或申诉记录
	 * @param orderId
	 * @param refundType
	 * @return
	 */
	public PayRefundAnnal findPayRefund(Integer orderId, String refundType);
	
	/**
	 * 同意订单退费
	 * @param orderId
	 * @return
	 */
	public String validPayRefundAnnal(Integer orderId, PayRefundRemark remark, int channel);
}
