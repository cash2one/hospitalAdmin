/**
 * 
 */
package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.entity.HospitalDoctorNotice;

/**
 * @author huangcr
 *
 * 2017-7-13
 */
public interface HospitalDoctorNoticeDao extends BaseDao<HospitalDoctorNotice, Integer>{
	List<HospitalDoctorNotice> findAll(Integer status,Integer hospitalId);
	List<HospitalDoctorNotice> findAllisnotZero(Integer status,Integer hospitalId);
	void deleteDoctor(Integer status,Integer hospitalId);
	void deleteDoctorByPhone(String phone,Integer hospitalId);
	void updateStatus(Integer status,Integer hospitalId);
	HospitalDoctorNotice getHospitalDoctorNotice(String username,Integer hospitalId);
	void updateStatusByUsername(String username, Integer status,Integer hospitalId);
	void updateStatusByNewStatus(Integer status,Integer newStatus,Integer hospitalId);
}
