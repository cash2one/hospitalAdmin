package com.jumper.hospital.dao.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.jumper.hospital.dao.HospitalInfoDao;
import com.jumper.hospital.entity.HospitalInfo;
/**
 * 医院信息
 * @author tanqing
 *
 */
@Repository
public class HospitalInfoDaoImpl extends BaseDaoImpl<HospitalInfo, Integer> implements HospitalInfoDao{

	@SuppressWarnings("unchecked")
	public List<HospitalInfo> findForAutoComplete(String q) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.like("name", q, MatchMode.ANYWHERE));
			return c.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
