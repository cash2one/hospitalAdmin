package com.jumper.hospital.dao;

import java.sql.Timestamp;
import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserBloodFatRecord;

public interface UserBloodFatRecordDao extends BaseDao<UserBloodFatRecord, Integer> {

	 /**
	  * 分页查询
	  * @param bloodFat
	  * @param page
	  * @param startTime
	  * @param endTime
	  * @return
	  */
	 Page<UserBloodFatRecord>  getUserBloodFatRecordWithPage(UserBloodFatRecord bloodFat,Page<UserBloodFatRecord> page ,Timestamp startTime, Timestamp endTime );
	 
	 
	 List<UserBloodFatRecord>  findBloodFatByCondition(UserBloodFatRecord BloodFat,Timestamp startTime, Timestamp endTime );
}
