package com.jumper.hospital.entity;
/**
 * 订单消费详情临时表
 * @author rent
 * @date 2015-11-24
 */
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jumper.hospital.enums.ReportState;

@Entity
@Table(name="monitor_order_consumer_temp")
public class MonitorOrderConsumerTemp extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 对应医院购买的监控订单 **/
	private MonitorOrder monitorOrderId;
	/** 订单消费状态(报告单是否已出) **/
	private ReportState state;
	/** 本次消费订单的订单号 **/
	private String orderId;
	/** 订单消费用户，此处方便查询用 **/
	private UserInfo userId;
	/** 本次订单消费下单时间 **/
	private Timestamp applyTime;
	/** 问题ID 用于聊天 **/
	private Long jid;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name="monitor_order_id")
	public MonitorOrder getMonitorOrderId() {
		return monitorOrderId;
	}
	public void setMonitorOrderId(MonitorOrder monitorOrderId) {
		this.monitorOrderId = monitorOrderId;
	}
	public ReportState getState() {
		return state;
	}
	public void setState(ReportState state) {
		this.state = state;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name="userId")
	public UserInfo getUserId() {
		return userId;
	}
	public void setUserId(UserInfo userId) {
		this.userId = userId;
	}
	public Timestamp getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}
	public Long getJid() {
		return jid;
	}
	public void setJid(Long jid) {
		this.jid = jid;
	}
	
}
