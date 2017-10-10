package com.jumper.hospital.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jumper.hospital.entity.FamilyUserPressureRecord;

public interface FamilyUserPressureRecordDao extends BaseDao<FamilyUserPressureRecord, Integer> {
/**
 * 获取当前的血压数据
 * @param userid
 * @return
 */
	FamilyUserPressureRecord getCurrentPressure(Integer userid);
/**
 * 获取所有的数据到vo
 * @return
 */
@SuppressWarnings("rawtypes")
List<Map> getAll(Integer userid,String date);
/**
 * 根据用户id 和检测时间
 * @param userid
 * @param checkTime
 * @return
 */
FamilyUserPressureRecord getResultByUseridAndCheckTime(Integer userid,Date checkTime);


}
