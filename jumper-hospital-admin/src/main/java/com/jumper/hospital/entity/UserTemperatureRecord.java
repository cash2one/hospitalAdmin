package com.jumper.hospital.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * entity
 * @author wt
 */
@Entity
@Table(name="user_temperature_record")
public class UserTemperatureRecord extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	/** 测试用户 **/
	private UserInfo userInfo;
	
	private Double average_value;
	
	private Integer temperature_state;
	
	private Timestamp add_time;
	
	private Timestamp start_time;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public Double getAverage_value() {
		return average_value;
	}
	public void setAverage_value(Double average_value) {
		this.average_value = average_value;
	}
	public Integer getTemperature_state() {
		return temperature_state;
	}
	public void setTemperature_state(Integer temperature_state) {
		this.temperature_state = temperature_state;
	}
	public Timestamp getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Timestamp add_time) {
		this.add_time = add_time;
	}
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	
	
	
}
