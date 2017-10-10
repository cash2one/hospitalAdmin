package com.jumper.hospital.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * 远程监测胎心测试数据保存表
 * @author rent
 */
@Entity
@Table(name="monitor_heartrate_record")
public class MonitorHeartrateRecord extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	/** 测试用户 **/
	private UserInfo userInfo;
	
	private String fetal_move_files;
	
	private String record_files;
	
	private Timestamp add_time;
	
	private String raw_files;
	
	private String title;
	/**
	 * 胎心记录类型:0:成人,1:婴儿
	 */
	private Integer type;
	
	private Double average_rate;
	
	private String fetal_move_value;
	
	private Integer fetal_move_times;
	/** 胎心测试时长(单位：秒) **/
	private Integer testTime;
	/** 宫缩记录 **/
	private String uterusRecord;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getFetal_move_files() {
		return fetal_move_files;
	}
	public void setFetal_move_files(String fetal_move_files) {
		this.fetal_move_files = fetal_move_files;
	}
	public String getRecord_files() {
		return record_files;
	}
	public void setRecord_files(String record_files) {
		this.record_files = record_files;
	}
	public Timestamp getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Timestamp add_time) {
		this.add_time = add_time;
	}
	public String getRaw_files() {
		return raw_files;
	}
	public void setRaw_files(String raw_files) {
		this.raw_files = raw_files;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getAverage_rate() {
		return average_rate;
	}
	public void setAverage_rate(Double average_rate) {
		this.average_rate = average_rate;
	}
	public String getFetal_move_value() {
		return fetal_move_value;
	}
	public void setFetal_move_value(String fetal_move_value) {
		this.fetal_move_value = fetal_move_value;
	}
	public Integer getFetal_move_times() {
		return fetal_move_times;
	}
	public void setFetal_move_times(Integer fetal_move_times) {
		this.fetal_move_times = fetal_move_times;
	}
	public Integer getTestTime() {
		return testTime;
	}
	public void setTestTime(Integer testTime) {
		this.testTime = testTime;
	}
	public String getUterusRecord() {
		return uterusRecord;
	}
	public void setUterusRecord(String uterusRecord) {
		this.uterusRecord = uterusRecord;
	}
	
}
