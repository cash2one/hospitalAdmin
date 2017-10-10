package com.jumper.hospital.dao;

import java.util.List;


import com.jumper.hospital.entity.HospitalDoctorMajor;

public interface HospitalDoctorMajorDao extends BaseDao<HospitalDoctorMajor, Integer> {

	/**
	 * 获取所有的科室信息
	 * @return
	 */
	List<HospitalDoctorMajor> getAllMajorList();

	/**
	 * 通过科室id查询科室信息
	 * @param id
	 * @return
	 */
	HospitalDoctorMajor findMajorInfoById(int id);
	
}
