package com.jumper.hospital.dao.impl;
/**
 * 根据医院id或者该医院的支付宝信息
 */
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.AlipayConfigDao;
import com.jumper.hospital.entity.AlipayConfig;

@Repository
public class AlipayConfigDaoImpl extends BaseDaoImpl<AlipayConfig, Integer> implements AlipayConfigDao {
	
	@Override
	public AlipayConfig getOrderAlipayConfig(Integer hospitalId) {
		try {
			if(hospitalId == null || hospitalId == 0){
				return null;
			}
			//HQL分页查询
			String hSql = (" FROM  AlipayConfig alipayConfig  WHERE alipayConfig.hospitalId = " + hospitalId);
			
			Query cQuery = this.getSessionFactory().getCurrentSession().createQuery(hSql);
			AlipayConfig AlipayConfig = ((AlipayConfig)cQuery.uniqueResult());
			return AlipayConfig;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}