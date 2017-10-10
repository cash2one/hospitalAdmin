package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserWeightRecord;
import com.jumper.hospital.vo.monitorData.Params;
/**
 * 监测体重数据service
 * @author admin
 * 2016-8-3
 */
public interface UserWeightRecordService extends BaseService<UserWeightRecord, Integer> {
	
	/**
	 * 查询总记录
	 * @param userId
	 * @return
	 */
	public int  getCount(Integer userId);
	/**
	 * 根据
	 * @param userWeightRecord
	 * @return
	 */
	public List<UserWeightRecord> findByCondtion(UserWeightRecord userWeightRecord,Params params);
	
	
	/**
	 * 获取所有的体重数据
	 * @return
	 */
	public Page<UserWeightRecord> getUserWeightRecordWithPage(Page<UserWeightRecord> page,UserWeightRecord weightRecord,Params params);
	
	
}
