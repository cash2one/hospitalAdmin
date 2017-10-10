package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.HospitalSubordinate;
/**
 * 医院从属关系信息 数据访问接口
 * @author rengn 任冠男
 *
 */
public interface HospitalSubordinateDao extends BaseDao<HospitalSubordinate, Integer> {

	/**
	 * 根据医院ID查询 从属医院列表
	 * @param hospitalId
	 * @param page
	 * @return
	 */
	Page<HospitalSubordinate> getHospitalSubordinateList(Integer hospitalId, Page<HospitalSubordinate> page);

	public List<HospitalSubordinate> findHospitalSubordinateList(Integer hospitalId);
	
	public List<Integer> findSubordinateList(Integer hospitalId);
	/**
	 * 判断当前医院是否为下级医院
	 * @param hospitalId
	 * @return
	 */
	public Boolean isSubordinatehospital(Integer hospitalId);
}
