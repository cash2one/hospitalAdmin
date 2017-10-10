package com.jumper.hospital.entity;
/**
 * 医院的监控设置 (常规监控、实时监控的开通与关闭)
 * Remark:医院开通远程监控医院的时候默认添加，但状态为关闭
 * @author rent
 * @date 2015-09-15
 */
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.jumper.hospital.enums.RemoteType;

@Entity
@Table(name="monitor_setting")
public class MonitorSetting extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 监控类型 **/
	private RemoteType type;
	/** 是否开启 **/
	private Boolean isOpen;
	/** 远程监控医院 **/
	private MonitorHospital monitorHospitalId;
	/** 远程监控类型详情列表 **/
	private List<MonitorSettingOption> options;

	public RemoteType getType() {
		return type;
	}

	public void setType(RemoteType type) {
		this.type = type;
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="monitor_hospital_id")
	public MonitorHospital getMonitorHospitalId() {
		return monitorHospitalId;
	}

	public void setMonitorHospitalId(MonitorHospital monitorHospitalId) {
		this.monitorHospitalId = monitorHospitalId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "monitorSetId")
	public List<MonitorSettingOption> getOptions() {
		return options;
	}

	public void setOptions(List<MonitorSettingOption> options) {
		this.options = options;
	}
	
}
