package com.jumper.hospital.service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.HospitalConsServiceOrder;

public interface HospitalConsServiceOrderService extends
		BaseService<HospitalConsServiceOrder, Integer> {

	/**
	 * 查询本医院的问诊订单列表
	 * @param page
	 * @param id
	 * @return
	 */
	Page<HospitalConsServiceOrder> findVisitOrderList(
			Page<HospitalConsServiceOrder> page, Integer id);

	/**
	 * 条件查询问诊订单
	 * @param page
	 * @param id
	 * @param searchKey
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Page<HospitalConsServiceOrder> findVisitOrderListByConds(
			Page<HospitalConsServiceOrder> page, Integer id, String searchKey,
			String startTime, String endTime);

	/**
	 * 查看结算详情与未结算详情订单
	 * @param page
	 * @param hospitalId
	 * @param state
	 * @param withdrawalsId
	 * @return
	 */
	Page<HospitalConsServiceOrder> getConsServiceOrderList(
			Page<HospitalConsServiceOrder> page, Integer hospitalId, Integer state,
			Integer withdrawalsId);

	/**
	 * 查询医院为结算的金额
	 * @param page
	 * @param hospitalId
	 * @param endTime 
	 * @param startTime 
	 * @return
	 */
	Page<HospitalConsServiceOrder> getUnBlanceServiceOrderList(
			Page<HospitalConsServiceOrder> page, Integer hospitalId, String startTime, String endTime);
		
}
