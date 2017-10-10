package com.jumper.hospital.dao;
/**
 * 远程监控项目Dao
 * @author rent
 * @date 2015-09-30
 */
import com.jumper.hospital.entity.MonitorProject;

public interface MonitorProjectDao extends BaseDao<MonitorProject, Integer> {

	/**
	 * 查询某次远程监控项目的保存记录，此处查询是为了重复监测更新监测数据
	 * @param monitorId 监控ID
	 * @param monitorType 监控类型
	 * @return MonitorProject
	 */
	public MonitorProject findProjectByMonitorIdAndType(Integer monitorId, Integer monitorType);
}
