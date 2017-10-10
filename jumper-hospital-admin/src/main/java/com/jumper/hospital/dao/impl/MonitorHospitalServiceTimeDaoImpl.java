package com.jumper.hospital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.MonitorHospitalServiceTimeDao;
import com.jumper.hospital.entity.MonitorHospitalServiceTime;


@Repository
public class MonitorHospitalServiceTimeDaoImpl extends BaseDaoImpl<MonitorHospitalServiceTime, Integer>
		implements MonitorHospitalServiceTimeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorHospitalServiceTime> getHospitalDutyRoster(
			Integer hospitalId) {

		Criteria c = createCriteria();
		//Criteria c1 = c.createCriteria();
		//c1.add(Restrictions.eq("hospitalId", hospitalId));
		c.add(Restrictions.eq("hospitalId", hospitalId));
		c.addOrder(Order.asc("dayOfWeek"));
		c.addOrder(Order.asc("startTime"));
		List<MonitorHospitalServiceTime> list = c.list();
		return list;
	}
}
