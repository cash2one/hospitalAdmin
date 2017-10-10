package com.jumper.hospital.service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalDoctorOrderRefund;
import com.jumper.hospital.vo.VOConsultRefund;

public interface HospitalDoctorOrderRefundService extends BaseService<HospitalDoctorOrderRefund, Integer> {

	/**
	 * 查询问诊退费申请及处理信息
	 * @param type 
	 * @param state
	 * @param hospitalId
	 * @param orderId
	 * @param page
	 * @return
	 */
	public Page<VOConsultRefund> getConsultRefundDataList(Integer type, Integer state, Integer hospitalId, String orderId, Page<VOConsultRefund> page);
	/**
	 * 拒绝退款
	 * @param admin
	 * @param id
	 * @param reason
	 * @return
	 */
	public String doRefundRefuse(Admin admin, Integer id, String reason);
	/**
	 * 同意退款
	 * @param admin
	 * @param id
	 * @return
	 */
	public String doRefund(Admin admin, Integer id, Integer type);
}
