package com.jumper.hospital.dao.impl;
/**
 * 根据医院id或者该医院的微信信息
 */
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.WXpayConfigDao;
import com.jumper.hospital.entity.WXpayConfig;

@Repository
public class WXpayConfigDaoImpl extends BaseDaoImpl<WXpayConfig, Integer> implements WXpayConfigDao {
	
	@Override
	public WXpayConfig getOrderWXpayConfig(Integer hospitalId) {
		try {
			if(hospitalId == null || hospitalId == 0){
				return null;
			}
			//HQL分页查询
			String hSql = (" FROM  WXpayConfig wxpayConfig  WHERE wxpayConfig.hospitalId = " + hospitalId);
			
			Query cQuery = this.getSessionFactory().getCurrentSession().createQuery(hSql);
			WXpayConfig wxpayConfig = ((WXpayConfig)cQuery.uniqueResult());
			return wxpayConfig;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}