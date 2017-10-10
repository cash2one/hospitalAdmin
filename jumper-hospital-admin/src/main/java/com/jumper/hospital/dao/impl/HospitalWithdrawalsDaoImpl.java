package com.jumper.hospital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.HospitalWithdrawalsDao;
import com.jumper.hospital.entity.HospitalWithdrawals;

@Repository
public class HospitalWithdrawalsDaoImpl extends BaseDaoImpl<HospitalWithdrawals, Integer> implements  HospitalWithdrawalsDao{

	@Override
	public Page<HospitalWithdrawals> getWithdrawalsList(Page<HospitalWithdrawals> page, Integer hospitalId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", hospitalId));
		c.addOrder(Order.desc("addTime"));
		c.add(Restrictions.eq("state", 1));//已完成的提现列表
		return findPageByCriteria(page, c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getUnBlancedWithdrawals(Integer hospitalId) {
		String hql="select id from HospitalWithdrawals where hospitalId=? and state=? ";
		Query query=createQuery(hql, new Object[]{hospitalId,0});
		return query.list();
	}

}
