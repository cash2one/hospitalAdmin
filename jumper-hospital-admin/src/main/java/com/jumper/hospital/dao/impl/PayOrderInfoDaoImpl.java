package com.jumper.hospital.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.PayOrderInfoDao;
import com.jumper.hospital.entity.PayOrderInfo;

@Repository
public class PayOrderInfoDaoImpl extends BaseDaoImpl<PayOrderInfo, Integer> implements PayOrderInfoDao {

	/**
	 * 查询支付订单
	 * @param orderNo
	 * @return
	 */
	public PayOrderInfo getPayOrderInfoByOrderNo(String orderNo) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("orderNo", orderNo));
			
			PayOrderInfo result = (PayOrderInfo)c.uniqueResult();
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 查询支付列表信息
	 * @param orderNoList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PayOrderInfo> getPayOrderInfoList(List<String> orderNoList) {
		try {
			Criteria c = createCriteria();
			if (!orderNoList.isEmpty()) {
				c.add(Restrictions.in("orderNo", orderNoList));
				return c.list();
			}
 			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param orderNo
	 * @return
	 */
	public PayOrderInfo getPayOrderInfoValid(String orderNo) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("orderNo", orderNo));
			c.add(Restrictions.isNotNull("orderPayTime"));
			
			PayOrderInfo result = (PayOrderInfo)c.uniqueResult();
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
