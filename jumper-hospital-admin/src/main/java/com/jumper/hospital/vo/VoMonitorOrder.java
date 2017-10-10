package com.jumper.hospital.vo;

import java.io.Serializable;

public class VoMonitorOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 订单记录ID **/
	private Integer id;
	/** 手机号码 **/
	private String mobile;
	/** 真实姓名 **/
	private String realName;
	/** 年龄 **/
	private Integer age;
	/** 监控服务ID **/
	private Integer optionId;
	/** 监控用户ID **/
	private Integer monitorUserId;
	/** 用户预产期 **/
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getOptionId() {
		return optionId;
	}
	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}
	public Integer getMonitorUserId() {
		return monitorUserId;
	}
	public void setMonitorUserId(Integer monitorUserId) {
		this.monitorUserId = monitorUserId;
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
