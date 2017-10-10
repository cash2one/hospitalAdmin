package com.jumper.hospital.dao;
/**
 * 远程监控项目数据记录Dao
 * @author rent
 * @date 2015-09-30
 */
import com.jumper.hospital.entity.MonitorProjectRecord;

public interface MonitorProjectRecordDao extends BaseDao<MonitorProjectRecord, Integer> {

	/**
	 * 删除监控数据，用于第二次上传数据
	 * @param consumerId 消费订单ID
	 * @param recordType 记录类型
	 * @return boolean true | false
	 */
	public boolean deleteProjectByConsumerIdAndRecordType(Integer consumerId, Integer recordType);
}
