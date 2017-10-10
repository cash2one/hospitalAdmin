package com.jumper.hospital.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.MonitorOrderDao;
import com.jumper.hospital.dao.MonitorSettingDao;
import com.jumper.hospital.entity.MonitorSetting;
import com.jumper.hospital.entity.MonitorSettingOption;
import com.jumper.hospital.enums.RemoteType;
import com.jumper.hospital.service.MonitorSettingService;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.vo.VoMonitorSetting;
import com.jumper.hospital.vo.VoMonitorSettingOption;

@Service
public class MonitorSettingServiceImpl extends BaseServiceImpl<MonitorSetting, Integer> implements MonitorSettingService {

	private static final Logger logger = Logger.getLogger(MonitorSettingServiceImpl.class);
	@Autowired
	private MonitorSettingDao monitorSettingDaoImpl;
	@Autowired
	private MonitorOrderDao monitorOrderDaoImpl;
	
	@Override
	public BaseDao<MonitorSetting, Integer> getBaseDAO() {
		return monitorSettingDaoImpl;
	}

	@Override
	public List<VoMonitorSetting> findByHospitalIdAndRemoteType(Integer hospitalId) {
		try {
			List<VoMonitorSetting> voList = new ArrayList<VoMonitorSetting>();
			List<MonitorSetting> setting = monitorSettingDaoImpl.findByHospitalIdAndRemoteType(hospitalId);
			if(ArrayUtils.isNotEmpty(setting)){
				for(MonitorSetting ms : setting){
					VoMonitorSetting vo = new VoMonitorSetting();
					vo.setType(ms.getType().ordinal());
					
					List<VoMonitorSettingOption> optionList = new ArrayList<VoMonitorSettingOption>();
					if(ArrayUtils.isNotEmpty(ms.getOptions())){
						for(MonitorSettingOption op : ms.getOptions()){
							VoMonitorSettingOption voOption = new VoMonitorSettingOption();
							voOption.setId(op.getId());
							voOption.setNumber(op.getNumber());
							voOption.setPrice(op.getPrice());
							optionList.add(voOption);
						}
					}
					vo.setOptions(optionList);
					voList.add(vo);
				}
			}
			return voList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询医院开通的监控服务信息异常！异常原因:"+e.getMessage());
			return null;
		}
	}

	@Override
	public boolean checkMonitorIsOpen(Integer hospitalId, RemoteType type) {
		return monitorSettingDaoImpl.checkMonitorIsOpen(hospitalId, type);
	}

	@Override
	public boolean checkRoutineOrderIsUseFinish(Integer hospitalId, RemoteType type) {
		return monitorOrderDaoImpl.doCheckRoutineOrderHasUsedUp(hospitalId, type);
	}

}
