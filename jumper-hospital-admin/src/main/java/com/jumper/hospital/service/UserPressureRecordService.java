package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserPressureRecord;
import com.jumper.hospital.vo.monitorData.Params;

public interface UserPressureRecordService extends BaseService<UserPressureRecord, Integer> {

	/**
	 * 分页查询
	 * @param pressure
	 * @param page
	 * @param params
	 * @return
	 */
	Page<UserPressureRecord> findByConditionWithPage(UserPressureRecord pressure,Page<UserPressureRecord> page,Params params);
	
	/**
	 * 获取总记录条数
	 * @param pressure
	 * @return
	 */
	int getCount (UserPressureRecord pressure ) ;
	
	
	/**
	 * 不分页查询
	 * @param pressure
	 * @return
	 */
	List<UserPressureRecord> findByCondition(UserPressureRecord pressure,Params params);
	
	
}
