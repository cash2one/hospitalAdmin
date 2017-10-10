package com.jumper.hospital.vo;

import java.io.Serializable;

public class VoMonitorSettingOption implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 服务ID **/
	private Integer id;
	/** 服务次数 **/
	private Integer number;
	/** 监控服务金额 **/
	private Double price;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

}
