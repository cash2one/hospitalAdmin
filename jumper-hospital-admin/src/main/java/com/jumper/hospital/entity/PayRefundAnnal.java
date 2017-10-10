package com.jumper.hospital.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 退款/申诉记录
 * 
 * @date ：2016年7月12日 下午3:24:20
 * @version ： 1.0
 * @parameter
 */
@Entity
@Table(name = "pay_refund_annal")
public class PayRefundAnnal extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	/** 用户名称 */
	private String userName;
	/**医生Id/医院Id*/
	private Integer doctorId;
	/** 提供服务者的名称（医生/医院名称） */
	private String serviceName;
	/** 退款原因 */
	private String refundOrigin;
	/** 退款金额 */
	private Double money;
	/** 退款/申诉 状态*/
	private Integer status;
	/** 申请/申诉 时间 */
	private Date refundTime;
	/** 退款类型(申请退款/申诉退款) 1:申请退款    2:申诉退款 */
	private String refundType;
	/** 订单id */
	private PayOrderInfo orderId;
	/** 服务类型 	图文咨询:1	医院问诊:2	胎心监护:3	在线问诊:4	私人医生:5	图文资讯:6 */
	private Integer serviceType;
	/** 是否是医生或者医院创建  0:平台创建   1:医生或者医院创建*/
	private Integer isDoctors;
	/** 订单号 */ 
	private String orderNo;
	/**是医生 还是医院（1: 医生   2: 医院） */
	private Integer doctorType;
	/**办理人(申请的最终处理人)**/
	private String handleName;
	/**办理人的处理意见**/
	private String handleRemark;
	/**订单结束时间**/
	private Date rufundEndTime;
	/**  当前处理者   */
	private String currentUserid;
	/** 临时属性：订单 */
	private MonitorOrder monitorOrder;
	/**  支付配置id  */
	private Integer payConfigId;
	/** 是否是独立医院  0: 平台     1:是独立医院   默认0*/
	private Integer isAutonomy;

	public Integer getDoctorType() {
		return doctorType;
	}

	public void setDoctorType(Integer doctorType) {
		this.doctorType = doctorType;
	}

	public Integer getIsDoctors() {
		return isDoctors;
	}

	public void setIsDoctors(Integer isDoctors) {
		this.isDoctors = isDoctors;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getRefundOrigin() {
		return refundOrigin;
	}

	public void setRefundOrigin(String refundOrigin) {
		this.refundOrigin = refundOrigin;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public String getRefundType() {
		return refundType;
	}

	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}
	
	@ManyToOne(targetEntity=PayOrderInfo.class, optional=false, fetch = FetchType.EAGER) 
	@JoinColumn(name="order_id") 
	public PayOrderInfo getOrderId() {
		return orderId;
	}

	public void setOrderId(PayOrderInfo orderId) {
		this.orderId = orderId;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Transient
	public MonitorOrder getMonitorOrder() {
		return monitorOrder;
	}

	public void setMonitorOrder(MonitorOrder monitorOrder) {
		this.monitorOrder = monitorOrder;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public String getHandleName() {
		return handleName;
	}

	public void setHandleName(String handleName) {
		this.handleName = handleName;
	}

	public String getHandleRemark() {
		return handleRemark;
	}

	public void setHandleRemark(String handleRemark) {
		this.handleRemark = handleRemark;
	}

	public Date getRufundEndTime() {
		return rufundEndTime;
	}

	public void setRufundEndTime(Date rufundEndTime) {
		this.rufundEndTime = rufundEndTime;
	}

	public String getCurrentUserid() {
		return currentUserid;
	}

	public void setCurrentUserid(String currentUserid) {
		this.currentUserid = currentUserid;
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
		return "PayRefundAnnal [userName=" + userName + ", doctorId="
				+ doctorId + ", serviceName=" + serviceName + ", refundOrigin="
				+ refundOrigin + ", money=" + money + ", status=" + status
				+ ", refundTime=" + refundTime + ", refundType=" + refundType
				+ ", orderId=" + orderId + ", serviceType=" + serviceType
				+ ", isDoctors=" + isDoctors + ", orderNo=" + orderNo
				+ ", doctorType=" + doctorType + ", handleName=" + handleName
				+ ", handleRemark=" + handleRemark + ", rufundEndTime="
				+ rufundEndTime + ", currentUserid=" + currentUserid
				+ ", monitorOrder=" + monitorOrder + ", payConfigId="
				+ payConfigId + ", isAutonomy=" + isAutonomy + "]";
	}

}
