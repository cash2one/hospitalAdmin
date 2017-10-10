package com.jumper.hospital.dao;
/**
 * 血氧、心率测试数据Dao
 * @author rent
 * @date 2015-09-21
 */
import java.sql.Timestamp;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserOxygenRecord;

public interface UserOxygenRecordDao extends BaseDao<UserOxygenRecord, Integer> {

  Page<UserOxygenRecord> getUserOxygenRecordWithPage(UserOxygenRecord oxy, Page<UserOxygenRecord> page,Timestamp startTime, Timestamp endTime);
}
