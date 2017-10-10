package com.jumper.hospital.dao;

import java.sql.Timestamp;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserUrineRecord;

public interface UserUrineRecordDao extends BaseDao<UserUrineRecord, Integer> {

	Page<UserUrineRecord> getUrineByConditionWithPage(UserUrineRecord urine, Page<UserUrineRecord> page, Timestamp staTime, Timestamp edTime);

}
