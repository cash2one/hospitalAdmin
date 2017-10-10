package com.jumper.hospital.vo;

import java.io.Serializable;

public class VoMonitorUserAutoComplete implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 用户ID **/
	private Integer id;
	/** 手机号码 **/
	private String mobile;
	/** 姓名 **/
	private String name;
	/** 年龄 **/
	private Integer age;
	/** 预产期 **/
	private String edc;
	
	/*用户地址*/
	private String address;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getEdc() {
		return edc;
	}
	public void setEdc(String edc) {
		this.edc = edc;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
