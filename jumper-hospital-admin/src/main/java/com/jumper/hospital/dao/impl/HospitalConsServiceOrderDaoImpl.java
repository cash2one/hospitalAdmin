package com.jumper.hospital.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.HospitalConsServiceOrderDao;
import com.jumper.hospital.entity.HospitalConsServiceOrder;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.TimeUtils;

@Repository
public class HospitalConsServiceOrderDaoImpl extends
		BaseDaoImpl<HospitalConsServiceOrder, Integer> implements
		HospitalConsServiceOrderDao {

	@Override
	public Page<HospitalConsServiceOrder> findVisitOrderList(
			Page<HospitalConsServiceOrder> page, Integer id) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalInfo.id", id));
		c.add(Restrictions.eq("isEnabled", 0));
		c.add(Restrictions.eq("status", 1));
		c.addOrder(Order.desc("addTime"));
		return findPageByCriteria(page, c);
	}

	@Override
	public Page<HospitalConsServiceOrder> findVisitOrderListByConds(
			Page<HospitalConsServiceOrder> page, Integer id, String searchKey,
			String startTime, String endTime) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalInfo.id", id));
		c.add(Restrictions.eq("isEnabled", 0));
		c.add(Restrictions.eq("status", 1));
		c.createAlias("userInfo","u");
		if(!StringUtils.isEmpty(searchKey)){
			c.add(Restrictions.or(Restrictions.like("u.nickName",searchKey,MatchMode.ANYWHERE), Restrictions.like("u.mobile",searchKey,MatchMode.ANYWHERE)));
		}
		if(StringUtils.isNotEmpty(startTime)){
			c.add(Restrictions.ge("addTime", TimeUtils.getTimestampDate(startTime, Consts.FORMAT_TIME_THREE)));
		}
		if(StringUtils.isNotEmpty(endTime)){
			c.add(Restrictions.le("addTime", TimeUtils.getTimestampDate(endTime+" 23:59:59", Consts.FORMAT_TIME_ONE)));
		}
		c.addOrder(Order.desc("addTime"));
		return findPageByCriteria(page, c);
	}

	@Override
	public Page<HospitalConsServiceOrder> getConsServiceOrderList(
			Page<HospitalConsServiceOrder> page, Integer hospitalId, Integer state,
			Integer withdrawalsId) {
		
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalInfo.id", hospitalId));
		if(state!=null&&state.intValue()!=-1){
			c.add(Restrictions.eq("status", state));
		}
		if(withdrawalsId!=null&&withdrawalsId.intValue()!=-1){
			c.add(Restrictions.eq("withdrawalsId", withdrawalsId)); 
		}
	
		c.addOrder(Order.desc("addTime"));
		return findPageByCriteria(page, c);
	}

	@Override
	public Page<HospitalConsServiceOrder> getUnBlanceServiceOrderList(
			Page<HospitalConsServiceOrder> page, Integer hospitalId,Object[] idArr,String startTime,String endTime) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalInfo.id", hospitalId));
		
	    if(idArr!=null&&idArr.length!=0){  
	      //查询所有withdrawalsId 为未结算与withdrawalsId 为null的数据
		  c.add(Restrictions.or(Restrictions.in("withdrawalsId", idArr),Restrictions.or(Restrictions.isNull("withdrawalsId"))));//过滤所有已结算的月度
	    }else{
	    	//查询所有为null的数据
	    	c.add(Restrictions.or(Restrictions.isNull("withdrawalsId")));
	    }
	
	    c.add(Restrictions.ne("status", 0));
	    
		if(StringUtils.isNotBlank(startTime)){
			SimpleDateFormat dateFormat = new SimpleDateFormat(
    				"yyyy-MM-dd");
    		try {
    		   Date	dateTime =dateFormat.parse(startTime);  //大于等于起始时间
    		   c.add(Restrictions.ge("addTime", dateTime));
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
		}
		if(StringUtils.isNotBlank(endTime)){
			SimpleDateFormat dateFormat = new SimpleDateFormat(
    				"yyyy-MM-dd");
    		try {
    		   Date	dateTime =dateFormat.parse(endTime);  //小于等于结束时间
    		   c.add(Restrictions.le("addTime", dateTime));
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
		}
		c.addOrder(Order.desc("addTime"));//降序
		return findPageByCriteria(page, c);
	}


}
