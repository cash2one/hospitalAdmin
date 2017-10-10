package com.jumper.hospital.dao.impl;

import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.HospitalCloudVisitorDao;
import com.jumper.hospital.entity.CloudVisitorAccount;

@Repository
public class HospitalCloudVisitorDaoImpl extends BaseDaoImpl<CloudVisitorAccount, Integer> implements HospitalCloudVisitorDao{

	/*public Boolean checkWorkNumHasExist(String doctorWorkNum){
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("doctorWorkNum", doctorWorkNum));
			c.setProjection(Projections.rowCount());
			long count = (Long) c.uniqueResult();
			return count > 0;
		} catch (HibernateException e) {
			e.printStackTrace();
			return true;
		}
		
	}*/
}
