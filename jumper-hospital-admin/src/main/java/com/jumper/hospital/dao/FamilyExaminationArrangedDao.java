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

import java.text.ParseException;
import java.util.List;

import com.jumper.hospital.entity.FamilyExaminationArranged;
import com.jumper.hospital.vo.familyDoctor.VOFamilyExaminationArranged;

public interface FamilyExaminationArrangedDao extends BaseDao<FamilyExaminationArranged, Integer>
{
	/**
	 * 根据用户id 和 状态获取当天的数据
	 * @param id
	 * @param state
	 * @return
	 * @throws ParseException
	 */
	public List<VOFamilyExaminationArranged> getFamilyExaminationArrangedsByUserInfoId(
			Integer id, Short state) throws ParseException;

	public void saveFamilyExaminationArranged(
			FamilyExaminationArranged examinationArranged);
/**
 * 获取用户的检测记录
 * @param id
 * @param s
 * @return
 */
	public List<VOFamilyExaminationArranged> getFamilyExaminationArrangedsByUserInfoId4State(
			Integer id, short s);
/**
 * 根据用户id到排期表中查找未完成的任务的最近的一条数据
 * @param userid
 * @param state
 */
public FamilyExaminationArranged getRecentlyFamilyExaminationArrangedByUserInfoId(Integer userid,
		Short state);
}
