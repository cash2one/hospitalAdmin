package com.jumper.hospital.dao;
/**
 * 血压测试数据Dao
 * @author rent
 * @date 2015-09-21
 */
import java.sql.Timestamp;
import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserPressureRecord;

public interface UserPressureRecordDao extends BaseDao<UserPressureRecord, Integer> {

	 /**
	  * 分页查询
	  * @param pressure
	  * @param page
	  * @param staTime
	  * @param endTime
	  * @return
	  */
	 Page<UserPressureRecord>  findByConditionWithPage(UserPressureRecord pressure,Page<UserPressureRecord> page,Timestamp staTime,Timestamp endTime) ;
	 
	 
	 int getCount (UserPressureRecord pressure);
	 
	 
	 List<UserPressureRecord>  findByCondition(UserPressureRecord pressure,Timestamp staTime,Timestamp endTime);
}
