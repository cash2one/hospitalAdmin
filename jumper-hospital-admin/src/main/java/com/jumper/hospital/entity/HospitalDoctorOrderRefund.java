package com.jumper.hospital.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="hospital_doctor_order_refund")
public class HospitalDoctorOrderRefund extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 图文咨询订单ID **/
	private Integer servicesId;
	/** 退款金额 **/
	private Double refund;
	/** 推荐申请原因 **/
	private String reason;
	/** 处理该退款信息的管理员 **/
	private String operator;
	/** 订单退款状态 2:退款审核中 3:已退款 4:拒绝退费 5:申诉中;6:申诉成功并退款;7:申诉失败 **/
	private Integer state;
	/** 提醒状态 0:未提醒 1:已提醒 **/
	private Integer alert;
	/** 退费服务类型 1：图文咨询；2：医院问诊；3：私人医生 **/
	private Integer type;
	/** 医院问诊服务订单ID **/
	private Integer consOrderId;
	/** 拒绝退费原因 **/
	private String refuseReason;
	/** 申诉原因 **/
	private String complaintReason;
	/** 数据添加时间 **/
	private Timestamp addTime;
	/** 退款时间 **/
	private Timestamp refundSuccTime;
	/** 拒绝退款时间 **/
	private Timestamp refundFailTime;
	/** 退款申诉时间 **/
	private Timestamp complaintTime;
	/** 申诉成功时间 **/
	private Timestamp complaintSuccTime;
	/** 申诉失败时间 **/
	private Timestamp complaintFailTime;
	/** 支付宝或微信订单流水号 **/
	private String transactionId;
	
	public Integer getServicesId() {
		return servicesId;
	}
	public void setServicesId(Integer servicesId) {
		this.servicesId = servicesId;
	}
	public Double getRefund() {
		return refund;
	}
	public void setRefund(Double refund) {
		this.refund = refund;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getAlert() {
		return alert;
	}
	public void setAlert(Integer alert) {
		this.alert = alert;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getConsOrderId() {
		return consOrderId;
	}
	public void setConsOrderId(Integer consOrderId) {
		this.consOrderId = consOrderId;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public Timestamp getRefundSuccTime() {
		return refundSuccTime;
	}
	public void setRefundSuccTime(Timestamp refundSuccTime) {
		this.refundSuccTime = refundSuccTime;
	}
	public Timestamp getRefundFailTime() {
		return refundFailTime;
	}
	public void setRefundFailTime(Timestamp refundFailTime) {
		this.refundFailTime = refundFailTime;
	}
	public Timestamp getComplaintTime() {
		return complaintTime;
	}
	public void setComplaintTime(Timestamp complaintTime) {
		this.complaintTime = complaintTime;
	}
	public Timestamp getComplaintSuccTime() {
		return complaintSuccTime;
	}
	public void setComplaintSuccTime(Timestamp complaintSuccTime) {
		this.complaintSuccTime = complaintSuccTime;
	}
	public Timestamp getComplaintFailTime() {
		return complaintFailTime;
	}
	public void setComplaintFailTime(Timestamp complaintFailTime) {
		this.complaintFailTime = complaintFailTime;
	}
	public String getRefuseReason() {
		return refuseReason;
	}
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	public String getComplaintReason() {
		return complaintReason;
	}
	public void setComplaintReason(String complaintReason) {
		this.complaintReason = complaintReason;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
}
