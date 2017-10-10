package com.jumper.hospital.dao;
/**
 * 血糖测试数据Dao
 * @author rent
 * @date 2015-09-21
 */
import java.sql.Timestamp;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserSugarRecord;

public interface UserSugarRecordDao extends BaseDao<UserSugarRecord, Integer> {

	UserSugarRecord  findByUserId(Integer userId);
	
	int getCount(Integer userId);
	
	Page<UserSugarRecord>  findByConditionWithPage(UserSugarRecord suage,Page<UserSugarRecord> page,Timestamp staTime,Timestamp endTime);
	
	
}
