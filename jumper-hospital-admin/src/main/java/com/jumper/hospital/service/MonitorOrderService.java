package com.jumper.hospital.service;
import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.MonitorOrder;
import com.jumper.hospital.entity.MonitorUserInfo;
import com.jumper.hospital.vo.VoMonitorOrder;

public interface MonitorOrderService extends BaseService<MonitorOrder, Integer> {

	/**
	 * 查询订单列表
	 * @param type 查询类型(0:今天，1:全部，2:失效)
	 * @param searchKey 搜索关键字(姓名、手机号码)
	 * @param startTime 搜索开始时间
	 * @param endTime 搜索结束时间
	 * @param page 翻页，主要用户下一页
	 * @param hospitalId 医院ID
	 * @param remoteType 监测类型
	 * @param expireType 失效类型(退费、订单剩余次数为0)
	 * @param invalidStartTime 失效开始时间
	 * @param invalidEndTime 失效结束时间
	 * @return
	 */
	public Page<MonitorOrder> findOrderList(Integer type, String searchKey, String startTime, String endTime, Page<MonitorOrder> page,
			Integer hospitalId, Integer remoteType, Integer expireType, String invalidStartTime, String invalidEndTime);
	
	/**
	 * 新增订单
	 * @param userInfo 监控用户信息
	 * @param optionId 服务选项信息
	 * @param hospitalId 医院ID
	 * @return
	 */
	public String addMonitorOrder(MonitorUserInfo userInfo, Integer optionId, Integer hospitalId, String edcDate);
	
	/**
	 * 修改用户购买监控订单信息返回数据Vo转换
	 * @param order 订单ID
	 * @return
	 */
	public VoMonitorOrder convertOrderDetail(MonitorOrder order);

	/**
	 * 获取订单列表
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
	 * 获取医院远程监测服务数量
	 * @param id 医院ID
	 * @return
	 */
	public Integer getRemoteCountByHospital(Integer id);

	/**
	 * 获取医院问诊总收益
	 * @param id
	 * @return
	 */
	public Double getSumTrueCost(Integer id);

	/**
	 * 远程监控收益
	 * @param id
	 * @return
	 */
	public Double getSumMoney(Integer id);

	/**
	 * 条件查询的时候获取问诊总收益
	 * @param searchKey
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Double getSumTrueCostByConds(Integer id,String searchKey, String startTime,
			String endTime);

	/**
	 * 条件查询获取远程监控总收益
	 * @param id
	 * @param searchKey
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Double getSumMoneyByConds(Integer id, String searchKey, String startTime, String endTime);
	/**
	 * 院内胎监重新测试
	 * @param id
	 * @return
	 */
	public String doChgInsideOrder(Integer id);
}