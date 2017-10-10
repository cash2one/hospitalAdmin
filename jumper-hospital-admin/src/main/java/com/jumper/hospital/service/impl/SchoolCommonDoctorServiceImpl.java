package com.jumper.hospital.service.impl;
/**
 * 常用授课医师Service实现类
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.SchoolCommonDoctorDao;
import com.jumper.hospital.entity.SchoolCommonDoctor;
import com.jumper.hospital.service.SchoolCommonDoctorService;

@Service
public class SchoolCommonDoctorServiceImpl extends BaseServiceImpl<SchoolCommonDoctor, Integer> implements SchoolCommonDoctorService {

	@Autowired
	private SchoolCommonDoctorDao schoolCommonDoctorDaoImpl;
	
	@Override
	public BaseDao<SchoolCommonDoctor, Integer> getBaseDAO() {
		return schoolCommonDoctorDaoImpl;
	}

	public List<SchoolCommonDoctor> getSchoolCommonDoctorByHospital(Integer hospitalId){
		return schoolCommonDoctorDaoImpl.getSchoolCommonDoctorByHospital(hospitalId);
	}

	@Override
	public SchoolCommonDoctor addSchoolCommonDoctor(String doctorName, String doctorTitle, Integer hospitalId) {
		if(StringUtils.isEmpty(doctorName) || hospitalId == null){
			return null;
		}
		SchoolCommonDoctor scd = new SchoolCommonDoctor();
		scd.setHospitalId(hospitalId);
		scd.setDoctorName(doctorName);
		scd.setDoctorTitle(doctorTitle);
		return schoolCommonDoctorDaoImpl.persist(scd);
	}

	@Override
	public boolean checkDoctorHasExist(String doctorName, String doctorTitle, Integer hospitalId) {
		return schoolCommonDoctorDaoImpl.checkDoctorHasExist(doctorName, doctorTitle, hospitalId);
	}
	
}
