package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.entity.HospitalDoctorInfo;

public interface HospitalDoctorInfoService extends BaseService<HospitalDoctorInfo, Integer> {
	
	/**
	 * 根据医院和电话号码去获取医生信息
	 * @param id
	 * @param mobile
	 * @return
	 */
	HospitalDoctorInfo getDoctorByPhone(String mobile);
	
	/**
	 * 根据搜索关键字获取医生的id列表
	 * @param id
	 * @param searchDoctor
	 * @return
	 */
	List<Integer> findDoctorIdsByDoctorName(Integer id, String searchDoctor);
	/**
	 * 查询所有医生信息
	 */
	List<HospitalDoctorInfo> findAllDoctor(Integer hospitalId);
	/**
	 * 根据医生姓名模糊查询
	 */
	List<HospitalDoctorInfo> findDoctorByName(String name,Integer hospitalId);
}
