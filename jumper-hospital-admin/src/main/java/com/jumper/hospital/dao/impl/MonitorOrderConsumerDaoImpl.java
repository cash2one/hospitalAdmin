package com.jumper.hospital.dao.impl;
/**
 * 远程监控消费订单Dao实现类
 * @author rent
 * @date 2015-09-22
 */
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.HospitalSubordinateDao;
import com.jumper.hospital.dao.MonitorHospitalDao;
import com.jumper.hospital.dao.MonitorOrderConsumerDao;
import com.jumper.hospital.dao.MonitorUserInfoDao;
import com.jumper.hospital.entity.HospitalSubordinate;
import com.jumper.hospital.entity.MonitorHospital;
import com.jumper.hospital.entity.MonitorOrderConsumer;
import com.jumper.hospital.enums.RemoteType;
import com.jumper.hospital.enums.ReportState;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.TimeUtils;

@Repository
public class MonitorOrderConsumerDaoImpl extends BaseDaoImpl<MonitorOrderConsumer, Integer> implements MonitorOrderConsumerDao {

	@Autowired
	private MonitorHospitalDao monitorHospitalDaoImpl;
	@Autowired
	private MonitorUserInfoDao monitorUserInfoDaoImpl;
	@Autowired
	private HospitalSubordinateDao hospitalSubordinateDaoImpl;

	
	@Override
	public Page<MonitorOrderConsumer> findNotFinishOrder(Page<MonitorOrderConsumer> page, Integer hospitalId) {
		try {
			List<MonitorHospital> monitorHospitalList = new ArrayList<MonitorHospital>();
			MonitorHospital monitorHospital = new MonitorHospital();
			monitorHospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(hospitalId);
			monitorHospitalList.add(monitorHospital);
			//查询所有从属医院
			List<HospitalSubordinate> HospitalSubordinateList = hospitalSubordinateDaoImpl.findHospitalSubordinateList(hospitalId);
			for(HospitalSubordinate hospitalSubordinate : HospitalSubordinateList){
				Integer subordinateId = hospitalSubordinate.getSubordinateId().getId();
				if(subordinateId != null){
					monitorHospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(subordinateId);
					if(monitorHospital != null){
						monitorHospitalList.add(monitorHospital);
					}
				}
			}
			Criteria c = createCriteria();
			Criteria c1 = c.createCriteria("monitorOrderId");
			c1.add(Restrictions.in("monitorHospitalId", monitorHospitalList));
			c.add(Restrictions.eq("state", ReportState.待审核));
			c.addOrder(Order.asc("applyTime"));
			Page<MonitorOrderConsumer> pageData = findPageByCriteria(page, c);
			return pageData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<MonitorOrderConsumer> findFinishedOrder(Page<MonitorOrderConsumer> page, Integer hospitalId, String searchKey, String startTime, String endTime, RemoteType remoteType, Integer selectId,List<Integer> doctorIds) {
		try {
			List<MonitorHospital> monitorHospitalList = new ArrayList<MonitorHospital>();
			MonitorHospital monitorHospital = new MonitorHospital();
			//选择某个医院时
			if(selectId != null && selectId != 0){
				monitorHospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(selectId);
				monitorHospitalList.add(monitorHospital);
			}else{
				monitorHospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(hospitalId);
				monitorHospitalList.add(monitorHospital);
				//查询所有从属医院
				List<HospitalSubordinate> HospitalSubordinateList = hospitalSubordinateDaoImpl.findHospitalSubordinateList(hospitalId);
				for(HospitalSubordinate hospitalSubordinate : HospitalSubordinateList){
					Integer subordinateId = hospitalSubordinate.getSubordinateId().getId();
					if(subordinateId != null){
						monitorHospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(subordinateId);
						if(monitorHospital != null){
							monitorHospitalList.add(monitorHospital);
						}
					}
				}
			}
			Criteria c = createCriteria();
			Criteria c1 = c.createCriteria("monitorOrderId");
			if(remoteType != null ){
				c1.add(Restrictions.eq("remoteType",remoteType));
			}
			c1.add(Restrictions.in("monitorHospitalId", monitorHospitalList));
			if(StringUtils.isNotEmpty(searchKey)){
				List<Integer> list = monitorUserInfoDaoImpl.findUserInfoByNameOrMobile(searchKey);
				if(ArrayUtils.isNotEmpty(list)){
					c1.add(Restrictions.in("monitorUserId.id", list));
				}else{
					c1.add(Restrictions.eq("monitorUserId.id", 0));
				}
			}
			if(StringUtils.isNotEmpty(startTime)){
				c.add(Restrictions.ge("applyTime", TimeUtils.getTimestampDate(startTime, Consts.FORMAT_TIME_THREE)));
			}
			if(StringUtils.isNotEmpty(endTime)){
				c.add(Restrictions.le("applyTime", TimeUtils.getTimestampDate(endTime + " 23:59:59", Consts.FORMAT_TIME_ONE)));
			}
			if(null!=doctorIds&&doctorIds.size()>0){
				c.add(Restrictions.in("doctorId", doctorIds));
			}
			
			c.add(Restrictions.eq("state", ReportState.审核完成));
			c.addOrder(Order.desc("reportTime"));
			Page<MonitorOrderConsumer> pageData = findPageByCriteria(page, c);
			return pageData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<MonitorOrderConsumer> findRealTimeOrder(Page<MonitorOrderConsumer> page, Integer hospitalId) {
		try {
			MonitorHospital mHospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(hospitalId);
			Criteria c = createCriteria();
			Criteria c1 = c.createCriteria("monitorOrderId");
			c1.add(Restrictions.eq("monitorHospitalId", mHospital));
			c1.add(Restrictions.eq("remoteType", RemoteType.实时监护));
			c.addOrder(Order.asc("applyTime"));
			Page<MonitorOrderConsumer> pageData = findPageByCriteria(page, c);
			return pageData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Integer getMonitorSequence(Integer id, Timestamp applyTime) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("monitorOrderId.id", id));
		c.add(Restrictions.lt("applyTime", applyTime));
		c.setProjection(Projections.rowCount());
		Long count = (Long) c.uniqueResult();
		return (int) (count+1);
	}
	
	@Override
	public Integer finishedOrderCount(Integer hospitalId, String searchKey,String startTime, String endTime, RemoteType remoteType,Integer selectId, 
					List<Integer> doctorIds) {
		
		List<MonitorHospital> monitorHospitalList = new ArrayList<MonitorHospital>();
		MonitorHospital monitorHospital = new MonitorHospital();
		//选择某个医院时
		if(selectId != null && selectId != 0){
			monitorHospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(selectId);
			monitorHospitalList.add(monitorHospital);
		}else{
			monitorHospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(hospitalId);
			monitorHospitalList.add(monitorHospital);
			//查询所有从属医院
			List<HospitalSubordinate> HospitalSubordinateList = hospitalSubordinateDaoImpl.findHospitalSubordinateList(hospitalId);
			for(HospitalSubordinate hospitalSubordinate : HospitalSubordinateList){
				Integer subordinateId = hospitalSubordinate.getSubordinateId().getId();
				if(subordinateId != null){
					monitorHospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(subordinateId);
					if(monitorHospital != null){
						monitorHospitalList.add(monitorHospital);
					}
				}
			}
		}
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT" +
						" count(1)" +
				   " FROM" +
				        " monitor_order_consumer c" +
				   " LEFT JOIN" +
				   		" monitor_order o " +
				   " ON" +
				   		" c.monitor_order_id = o.id" +
				   " WHERE 1=1 ");
		if(remoteType != null ){
			//c1.add(Restrictions.eq("remoteType",remoteType));
			sql.append(" and o.remote_type="+remoteType.ordinal());
		}
		if(monitorHospitalList.size()>0){
			sql.append(" and o.monitor_hospital_id in( ");
			for(MonitorHospital list:monitorHospitalList){
				sql.append("'"+list.getId()+"'"+",");
			}
			sql.append("'-11'"+") ");
		}
		
		if(StringUtils.isNotEmpty(searchKey)){
			List<Integer> list = monitorUserInfoDaoImpl.findUserInfoByNameOrMobile(searchKey);
			if(ArrayUtils.isNotEmpty(list)){
				sql.append("  and o.monitor_user_id  in ( ");
				for(int i=0;i<list.size();i++){
					sql.append("'"+list.get(i)+"'"+",");
				}
				sql.append("'-11'"+")");
				//c1.add(Restrictions.in("monitorUserId.id", list));
			}else{
				sql.append("  and o.monitor_user_id = '0' ");
				//c1.add(Restrictions.eq("monitorUserId.id", 0));
			}
		}
		
		if(StringUtils.isNotEmpty(startTime)){
			//c.add(Restrictions.ge("applyTime", TimeUtils.getTimestampDate(startTime, Consts.FORMAT_TIME_THREE)));
			sql.append("and c.apply_time > '"+startTime+"'");
		}
		if(StringUtils.isNotEmpty(endTime)){
			//c.add(Restrictions.le("applyTime", TimeUtils.getTimestampDate(endTime + " 23:59:59", Consts.FORMAT_TIME_ONE)));
			sql.append("and c.apply_time < '"+endTime+" 23:59:59'");
		}
		if(null!=doctorIds&&doctorIds.size()>0){
			sql.append("and c.doctor_id in( ");
			for(int i=0;i<doctorIds.size();i++){
				sql.append("'"+doctorIds.get(i)+"'"+",");
			}
			sql.append("'-11'"+")");
			//c.add(Restrictions.in("doctorId", doctorIds));
		}
		sql.append(" and  c.state=1 ");
		//c.add(Restrictions.eq("state", ReportState.审核完成));
		sql.append(" GROUP BY date_format(c.report_time, '%Y-%m-%d'),c.user_id");
		//String countSql = "select count(*) "+ sql +";";
		String countSql = "select count(1) from (  "+ sql +") as totalCount;";
		int count = executeCountSql(countSql);
		return count;
	}
	/**
	 * 当前医院未完成报告数
	 */
	@Override
	public Integer countNotFinishOrder(Integer hospitalId) {
		try {
			List<MonitorHospital> monitorHospitalList = new ArrayList<MonitorHospital>();
			MonitorHospital monitorHospital = new MonitorHospital();
			monitorHospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(hospitalId);
			monitorHospitalList.add(monitorHospital);
			//查询所有从属医院
			List<HospitalSubordinate> HospitalSubordinateList = hospitalSubordinateDaoImpl.findHospitalSubordinateList(hospitalId);
			for(HospitalSubordinate hospitalSubordinate : HospitalSubordinateList){
				Integer subordinateId = hospitalSubordinate.getSubordinateId().getId();
				if(subordinateId != null){
					monitorHospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(subordinateId);
					if(monitorHospital != null){
						monitorHospitalList.add(monitorHospital);
					}
				}
			}
			Criteria c = createCriteria();
			Criteria c1 = c.createCriteria("monitorOrderId");
			c1.add(Restrictions.in("monitorHospitalId", monitorHospitalList));
			c.add(Restrictions.eq("state", ReportState.待审核));
			c.addOrder(Order.asc("applyTime"));
			Integer totalNotFinishOrder= c.list().size();
			return totalNotFinishOrder;
		} catch (Exception e) { 
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 当前从属医院已审核报告  但未查阅的报告
	 */
	@Override
	public Integer totalFinishUnRedReport(Integer hospitalId) {
		try {
			List<MonitorHospital> monitorHospitalList = new ArrayList<MonitorHospital>();
			MonitorHospital monitorHospital = new MonitorHospital();
			monitorHospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(hospitalId);
			Criteria c = createCriteria();
			Criteria c1 = c.createCriteria("monitorOrderId");
			monitorHospitalList.add(monitorHospital);
			c1.add(Restrictions.in("monitorHospitalId", monitorHospitalList));
			c.add(Restrictions.eq("state", ReportState.审核完成));
			c.add(Restrictions.eq("isViewed", false));
			Integer  totalFinishUnRedReport=c.list().size();
			return totalFinishUnRedReport;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新下级医院是否评审的状态
	 */
	@Override
	public Boolean updateIsViewed(Integer reportId) {
		try{
			MonitorOrderConsumer MonitorOrderConsumer=get(reportId);
			MonitorOrderConsumer.setIsViewed(true);
			update(MonitorOrderConsumer);
		}catch (Exception e) {
			return false;
		}
		return true;
	}

}
