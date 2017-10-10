package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.HospitalWithdrawals;

public interface HospitalWithdrawalsDao extends BaseDao<HospitalWithdrawals, Integer>{

	/**
	 * 医院结算列表
	 * @param page
	 * @param hospitalId
	 * @return
	 */
	Page<HospitalWithdrawals> getWithdrawalsList(Page<HospitalWithdrawals> page, Integer hospitalId);

	/**
	 * 查询所有未结算的月度结算Id
	 * @param hospitalId
	 * @return
	 */
	List<Integer> getUnBlancedWithdrawals(Integer hospitalId);

}
