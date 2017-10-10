package com.jumper.hospital.vo;

import java.sql.Timestamp;

public class VOConsultRefund {

	/** id **/
	private Integer id;
	/** 订单ID **/
	private String orderId;
	/** 申请时间 **/
	private Timestamp time;
	/** 退费金额 **/
	private Double money;
	/** 退费原因 **/
	private String reason;
	/** 退费状态 **/
	private Integer state;
	/** 操作人 **/
	private String operater;
	/** 付费问诊订单ID **/
	private Integer conId;
	/** 自动退款时间 **/
	private Timestamp autoTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getOperater() {
		return operater;
	}
	public void setOperater(String operater) {
		this.operater = operater;
	}
	public Integer getConId() {
		return conId;
	}
	public void setConId(Integer conId) {
		this.conId = conId;
	}
	public Timestamp getAutoTime() {
		return autoTime;
	}
	public void setAutoTime(Timestamp autoTime) {
		this.autoTime = autoTime;
	}
	
}
