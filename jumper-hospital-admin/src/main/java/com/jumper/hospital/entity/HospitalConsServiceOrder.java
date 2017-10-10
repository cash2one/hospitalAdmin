package com.jumper.hospital.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="hospital_cons_service_order")
public class HospitalConsServiceOrder extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	//订单费用
	private double trueCost;
	
	//服务id
	private HospitalService hospitalService;
	
	//用户id
	private UserInfo userInfo;
	
	//添加时间
	private Date addTime;
	
	//订单状态
	private Integer status;
	
	//订单id
	private String orderId;
	
	//支付方式：1、支付宝；2、微信支付
	private Integer payType;
	
	//订单支付成功的时间
	private Date paySuccTime;
	
	//医院id
	private HospitalInfo hospitalInfo;
	
	//微信或支付宝单号
	private String transactionId;
	
	//是否可用(删除与否)
	private Integer isEnabled;
	
	private Integer withdrawalsId;  //月度结算ID

	@Column(name="true_cost")
	public double getTrueCost() {
		return trueCost;
	}

	public void setTrueCost(double trueCost) {
		this.trueCost = trueCost;
	}

	@ManyToOne
	@JoinColumn(name="service_id")
	public HospitalService getHospitalService() {
		return hospitalService;
	}

	public void setHospitalService(HospitalService hospitalService) {
		this.hospitalService = hospitalService;
	}

	@ManyToOne
	@JoinColumn(name="user_id")
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Column(name="add_time")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="order_id")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name="pay_type")
	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	@Column(name="pay_succ_time")
	public Date getPaySuccTime() {
		return paySuccTime;
	}

	public void setPaySuccTime(Date paySuccTime) {
		this.paySuccTime = paySuccTime;
	}

	@ManyToOne
	@JoinColumn(name="hospital_id")
	public HospitalInfo getHospitalInfo() {
		return hospitalInfo;
	}

	public void setHospitalInfo(HospitalInfo hospitalInfo) {
		this.hospitalInfo = hospitalInfo;
	}

	@Column(name="transaction_id")
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Column(name="is_enabled")
	public Integer getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Integer getWithdrawalsId() {
		return withdrawalsId;
	}

	public void setWithdrawalsId(Integer withdrawalsId) {
		this.withdrawalsId = withdrawalsId;
	}
	
	
	
}
