package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.entity.HospitalInfo;
/**
 * 医院信息
 * @author tanqing
 *
 */
public interface HospitalInfoDao extends BaseDao<HospitalInfo, Integer> {

	/**
	 * 自动补全医院查询
	 * @param q 参数
	 * @return
	 */
	public List<HospitalInfo> findForAutoComplete(String q);
}
