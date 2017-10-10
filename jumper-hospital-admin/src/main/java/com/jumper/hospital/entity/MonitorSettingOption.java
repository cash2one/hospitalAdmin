package com.jumper.hospital.entity;
/**
 * 远程监控服务项目设置
 * Remark:服务的次数与价格
 * @author rent
 * @date 2015-09-15
 */
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="monitor_setting_option")
public class MonitorSettingOption extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 监控服务次数 **/
	private Integer number;
	/** 监控服务金额 **/
	private Double price;
	/** 所对应的监控类型 **/
	private MonitorSetting monitorSetId;
	
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name="monitor_set_id")
	public MonitorSetting getMonitorSetId() {
		return monitorSetId;
	}
	public void setMonitorSetId(MonitorSetting monitorSetId) {
		this.monitorSetId = monitorSetId;
	}
	
}
