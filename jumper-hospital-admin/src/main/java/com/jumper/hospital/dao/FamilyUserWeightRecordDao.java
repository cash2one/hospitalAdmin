package com.jumper.hospital.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jumper.hospital.entity.FamilyUserWeightRecord;
public interface FamilyUserWeightRecordDao extends BaseDao<FamilyUserWeightRecord, Integer> {

	FamilyUserWeightRecord getCurrentWeight(Integer userid);
/**
 * 获取所有的数据
 * @return
 */
	@SuppressWarnings("rawtypes")
	List<Map> getAll(Integer userid,String date);
	/**
	 * 根据用户的id  和检测的日期查找记录
	 * @param userid
	 * @param checkTime
	 * @return
	 */
FamilyUserWeightRecord getResultByUseridAndCheckTime(Integer userid,Date checkTime);

}
