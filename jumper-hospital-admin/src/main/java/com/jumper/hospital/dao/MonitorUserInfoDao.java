package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.entity.MonitorUserInfo;

public interface MonitorUserInfoDao extends BaseDao<MonitorUserInfo, Integer> {

	/**
	 * 校验APP用户是否已绑定到监控用户上
	 * @param userId app用户ID
	 * @return boolean true | false
	 */
	public boolean doCheckUserIsBind(Integer userId);
	
	/**
	 * 绑定APP用户信息
	 * @param mobile 用户手机号码
	 * @return MonitorUserInfo 绑定成功后的用户
	 */
	public MonitorUserInfo doBindUserInfo(String mobile, Integer userId);
	
	/**
	 * 根据APP用户获取远程监控用户信息
	 * @param userId APP用户ID
	 * @return monitorUserInfo
	 */
	public MonitorUserInfo getMonitorUserInfo(Integer userId);
	
	/**
	 * 根据用户手机号码自动补全
	 * @param mobile 手机号码
	 * @return 
	 */
	public List<MonitorUserInfo> findUserAutoComplete(String mobile);
	
	/**
	 * 搜索用户信息
	 * @param searchKey
	 * @return
	 */
	public List<Integer> findUserInfoByNameOrMobile(String searchKey);
	
	/**
	 * 检测手机号码是否已经注册过监测用户
	 * @param mobile
	 * @return
	 */
	public boolean doCheckMobileIsExist(String mobile);
	/**
	 * 手机号码查询用户
	 * @param mobile
	 * @return
	 */
	public MonitorUserInfo findMUSerByMobile(String mobile);
}
