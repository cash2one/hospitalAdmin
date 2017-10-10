/**
 * 
 */
package com.jumper.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalDoctorTitleDao;
import com.jumper.hospital.entity.HospitalDoctorTitle;
import com.jumper.hospital.service.HospitalDoctorTitleService;

/**
 * @author huangcr
 *
 * 2017-7-13
 */
@Service
public class HospitalDoctorTitleServiceImpl extends BaseServiceImpl<HospitalDoctorTitle, Integer> implements HospitalDoctorTitleService {
	@Autowired
	HospitalDoctorTitleDao hospitalDoctorTitleDao;
	/**
		 * @author huangcr
		 * @date 2017-7-13 下午2:39:05
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @return
		 * @throws
		 */ 
	@Override
	public BaseDao<HospitalDoctorTitle, Integer> getBaseDAO() {
		// TODO Auto-generated method stub
		return hospitalDoctorTitleDao;
	}

	/**
		 * @author huangcr
		 * @date 2017-7-13 下午2:39:37
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param id
		 * @param @return
		 * @throws
		 */ 
	@Override
	public HospitalDoctorTitle findById(Integer id) {
		// TODO Auto-generated method stub
		return hospitalDoctorTitleDao.findById(id);
	}

}
