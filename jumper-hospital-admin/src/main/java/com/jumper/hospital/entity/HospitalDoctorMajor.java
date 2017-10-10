package com.jumper.hospital.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 科室信息
 * @author gyx
 */
@Entity
@Table(name="hospital_doctor_major")
public class HospitalDoctorMajor extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	//科室名称
	private String major;
	//科室图片
	private String imageUrl;
	//添加时间
	private Date addTime;
	
	
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	@Column(name="image_url")
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Column(name="add_time")
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	
}
