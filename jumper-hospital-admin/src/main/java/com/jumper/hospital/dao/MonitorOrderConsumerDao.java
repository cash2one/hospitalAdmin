package com.jumper.hospital.dao;
/**
 * 远程监控消费订单Dao
 * @author rent
 * @date 2015-09-22
 */
import java.sql.Timestamp;
import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.MonitorOrderConsumer;
import com.jumper.hospital.enums.RemoteType;

public interface MonitorOrderConsumerDao extends BaseDao<MonitorOrderConsumer, Integer> {

	/**
	 * 医院后台获取未审核订单信息
	 * @param page 分页对象
	 * @param hospitalId 医院ID
	 * @return page
	 */
	public Page<MonitorOrderConsumer> findNotFinishOrder(Page<MonitorOrderConsumer> page, Integer hospitalId);
	
	/**
	 * 医院后台获取已审核完成的订单信息
	 * @param page 分页对象
	 * @param hospitalId 医院ID
	 * @return
	 */
	public Page<MonitorOrderConsumer> findFinishedOrder(Page<MonitorOrderConsumer> page, Integer hospitalId, String searchKey, String startTime, String endTime, RemoteType remoteType, Integer selectId,List<Integer> doctorIds);
	
	/**
	 * 查询当前实时监测用户
	 * @param page 分页信息
	 * @param hospitalId 医院ID
	 * @return
	 */
	public Page<MonitorOrderConsumer> findRealTimeOrder(Page<MonitorOrderConsumer> page, Integer hospitalId);

	public Integer getMonitorSequence(Integer id, Timestamp applyTime);
	
	/**
	 * 郏县获取有效的已完成报告数
	 * @param hospitalId 当前医院ID
	 * @param searchKey 关键子搜索
	 * @param startTime 开始时间
	 * @param endTime	结束时间
	 * @param remoteType
	 * @param selectId
	 * @param hospitalIds
	 * @param name 
	 * @return
	 */
	public Integer finishedOrderCount(Integer hospitalId, String searchKey,String startTime, String endTime, RemoteType remoteType,
			Integer selectId, List<Integer> doctorIds);
	/**
	 * 当前医院未完成报告数
	 * @param hospitalId
	 * @return
	 */
	public Integer countNotFinishOrder(Integer hospitalId);
	/**
	 * 当前从属医院已经审核过但是没有阅读的报告
	 * @param hospitalId
	 * @return
	 */
	public Integer totalFinishUnRedReport(Integer hospitalId);
	
	/**
	 * 更新下级医院审核状态
	 * @param reportId
	 */
	public Boolean updateIsViewed(Integer reportId);


}
