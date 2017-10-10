package com.jumper.hospital.vo;

import java.io.Serializable;

/**
 * 身份信息
 */
public class Principal implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	private Integer id;

	/** 用户名 */
	private String username;
	
	/** 设置医院Id */
	private Integer hospitalId;

	/**
	 * @param id ID
	 * @param username 用户名
	 */
	public Principal(Integer id, String username) {
		this.id = id;
		this.username = username;
	}
	
	

	public Principal(Integer id, String username, Integer hospitalId) {
		super();
		this.id = id;
		this.username = username;
		this.hospitalId = hospitalId;
	}



	/**
	 * 获取ID
	 * @return ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置ID
	 * @param id ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取用户名
	 * @return 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置用户名
	 * @param username 用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/** 设置医院Id */
	public Integer getHospitalId() {
		return hospitalId;
	}
	
	/** 设置医院Id */
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	@Override
	public String toString() {
		return username;
	}

}