package com.jumper.hospital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalDoctorInfoDao;
import com.jumper.hospital.entity.HospitalDoctorInfo;
import com.jumper.hospital.service.HospitalDoctorInfoService;

@Service
public class HospitalDoctorInfoServiceImpl extends BaseServiceImpl<HospitalDoctorInfo, Integer> implements  HospitalDoctorInfoService {

//	private static final Logger logger = Logger.getLogger(HospitalDoctorInfoServiceImpl.class);

	@Autowired
	private  HospitalDoctorInfoDao hospitalDoctorInfoDao;
	
	@Override
	public BaseDao<HospitalDoctorInfo, Integer> getBaseDAO() {
		return hospitalDoctorInfoDao;
	}

	@Override
	public HospitalDoctorInfo getDoctorByPhone(String mobile) {
		
		HospitalDoctorInfo hospitalDoctorInfo= hospitalDoctorInfoDao.findUniqueBy("phone", mobile);
		return hospitalDoctorInfo;
	}

	@Override
	public List<Integer> findDoctorIdsByDoctorName(Integer id,
			String searchDoctor) {
		
		List<Integer> doctorids=hospitalDoctorInfoDao.findDoctorIdsByDoctorName( id,searchDoctor);
		
		return doctorids;
	}

	/**
		 * @author huangcr
		 * @date 2017-7-12 下午3:09:10
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @return
		 * @throws
		 */ 
	@Override
	public List<HospitalDoctorInfo> findAllDoctor(Integer hospitalId) {
		// TODO Auto-generated method stub
		List<HospitalDoctorInfo> doctors = hospitalDoctorInfoDao.findAllDoctor(hospitalId);
		return doctors;
	}

	/**
		 * @author huangcr
		 * @date 2017-7-13 上午9:45:37
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param name
		 * @param @return
		 * @throws
		 */ 
	@Override
	public List<HospitalDoctorInfo> findDoctorByName(String name,Integer hospitalId) {
		// TODO Auto-generated method stub
		return hospitalDoctorInfoDao.findDoctorByName(name,hospitalId);
	}

}
