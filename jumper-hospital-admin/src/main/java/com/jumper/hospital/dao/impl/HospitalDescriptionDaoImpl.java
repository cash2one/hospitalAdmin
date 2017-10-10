package com.jumper.hospital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.HospitalDescriptionDao;
import com.jumper.hospital.entity.HospitalDescription;
import com.jumper.hospital.utils.ArrayUtils;
@Repository
public class HospitalDescriptionDaoImpl extends BaseDaoImpl<HospitalDescription, Integer> implements HospitalDescriptionDao {
	/**
	 * 通过类型描述类型和医院id获得医院的描述信息
	 * @param type
	 * @return
	 */
	@Override
	public List<HospitalDescription> getHospDescByType(Integer id,int type) {
		Criteria c = createCriteria();
		if(null != id && 0 != id ){
			c.add(Restrictions.eq("hospitalId.id", id));
		}
		if(type < 2){
			c.add(Restrictions.eq("type", type));
		}
		//以addTime降序排列
		c.addOrder(Order.desc("addTime"));
		@SuppressWarnings("unchecked")
		List<HospitalDescription> list=c.list();
		if(ArrayUtils.isNotEmpty(list)){
			return list;
		}
		return null;
	}

}
