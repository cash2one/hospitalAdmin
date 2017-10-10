package com.jumper.hospital.dao.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.HospitalSubordinateDao;
import com.jumper.hospital.entity.HospitalSubordinate;
/**
 * 医院从属关系信息 数据访问实现类
 * @author rengn 任冠男
 *
 */
@Repository
public class HospitalSubordinateDaoImpl extends BaseDaoImpl<HospitalSubordinate, Integer> implements HospitalSubordinateDao{
//	@Autowired
//	private HospitalInfoDao hospitalInfoDaoImpl;
	@SuppressWarnings("unchecked")
	public List<HospitalSubordinate> findHospitalSubordinateList(Integer hospitalId){
	
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId.id", hospitalId));
		List<HospitalSubordinate> list = c.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> findSubordinateList(Integer hospitalId){
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("hospitalId.id", hospitalId));
			c.setProjection(Projections.groupProperty("subordinateId.id"));
			return c.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Page<HospitalSubordinate> getHospitalSubordinateList(Integer hospitalId, Page<HospitalSubordinate> page) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId.id", hospitalId));
		c.addOrder(Order.desc("createTime"));
		return findPageByCriteria(page, c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean isSubordinatehospital(Integer hospitalId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("subordinateId.id", hospitalId));
		List<HospitalSubordinate> list = c.list();
		if(list.size()>0){
			return true;
		}
		return false;
	}
}
