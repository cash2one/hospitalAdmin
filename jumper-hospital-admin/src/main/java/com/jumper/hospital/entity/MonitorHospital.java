package com.jumper.hospital.entity;
/**
 * 远程监控医院
 * @author rent
 * @date 2015-09-15
 */
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jumper.hospital.enums.Week;

@Entity
@Table(name="monitor_hospital")
public class MonitorHospital extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 开通远程监控的医院 **/
	private HospitalInfo hospitalId;
	/** 开通远程监控时间 **/
	private Timestamp addTime;
	/** 远程监控开始时间(周几) **/
	private Week startDay;
	/** 远程监控结束时间(周几) **/
	private Week endDay;
	/** 远程监控开始具体时间(09:00) **/
	private String startTime;
	/** 远程监控结束具体时间(18:00) **/
	private String endTime;
	/** 是否开通 **/
	private Boolean isOpen;
	/** 该医院下远程监控订单列表 **/
	private List<MonitorOrder> orderList;
	/** 医院监控服务列表(常规监控，远程监控) **/
	private List<MonitorSetting> settingList;
	/** 是否开通短信通知（1：是，2:否）**/
	private Integer openMsg;
	/*报告未审核隔了多少个小时自动退换用户次数*/
	private Integer returnDeadline;
	/** 异常时间 （单位：分钟）**/
	private Integer abnormalTime;
	/** 预警记录下限 **/
	private Integer lowerLimit;
	/** 预警记录上限 **/
	private Integer upperLimit;
	
	/*评分方式 0:Kreb's 1:改良Fischer  2:三级图形分类评分方法（ACCOG）*/
	private Integer scoringMethod;
	
	public Integer getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public Integer getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Integer upperLimit) {
		this.upperLimit = upperLimit;
	}

	/*******************************************getter and setter*******************************************/
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="hospital_id")
	public HospitalInfo getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(HospitalInfo hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "monitorHospitalId")
	public List<MonitorOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<MonitorOrder> orderList) {
		this.orderList = orderList;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "monitorHospitalId")
	public List<MonitorSetting> getSettingList() {
		return settingList;
	}

	public void setSettingList(List<MonitorSetting> settingList) {
		this.settingList = settingList;
	}

	public Week getStartDay() {
		return startDay;
	}

	public void setStartDay(Week startDay) {
		this.startDay = startDay;
	}

	public Week getEndDay() {
		return endDay;
	}

	public void setEndDay(Week endDay) {
		this.endDay = endDay;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	public Integer getOpenMsg() {
		return openMsg;
	}

	public void setOpenMsg(Integer openMsg) {
		this.openMsg = openMsg;
	}

	public Integer getAbnormalTime() {
		return abnormalTime;
	}

	public void setAbnormalTime(Integer abnormalTime) {
		this.abnormalTime = abnormalTime;
	}

	public Integer getReturnDeadline() {
		return returnDeadline;
	}

	public void setReturnDeadline(Integer returnDeadline) {
		this.returnDeadline = returnDeadline;
	}

	public Integer getScoringMethod() {
		return scoringMethod;
	}

	public void setScoringMethod(Integer scoringMethod) {
		this.scoringMethod = scoringMethod;
	}
	
}
