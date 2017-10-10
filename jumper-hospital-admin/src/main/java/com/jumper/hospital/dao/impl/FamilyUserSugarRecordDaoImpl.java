/*
 * @文件名： FamilyExaminationOrderDaoImpl.java
 * @创建人: aaron
 * @创建时间: 2016-2-19
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

import com.jumper.hospital.dao.FamilyUserSugarRecordDao;
import com.jumper.hospital.entity.FamilyUserSugarRecord;

/**
 * 类名称：FamilyExaminationOrderDaoImpl
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-19 下午3:53:38
 * 修改人：aaron
 * 修改时间：2016-2-19 下午3:53:38
 * 修改备注：
 * @version  1.0
 */
@Repository
public class FamilyUserSugarRecordDaoImpl extends BaseDaoImpl<FamilyUserSugarRecord, Integer> implements FamilyUserSugarRecordDao
{

	@SuppressWarnings("unchecked")
	@Override
	public FamilyUserSugarRecord getCurrentSugarById(Integer userid) throws Exception {
		 
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = format.format(new Date());
		List<FamilyUserSugarRecord> list = this.getSession().createQuery
				(" from FamilyUserSugarRecord fus where fus.familyUserId=? and Date(fus.addTime)=? order by fus.addTime desc")
		.setParameter(0, userid).setTimestamp(1, java.sql.Date.valueOf(currentDate)).list();
		return  list.size()==0?null:list.get(0); 
		 
		}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map> getAll(Integer userid,String checkTime) throws Exception {
		  List<Map> list = null;
			list = this.getSession().createQuery
					("select new map(fus.averageValue as averageValue,fus.addTime as addTime,fus.testTimeState as testTimeState)" +
					" from FamilyUserSugarRecord fus where fus.familyUserId=? and Date(fus.addTime)<='"+checkTime+"' order by fus.addTime")
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
	public FamilyUserSugarRecord getResultByUseridAndCheckTime(Integer userid, Date checkTime) {//
		List<FamilyUserSugarRecord> list = this.getSession().createQuery("from FamilyUserSugarRecord fr where fr.familyUserId=:userid and Date(fr.addTime)='"+new SimpleDateFormat("yyyy-MM-dd").format(checkTime)+"' order by fr.addTime desc")
		.setParameter("userid", userid).list();
		return null==list||list.size()<=0?null:list.get(0);
	}
}