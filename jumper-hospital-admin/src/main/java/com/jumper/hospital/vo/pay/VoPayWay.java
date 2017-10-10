package com.jumper.hospital.vo.pay;

import java.io.Serializable;

/**
 * 支付方式
 * @author rent
 * @date 2016-08-04
 */
public class VoPayWay implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/** 类型 **/
	private Integer type;
	/** 名称 **/
	private String name;
	/** 图标 **/
	private String icon;
	/** 支付参数ID **/
	private Integer payConfigId;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getPayConfigId() {
		return payConfigId;
	}
	public void setPayConfigId(Integer payConfigId) {
		this.payConfigId = payConfigId;
	}
	
}
