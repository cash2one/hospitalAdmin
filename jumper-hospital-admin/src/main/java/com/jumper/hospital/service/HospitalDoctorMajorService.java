package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.entity.HospitalDoctorMajor;

public interface HospitalDoctorMajorService extends
		BaseService<HospitalDoctorMajor, Integer> {

	/**
	 * 获取所有的科室信息
	 * @return
	 */
	List<HospitalDoctorMajor> getMajorList();

	/**
	 * 通过科室id查询科室信息
	 * @param id
	 * @return
	 */
	HospitalDoctorMajor findMajorInfoById(int id);

}
