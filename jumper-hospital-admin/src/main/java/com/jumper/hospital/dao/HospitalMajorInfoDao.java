package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.entity.HospitalMajorInfo;

public interface HospitalMajorInfoDao extends
		BaseDao<HospitalMajorInfo, Integer> {

	/**
	 * 添加科室信息
	 * @param hospitalId
	 * @param majors
	 */
	void addMajorInfo(int hospitalId, int[] majors);

	/**
	 * 根据医院id获取医院开通的科室
	 * @param id
	 * @return
	 */
	List<HospitalMajorInfo> findHosMajors(Integer id);
	
	/**
	 * 根据医院id 和状态获取医院开通的科室
	 * @param id
	 * @return
	 */
	List<HospitalMajorInfo> findHosMajorsByIsnetwork(Integer id,Integer isnetwork);

}
