/*
 * @文件名： FamilyUserinfoDaoImpl.java
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.FamilyMoitorRecordsDao;
import com.jumper.hospital.entity.FamilyMoitorRecords;

/**
 * 类名称：FamilyUserinfoDaoImpl
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-17 下午1:33:46
 * 修改人：aaron
 * 修改时间：2016-2-17 下午1:33:46
 * 修改备注：
 * @version  1.0
 */
@Repository
public class FamilyMoitorRecordsDaoImpl extends BaseDaoImpl<FamilyMoitorRecords, Integer> implements FamilyMoitorRecordsDao
{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map> getDetectionType(Integer eid) {
		return this.getSession().createQuery("select new map(f.recordType as recordType) from FamilyMoitorRecords f where f.eid=:eid").setParameter("eid", eid).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Integer> getExamineRecords(Integer eid, Date checkTime) {
		List<FamilyMoitorRecords> list = this.getSession().createQuery
				("from FamilyMoitorRecords f where f.eid=:eid and date_format(f.addTime,'%Y-%m-%d')=:checkTime")
		.setParameter("eid", eid)
		.setParameter("checkTime", new SimpleDateFormat("yyyy-MM-dd").format(checkTime)).list();
		HashMap<String, Integer> resultMap = new HashMap<String,Integer>();
		Map<Integer, String> typeMap = FamilyMoitorRecords.typeMap;
		for (int i = 0; i <list.size(); i++) {
			FamilyMoitorRecords records = list.get(i);
			if(null!=records){
				String key = typeMap.get(records.getRecordType());
				resultMap.put(key, records.getRecordId());
			}
		}
		return resultMap;
	}
}