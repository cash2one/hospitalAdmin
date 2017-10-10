package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.entity.HospitalMajorInfo;

public interface HospitalMajorInfoService extends BaseService<HospitalMajorInfo, Integer> {
	
	/**
	 * 医院科室信息添加
	 */
	void addMajorInfo(int hospitalId,int majors[]);
	

	/**
	 * 根据医院id获取医院所开通的科室
	 * @param id
	 * @return
	 */
	List<HospitalMajorInfo> findHosMajors(Integer id);
	/**
	 * 根据医院id和状态获取医院所开通的科室
	 * @param id
	 * @return
	 */
	List<HospitalMajorInfo> findHosMajorsByIsnetwork(Integer id,Integer isnetwork);
	
	/**
	 * 开通关闭科室网络诊室
	 * @param state 状态:1，开通  0，关闭
	 * @param hospitalInfo 医院信息
	 * @return
	 */
	public String openMajorNetwork(Integer majorId,String status,Integer hospitalId,Integer userId);
	



}
