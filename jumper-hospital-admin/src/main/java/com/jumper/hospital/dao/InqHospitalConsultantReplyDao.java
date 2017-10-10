package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.entity.InqHospitalConsultant;
import com.jumper.hospital.entity.InqHospitalConsultantReply;


public interface InqHospitalConsultantReplyDao extends BaseDao<InqHospitalConsultantReply, Integer> {

	/**
	 * 通过问题id查询回复信息
	 * @param idInt
	 * @return
	 */
	List<InqHospitalConsultantReply> findReplysById(Integer idInt);

	/**
	 * 院方回复用户信息
	 * @param inqHospitalConsultant
	 * @param consId
	 * @param hospitalReply
	 */
	void addHospitalConsultantReply(
			InqHospitalConsultant inqHospitalConsultant, Integer consId,
			String hospitalReply);

	/**
	 * 通过问题id查询院方回复信息
	 * @param consultId
	 * @return
	 */
	InqHospitalConsultantReply findHospitalReplysById(int consultId);

	/**
	 * 查询最新的用户咨询记录
	 * @param consultId
	 * @param replyInfo
	 * @return
	 */
	List<InqHospitalConsultantReply> findUserReplysById(int consultId,
			String lastTime);

	
	
}
