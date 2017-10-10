package com.jumper.hospital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="monitor_hospital_service_time")
public class MonitorHospitalServiceTime extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/*一周的第几天 0：周一、1：周二、2：周三、3：周四、4：周五、5：周六、6：周天*/
	private Integer dayOfWeek;
	/*医院id外键*/
	//private HospitalInfo hospitalIds;
	/*医院id外键*/
	private Integer hospitalId;
	/*某一天服务开启时间0~23之间*/
	private String startTime;
	/*某一天服务结束时间时间0~23之间*/
	private String endTime;
	/*当前时间是否生效  0:未生效  1：已生效*/
	private Boolean isEnabled;
	
	public Integer getDayOfWeek() {
		return dayOfWeek;
	}
	
	@Column(name="day_of_week")
	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
/*	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="hospital_id")
	public HospitalInfo getHospitalIds() {
		return hospitalIds;
	}
	public void setHospitalIds(HospitalInfo hospitalIds) {
		this.hospitalIds = hospitalIds;
	}*/
	
	@Column(name="start_time")
	public String getStartTime() {
		return startTime;
	}
	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	@Column(name="end_time")
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}
