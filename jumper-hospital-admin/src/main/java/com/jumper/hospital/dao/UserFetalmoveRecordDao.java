package com.jumper.hospital.dao;
/**
 * 胎动数据操作Dao
 * @author rent
 * @date 2015-09-21
 */

import java.sql.Timestamp;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserFetalmoveRecord;

public interface UserFetalmoveRecordDao extends BaseDao<UserFetalmoveRecord, Integer> {

	Page<UserFetalmoveRecord> getFetalmoveRecordWithPage( UserFetalmoveRecord fetalmove, Page<UserFetalmoveRecord> page,
			Timestamp staTime, Timestamp edTime);

}
