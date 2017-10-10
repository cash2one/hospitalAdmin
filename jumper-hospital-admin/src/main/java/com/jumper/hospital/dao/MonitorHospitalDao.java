package com.jumper.hospital.dao;
/**
 * 远程监控医院Dao接口
 * @author rent
 * @date 2015-10-10
 */
import java.util.List;

import com.jumper.hospital.entity.MonitorHospital;

public interface MonitorHospitalDao extends BaseDao<MonitorHospital, Integer> {

	/**
	 * 通过省市查询已开通远程监控的医院信息
	 * @param provinceId 省份ID
	 * @param cityId 城市ID
	 * @return List<MonitorHospital>
	 */
	public List<MonitorHospital> findHospitalListByProvinceAndCity(Integer provinceId, Integer cityId);
	
	/**
	 * 通过医院ID查询远程监控医院信息
	 * @param hospitalId 医院ID
	 * @return MonitorHospital
	 */
	public MonitorHospital findMonitorHospitalByHospitalId(Integer hospitalId);
}
