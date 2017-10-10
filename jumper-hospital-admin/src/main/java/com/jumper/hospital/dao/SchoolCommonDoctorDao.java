package com.jumper.hospital.dao;
/**
 * 常用授课医生Dao
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import com.jumper.hospital.entity.SchoolCommonDoctor;

public interface SchoolCommonDoctorDao extends BaseDao<SchoolCommonDoctor, Integer> {

	/**
	 * 通过医院获取常用授课医师信息
	 * @param hospitalId 医院ID
	 * @return
	 */
	public List<SchoolCommonDoctor> getSchoolCommonDoctorByHospital(Integer hospitalId);
	/**
	 * 校验该医生信息是否重复添加
	 * @param doctorName
	 * @param doctorTitle
	 * @param hospitalId
	 * @return
	 */
	public boolean checkDoctorHasExist(String doctorName, String doctorTitle, Integer hospitalId);
}
