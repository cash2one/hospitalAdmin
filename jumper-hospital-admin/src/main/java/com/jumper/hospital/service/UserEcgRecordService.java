package com.jumper.hospital.service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserEcgRecord;
import com.jumper.hospital.vo.monitorData.Params;

public interface UserEcgRecordService extends BaseService<UserEcgRecord, Integer> {

	public int getCount(UserEcgRecord ecg );
	
	
	Page<UserEcgRecord>  getEcgByConditionWithPage(UserEcgRecord ecg,Page<UserEcgRecord> page ,Params params);
}
