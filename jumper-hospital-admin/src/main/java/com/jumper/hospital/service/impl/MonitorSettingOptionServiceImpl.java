package com.jumper.hospital.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.MonitorSettingDao;
import com.jumper.hospital.dao.MonitorSettingOptionDao;
import com.jumper.hospital.entity.MonitorSetting;
import com.jumper.hospital.entity.MonitorSettingOption;
import com.jumper.hospital.service.MonitorSettingOptionService;
import com.jumper.hospital.utils.BeanUtils;
@Service
public class MonitorSettingOptionServiceImpl extends BaseServiceImpl<MonitorSettingOption, Integer> implements MonitorSettingOptionService{
	
	private static final Logger logger = Logger.getLogger(MonitorSettingOptionServiceImpl.class);
	
	@Autowired
	private MonitorSettingOptionDao monitorSettingOptionDao;
	@Autowired
	private MonitorSettingDao monitorSettingDaoImpl;
	
	@Override
	public BaseDao<MonitorSettingOption, Integer> getBaseDAO() {
		return monitorSettingOptionDao;
	}
	
	@Override
	public Boolean saveOrUpdateOption(MonitorSettingOption option, Integer settingId) {
		try {
			if(option.getId() != null){
				MonitorSettingOption targetOption = monitorSettingOptionDao.get(option.getId());
				BeanUtils.copy(option, targetOption);
				monitorSettingOptionDao.save(targetOption);
				return true;
			}else{
				MonitorSetting setting = monitorSettingDaoImpl.get(settingId);
				option.setMonitorSetId(setting);
				monitorSettingOptionDao.save(option);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增或修改服务详情信息异常！异常原因:"+e.getMessage());
			return false;
		}
	}

}
