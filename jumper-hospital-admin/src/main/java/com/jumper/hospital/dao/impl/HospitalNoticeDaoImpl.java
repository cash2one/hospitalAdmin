package com.jumper.hospital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.HospitalNoticeDao;
import com.jumper.hospital.entity.HospitalNotice;

@Repository
public class HospitalNoticeDaoImpl extends BaseDaoImpl<HospitalNotice, Integer>
		implements HospitalNoticeDao {

	@Override
	public Page<HospitalNotice> findNoticeList(Page<HospitalNotice> page,
			Integer id) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalInfo.id", id));
		c.add(Restrictions.eq("isDelete", 0));
		c.addOrder(Order.desc("status"));
		c.addOrder(Order.desc("endTime"));
		return findPageByCriteria(page, c);
		
	}

	//找出status为1的公告
	@SuppressWarnings("unchecked")
	public List<HospitalNotice> findNoticeList1(Integer id){
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalInfo.id", id));
		c.add(Restrictions.eq("isDelete", 0));
		c.add(Restrictions.eq("status", 1));
		return c.list();
	}
	
	//找出未发布和已过期的公告
	@SuppressWarnings("unchecked")
	public List<HospitalNotice> findNoticeList2(Integer id){
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalInfo.id", id));
		c.add(Restrictions.eq("isDelete", 0));
		c.add(Restrictions.not(Restrictions.eq("status", 1)));
		c.add(Restrictions.or(Restrictions.eq("status", 0), Restrictions.eq("status", -1)));
		c.addOrder(Order.desc("endTime"));
		return c.list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HospitalNotice> findNotices(int hospitalId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalInfo.id", hospitalId));
		c.add(Restrictions.eq("isDelete", 0));
		return c.list();
	}
	
}
