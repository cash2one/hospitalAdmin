/*
 * @文件名： FamilyRemindDao.java
 * @创建人: aaron
 * @创建时间: 2016-2-20
 * @包名： com.jumper.hospital.dao
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jumper.hospital.entity.FamilyUserSugarRecord;


public interface FamilyUserSugarRecordDao extends BaseDao<FamilyUserSugarRecord, Integer>
{
/**
 * 用户id获取用户的血糖记录数据中的添加时间的值最大的一条记录且日期为当天
 * @param userid
 * @return 
 * @throws Exception 
 */
	FamilyUserSugarRecord getCurrentSugarById(Integer userid) throws Exception;

@SuppressWarnings("rawtypes")
List<Map> getAll(Integer userid,String checkTime) throws Exception;
/**
 * 根据userid  和addTime从血糖记录表汇总获取数据
 * @param userid
 * @param checkTime
 * @return
 */
FamilyUserSugarRecord getResultByUseridAndCheckTime(Integer userid,Date checkTime);
}
