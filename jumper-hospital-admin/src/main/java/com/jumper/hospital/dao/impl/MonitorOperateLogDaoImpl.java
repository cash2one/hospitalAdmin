package com.jumper.hospital.dao.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.MonitorOperateLogDao;
import com.jumper.hospital.entity.MonitorHospital;
import com.jumper.hospital.entity.MonitorOperateLog;
import com.jumper.hospital.vo.VOMonitorOperateLog;

@Repository
public class MonitorOperateLogDaoImpl extends BaseDaoImpl<MonitorOperateLog, Integer>
implements MonitorOperateLogDao {

	@Override
	public void addOperateLog(VOMonitorOperateLog vo) {
		
		MonitorOperateLog monitorOperateLog=new MonitorOperateLog();
		monitorOperateLog.setAction(vo.getAction());
		monitorOperateLog.setHospitalInfoId(vo.getHospitalId());
		monitorOperateLog.setMonitorAdminId(vo.getMonitorAdminId());
		monitorOperateLog.setUserName(vo.getUserName());
		monitorOperateLog.setName(vo.getName());
		monitorOperateLog.setModule(vo.getModule());
		monitorOperateLog.setObject(vo.getObject());
		monitorOperateLog.setObjectContent(vo.getObjectContent());
		monitorOperateLog.setOperateTime(vo.getOperateTime());
		
		@SuppressWarnings("unused")
		MonitorOperateLog  monitorOperateLog1 =  persist(monitorOperateLog);
	}

	
	@Override
	public Page<MonitorOperateLog> findLogPageByCondition(
			Page<MonitorOperateLog> page, String searchKey, String searchType,
			Integer logType, Date beginTime, Date endTime ,Integer hospitalInfoId) {
		
		try{
			Criteria c = createCriteria();
			if(searchKey!=null &&!"".equals(searchKey)){
				//如果是0 则为按照操作者字段来搜索
				if("1".equals(searchType) ){
					c.add(Restrictions.like("objectContent", searchKey, MatchMode.ANYWHERE));
				}else{
					//否则按照操作字段的内容来搜索
					c.add(Restrictions.like("name", searchKey, MatchMode.ANYWHERE));
				}
			}
			//查询登陆日志
			if(logType!=null&&0==logType){
				c.add(Restrictions.in("action",new String[]{"退出","登录"}));
			}
			//查询操作日志
			if(logType!=null&&1==logType){
				c.add(Restrictions.not(Restrictions.in("action", new String[]{"退出","登录"})));
			}
			//日志的范围  开始时间
			if(beginTime!=null){
				c.add(Restrictions.gt("operateTime", beginTime));
			}
			//日志的范围  结束时间
			if(endTime!=null){
				c.add(Restrictions.lt("operateTime",endTime ));
			}
			//只查询当前医院的日志
			if(hospitalInfoId!=null){
				c.add(Restrictions.eq("hospitalInfoId", hospitalInfoId));
			}
			
			c.addOrder(Order.desc("operateTime"));
			
			Page<MonitorOperateLog> pageData = findPageByCriteria(page, c);
			
			return pageData;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public boolean updateReturnDeadline(MonitorHospital monitorHospital, Integer returnDeadline) {
		
		try {
			monitorHospital.setReturnDeadline(returnDeadline);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
