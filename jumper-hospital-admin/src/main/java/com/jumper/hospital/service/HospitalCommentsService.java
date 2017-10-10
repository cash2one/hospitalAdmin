package com.jumper.hospital.service;

import com.jumper.hospital.entity.HospitalComments;

public interface HospitalCommentsService extends BaseService<HospitalComments, Integer> {

	/**
	 * 根据咨询id查询咨询评价
	 * @param id
	 * @return
	 */
	HospitalComments getCommentByConsId(Integer id);
	
}
