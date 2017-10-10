package com.jumper.hospital.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * HospitalDoctorTitle entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "hospital_doctor_title", catalog = "jumper_anglesound")
public class HospitalDoctorTitle implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer title;
	private Timestamp addTime;
	private String name;
	 
	// Constructors

	/** default constructor */
	public HospitalDoctorTitle() {
	}


	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "title")
	public Integer getTitle() {
		return this.title;
	}

	public void setTitle(Integer title) {
		this.title = title;
	}

	@Column(name = "add_time", length = 19)
	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	@Column(name = "name", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


}