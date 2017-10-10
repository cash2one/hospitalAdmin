package com.jumper.hospital.dao;
/**
 * 已消费订单临时表
 * @author rent
 * @date 2015-11-24
 */
import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.MonitorOrderConsumerTemp;

public interface MonitorOrderConsumerTempDao extends BaseDao<MonitorOrderConsumerTemp, Integer> {

	/**
	 * 查询当前实时监测用户
	 * @param page 分页信息
	 * @param hospitalId 医院ID
	 * @return
	 */
	public Page<MonitorOrderConsumerTemp> findRealTimeOrder(Page<MonitorOrderConsumerTemp> page, Integer hospitalId);
}
