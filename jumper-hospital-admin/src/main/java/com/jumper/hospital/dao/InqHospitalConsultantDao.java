package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.HospitalConsServiceOrder;
import com.jumper.hospital.entity.InqHospitalConsultant;

public interface InqHospitalConsultantDao extends BaseDao<InqHospitalConsultant, Integer> {
	
	/**
	 * 点击查看详情前修改回复状态
	 */
	int updateStatus(Integer id,Integer status);

	/**
	 * 获取今日未结束的咨询列表
	 * @param id
	 * @return
	 */
	Page<InqHospitalConsultant> findTodayConsultList(Page<InqHospitalConsultant> page,Integer id);
	
	/**
	 * 获取所有订单中已支付的订单
	 * 
	 */
	List<HospitalConsServiceOrder> findConsultListing();
	/**
	 * 查询所有已经结束的问题
	 * @param page
	 * @param id
	 * @return
	 */
	Page<InqHospitalConsultant> findAllConsultList(
			Page<InqHospitalConsultant> page, Integer id);

	/**
	 * 条件查询今日问诊列表
	 * @param page
	 * @param id
	 * @param searchKey
	 * @return
	 */
	Page<InqHospitalConsultant> findTodayConsultListByConds(
			Page<InqHospitalConsultant> page, Integer id, String searchKey);

	/**
	 * 条件查询所有结束问诊列表
	 * @param page
	 * @param id
	 * @param type
	 * @param searchKey
	 * @return
	 */
	Page<InqHospitalConsultant> findAllConsultListByConds(Page<InqHospitalConsultant> page,
			Integer id, String searchKey,String visitType);

	/**
	 * 通过问诊类型查询结束问诊列表
	 * @param page
	 * @param id
	 * @param visitType
	 * @return
	 */
	Page<InqHospitalConsultant> findAllConsultListByType(Page<InqHospitalConsultant> page, Integer id,
			String visitType);
	/**
	 * 通过问诊订单查询该问诊记录
	 * @param conId 问诊订单ID
	 * @return
	 */
	public InqHospitalConsultant getInqConsultantByConOrderId(Integer conId);
	
	/**
	 *查询出所有的问诊 排除已经结束的
	 * @param 
	 * @return
	 */
	public List<InqHospitalConsultant> findTodayAllConsultList();
}
