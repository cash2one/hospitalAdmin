/*
 * @文件名： FamilyExaminationOrderDao.java
 * @创建人: aaron
 * @创建时间: 2016-2-19
 * @包名： com.jumper.hospital.dao
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jumper.hospital.entity.FamilyUserTemperatureRecord;

 
public interface FamilyUserTemperatureRecordDao extends BaseDao<FamilyUserTemperatureRecord, Integer>
{
/**
 * 根据userid 获取改用户的当前最新的体温检测数据
 * @param userid
 * @return
 */
	FamilyUserTemperatureRecord getCurrentTemperature(Integer userid);
/**
 * 获取所有的数据
 * @return
 */
@SuppressWarnings("rawtypes")
List<Map> getAll(Integer userid,String checkTime);
/**
 * 根据用户id 和检测的时间获取温度记录
 * @param userid
 * @param checkTime
 * @return
 */
FamilyUserTemperatureRecord getResultByUseridAndCheckTime(Integer userid, Date checkTime);}
