/**
 * 
 */
package com.jumper.hospital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalDoctorNoticeDao;
import com.jumper.hospital.entity.HospitalDoctorNotice;
import com.jumper.hospital.service.HospitalDoctorNoticeService;

/**
 * @author huangcr
 *
 * 2017-7-13
 */
@Service
public class HospitalDoctorNoticeServiceImpl extends BaseServiceImpl<HospitalDoctorNotice, Integer> implements HospitalDoctorNoticeService {

	@Autowired HospitalDoctorNoticeDao hospitalDoctorNoticeDao;
	/**
	 * @author huangcr
	 * @date 2017-7-13 上午11:56:43
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @throws
	 */ 
	@Override
	public BaseDao<HospitalDoctorNotice, Integer> getBaseDAO() {
		// TODO Auto-generated method stub
		return hospitalDoctorNoticeDao;
	}
	@Override
	public List<HospitalDoctorNotice> findAll(Integer status,Integer hospitalId){
		return hospitalDoctorNoticeDao.findAll(status,hospitalId);
	}
	/**
		 * @author huangcr
		 * @date 2017-7-13 下午1:43:47
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param 
		 * @throws
		 */ 
	@Override
	public void deleteDoctor(Integer status,Integer hospitalId) {
		// TODO Auto-generated method stub
		hospitalDoctorNoticeDao.deleteDoctor(status,hospitalId);
	}
	/**
		 * @author huangcr
		 * @date 2017-7-13 下午6:36:00
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param phone
		 * @throws
		 */ 
	@Override
	public void deleteDoctorByPhone(String username,Integer hospitalId) {
		// TODO Auto-generated method stub
		hospitalDoctorNoticeDao.deleteDoctorByPhone(username,hospitalId);
	}
	/**
		 * @author huangcr
		 * @date 2017-7-13 下午6:44:33
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param doctorNotice
		 * @throws
		 */ 
	@Override
	public void addDoctorNotice(HospitalDoctorNotice doctorNotice) {
		// TODO Auto-generated method stub
		hospitalDoctorNoticeDao.save(doctorNotice);
	}
	/**
		 * @author huangcr
		 * @date 2017-7-14 上午10:42:55
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param hospitalId
		 * @throws
		 */ 
	@Override
	public void updateStatus(Integer status,Integer hospitalId) {
		// TODO Auto-generated method stub
		hospitalDoctorNoticeDao.updateStatus(status,hospitalId);
	}
	/**
		 * @author huangcr
		 * @date 2017-7-26 下午2:18:51
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param username
		 * @param @param hospitalId
		 * @param @return
		 * @throws
		 */ 
	@Override
	public HospitalDoctorNotice getHospitalDoctorNotice(String username,
			Integer hospitalId) {
		// TODO Auto-generated method stub
		return hospitalDoctorNoticeDao.getHospitalDoctorNotice(username,hospitalId);
	}
	/**
		 * @author huangcr
		 * @date 2017-7-26 下午2:30:52
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param username
		 * @param @param status
		 * @param @param hospitalId
		 * @throws
		 */ 
	@Override
	public void updateStatusByUsername(String username, Integer status,
			Integer hospitalId) {
		// TODO Auto-generated method stub
		hospitalDoctorNoticeDao.updateStatusByUsername(username,status,hospitalId);
	}
	/**
		 * @author huangcr
		 * @date 2017-7-26 下午2:52:28
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param status
		 * @param @param newStatus
		 * @param @param hospitalId
		 * @throws
		 */ 
	@Override
	public void updateStatusByNewStatus(Integer status, Integer newStatus,
			Integer hospitalId) {
		// TODO Auto-generated method stub
		hospitalDoctorNoticeDao.updateStatusByNewStatus(status,newStatus,hospitalId);
	}
	/**
		 * @author huangcr
		 * @date 2017-7-26 下午3:09:04
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param status
		 * @param @param hospitalId
		 * @param @return
		 * @throws
		 */ 
	@Override
	public List<HospitalDoctorNotice> findAllisnotZero(Integer status,
			Integer hospitalId) {
		// TODO Auto-generated method stub
		return hospitalDoctorNoticeDao.findAllisnotZero(status,hospitalId);
	}
	
	
	

}
