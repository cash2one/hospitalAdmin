package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.HospitalNotice;

public interface HospitalNoticeDao extends BaseDao<HospitalNotice, Integer> {

	/**
	 * 获取当前医院的公告列表
	 * @param page
	 * @param id
	 * @return
	 */
	Page<HospitalNotice> findNoticeList(Page<HospitalNotice> page, Integer id);

	/**
	 * 获取医院的公告列表
	 * @param hospitalId
	 * @return
	 */
	List<HospitalNotice> findNotices(int hospitalId);
	
}
