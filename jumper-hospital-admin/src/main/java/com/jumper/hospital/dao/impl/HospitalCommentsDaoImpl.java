package com.jumper.hospital.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.HospitalCommentsDao;
import com.jumper.hospital.entity.HospitalComments;

@Repository
public class HospitalCommentsDaoImpl extends BaseDaoImpl<HospitalComments, Integer> implements
		HospitalCommentsDao {

	@Override
	public HospitalComments getCommentByConsId(Integer id) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("consId", id));
		c.setMaxResults(1);
		HospitalComments comments = (HospitalComments) c.uniqueResult();
		if(comments!=null){
			return comments;
		}
		return null;
	}
	

}
