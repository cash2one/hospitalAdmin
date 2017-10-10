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
@Table(name="user_oxygen_record")
public class UserOxygenRecord extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	/** 测试用户 **/
	private UserInfo userInfo;
	/** 血氧记录文件URL **/
	private String oxygen_file;
	/** 心率记录文件URL **/
	private String pulse_file;
	/** 血氧平均值 **/
	private double average_oxygen;
	/** 心率平均值 **/
	private Integer average_pulse;
	/** 测试时长(单位：S) **/
	private Integer test_time_length;
	/** 测试时间 **/
	private Timestamp test_time;
	/** 添加时间 **/
	private Timestamp add_time;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getOxygen_file() {
		return oxygen_file;
	}
	public void setOxygen_file(String oxygen_file) {
		this.oxygen_file = oxygen_file;
	}
	public String getPulse_file() {
		return pulse_file;
	}
	public void setPulse_file(String pulse_file) {
		this.pulse_file = pulse_file;
	}
	public double getAverage_oxygen() {
		return average_oxygen;
	}
	public void setAverage_oxygen(double average_oxygen) {
		this.average_oxygen = average_oxygen;
	}
	public Integer getAverage_pulse() {
		return average_pulse;
	}
	public void setAverage_pulse(Integer average_pulse) {
		this.average_pulse = average_pulse;
	}
	public Integer getTest_time_length() {
		return test_time_length;
	}
	public void setTest_time_length(Integer test_time_length) {
		this.test_time_length = test_time_length;
	}
	public Timestamp getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Timestamp add_time) {
		this.add_time = add_time;
	}
	public Timestamp getTest_time() {
		return test_time;
	}
	public void setTest_time(Timestamp test_time) {
		this.test_time = test_time;
	}
	
}
