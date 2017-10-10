package com.jumper.hospital.dao;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.MonitorOrder;
import com.jumper.hospital.enums.RemoteType;

public interface MonitorOrderSpecDao extends BaseDao<MonitorOrder, Integer> {

	/**
	 * 统计今日订单使用次数
	 * @param hospitalId
	 * @return
	 */
	public int countOrderUsedToday(Integer hospitalId);
	
	/**
	 * 查询失效订单线上总数
	 * @param hospitalId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int countOrderInvalid(int hospitalId, String startTime, String endTime);
	
	/**
	 * 查询订单列表
	 * @param type 查询类型(0:今天，1:全部，2:失效)
	 * @param searchKey 搜索关键字(姓名、手机号码)
	 * @param startTime 搜索开始时间
	 * @param endTime 搜索结束时间
	 * @param page 分页信息，主要用于下一页
	 * @param hospitalId 医院ID
	 * @param remoteType 监控类型
	 * @param expireType 失效类型(退费、次数为0)
	 * @param invalidStartTime 失效开始时间
	 * @param invalidEndTime 失效结束时间
	 * @return
	 */
	public Page<MonitorOrder> findOrderList(Integer type, String searchKey, String startTime, String endTime, Page<MonitorOrder> page, 
			Integer hospitalId, Integer remoteType, Integer expireType, String invalidStartTime, String invalidEndTime,Integer payChannels);

	/**
	 * 获取远程监控订单
	 * @param page
	 * @param id
	 * @return
	 */
	public Page<MonitorOrder> findMonitorOrderList(Page<MonitorOrder> page, Integer id, String startTime,String endTime,String searchKey);

	/**
	 * 获取总收益
	 * @param sql
	 * @return
	 */
	public Double getProfits(String sql);

	/**
	 * 获取医院远程监测服务总人数
	 * @param id 医院ID
	 * @return
	 */
	public Integer getRemoteCountByHosptial(Integer id);

	/**
	 * 获取医院问诊总收益
	 * @param sql
	 * @return
	 */
	public Double getSumTrueCost(String sql);

	/**
	 * 获取远程监控总收益
	 * @param sql
	 * @return
	 */
	public Double getSumMoney(String sql);

	/**
	 * 带条件的医院问诊总收益
	 * @param sql
	 * @return
	 */
	public Double getSumTrueCostByConds(String sql);

	/**
	 * 带条件的远程监控总收益
	 * @param sql
	 * @return
	 */
	public Double getSumMoneyByConds(String sql);
	/**
	 * 校验该医院下的实时监护订单是否都试用完毕
	 * @param hospitalId 医院ID
	 * @param type 监测类型
	 * @return
	 */
	public boolean doCheckRoutineOrderHasUsedUp(Integer hospitalId, RemoteType type);
	
	/**
	 * 查找订单
	 * @param orderNo
	 * @return
	 */
	public MonitorOrder findOrder(String orderNo);
}
