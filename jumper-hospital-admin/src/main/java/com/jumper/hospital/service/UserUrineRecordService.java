package com.jumper.hospital.service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserUrineRecord;
import com.jumper.hospital.vo.monitorData.Params;

public interface UserUrineRecordService extends BaseService<UserUrineRecord, Integer> {

	public int getCount(UserUrineRecord urine);
	
	/**
	 * 分页查询
	 * @param urine
	 * @param page
	 * @param params
	 * @return
	 */
    public  Page<UserUrineRecord>	getUrineByConditionWithPage(UserUrineRecord urine, Page<UserUrineRecord> page ,Params params);
    
}
