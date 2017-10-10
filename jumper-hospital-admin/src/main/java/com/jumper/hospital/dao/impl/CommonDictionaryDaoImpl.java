package com.jumper.hospital.dao.impl;
/**
 * 字典Dao实现类
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.CommonDictionaryDao;
import com.jumper.hospital.entity.CommonDictionary;

@Repository
public class CommonDictionaryDaoImpl extends BaseDaoImpl<CommonDictionary, Integer> implements CommonDictionaryDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CommonDictionary> getByRelationIdAndType(Integer relationId, Integer type) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("relationId", relationId));
		c.add(Restrictions.eq("type", type));
		c.addOrder(Order.asc("id"));
		return c.list();
	}

	@Override
	public Integer getCommonDictionayCount(Integer type, Integer hospitalId) {
		Criteria cri=this.createCriteria();
		cri.add(Restrictions.eq("relationId", hospitalId));
		cri.add(Restrictions.eq("type", type));
		return this.countCriteriaResult(cri);
		
	}

}
