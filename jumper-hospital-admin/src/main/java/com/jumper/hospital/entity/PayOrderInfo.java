package com.jumper.hospital.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 订单明细记录
 * 
 * @date ：2016年7月12日 下午3:24:00
 * @version ： 1.0
 * @parameter
 */
@Entity
@Table(name = "pay_order_info")
public class PayOrderInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	/** 用户名称 */
	private String userName;
	/** 医生ID/医院ID */
	private int doctorId;
	/** 医生名称/医院名称 */
	private String doctorName;
	/** 支付订单号 */ 
	private String payOrderNo;
	/** 支付渠道(0.线下支付，1:支付宝支付,2:微信支付) */
	private Integer payChannels;
	/** 交易状态(-1：默认初始值(还未付款状态),-2:付款失败0:待支付，1已支付，2已退费，3已结束，4已关闭*/
	private Integer dealState;
	/** 交易金额 */
	private Double deaMoney;
	/** 服务类型(1.图文咨询;2.医院问诊;3.胎心监护;4.在线问诊;5.私人医生) */
	private Integer serviceType;
	/** 处理状态(0：默认初始值，-1：申请退款失败，-2：申诉退款失败，1:申请退款;2:退款处理中;3:退款已完成 ；   4申诉退款；   5申诉中；   6 申诉成功；) */
	private Integer handleState;
	/** 收入金额 */
	private Double incomeMoney;
	/** 退款金额 */
	private Double refundMoney;
	/** 订单创建时间 */
	private Date orderStartTime;
	/** 订单支付时间 */
	private Date orderPayTime;
	/** 申请退款时间 */
	private Date refundTime;
	/** 申请退款结束时间 */
	private Date refundEndTime;
	/** 申诉退费时间 */
	private Date appealTime;
	/** 申诉退费结束时间 */
	private Date appealEndTime;
	/** 订单结束时间 */
	private Date orderEndTime;
	/** 订单类型 0:平台创建   1:医生或者医院创建*/
	private Integer orderType;
	/** 订单号 */
	private String orderNo;
	/**是医生 还是医院（1: 医生   2: 医院） */
	private Integer doctorType;
	/**  支付配置id  */
	private Integer payConfigId;
	/** 是否是独立医院  0: 平台     1:是独立医院   默认0*/
	private Integer isAutonomy;
	/** 用户ID **/
	private Integer userId;
	
	
	public Integer getDoctorType() {
		return doctorType;
	}

	public void setDoctorType(Integer doctorType) {
		this.doctorType = doctorType;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public Integer getPayChannels() {
		return payChannels;
	}

	public void setPayChannels(Integer payChannels) {
		this.payChannels = payChannels;
	}

	public Integer getDealState() {
		return dealState;
	}

	public void setDealState(Integer dealState) {
		this.dealState = dealState;
	}

	public Double getDeaMoney() {
		return deaMoney;
	}

	public void setDeaMoney(Double deaMoney) {
		this.deaMoney = deaMoney;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getHandleState() {
		return handleState;
	}

	public void setHandleState(Integer handleState) {
		this.handleState = handleState;
	}

	public Double getIncomeMoney() {
		return incomeMoney;
	}

	public void setIncomeMoney(Double incomeMoney) {
		this.incomeMoney = incomeMoney;
	}

	public Double getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(Double refundMoney) {
		this.refundMoney = refundMoney;
	}

	public Date getOrderStartTime() {
		return orderStartTime;
	}

	public void setOrderStartTime(Date orderStartTime) {
		this.orderStartTime = orderStartTime;
	}

	public Date getOrderPayTime() {
		return orderPayTime;
	}

	public void setOrderPayTime(Date orderPayTime) {
		this.orderPayTime = orderPayTime;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public Date getAppealTime() {
		return appealTime;
	}

	public void setAppealTime(Date appealTime) {
		this.appealTime = appealTime;
	}

	public Date getOrderEndTime() {
		return orderEndTime;
	}

	public void setOrderEndTime(Date orderEndTime) {
		this.orderEndTime = orderEndTime;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Date getRefundEndTime() {
		return refundEndTime;
	}

	public void setRefundEndTime(Date refundEndTime) {
		this.refundEndTime = refundEndTime;
	}

	public Date getAppealEndTime() {
		return appealEndTime;
	}

	public void setAppealEndTime(Date appealEndTime) {
		this.appealEndTime = appealEndTime;
	}
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getPayConfigId() {
		return payConfigId;
	}

	public void setPayConfigId(Integer payConfigId) {
		this.payConfigId = payConfigId;
	}

	public Integer getIsAutonomy() {
		return isAutonomy;
	}

	public void setIsAutonomy(Integer isAutonomy) {
		this.isAutonomy = isAutonomy;
	}

	@Override
	public String toString() {
		return "PayOrderInfo [userName=" + userName + ", doctorId=" + doctorId
				+ ", doctorName=" + doctorName + ", payOrderNo=" + payOrderNo
				+ ", payChannels=" + payChannels + ", dealState=" + dealState
				+ ", deaMoney=" + deaMoney + ", serviceType=" + serviceType
				+ ", handleState=" + handleState + ", incomeMoney="
				+ incomeMoney + ", refundMoney=" + refundMoney
				+ ", orderStartTime=" + orderStartTime + ", orderPayTime="
				+ orderPayTime + ", refundTime=" + refundTime
				+ ", refundEndTime=" + refundEndTime + ", appealTime="
				+ appealTime + ", appealEndTime=" + appealEndTime
				+ ", orderEndTime=" + orderEndTime + ", orderType=" + orderType
				+ ", orderNo=" + orderNo + ", doctorType=" + doctorType
				+ ", payConfigId=" + payConfigId + ", isAutonomy=" + isAutonomy
				+ "]";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
