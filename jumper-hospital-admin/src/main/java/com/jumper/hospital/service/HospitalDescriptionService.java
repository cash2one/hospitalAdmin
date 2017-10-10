package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.entity.HospitalDescription;

public interface HospitalDescriptionService extends BaseService<HospitalDescription, Integer>{
	/**
	 * 通过类型描述类型和医院id获得医院的描述信息
	 * @param id
	 * @param type
	 * @return
	 */
	public List<HospitalDescription> getHospDescByType(Integer id,int type);
	/**
	 * 添加、修改医院描述信息
	 * @param type
	 * @param hospitalId
	 * @param content
	 * @param imgUrl
	 * @param id
	 * @param addTime
	 * @return
	 */
	public String addOrUpdate(String type,String hospitalId,String content,String imgUrl,String id,String addTime);
}
