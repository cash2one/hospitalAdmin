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
@Table(name="user_weight_record")
public class UserWeightRecord extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private UserInfo userInfo;
	/** 体重测试平均值 **/
	private double average_value;
	/** 体重测试状态(0：偏轻，1：正常，2：偏重) **/
	private Integer weight_state;
	/** 测试周 **/
	private Integer test_week;
	/** 测试天 **/
	private Integer test_day;
	/** 测试时间 **/
	private Timestamp test_time;
	/** 记录添加时间 **/
	private Timestamp add_time;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public double getAverage_value() {
		return average_value;
	}
	public void setAverage_value(double average_value) {
		this.average_value = average_value;
	}
	public Integer getWeight_state() {
		return weight_state;
	}
	public void setWeight_state(Integer weight_state) {
		this.weight_state = weight_state;
	}
	public Integer getTest_week() {
		return test_week;
	}
	public void setTest_week(Integer test_week) {
		this.test_week = test_week;
	}
	public Integer getTest_day() {
		return test_day;
	}
	public void setTest_day(Integer test_day) {
		this.test_day = test_day;
	}
	public Timestamp getTest_time() {
		return test_time;
	}
	public void setTest_time(Timestamp test_time) {
		this.test_time = test_time;
	}
	public Timestamp getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Timestamp add_time) {
		this.add_time = add_time;
	}
	
}
