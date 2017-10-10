package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserBloodFatRecord;
import com.jumper.hospital.vo.monitorData.Params;

public interface UserBloodFatRecordService extends BaseService<UserBloodFatRecord, Integer> {

		int getCount(UserBloodFatRecord blood);
		
		/**
		 * @param bloodFat
		 * @param page
		 * @param params
		 * @return
		 */
		Page<UserBloodFatRecord>  findByConditionWithPage(UserBloodFatRecord bloodFat,Page<UserBloodFatRecord> page,Params params);
		
		/**
		 * 不分页
		 * @param bloodFat
		 * @param params
		 * @return
		 */
		List<UserBloodFatRecord>   findByUserBloodFatCondition(UserBloodFatRecord bloodFat,Params params);
}
