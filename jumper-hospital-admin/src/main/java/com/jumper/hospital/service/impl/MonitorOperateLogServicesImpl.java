package com.jumper.hospital.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.MonitorOperateLogDao;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.MonitorHospital;
import com.jumper.hospital.entity.MonitorOperateLog;
import com.jumper.hospital.service.MonitorOperateLogServices;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.vo.VOMonitorOperateLog;

@Service
public class MonitorOperateLogServicesImpl extends BaseServiceImpl<MonitorOperateLog, Integer> 
implements MonitorOperateLogServices {

	@Autowired
	private MonitorOperateLogDao monitorOperateLogDao;
	
	@Override
	public BaseDao<MonitorOperateLog, Integer> getBaseDAO() {
		return monitorOperateLogDao;
	}

	
	@Override
	public void addOperateLog(VOMonitorOperateLog vo) {
		monitorOperateLogDao.addOperateLog(vo);
	}
	
	public void addOperateLog(Admin admin,String module,String action,String object,String objectContent) {
		VOMonitorOperateLog vo=new VOMonitorOperateLog();
		Integer hospitalId= admin.getHospitalInfo().getId();
		Timestamp operateTime= new Timestamp(System.currentTimeMillis());
		vo.setOperateTime(operateTime);
		vo.setHospitalId(hospitalId);
		vo.setUserName(admin.getUsername());
		vo.setName(admin.getName());
		vo.setMonitorAdminId(admin.getId());
		vo.setModule(module);
		vo.setAction(action);
		vo.setObject(object);
		vo.setObjectContent(objectContent);
		monitorOperateLogDao.addOperateLog(vo);
	}


	@Override
	public Page<VOMonitorOperateLog> findLogPageByCondition(
			Page<MonitorOperateLog> page, String searchKey, String searchType,
			Integer logType, Date beginTime, Date endTime ,Integer hospitalInfoId) {
		try {
			Page<MonitorOperateLog> data= monitorOperateLogDao.findLogPageByCondition(page,searchKey,searchType,logType,beginTime,endTime,hospitalInfoId);
			Page<VOMonitorOperateLog> pageData = convertData(data);
			return pageData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 转换数据
	 * @param data
	 * @return
	 */
	private Page<VOMonitorOperateLog> convertData(Page<MonitorOperateLog> page) {
		Page<VOMonitorOperateLog> pageData = new Page<VOMonitorOperateLog>();
		List<VOMonitorOperateLog> dataList = new ArrayList<VOMonitorOperateLog>();
		
		try {
			if (page != null && ArrayUtils.isNotEmpty(page.getResult())) {
				for (MonitorOperateLog log : page.getResult()) {
					if (log != null) {
						VOMonitorOperateLog vo=new VOMonitorOperateLog();
						vo.setAction(log.getAction());
						vo.setHospitalId(log.getHospitalInfoId());
						vo.setModule(log.getModule());
						vo.setMonitorAdminId(log.getMonitorAdminId());
						vo.setName(log.getName());
						vo.setObject(log.getObject());
						vo.setObjectContent(log.getObjectContent());
						vo.setOperateTime(log.getOperateTime());
						vo.setUserName(log.getUserName());
						dataList.add(vo);
					}
				}
				pageData.setPageNo(page.getPageNo());
				pageData.setPageSize(page.getPageSize());
				pageData.setResult(dataList);
				pageData.setTotalCount(page.getTotalCount());
			}
			return pageData;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}


	@Override
	public boolean updateReturnDeadline(MonitorHospital monitorHospital, Integer returnDeadline) {
		
		try {
			boolean falg= monitorOperateLogDao.updateReturnDeadline(monitorHospital,returnDeadline);
			return falg;
		} catch (Exception e) {
			 e.printStackTrace();
			 return false;
		}
	}
	
}
