package com.jumper.hospital.dao.impl;
/**
 * 已消费订单临时表
 * @author rent
 * @date 2015-11-24
 */
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.MonitorHospitalDao;
import com.jumper.hospital.dao.MonitorOrderConsumerTempDao;
import com.jumper.hospital.entity.MonitorHospital;
import com.jumper.hospital.entity.MonitorOrderConsumerTemp;
import com.jumper.hospital.enums.RemoteType;

@Repository
public class MonitorOrderConsumerTempDaoImpl extends BaseDaoImpl<MonitorOrderConsumerTemp, Integer> implements MonitorOrderConsumerTempDao {

	@Autowired
	private MonitorHospitalDao monitorHospitalDaoImpl;
	
	@Override
	public Page<MonitorOrderConsumerTemp> findRealTimeOrder(Page<MonitorOrderConsumerTemp> page, Integer hospitalId) {
		try {
			MonitorHospital mHospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(hospitalId);
			Criteria c = createCriteria();
			Criteria c1 = c.createCriteria("monitorOrderId");
			c1.add(Restrictions.eq("monitorHospitalId", mHospital));
			c1.add(Restrictions.or(Restrictions.eq("remoteType", RemoteType.实时监护), Restrictions.eq("remoteType", RemoteType.院内监护)));
			c.addOrder(Order.asc("applyTime"));
			Page<MonitorOrderConsumerTemp> pageData = findPageByCriteria(page, c);
			return pageData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
