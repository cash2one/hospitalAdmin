package com.jumper.hospital.entity;
/**
 * 远程监控订单
 * @author rent
 * @date 2015-09-15
 */
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jumper.hospital.enums.RemoteOrderState;
import com.jumper.hospital.enums.RemoteType;
import com.jumper.hospital.utils.TimeUtils;

@Entity
@Table(name="monitor_order")
public class MonitorOrder extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 订单所对应的远程监控医院ID **/
	private MonitorHospital monitorHospitalId;
	/** 购买的远程监控次数 **/
	private Integer monitorCount;
	/** 远程监控剩余次数 **/
	private Integer leftCount;
	/** 购买服务的金额 **/
	private double money;
	/** 购买的服务类型 **/
	private RemoteType remoteType;
	/** 远程监控下单用户 **/
	private MonitorUserInfo monitorUserId;
	/** 定单是否已退费 **/
	private Boolean isEffective;
	/** 购买医院监控服务的ID **/
	private Integer optionId;
	/** 下单时间 **/
	private Timestamp addTime;
	/** 订单失效时间 **/
	private Timestamp expireTime;
	/** 订单状态，主要用于院内监测(0：待监测；1：监测中；2:已完成) **/
	private RemoteOrderState state;
	/** 远程监控订单消费信息列表(一个订单可以消费多次) **/
	private List<MonitorOrderConsumer> consumerList;
	/** 用户孕周 **/
	@SuppressWarnings("unused")
	private Integer pregnantWeek;
	
	/** 平台订单号 **/
	private String orderId;
	/** 交易状态(-1：申请退款失败，-2：申诉退款失败，0:待支付，1已支付，2已退费，3已结束，4已关闭）;*/
	private Integer dealStates;
	/** 支付渠道(0.线下支付，1:支付宝支付,2:微信支付,3:设备租赁) */
	private Integer payChannels;
	/*设备租赁订单ID*/
	private String leaseOrderId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="monitor_hospital_id")
	public MonitorHospital getMonitorHospitalId() {
		return monitorHospitalId;
	}
	public void setMonitorHospitalId(MonitorHospital monitorHospitalId) {
		this.monitorHospitalId = monitorHospitalId;
	}
	public Integer getMonitorCount() {
		return monitorCount;
	}
	public void setMonitorCount(Integer monitorCount) {
		this.monitorCount = monitorCount;
	}
	public Integer getLeftCount() {
		return leftCount;
	}
	public void setLeftCount(Integer leftCount) {
		this.leftCount = leftCount;
	}
	
	public String getLeaseOrderId() {
		return leaseOrderId;
	}
	public void setLeaseOrderId(String leaseOrderId) {
		this.leaseOrderId = leaseOrderId;
	}
	public Integer getPayChannels() {
		return payChannels;
	}
	public void setPayChannels(Integer payChannels) {
		this.payChannels = payChannels;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public RemoteType getRemoteType() {
		return remoteType;
	}
	public void setRemoteType(RemoteType remoteType) {
		this.remoteType = remoteType;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="monitor_user_id")
	public MonitorUserInfo getMonitorUserId() {
		return monitorUserId;
	}
	public void setMonitorUserId(MonitorUserInfo monitorUserId) {
		this.monitorUserId = monitorUserId;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "monitorOrderId")
	public List<MonitorOrderConsumer> getConsumerList() {
		return consumerList;
	}
	public void setConsumerList(List<MonitorOrderConsumer> consumerList) {
		this.consumerList = consumerList;
	}
	public Boolean getIsEffective() {
		return isEffective;
	}
	public void setIsEffective(Boolean isEffective) {
		this.isEffective = isEffective;
	}
	public Integer getOptionId() {
		return optionId;
	}
	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}
	public Timestamp getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Timestamp expireTime) {
		this.expireTime = expireTime;
	}
	public RemoteOrderState getState() {
		return state;
	}
	public void setState(RemoteOrderState state) {
		this.state = state;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getDealStates() {
		return dealStates;
	}
	public void setDealStates(Integer dealStates) {
		this.dealStates = dealStates;
	}
	@Transient
	public Integer getPregnantWeek() {
		Date edc;
		if(monitorUserId.getEdc() != null){
			edc = monitorUserId.getEdc();
		}else{
			edc = monitorUserId.getUserId() != null ? monitorUserId.getUserId().getExpectedDateOfConfinement() : null;
		}
		if(edc != null){
			try {
				return TimeUtils.getPregnantWeek(edc, TimeUtils.getCurrentTime())[0];
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	public void setPregnantWeek(Integer pregnantWeek) {
		this.pregnantWeek = pregnantWeek;
	}
	
}
