/**
 * 
 */
package com.jumper.hospital.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.HospitalDoctorTitleDao;
import com.jumper.hospital.entity.HospitalDoctorTitle;

/**
 * @author huangcr
 *
 * 2017-7-13
 */
@Repository
public class HospitalDoctorTitleDaoImpl extends BaseDaoImpl<HospitalDoctorTitle, Integer> implements HospitalDoctorTitleDao{
	
	@Override
	public HospitalDoctorTitle findById(Integer id){
		Criteria c = createCriteria();
		c.add(Restrictions.eq("id", id));
		if(c.list().size()>0){
			return (HospitalDoctorTitle) c.list().get(0);
		}else{
			return null;
		}
	}
}
