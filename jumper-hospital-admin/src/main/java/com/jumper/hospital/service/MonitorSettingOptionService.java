package com.jumper.hospital.service;

import com.jumper.hospital.entity.MonitorSettingOption;

public interface MonitorSettingOptionService extends BaseService<MonitorSettingOption, Integer>{

	/**
	 * 监控套餐详情设置
	 * @param option 详情信息
	 * @param settingId 监控ID
	 * @return boolean
	 */
	public Boolean saveOrUpdateOption(MonitorSettingOption option, Integer settingId);
}
