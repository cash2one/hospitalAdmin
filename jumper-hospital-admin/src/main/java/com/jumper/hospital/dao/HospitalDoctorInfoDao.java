package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.entity.HospitalDoctorInfo;
/**
 * 医院医生信息
 * @author tanqing
 *
 */
public interface HospitalDoctorInfoDao extends BaseDao<HospitalDoctorInfo, Integer> {

	/**
	 * 
	 *查询医生信息
	 */
	public HospitalDoctorInfo selectDoctorById(Integer id);
	
	
	/**
	 * 
	 *查询医生信息,根据电话号码查询(phone)
	 */
	public HospitalDoctorInfo selectDoctorByPhone(Integer hospitalId,String phone);
	
	/**
	 * 根据搜索关键字查询当前医院符合插叙的的医生id列表
	 * @param id
	 * @param searchDoctor
	 * @return
	 */
	public List<Integer> findDoctorIdsByDoctorName(Integer id,
			String searchDoctor);
	/**
	 * 查询所有医生信息
	 */
	public List<HospitalDoctorInfo> findAllDoctor(Integer hospitalId);
	/**
	 * 根据医生姓名模糊查询
	 */
	public List<HospitalDoctorInfo> findDoctorByName(String name,Integer hospitalId);
}
