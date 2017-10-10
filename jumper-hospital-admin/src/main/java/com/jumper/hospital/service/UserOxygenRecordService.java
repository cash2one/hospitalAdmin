package com.jumper.hospital.service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserOxygenRecord;
import com.jumper.hospital.vo.monitorData.Params;

public interface UserOxygenRecordService extends BaseService<UserOxygenRecord, Integer> {
	
	/**
	 * 获取记录总数
	 * @param oxy
	 * @return
	 */
	int getCount(UserOxygenRecord oxy);
	
	/**
	 * 分页查询
	 * @param oxy
	 * @param page
	 * @param params
	 * @return
	 */
	Page<UserOxygenRecord> findByConditionWithPage(UserOxygenRecord oxy,Page<UserOxygenRecord> page, Params params);

}
