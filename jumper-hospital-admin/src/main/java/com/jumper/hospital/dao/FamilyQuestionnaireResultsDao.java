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

import java.util.List;
import java.util.Map;

import com.jumper.hospital.entity.FamilyQuestionnaireResults;

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
public interface FamilyQuestionnaireResultsDao extends BaseDao<FamilyQuestionnaireResults, Integer>
{
/**根据用户id或用户的问卷报告**/
	List<Map<String,Object>> getQuestionReport(Integer userid);
 
}
