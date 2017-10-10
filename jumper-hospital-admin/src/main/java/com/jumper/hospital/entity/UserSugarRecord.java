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
@Table(name="user_sugar_record")
public class UserSugarRecord extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	/** 测试用户 **/
	private UserInfo userInfo;
	/** 血糖平均值 **/
	private double average_value;
	/** 血糖状态(0:偏低，1：正常，2：偏高) **/
	private Integer sugar_state;
	/** 测试时间状态(0：早餐前，1：早餐后，2：午餐前，3：午餐后，4：晚餐前，5：晚餐后，6：睡前) **/
	private Integer test_time_state;
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
	public double getAverage_value() {
		return average_value;
	}
	public void setAverage_value(double average_value) {
		this.average_value = average_value;
	}
	public Integer getSugar_state() {
		return sugar_state;
	}
	public void setSugar_state(Integer sugar_state) {
		this.sugar_state = sugar_state;
	}
	public Integer getTest_time_state() {
		return test_time_state;
	}
	public void setTest_time_state(Integer test_time_state) {
		this.test_time_state = test_time_state;
	}
	public Timestamp getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Timestamp add_time) {
		this.add_time = add_time;
	}
	
	
	
}
