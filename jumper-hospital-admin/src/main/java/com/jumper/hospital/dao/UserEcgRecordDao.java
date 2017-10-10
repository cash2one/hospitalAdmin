package com.jumper.hospital.dao;

import java.sql.Timestamp;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserEcgRecord;

public interface UserEcgRecordDao extends BaseDao<UserEcgRecord, Integer> {

	Page<UserEcgRecord> getEcgByConditionWithPage(UserEcgRecord ecg, Page<UserEcgRecord> page, Timestamp staTime, Timestamp endTime);

}
