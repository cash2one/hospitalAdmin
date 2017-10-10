package com.jumper.hospital.dao.impl;
/**
 * 常用授课医师信息Dao实现类
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.SchoolCommonDoctorDao;
import com.jumper.hospital.entity.SchoolCommonDoctor;

@Repository
public class SchoolCommonDoctorDaoImpl extends BaseDaoImpl<SchoolCommonDoctor, Integer> implements SchoolCommonDoctorDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SchoolCommonDoctor> getSchoolCommonDoctorByHospital(Integer hospitalId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", hospitalId));
		c.addOrder(Order.desc("id"));
		return c.list();
	}

	@Override
	public boolean checkDoctorHasExist(String doctorName, String doctorTitle, Integer hospitalId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", hospitalId));
		c.add(Restrictions.eq("doctorName", doctorName));
		c.add(Restrictions.eq("doctorTitle", doctorTitle));
		c.setProjection(Projections.rowCount());
		long count = (Long) c.uniqueResult();
		if(count > 0){
			return true;
		}
		return false;
	}

}
