package com.jumper.hospital.dao;
/**
 * 用户体温测试数据Dao
 * @author rent
 * @date 2015-09-21
 */
import java.sql.Timestamp;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserTemperatureRecord;

public interface UserTemperatureRecordDao extends BaseDao<UserTemperatureRecord, Integer> {

	Page<UserTemperatureRecord>  getUserTemperatureWithPage(UserTemperatureRecord temper,Page<UserTemperatureRecord> page,Timestamp startTime, Timestamp endTime) ;
}
