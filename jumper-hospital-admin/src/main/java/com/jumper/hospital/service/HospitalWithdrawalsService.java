package com.jumper.hospital.service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.HospitalWithdrawals;

public interface HospitalWithdrawalsService extends BaseService<HospitalWithdrawals, Integer>{

	/**
	 * 医院结算列表
	 * @param page
	 * @param id
	 * @return
	 */
	Page<HospitalWithdrawals> getWithdrawalsList(Page<HospitalWithdrawals> page, Integer hospitalId);

}
