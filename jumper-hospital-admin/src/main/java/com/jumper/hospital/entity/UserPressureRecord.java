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
@Table(name="user_pressure_record")
public class UserPressureRecord extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	/** 测试用户 **/
	private UserInfo userInfo;
	/** 血压低值 **/
	private Integer pressure_low;
	/** 血压高值 **/
	private Integer pressure_height;
	/** 血压状态(0：偏低，1：正常，2：偏高) **/
	private Integer pressure_state;  
	/** 用户测试时间 **/
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
	public Integer getPressure_low() {
		return pressure_low;
	}
	public void setPressure_low(Integer pressure_low) {
		this.pressure_low = pressure_low;
	}
	public Integer getPressure_height() {
		return pressure_height;
	}
	public void setPressure_height(Integer pressure_height) {
		this.pressure_height = pressure_height;
	}
	public Integer getPressure_state() {
		return pressure_state;
	}
	public void setPressure_state(Integer pressure_state) {
		this.pressure_state = pressure_state;
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
