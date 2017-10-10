package com.jumper.hospital.entity;
/**
 * 监控项目(常规监控、实时监控所进行的监控项目)
 * @author rent
 * @date 2015-09-17
 */
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.jumper.hospital.enums.MonitorTestState;

@Entity
@Table(name="monitor_project")
public class MonitorProject extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 本次监测项目所对应的消费订单ID **/
	private MonitorOrderConsumer consumerId;
	/** 监测项目类型(类型定义在枚举类UserTestRecordType中) **/
	private Integer recordType;
	/** 当前监控项目完成状态(未完成、已完成) **/
	private MonitorTestState state;
	/** 监测项目添加时间 **/
	private Timestamp addTime;
	/** 本次监测项目所对应的监测数据 **/
	private List<MonitorProjectRecord> recordList;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="consumer_id")
	public MonitorOrderConsumer getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(MonitorOrderConsumer consumerId) {
		this.consumerId = consumerId;
	}
	public Integer getRecordType() {
		return recordType;
	}
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	public MonitorTestState getState() {
		return state;
	}
	public void setState(MonitorTestState state) {
		this.state = state;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "projectId")
	public List<MonitorProjectRecord> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<MonitorProjectRecord> recordList) {
		this.recordList = recordList;
	}
	
}
