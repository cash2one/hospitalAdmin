package com.jumper.hospital.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.TimestampType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.MonitorHospitalDao;
import com.jumper.hospital.dao.PayRefundAnnalDao;
import com.jumper.hospital.entity.MonitorHospital;
import com.jumper.hospital.entity.MonitorOrder;
import com.jumper.hospital.entity.PayRefundAnnal;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.TimeUtils;

@Repository
public class PayRefundAnnalDaoImpl extends BaseDaoImpl<PayRefundAnnal, Integer> implements PayRefundAnnalDao {

	@Autowired
	private MonitorHospitalDao monitorHospitalDaoImpl;
	
	/**
	 * 获取退款单列表
	 * @param page
	 * @param hospitalId
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public Page<PayRefundAnnal> findPayRefundAnnalList(Page<PayRefundAnnal> page, Integer hospitalId, String startTime, String endTime) {
		try {
			if(hospitalId == null || hospitalId == 0){
				return null;
			}
			MonitorHospital mHospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(hospitalId);
			if(mHospital == null){
				return new Page<PayRefundAnnal>();
			}
			//HQL分页查询
			StringBuffer baseHql = new StringBuffer();
			baseHql.append(" FROM PayRefundAnnal payRefundAnnal, MonitorOrder monitorOrder ")
				   .append(" WHERE payRefundAnnal.orderId.orderNo = monitorOrder.orderId ")
				   .append("      AND monitorOrder.monitorHospitalId.hospitalId.id = ").append(mHospital.getHospitalId().getId())
				   .append("      AND payRefundAnnal.refundType = '1'")
				   .append("      AND payRefundAnnal.serviceType = 3 ")
				   .append("      AND monitorOrder.dealStates != 4 ")
				   .append("      AND monitorOrder.dealStates != 3 ");
			
			if(StringUtils.isNotEmpty(startTime)){
				baseHql.append(" AND payRefundAnnal.refundTime >= :startsTime ");
			}
			if(StringUtils.isNotEmpty(endTime)){
				baseHql.append(" AND payRefundAnnal.refundTime <= :endsTime ");
			}
			baseHql.append(" ORDER BY payRefundAnnal.id DESC ");
			
			String qHql = "SELECT new list(payRefundAnnal, monitorOrder) " + baseHql.toString();
			String cHql = "SELECT COUNT(*) " + baseHql.toString();
			
			Query cQuery = this.getSessionFactory().getCurrentSession().createQuery(cHql);
			if(StringUtils.isNotEmpty(startTime)){
				cQuery.setParameter("startsTime", TimeUtils.getTimestampDate(startTime+" 00:00:00", Consts.FORMAT_TIME_ONE), TimestampType.INSTANCE);
			}
			if(StringUtils.isNotEmpty(endTime)){
				cQuery.setParameter("endsTime", TimeUtils.getTimestampDate(endTime+" 23:59:59", Consts.FORMAT_TIME_ONE), TimestampType.INSTANCE);
			}
			
			Query qQuery = this.getSessionFactory().getCurrentSession().createQuery(qHql);
			if(StringUtils.isNotEmpty(startTime)){
				qQuery.setParameter("startsTime", TimeUtils.getTimestampDate(startTime+" 00:00:00", Consts.FORMAT_TIME_ONE), TimestampType.INSTANCE);
			}
			if(StringUtils.isNotEmpty(endTime)){
				qQuery.setParameter("endsTime", TimeUtils.getTimestampDate(endTime+" 23:59:59", Consts.FORMAT_TIME_ONE), TimestampType.INSTANCE);
			}
			qQuery.setFirstResult(page.getFirst() - 1).setMaxResults(page.getPageSize());
			//查询总记录数
			long count = ((Long)cQuery.uniqueResult()).longValue();
			List<PayRefundAnnal> rsList = new ArrayList<PayRefundAnnal>();
			
			for(List<Object> rs : (List<List<Object>>)qQuery.list()) {
				PayRefundAnnal item1 = (PayRefundAnnal)rs.get(0);
				MonitorOrder item2 = (MonitorOrder)rs.get(1);
				
				item1.setMonitorOrder(item2);
				rsList.add(item1);
			}
			
			//计算分页结果
			page.setTotalCount(count);
			page.setResult(rsList);
		
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 查找订单的退款或申诉记录
	 * @param orderId
	 * @param refundType
	 * @return
	 */
	public PayRefundAnnal findPayRefund(Integer orderId, String refundType) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("orderId.id", orderId));
			c.add(Restrictions.eq("refundType", refundType));
			
			PayRefundAnnal payRefundAnnal = (PayRefundAnnal)c.uniqueResult();
			
			return payRefundAnnal;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void savePayRefundAnnal(PayRefundAnnal annl) {
		Session session=this.getSessionFactory().openSession();
		Transaction trans=session.beginTransaction();
		session.save(annl);
		trans.commit();
		session.flush();
		session.close();
	}
}
