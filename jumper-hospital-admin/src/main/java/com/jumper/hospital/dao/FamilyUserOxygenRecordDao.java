/*
 * @文件名： FamilyExaminationDao.java
 * @创建人: aaron
 * @创建时间: 2016-2-17
 * @包名： com.jumper.hospital.dao
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jumper.hospital.entity.FamilyUserOxygenRecord;

public interface FamilyUserOxygenRecordDao extends BaseDao<FamilyUserOxygenRecord, Integer>
{
/**
 * 获取用户当前的血氧检测数据
 * @param userid
 * @return
 */
	FamilyUserOxygenRecord getCurrentOxygen(Integer userid);
/**
 * 获取所有的数据
 * @return
 */
@SuppressWarnings("rawtypes")
List<Map> getAll(Integer useridk,String checkTime);
/**
 * 根据用户id和检测时间获取血氧数据
 * @param userid
 * @param checkTime
 * @return
 */
FamilyUserOxygenRecord getResultByUseridAndCheckTime(Integer userid,Date checkTime);
 }