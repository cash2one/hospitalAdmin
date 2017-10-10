package com.jumper.hospital.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 退款/申诉 备注
 * 
 * @date ：2016年7月12日 下午4:23:42
 * @version ： 1.0
 * @parameter
 */
@Entity
@Table(name = "pay_refund_remark")
public class PayRefundRemark  extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/** 退款/申诉记录id */
	private Integer refundId;
	/** 备注人 */
	private String userId;	
	/** 备注人名称 */
	private String userName; 
	/** 备注内容 */
	private String remark;
	/** 备注时间 */
	private Date remarkDate;
	/** 备注类型(1退款、2拒绝、3向上级提交) */
	private Integer remarkType;
	/** 退款/申诉类型(0:退款、1：申诉) */
	private Integer type;
	
	public Integer getRefundId() {
		return refundId;
	}
	public void setRefundId(Integer refundId) {
		this.refundId = refundId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRemarkDate() {
		return remarkDate;
	}
	public void setRemarkDate(Date remarkDate) {
		this.remarkDate = remarkDate;
	}
	
	public Integer getRemarkType() {
		return remarkType;
	}
	public void setRemarkType(Integer remarkType) {
		this.remarkType = remarkType;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "PayRefundRemark [refundId=" + refundId + ", userId=" + userId + ", userName=" + userName + ", remark="
				+ remark + ", remarkDate=" + remarkDate + ", remarkType=" + remarkType + ", type=" + type + "]";
	}
	
	
	
}
