package com.jumper.hospital.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="hospital_doctor_services_order")
public class HospitalDoctorServicesOrder extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 订单ID **/
	private String orderId;
	/** 类型 **/
	private Integer typeId;
	private Integer relateObjectId;
	private Double price;
	private Integer unit;
	private Double totalPrice;
	private Integer payType;
	private Timestamp paySuccessTime;
	private Timestamp addTime;
	private Integer doctorId;
	private String transactionId;
	private Boolean isEnabled;
	private Integer userId;;

	public String getOrderId() {
		return this.orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getTypeId() {
		return this.typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getRelateObjectId() {
		return this.relateObjectId;
	}
	public void setRelateObjectId(Integer relateObjectId) {
		this.relateObjectId = relateObjectId;
	}
	public Double getPrice() {
		return this.price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getUnit() {
		return this.unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public Double getTotalPrice() {
		return this.totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getPayType() {
		return this.payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Timestamp getPaySuccessTime() {
		return this.paySuccessTime;
	}
	public void setPaySuccessTime(Timestamp paySuccessTime) {
		this.paySuccessTime = paySuccessTime;
	}
	public Timestamp getAddTime() {
		return this.addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}