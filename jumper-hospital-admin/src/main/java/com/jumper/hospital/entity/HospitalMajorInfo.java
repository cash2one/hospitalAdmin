package com.jumper.hospital.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="hospital_major_info")
public class HospitalMajorInfo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	
	//医院id
	private Integer hospitalId;
	
	//科室id
	private Integer majorId;
	
	
	//添加时间
	private Date addTime;
	/**是否开通网络诊室*/
	private Integer isnetwork;
	
	/**是否删除*/
	private Integer isdelete;
	
	private HospitalDoctorMajor hospitalDoctorMajor;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name="major_id",insertable=false,updatable=false)
	public HospitalDoctorMajor getHospitalDoctorMajor() {
		return hospitalDoctorMajor;
	}
 
	public void setHospitalDoctorMajor(HospitalDoctorMajor hospitalDoctorMajor) {
		this.hospitalDoctorMajor = hospitalDoctorMajor;
	}

	@Column(name="is_network")
	public Integer getIsnetwork() {
		return isnetwork;
	}

	public void setIsnetwork(Integer isnetwork) {
		this.isnetwork = isnetwork;
	}

	@Column(name="hospital_id")
	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	@Column(name="major_id")
	public Integer getMajorId() {
		return majorId;
	}

	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}
	
	@Column(name="add_time")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	@Column(name="is_delete")
	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	
	
}
