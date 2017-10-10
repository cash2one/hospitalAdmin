package com.jumper.hospital.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalDoctorMajorDao;
import com.jumper.hospital.entity.HospitalDoctorMajor;
import com.jumper.hospital.service.HospitalDoctorMajorService;
@Service
public class HospitalDoctorMajorServiceImpl extends BaseServiceImpl<HospitalDoctorMajor, Integer>
		implements HospitalDoctorMajorService {
	@Autowired
	private HospitalDoctorMajorDao hospitalDoctorMajorDao;
	@Override
	public BaseDao<HospitalDoctorMajor, Integer> getBaseDAO() {
		return hospitalDoctorMajorDao;
	}
	

	@Override
	public List<HospitalDoctorMajor> getMajorList() {
		return hospitalDoctorMajorDao.getAllMajorList();
	}


	@Override
	public HospitalDoctorMajor findMajorInfoById(int id) {
		return hospitalDoctorMajorDao.findMajorInfoById(id);
	}
}
