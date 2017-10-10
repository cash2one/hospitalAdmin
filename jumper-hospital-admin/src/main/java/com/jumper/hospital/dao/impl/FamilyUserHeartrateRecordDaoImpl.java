/*
 * @文件名： FamilyExaminationDetailDaoImpl.java
 * @创建人: aaron
 * @创建时间: 2016-2-17
 * @包名： com.jumper.hospital.dao.impl
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.FamilyUserHeartrateRecordDao;
import com.jumper.hospital.entity.FamilyUserHeartrateRecord;
@Repository
public class FamilyUserHeartrateRecordDaoImpl extends BaseDaoImpl<FamilyUserHeartrateRecord, Integer> implements FamilyUserHeartrateRecordDao
{

	@SuppressWarnings("unchecked")
	@Override
	public FamilyUserHeartrateRecord getCurrentHeartrate(Integer userid) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = format.format(new Date());
		List<FamilyUserHeartrateRecord> list = this.getSession().createQuery("" +
				" from FamilyUserHeartrateRecord  fus where fus.familyUserId=? and Date(fus.addTime)=? order by fus.addTime desc")
		.setParameter(0, userid).setTimestamp(1, java.sql.Date.valueOf(currentDate)).list();
		return  list.size()==0?null:list.get(0);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map> getAll(Integer userid,String orderBy,String path) {
		return this.getSession().createQuery
				("select new map(fu.fetalMoveFiles,fu.recordFiles," +
				"fu.addTime,fu.appTestTime,fu.rawFiles,fu.averageRate," +
				"fu.fetalMoveValue,fu.fetalMoveTimes,fu.testTime,fu.addTime)" +
				" from FamilyUserHeartrateRecord fu where fu.familyUserId=?  order by fu.? ?")
				.setParameter(0, userid)
				.setParameter(1, orderBy)
				.setParameter(2, path).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public FamilyUserHeartrateRecord getResultByUseridAndCheckTime(
			Integer userid, Date checkTime) {
		List<FamilyUserHeartrateRecord> list = this.getSession().createQuery
		("from FamilyUserHeartrateRecord fr where fr.familyUserId=:userid and Date(fr.addTime)='"+new SimpleDateFormat("yyyy-MM-dd").format(checkTime)+"' order by fr.addTime desc")
		.setParameter("userid", userid).list();
		return null==list||list.size()==0?null:list.get(0);
	}
}
