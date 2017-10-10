package com.jumper.hospital.service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserTemperatureRecord;
import com.jumper.hospital.vo.monitorData.Params;

public interface UserTemperatureRecordService extends BaseService<UserTemperatureRecord, Integer> {

	/**
	 * 根据userId查询用户记录条数
	 * @param temper
	 * @return
	 */
	int getCount(UserTemperatureRecord temper);
	
	
	/**
	 * 分页查询
	 * @param page
	 * @param temper
	 * @param params
	 * @return
	 */
	Page<UserTemperatureRecord> getUserTemperatureWithPage(Page<UserTemperatureRecord> page,UserTemperatureRecord temper,Params params);
	
}
