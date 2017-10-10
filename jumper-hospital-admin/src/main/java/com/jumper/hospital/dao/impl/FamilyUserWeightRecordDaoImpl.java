/*
 * @文件名： FamilyExaminationDaoImpl.java
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
import com.jumper.hospital.dao.FamilyUserWeightRecordDao;
import com.jumper.hospital.entity.FamilyUserWeightRecord;


@Repository
public class FamilyUserWeightRecordDaoImpl extends BaseDaoImpl<FamilyUserWeightRecord, Integer> implements FamilyUserWeightRecordDao
{

	@SuppressWarnings("unchecked")
	@Override
	public FamilyUserWeightRecord getCurrentWeight(Integer userid) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = format.format(new Date());
		List<FamilyUserWeightRecord> list = this.getSession().createQuery
				(" from FamilyUserWeightRecord fus where fus.userId=? and Date(fus.addTime)=? order by fus.addTime desc")
		.setParameter(0, userid).setTimestamp(1, java.sql.Date.valueOf(currentDate)).list();
		return  list.size()==0?null:list.get(0);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map> getAll(Integer userid,String date) {
		List<Map> list=this.getSession().createQuery("select new map(fw.weightState as weightState,fw.addTime as addTime,fw.averageValue as averageValue)" +
				" from FamilyUserWeightRecord fw where fw.userId=? and Date(fw.addTime)<='"+date+"' order by fw.addTime ")
				.setParameter(0, userid)
				.list();
		
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			  map.put("addTime", map.get("addTime").toString().split(" ")[0]);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FamilyUserWeightRecord getResultByUseridAndCheckTime(Integer userid,
			Date checkTime) {
		 List<FamilyUserWeightRecord> list = this.getSession().createQuery("from FamilyUserWeightRecord fr where fr.userId=:userid and Date(fr.addTime)='"+new SimpleDateFormat("yyyy-MM-dd").format(checkTime)+"' order by fr.addTime desc")
		 .setParameter("userid", userid).list();
		 
		return null==list||list.size()<=0?null:list.get(0);
	}}
