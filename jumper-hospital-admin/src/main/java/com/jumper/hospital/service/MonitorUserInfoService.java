package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.entity.MonitorUserInfo;
import com.jumper.hospital.vo.VoMonitorUserAutoComplete;

public interface MonitorUserInfoService extends BaseService<MonitorUserInfo, Integer> {

	/**
	 * 根据redis队列转换监控用户信息
	 * @param queueList
	 * @return
	 */
	public List<MonitorUserInfo> convertRedisQueueToUserList(List<String> queueList);
	
	/**
	 * 根据用户手机号码自动补全
	 * @param mobile 手机号码
	 * @return 
	 */
	public List<VoMonitorUserAutoComplete> findUserAutoComplete(String mobile);
	/**
	 * 校验购买远程监护订单的手机号码是否已存在，如果存在则提醒是否覆盖信息
	 * @param mobile
	 * @return
	 */
	public MonitorUserInfo doCheckMobileIsExist(String mobile);
	
}
