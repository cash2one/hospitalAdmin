package com.jumper.hospital.vo;

import java.io.Serializable;

public class VoHospitalAutoComplete implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 医院ID **/
	private Integer id;
	/** 医院名称 **/
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
