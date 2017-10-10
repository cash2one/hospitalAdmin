package com.jumper.hospital.dao;
/**
 * 医院屏幕占用情况
 * @author rent
 * @date 2015-10-10
 */
import com.jumper.hospital.entity.MonitorSmallScreen;

public interface MonitorSmallScreenDao extends BaseDao<MonitorSmallScreen, Integer> {

	/**
	 * 通过医院ID查询医院屏幕使用状态
	 * @param hospitalId
	 * @return
	 */
	public MonitorSmallScreen findScreenStateByHospitalId(Integer hospitalId);
}
