package com.jumper.hospital.dao;
/**
 * 胎心测试数据Dao
 * @author rent
 * @date 2015-09-21
 */
import java.sql.Timestamp;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserHeartrateRecord;

public interface UserHeartrateRecordDao extends BaseDao<UserHeartrateRecord, Integer> {
	
	Page<UserHeartrateRecord> getUserHeartrateRecorWithPage(UserHeartrateRecord userHeartrateRecord, Page<UserHeartrateRecord> page,Timestamp startTime,Timestamp endTime);

}
