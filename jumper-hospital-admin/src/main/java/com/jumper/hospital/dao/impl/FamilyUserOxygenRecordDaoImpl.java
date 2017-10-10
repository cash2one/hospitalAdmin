/*
 * @文件名： FamilyVersionDaoImpl.java
 * @创建人: aaron
 * @创建时间: 2016-2-24
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

import com.jumper.hospital.dao.FamilyUserOxygenRecordDao;
import com.jumper.hospital.entity.FamilyUserOxygenRecord;

@Repository
public class FamilyUserOxygenRecordDaoImpl extends BaseDaoImpl<FamilyUserOxygenRecord, Integer> implements FamilyUserOxygenRecordDao
{

	@SuppressWarnings("unchecked")
	@Override
	public FamilyUserOxygenRecord getCurrentOxygen(Integer userid) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = format.format(new Date());
		List<FamilyUserOxygenRecord> list = this.getSession().createQuery(" from FamilyUserOxygenRecord fus where fus.familyUserId=? and Date(fus.addTime)=? order by fus.addTime desc")
				.setParameter(0, userid)
				.setTimestamp(1, java.sql.Date.valueOf(currentDate)).list();
		return  list.size()==0?null:list.get(0);
	}

	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map> getAll(Integer userid,String checkTime) {
		List<Map> list= this.getSession().createQuery
				("select new map(fo.oxygenFile as oxygenFile,fo.pulseFile as pulseFile,fo.averageOxygen as averageOxygen," +
				"fo.averagePulse as averagePulse,fo.testTimeLength as testTimeLength,fo.pi as pi,fo.addTime as addTime)" +
				" from FamilyUserOxygenRecord fo where fo.familyUserId=? and Date(fo.addTime)<='"+checkTime+"' order by fo.addTime")
				.setParameter(0, userid).list();
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			  map.put("addTime", map.get("addTime").toString().split(" ")[0]);
		}
		return list;
 
	}



	@SuppressWarnings("unchecked")
	@Override
	public FamilyUserOxygenRecord getResultByUseridAndCheckTime(Integer userid,
			Date checkTime) {
		List<FamilyUserOxygenRecord> list = this.getSession().createQuery("from FamilyUserOxygenRecord fr where fr.familyUserId=:userid and Date(fr.addTime)='"+new SimpleDateFormat("yyyy-MM-dd").format(checkTime)+"' order by fr.addTime desc")
		.setParameter("userid", userid).list();
		return null==list|| list.size()<=0?null:list.get(0);
	}

}