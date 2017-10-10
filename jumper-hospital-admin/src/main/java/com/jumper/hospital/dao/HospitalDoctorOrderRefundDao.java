package com.jumper.hospital.dao;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.HospitalDoctorOrderRefund;
import com.jumper.hospital.vo.VOConsultRefund;

public interface HospitalDoctorOrderRefundDao extends BaseDao<HospitalDoctorOrderRefund, Integer> {

	/**
	 * 医院问诊退款订单查询
	 * @param type 订单状态类型，啥叫订单状态类型啊，1：待处理，2：退款/拒绝
	 * @param state 退款状态
	 * @param hospitalId 医院ID
	 * @param orderId 订单ID
	 * @return
	 */
	public Page<VOConsultRefund> getConsultRefundDataList(Integer type, Integer state, Integer hospitalId, String orderId, Page<VOConsultRefund> page);
}
