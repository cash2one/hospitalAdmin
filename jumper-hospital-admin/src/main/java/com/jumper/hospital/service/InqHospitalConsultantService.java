package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.InqHospitalConsultant;

public interface InqHospitalConsultantService extends BaseService<InqHospitalConsultant, Integer> {
	
	/**
	 * 点击查看详情前修改回复状态
	 */
	int updateStatus(Integer id, Integer status);
	/**
	 * 获取医院今日未结束的咨询列表（未结束和未评价和未删除的）
	 * @param id
	 * @return
	 */
	Page<InqHospitalConsultant> findTodayConsultList(Page<InqHospitalConsultant> page,Integer id);

	/**
	 * 通过问题id查询当前问题信息
	 * @param idInt
	 * @return
	 */
	InqHospitalConsultant findConsultById(Integer idInt);

	/**
	 * 查询已经结束的问题
	 * @param page
	 * @param id
	 * @return
	 */
	Page<InqHospitalConsultant> findAllConsultList(
			Page<InqHospitalConsultant> page, Integer id);

	/**
	 * 条件查询今日未结束问诊列表
	 * @param page
	 * @param id
	 * @param searchKey
	 * @return
	 */
	Page<InqHospitalConsultant> findTodayConsultListByConds(
			Page<InqHospitalConsultant> page, Integer id, String searchKey);

	/**
	 * 修改咨询信息（更新 ）
	 * @param inqHospitalConsultant
	 */
	void updateHospitalConsult(InqHospitalConsultant inqHospitalConsultant);

	/**
	 * 条件查询结束问诊列表
	 * @param page
	 * @param id
	 * @param type
	 * @param searchKey
	 * @return
	 */
	Page<InqHospitalConsultant> findAllConsultListByConds(Page<InqHospitalConsultant> page,
			Integer id,String searchKey,String visitType);

	/**
	 * 通过问诊类型查询结束列表
	 * @param page
	 * @param id
	 * @param visitType
	 * @return
	 */
	Page<InqHospitalConsultant> findAllConsultListByType(Page<InqHospitalConsultant> page, Integer id,
			String visitType);
	
	/**
	 * 通过问诊订单ID查询问诊记录
	 * @param conId 问诊订单ID
	 * @return
	 */
	public InqHospitalConsultant getInqConsultantByConOrderId(Integer conId);
	
	/**
	 * 查询今日所有咨询列表
	 * @param 
	 * @return
	 */
	public List<InqHospitalConsultant> findTodayAllConsultList();
}
