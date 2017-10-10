package com.jumper.hospital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.InqHospitalConsultantDao;
import com.jumper.hospital.entity.InqHospitalConsultant;
import com.jumper.hospital.service.InqHospitalConsultantService;

@Service
public class InqHospitalConsultantServiceImpl extends BaseServiceImpl<InqHospitalConsultant,Integer>
		implements InqHospitalConsultantService {
	@Autowired
	private InqHospitalConsultantDao inqHospitalConsultantDao;

	@Override
	public BaseDao<InqHospitalConsultant, Integer> getBaseDAO() {
		return inqHospitalConsultantDao;
	}

	@Override
	public Page<InqHospitalConsultant> findTodayConsultList(Page<InqHospitalConsultant> page,Integer id) {
		return inqHospitalConsultantDao.findTodayConsultList(page,id);
	}

	@Override
	public InqHospitalConsultant findConsultById(Integer idInt) {
		return inqHospitalConsultantDao.findUniqueBy("id", idInt);
	}

	@Override
	public Page<InqHospitalConsultant> findAllConsultList(
			Page<InqHospitalConsultant> page, Integer id) {
		return inqHospitalConsultantDao.findAllConsultList(page,id);
	}

	@Override
	public Page<InqHospitalConsultant> findTodayConsultListByConds(
			Page<InqHospitalConsultant> page, Integer id, String searchKey) {
		return inqHospitalConsultantDao.findTodayConsultListByConds(page,id,searchKey);
	}

	@Override
	public void updateHospitalConsult(
			InqHospitalConsultant inqHospitalConsultant) {
		edit(inqHospitalConsultant);
	}

	@Override
	public Page<InqHospitalConsultant> findAllConsultListByConds(Page<InqHospitalConsultant> page,
			Integer id, String searchKey,String visitType) {
		return inqHospitalConsultantDao.findAllConsultListByConds(page,id,searchKey,visitType);
	}

	@Override
	public Page<InqHospitalConsultant> findAllConsultListByType(Page<InqHospitalConsultant> page,
			Integer id, String visitType) {
		return inqHospitalConsultantDao.findAllConsultListByType(page,id,visitType);
	}

	@Override
	public InqHospitalConsultant getInqConsultantByConOrderId(Integer conId) {
		return inqHospitalConsultantDao.getInqConsultantByConOrderId(conId);
	}

	public int updateStatus(Integer id,Integer status) {
		return inqHospitalConsultantDao.updateStatus(id,status);
	}
	
	public List<InqHospitalConsultant> findTodayAllConsultList() {
		return inqHospitalConsultantDao.findTodayAllConsultList();
	}

}
