/**
 * 
 */
package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.entity.HospitalDoctorNotice;

/**
 * @author huangcr
 *
 * 2017-7-13
 */
public interface HospitalDoctorNoticeService extends BaseService<HospitalDoctorNotice, Integer>{
	List<HospitalDoctorNotice> findAll(Integer status,Integer hospitalId);
	List<HospitalDoctorNotice> findAllisnotZero(Integer status,Integer hospitalId);
	void deleteDoctor(Integer status,Integer hospitalId);
	void deleteDoctorByPhone(String username,Integer hospitalId);
	void addDoctorNotice(HospitalDoctorNotice doctorNotice);
	void updateStatus(Integer status,Integer hospitalId);
	HospitalDoctorNotice getHospitalDoctorNotice(String username,Integer hospitalId);
	void updateStatusByUsername(String username,Integer status,Integer hospitalId);
	void updateStatusByNewStatus(Integer status,Integer newStatus,Integer hospitalId);
}
