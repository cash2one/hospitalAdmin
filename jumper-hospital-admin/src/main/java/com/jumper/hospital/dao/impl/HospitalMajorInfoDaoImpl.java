package com.jumper.hospital.dao.impl;


import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.HospitalMajorInfoDao;
import com.jumper.hospital.entity.HospitalMajorInfo;

@Repository
public class HospitalMajorInfoDaoImpl extends BaseDaoImpl<HospitalMajorInfo, Integer> implements
		HospitalMajorInfoDao {

	@Override
	public void addMajorInfo(int hospitalId, int[] majors) {
		for (int i = 0; i < majors.length; i++) {
			HospitalMajorInfo majorInfo = new HospitalMajorInfo();
			majorInfo.setAddTime(new Date());
			majorInfo.setHospitalId(hospitalId);
			majorInfo.setMajorId(majors[i]);
			save(majorInfo);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HospitalMajorInfo> findHosMajors(Integer id) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", id));
		c.add(Restrictions.eq("isdelete", 0));
		return c.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HospitalMajorInfo> findHosMajorsByIsnetwork(Integer id,Integer isnetwork) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", id));
		c.add(Restrictions.eq("isnetwork", isnetwork));
		c.add(Restrictions.eq("isdelete", 0));
		return c.list();
	}


}
