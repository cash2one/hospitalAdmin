package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.entity.HospitalDescription;

public interface HospitalDescriptionDao extends BaseDao<HospitalDescription, Integer> {
	/**
	 * 通过类型描述类型和医院id获得医院的描述信息
	 * @param id
	 * @param type
	 * @return
	 */
	public List<HospitalDescription> getHospDescByType(Integer id,int type);
}
