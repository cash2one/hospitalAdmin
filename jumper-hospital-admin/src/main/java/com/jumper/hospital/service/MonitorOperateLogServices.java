package com.jumper.hospital.service;

import java.util.Date;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.MonitorHospital;
import com.jumper.hospital.entity.MonitorOperateLog;
import com.jumper.hospital.vo.VOMonitorOperateLog;


public interface MonitorOperateLogServices extends BaseService<MonitorOperateLog, Integer> {
	/**
	 * 添加操作日志
	 * @param vo
	 */
	void addOperateLog(VOMonitorOperateLog vo);
	
	/**
	 * 添加日志操作
	 * @param admin 当前登陆用户
	 * @param module 操作模块
	 * @param action 操作动作
	 * @param object 操作对象
	 * @param objectContent 操作内容
	 */
	public void addOperateLog(Admin admin,String module,String action,String object,String objectContent);
	
	/**
	 * 
	 * @param page 分页信息
	 * @param searchKey 搜索关键字
	 * @param searchType 搜索的类型  0：操作者字段搜索   1：操作对象字段搜索
	 * @param logType 日志类型  0:登陆日志   1:操作日志
	 * @param beginTime 日志开始时间
	 * @param endTime   日志结束时间
	 * @return
	 */
	Page<VOMonitorOperateLog> findLogPageByCondition(
			Page<MonitorOperateLog> page, String searchKey, String searchType,
			Integer logType, Date beginTime, Date endTime,Integer hospitalInfoId);
	
	/**
	 * 修改医院的退款最长时间
	 * @param admin
	 * @param returnDeadline
	 * @return
	 */
	boolean updateReturnDeadline(MonitorHospital monitorHospital, Integer returnDeadline);

}
