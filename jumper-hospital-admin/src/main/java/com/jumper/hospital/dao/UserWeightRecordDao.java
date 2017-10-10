package com.jumper.hospital.dao;
/**
 * 用户体重测试记录Dao
 * @author rent
 * @date 2015-09-21
 */
import java.sql.Timestamp;
import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserWeightRecord;

public interface UserWeightRecordDao extends BaseDao<UserWeightRecord, Integer> {

  public int getCount(Integer userId);
	
   public List<UserWeightRecord> findByCondition(UserWeightRecord userWeightRecord, Timestamp startTime,Timestamp endTime);
	
   public Page<UserWeightRecord> getUserWeightRecordWithPage(Page<UserWeightRecord> page,UserWeightRecord weightRecord,Timestamp startTime,Timestamp endTime);
}
