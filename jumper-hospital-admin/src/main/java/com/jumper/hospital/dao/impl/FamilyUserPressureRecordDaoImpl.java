package com.jumper.hospital.dao.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.FamilyUserPressureRecordDao;
import com.jumper.hospital.entity.FamilyUserPressureRecord;

@Repository
public class FamilyUserPressureRecordDaoImpl extends BaseDaoImpl<FamilyUserPressureRecord, Integer> implements FamilyUserPressureRecordDao {

	@SuppressWarnings("unchecked")
	@Override
	public FamilyUserPressureRecord getCurrentPressure(Integer userid) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = format.format(new Date());
		List<FamilyUserPressureRecord> list= this.getSession().createQuery("" +
				" from FamilyUserPressureRecord fus where fus.familyUserInfo=? and Date(fus.addTime)=? order by fus.addTime desc")
		.setParameter(0, userid).setTimestamp(1, java.sql.Date.valueOf(currentDate)).list();
		return  list.size()==0?null:list.get(0);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map> getAll(Integer userid,String date_) {
		 List<Map> list = this.getSession().createQuery("select new  map(fup.pressureLow as pressureLow, fup.pressureHeight as pressureHeight, fup.addTime as addTime) " +
				" from FamilyUserPressureRecord as fup where fup.familyUserInfo=? and Date(fup.addTime)<='"+date_+"' order by fup.addTime")
				.setParameter(0, userid).list();
		 for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			map.put("addTime", String.valueOf(map.get("addTime")).split(" ")[0]);
		}
		 return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FamilyUserPressureRecord getResultByUseridAndCheckTime(
			Integer userid, Date checkTime) {
		List<FamilyUserPressureRecord> list = this.getSession().createQuery(" from FamilyUserPressureRecord fr where fr.familyUserInfo=:userid and Date(fr.addTime)='"+new SimpleDateFormat("yyyy-MM-dd").format(checkTime)+"' order by fr.addTime desc")
		.setParameter("userid", userid).list();
		return null==list || list.size()<=0?null:list.get(0);
	}
}
