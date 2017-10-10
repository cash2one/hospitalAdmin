package com.jumper.hospital.dao;

import com.jumper.hospital.entity.HospitalComments;

public interface HospitalCommentsDao extends BaseDao<HospitalComments, Integer> {

	/**
	 * 根据问题id查询问题的评价
	 * @param id
	 * @return
	 */
	HospitalComments getCommentByConsId(Integer id);

}
