package com.jumper.hospital.service;
/**
 * 常用授课医师Service
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import com.jumper.hospital.entity.SchoolCommonDoctor;

public interface SchoolCommonDoctorService extends BaseService<SchoolCommonDoctor, Integer> {

	/**
	 * 通过医院获取常用授课医师信息
	 * @param hospitalId 医院ID
	 * @return
	 */
	public List<SchoolCommonDoctor> getSchoolCommonDoctorByHospital(Integer hospitalId);
	
	/**
	 * 添加常用医生信息
	 * @param doctorName 医生名称
	 * @param doctorTitle 医生职称
	 * @param hospitalId 医院ID
	 * @return
	 */
	public SchoolCommonDoctor addSchoolCommonDoctor(String doctorName, String doctorTitle, Integer hospitalId);
	/**
	 * 校验医生是否重复添加
	 * @param doctorName
	 * @param doctorTitle
	 * @param hospitalId
	 * @return
	 */
	public boolean checkDoctorHasExist(String doctorName, String doctorTitle, Integer hospitalId);
}
