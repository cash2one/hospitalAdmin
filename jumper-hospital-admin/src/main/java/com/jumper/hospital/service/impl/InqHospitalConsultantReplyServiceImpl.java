package com.jumper.hospital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.InqHospitalConsultantReplyDao;
import com.jumper.hospital.entity.InqHospitalConsultant;
import com.jumper.hospital.entity.InqHospitalConsultantReply;
import com.jumper.hospital.service.InqHospitalConsultantReplyService;

@Service
public class InqHospitalConsultantReplyServiceImpl extends
		BaseServiceImpl<InqHospitalConsultantReply, Integer> implements InqHospitalConsultantReplyService {

	@Autowired
	private InqHospitalConsultantReplyDao inqHospitalConsultantReplyDao;
	@Override
	public BaseDao<InqHospitalConsultantReply, Integer> getBaseDAO() {
		return inqHospitalConsultantReplyDao;
	}
	@Override
	public List<InqHospitalConsultantReply> findReplysById(Integer idInt) {
		return inqHospitalConsultantReplyDao.findReplysById(idInt);
	}
	@Override
	public void addHospitalConsultantReply(
			InqHospitalConsultant inqHospitalConsultant, Integer consId,
			String hospitalReply) {
		inqHospitalConsultantReplyDao.addHospitalConsultantReply(inqHospitalConsultant,consId,hospitalReply);
	}
	@Override
	public InqHospitalConsultantReply findHospitalReplysById(int consultId) {
		return inqHospitalConsultantReplyDao.findHospitalReplysById(consultId);
	}
	@Override
	public List<InqHospitalConsultantReply> findUserReplysById(int consultId,
			String lastTime) {
		return inqHospitalConsultantReplyDao.findUserReplysById(consultId,lastTime);
	}
	


}
