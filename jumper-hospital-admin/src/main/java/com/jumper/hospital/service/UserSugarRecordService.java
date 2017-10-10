package com.jumper.hospital.service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserSugarRecord;
import com.jumper.hospital.vo.monitorData.Params;

public interface UserSugarRecordService  extends BaseService<UserSugarRecord,Integer>{
	
	UserSugarRecord  findByUserId(Integer userId);
	
	int  getCount(Integer userId);
	
	Page<UserSugarRecord> findByConditionWithPage(UserSugarRecord sugar,Page<UserSugarRecord> page,Params params);
	
}
