/**
 * 
 */
package com.jumper.hospital.dao;

import com.jumper.hospital.entity.HospitalDoctorTitle;

/**
 * @author huangcr
 *
 * 2017-7-13
 */
public interface HospitalDoctorTitleDao extends BaseDao<HospitalDoctorTitle, Integer>{
	HospitalDoctorTitle findById(Integer id);
}
