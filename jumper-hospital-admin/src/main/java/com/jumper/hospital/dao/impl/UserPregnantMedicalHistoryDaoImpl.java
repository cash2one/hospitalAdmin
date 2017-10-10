package com.jumper.hospital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.UserPregnantMedicalHistoryDao;
import com.jumper.hospital.entity.UserPregnantMedicalHistory;

@Repository
public class UserPregnantMedicalHistoryDaoImpl extends
		BaseDaoImpl<UserPregnantMedicalHistory, Integer> implements
		UserPregnantMedicalHistoryDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPregnantMedicalHistory> findPregnantMedicalHistory(int id,
			int i) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("userInfo.id", id));
		c.add(Restrictions.eq("type", i));
		return c.list();
	}
	@Override
	public String findUserPastHistoryById(int id){
		return (String) getSession().createSQLQuery("select name from user_past_history where id="+id).uniqueResult();
	}
	@Override
	public String findGeneticHistoryById(Integer objectId) {
		return (String) getSession().createSQLQuery("select name from user_genetic_history where id="+objectId).uniqueResult();
	}
	@Override
	public String findMaternalHistoryById(Integer objectId) {
		return (String) getSession().createSQLQuery("select name from user_maternal_history where id="+objectId).uniqueResult();
	}


}
