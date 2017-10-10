package com.jumper.hospital.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.HospitalTodayRecommendDao;
import com.jumper.hospital.entity.HospitalTodayRecommend;

@Repository
public class HospitalTodayRecommendDaoImpl extends
		BaseDaoImpl<HospitalTodayRecommend, Integer> implements
		HospitalTodayRecommendDao {

	@Override
	public HospitalTodayRecommend findhospitalTodayRecommend(Integer id) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalInfo.id", id));
		HospitalTodayRecommend hospitalTodayRecommend = (HospitalTodayRecommend) c.uniqueResult();
		return hospitalTodayRecommend;
	}
	

}
