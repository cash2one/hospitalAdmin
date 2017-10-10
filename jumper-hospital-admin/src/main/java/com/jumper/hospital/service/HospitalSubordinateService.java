package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.HospitalSubordinate;

/**
 * 医院从属关系表 业务实现接口
 * @author tanj 谭隽
 *
 */
public interface HospitalSubordinateService extends BaseService<HospitalSubordinate, Integer>{

	/**
	 * 根据上级医院 获取从属医院列表 分页
	 * @param page
	 * @param hospitalId
	 * @return
	 */
	public Page<HospitalSubordinate> getHospitalSubordinateList(Integer hospitalId, Page<HospitalSubordinate> page);
	/**
	 * 根据上级医院 查询未建从属医院
	 * @param id
	 * @return
	 */
	public List<HospitalInfo> getNotSubordinates(HospitalInfo hospitalInfo);
}
