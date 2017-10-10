package com.jumper.hospital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.MonitorSettingDao;
import com.jumper.hospital.entity.MonitorSetting;
import com.jumper.hospital.enums.RemoteType;

@Repository
public class MonitorSettingDaoImpl extends BaseDaoImpl<MonitorSetting, Integer> implements MonitorSettingDao {

	@SuppressWarnings("unchecked")
	public List<MonitorSetting> findByHospitalIdAndRemoteType(Integer hospitalId) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("isOpen", true));
			Criteria c1 = c.createCriteria("monitorHospitalId");
			c1.add(Restrictions.eq("hospitalId.id", hospitalId));
			
			List<MonitorSetting> list = c.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean checkMonitorIsOpen(Integer hospitalId, RemoteType type) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("type", type));
			c.add(Restrictions.eq("isOpen", true));
			Criteria c1 = c.createCriteria("monitorHospitalId");
			c1.add(Restrictions.eq("hospitalId.id", hospitalId));
			c.setProjection(Projections.rowCount());
			long result = (Long) c.uniqueResult();
			boolean flag = result > 0 ? true : false;
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
