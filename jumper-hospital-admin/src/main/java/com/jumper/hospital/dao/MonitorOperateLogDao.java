package com.jumper.hospital.dao;

import java.util.Date;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.MonitorHospital;
import com.jumper.hospital.entity.MonitorOperateLog;
import com.jumper.hospital.vo.VOMonitorOperateLog;

public interface MonitorOperateLogDao extends BaseDao<MonitorOperateLog, Integer>  {
	/**
	 *持久化操作日志 
	 */
	void addOperateLog(VOMonitorOperateLog vo);
	
	/**
	 * 获取操作日志
	 * @param page
	 * @param searchKey
	 * @param searchType
	 * @param logType
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	Page<MonitorOperateLog> findLogPageByCondition(
			Page<MonitorOperateLog> page, String searchKey, String searchType,
			Integer logType, Date beginTime, Date endTime,Integer hospitalInfoId);
	
	/**
	 * 
	 * @param admin
	 * @param returnDeadline
	 * @return
	 */
	boolean updateReturnDeadline(MonitorHospital monitorHospital, Integer returnDeadline);

	
}
