package com.jumper.hospital.service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.MonitorOrderConsumerTemp;
import com.jumper.hospital.vo.VoFinishedOrder;

public interface MonitorOrderConsumerTempService extends BaseService<MonitorOrderConsumerTemp, Integer> {

	/**
	 * 查找进行试试监测的用户
	 * @param page 分页信息
	 * @param hospitalId 医院ID
	 * @return
	 */
	public Page<VoFinishedOrder> findRealTimeOrder(Page<MonitorOrderConsumerTemp> page, Integer hospitalId);
}
