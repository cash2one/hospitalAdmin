package com.jumper.hospital.service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserHeartrateRecord;
import com.jumper.hospital.vo.monitorData.Params;
/**
 * 用户胎心记录表
 * @author admin
 * 2016-8-4
 */
public interface UserHeartrateRecordService extends BaseService<UserHeartrateRecord, Integer> {

	/**
	 * 分页查询
	 * @param userHeartrateRecord
	 * @param page
	 * @return
	 */
	public Page<UserHeartrateRecord> findByConditionWithPage(UserHeartrateRecord userHeartrateRecord,Page<UserHeartrateRecord> page,Params params);
}
