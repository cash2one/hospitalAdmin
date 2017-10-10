package com.jumper.hospital.dao.impl;
/**
 * 远程监控订单Dao层实现类
 * @author rent
 * @date 2015-09-18
 */
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.MonitorHospitalDao;
import com.jumper.hospital.dao.MonitorOrderDao;
import com.jumper.hospital.entity.MonitorHospital;
import com.jumper.hospital.entity.MonitorOrder;
import com.jumper.hospital.enums.RemoteType;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.TimeUtils;

@Repository
public class MonitorOrderDaoImpl extends BaseDaoImpl<MonitorOrder, Integer> implements MonitorOrderDao {

	@Autowired
	private MonitorHospitalDao monitorHospitalDaoImpl;
	
	@Override
	public Page<MonitorOrder> findOrderList(Integer type, String searchKey, String startTime, String endTime, Page<MonitorOrder> page, 
			Integer hospitalId, Integer remoteType, Integer expireType, String invalidStartTime, String invalidEndTime) {
		try {
			if(hospitalId == null || hospitalId == 0){
				return null;
			}
			MonitorHospital mHospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(hospitalId);
			
			Criteria c = createCriteria();
			if(mHospital == null){
				return new Page<MonitorOrder>();
			}
			c.add(Restrictions.eq("monitorHospitalId.id", mHospital.getId()));
			if(type == 0){/** 今天 **/
				c.add(Restrictions.ge("addTime", TimeUtils.getCurrentStartTime(0, new Date())));
				if(remoteType == 0){
					c.add(Restrictions.eq("remoteType", RemoteType.常规监护));
				}else if(remoteType == 1){
					c.add(Restrictions.eq("remoteType", RemoteType.实时监护));
				}else{
					c.add(Restrictions.eq("remoteType", RemoteType.院内监护));
				}
			}else if(type == 2){/** 失效订单 **/
				/** c.add(Restrictions.or(Restrictions.eq("isEffective", true), Restrictions.eq("leftCount", 0))); **/
				if(expireType != null){
					/** 消费结束 **/
					if(expireType == 0){
						c.add(Restrictions.eq("leftCount", 0));
					}else{
						c.add(Restrictions.eq("isEffective", true));
					}
				}
				if(StringUtils.isNotEmpty(invalidStartTime)){
					c.add(Restrictions.ge("expireTime", TimeUtils.getTimestampDate(invalidStartTime, Consts.FORMAT_TIME_THREE)));
				}
				if(StringUtils.isNotEmpty(invalidEndTime)){
					c.add(Restrictions.le("expireTime", TimeUtils.getTimestampDate(invalidEndTime+" 23:59:59", Consts.FORMAT_TIME_ONE)));
				}
			}else if(type == 1){
				/** 全部订单不包含失效 **/
				c.add(Restrictions.eq("isEffective", false));
				if(remoteType == 0){
					c.add(Restrictions.eq("remoteType", RemoteType.常规监护));
				}else if(remoteType == 1){
					c.add(Restrictions.eq("remoteType", RemoteType.实时监护));
				}else{
					c.add(Restrictions.eq("remoteType", RemoteType.院内监护));
				}
				c.add(Restrictions.not(Restrictions.eq("leftCount", 0)));
				if(StringUtils.isNotEmpty(startTime)){
					c.add(Restrictions.ge("addTime", TimeUtils.getTimestampDate(startTime, Consts.FORMAT_TIME_THREE)));
				}
				if(StringUtils.isNotEmpty(endTime)){
					c.add(Restrictions.le("addTime", TimeUtils.getTimestampDate(endTime+" 23:59:59", Consts.FORMAT_TIME_ONE)));
				}
			}
			
			if(StringUtils.isNotEmpty(searchKey)){
				Criteria c1 = c.createCriteria("monitorUserId");
				if(StringUtils.isNumeric(searchKey)){
					c1.add(Restrictions.like("mobile", searchKey, MatchMode.START));
				}else{
					c1.add(Restrictions.like("realName", searchKey, MatchMode.START));
				}
			}
			c.addOrder(Order.desc("addTime"));
			Page<MonitorOrder> pageData = findPageByCriteria(page, c);
			return pageData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<MonitorOrder> findMonitorOrderList(Page<MonitorOrder> page,
			Integer id, String searchKey,String startTime,String endTime) {
		if(id == null || id == 0){
			return null;
		}
		MonitorHospital mHospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(id);
		
		Criteria c = createCriteria();
		if(mHospital == null){
			return null;
		}
		c.add(Restrictions.eq("monitorHospitalId.id", mHospital.getId()));
		if(StringUtils.isNotEmpty(searchKey)){
			Criteria c1 = c.createCriteria("monitorUserId");
			if(StringUtils.isNumeric(searchKey)){
				c1.add(Restrictions.like("mobile", searchKey, MatchMode.ANYWHERE));
			}else{
				c1.add(Restrictions.like("realName", searchKey, MatchMode.ANYWHERE));
			}
		}
		
		if(StringUtils.isNotEmpty(startTime)){
			c.add(Restrictions.ge("addTime", TimeUtils.getTimestampDate(startTime, Consts.FORMAT_TIME_THREE)));
		}
		if(StringUtils.isNotEmpty(endTime)){
			c.add(Restrictions.le("addTime", TimeUtils.getTimestampDate(endTime, Consts.FORMAT_TIME_THREE)));
		}
		c.addOrder(Order.desc("addTime"));
		Page<MonitorOrder> pageData = findPageByCriteria(page, c);
		
		return pageData;
	}

	@Override
	public Double getProfits(String sql) {
		return (Double) getSession().createSQLQuery(sql).uniqueResult();
	}

	@Override
	public Integer getRemoteCountByHosptial(Integer id) {
		try {
			Criteria c = createCriteria();
			Criteria c1 = c.createCriteria("monitorHospitalId");
			c1.add(Restrictions.eq("hospitalId.id", id));
			c.setProjection(Projections.rowCount());
			long count = (Long) c.uniqueResult();
			return (int) count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Double getSumTrueCost(String sql) {
		Double profit = null;
		try {
			profit = (Double) getSession().createSQLQuery(sql).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profit;
	}

	@Override
	public Double getSumMoney(String sql) {
		return (Double) getSession().createSQLQuery(sql).uniqueResult();
	}

	@Override
	public Double getSumTrueCostByConds(String sql) {
		return (Double) getSession().createSQLQuery(sql).uniqueResult();
	}

	@Override
	public Double getSumMoneyByConds(String sql) {
		return (Double) getSession().createSQLQuery(sql).uniqueResult();
	}

	@Override
	public boolean doCheckRoutineOrderHasUsedUp(Integer hospitalId, RemoteType type) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("remoteType", type));
			c.add(Restrictions.gt("leftCount", 0));
			c.add(Restrictions.eq("isEffective", false));
			Criteria c1 = c.createCriteria("monitorHospitalId");
			c1.add(Restrictions.eq("hospitalId.id", hospitalId));
			c.setProjection(Projections.rowCount());
			long orderCount = (Long) c.uniqueResult();
			return orderCount > 0 ? false : true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


}