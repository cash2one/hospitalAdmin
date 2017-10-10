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

import com.jumper.hospital.entity.FamilyUserHeartrateRecord;

public interface FamilyUserHeartrateRecordDao extends BaseDao<FamilyUserHeartrateRecord, Integer>
{
/**
 * 获取当前的胎心的检测数据
 * @param userid
 * @return
 */
	FamilyUserHeartrateRecord getCurrentHeartrate(Integer userid);

@SuppressWarnings("rawtypes")
List<Map> getAll(Integer userid,String orderBy,String path);
/**
 * 根据用户id 和检测时间查询用户的胎心检测记录
 * @param userid
 * @param checkTime
 * @return
 */
FamilyUserHeartrateRecord getResultByUseridAndCheckTime(Integer userid,Date checkTime);//

}
