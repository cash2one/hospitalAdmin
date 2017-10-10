package com.jumper.hospital.dao.impl;
/**
 * 远程监控医院Dao实现类
 * @author rent
 * @date 2015-10-10
 */
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.MonitorHospitalDao;
import com.jumper.hospital.entity.MonitorHospital;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.utils.TimeUtils;

@Repository
public class MonitorHospitalDaoImpl extends BaseDaoImpl<MonitorHospital, Integer> implements MonitorHospitalDao {

	private static final Logger logger = Logger.getLogger(MonitorHospitalDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<MonitorHospital> findHospitalListByProvinceAndCity(Integer provinceId, Integer cityId) {
		Criteria c = createCriteria();
		Criteria c1 = c.createCriteria("hospitalId");
		c1.add(Restrictions.eq("province.id", provinceId));
		c1.add(Restrictions.eq("city.id", cityId));
		c.addOrder(Order.desc("addTime"));
		List<MonitorHospital> list = c.list();
		if(ArrayUtils.isNotEmpty(list)){
			return list;
		}
		return null;
	}

	@Override
	public MonitorHospital findMonitorHospitalByHospitalId(Integer hospitalId) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("hospitalId.id", hospitalId));
			c.setMaxResults(1);
			MonitorHospital monitorHospital = (MonitorHospital) c.uniqueResult();
			if(monitorHospital != null){
				return monitorHospital;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(TimeUtils.getCurrentTime("")+"--DaoImpl通过医院ID查询医院开通的远程监控信息异常!异常原因:"+e.getMessage());
			return null;
		}
	}

}
