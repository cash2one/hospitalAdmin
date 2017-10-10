package com.jumper.hospital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.HospitalDoctorMajorDao;
import com.jumper.hospital.entity.HospitalDoctorMajor;
@Repository
public class HospitalDoctorMajorDaoImpl extends BaseDaoImpl<HospitalDoctorMajor, Integer> implements
		HospitalDoctorMajorDao {

	/**
	 * 获取所有的科室信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HospitalDoctorMajor> getAllMajorList() {
		Criteria c = createCriteria();
		return c.list();
	}

	@Override
	public HospitalDoctorMajor findMajorInfoById(int id) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("id", id));
		return (HospitalDoctorMajor) c.uniqueResult();
	}
}
