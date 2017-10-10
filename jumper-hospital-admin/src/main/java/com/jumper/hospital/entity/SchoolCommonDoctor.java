package com.jumper.hospital.entity;
/**
 * 医生模板
 * @author rent
 * @date 2016-01-25	
 */
import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class SchoolCommonDoctor extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 医生名称 **/
	private String doctorName;
	/** 医生职称 **/
	private String doctorTitle;
	/** 医院ID **/
	private Integer hospitalId;

	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorTitle() {
		return doctorTitle;
	}
	public void setDoctorTitle(String doctorTitle) {
		this.doctorTitle = doctorTitle;
	}
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	
}
