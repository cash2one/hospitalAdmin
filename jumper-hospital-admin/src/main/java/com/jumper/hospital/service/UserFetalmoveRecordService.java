package com.jumper.hospital.service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserFetalmoveRecord;
import com.jumper.hospital.vo.monitorData.Params;

public interface UserFetalmoveRecordService extends BaseService<UserFetalmoveRecord, Integer> {

	int getCount(UserFetalmoveRecord fetalmove);

	Page<UserFetalmoveRecord> findByConditionWithPage( UserFetalmoveRecord fetalmove, Page<UserFetalmoveRecord> page,
			Params params);

}
