/*
 * @文件名： FamilyUserinfoDao.java
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

import com.jumper.hospital.entity.FamilyMoitorRecords;

/**
 * 类名称：FamilyUserinfoDao
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-17 下午1:30:40
 * 修改人：aaron
 * 修改时间：2016-2-17 下午1:30:40
 * 修改备注：
 * @version  1.0
 */
public interface FamilyMoitorRecordsDao extends BaseDao<FamilyMoitorRecords, Integer>
{
/**获取报告单检测了哪几种类型**/
	@SuppressWarnings("rawtypes")
	List<Map> getDetectionType(Integer eid);
/**根据用户id 和检测时间的用户的检测数据集合**/
@SuppressWarnings("rawtypes")
Map getExamineRecords(Integer eid, Date checkTime);

}
