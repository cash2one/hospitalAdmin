package com.jumper.hospital.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

/**
 * FamilyUserPressureRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "family_user_pressure_record", catalog = "jumper_anglesound")
public class FamilyUserPressureRecord implements java.io.Serializable {
	@Transient
	private static final long serialVersionUID = -4655901394113119737L;
	private Integer id;
	@NotNull
	private Integer familyUserInfo;// 用户id
	@NotNull @Min(value=1)
	private Integer pressureLow;// 血压低值  舒张压值
	@NotNull @Min(value=1)
	private Integer pressureHeight;//血压高值  收缩压值
	private Integer pressureState;// 血压状态 后台计算  /** 血压状态(0：偏低，1：正常，2：偏高) **/
	private Date addTime;/** 记录添加时间 **/
	@NotNull
	private Integer heartRate;/** 心率 **/
	private Timestamp testTime;// 测试时间
	private int heartRateState;//血压心率 状态值  0偏低 1 正常 2 偏高
	private int pressureLowResult;//舒张压值 0偏低 1 正常 2 偏高
	private int pressureHeightResult;//收缩压值 0偏低 1 正常 2 偏高
	
	private String flag;
    @Transient
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	// Constructors

	/** default constructor */
	public FamilyUserPressureRecord() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "family_user_info")
	public Integer getFamilyUserInfo() {
		return this.familyUserInfo;
	}

	public void setFamilyUserInfo(Integer familyUserInfo) {
		this.familyUserInfo = familyUserInfo;
	}

	@Column(name = "pressure_low")
	public Integer getPressureLow() {
		return this.pressureLow;
	}

	public void setPressureLow(Integer pressureLow) {
		this.pressureLow = pressureLow;
	}

	@Column(name = "pressure_height")
	public Integer getPressureHeight() {
		return this.pressureHeight;
	}

	public void setPressureHeight(Integer pressureHeight) {
		this.pressureHeight = pressureHeight;
	}

	@Column(name = "pressure_state")
	public Integer getPressureState() {
		return this.pressureState;
	}

	public void setPressureState(Integer pressureState) {
		this.pressureState = pressureState;
	}

	@Column(name = "test_time", length = 19)
	public Timestamp getTestTime() {
		return this.testTime;
	}

	public void setTestTime(Timestamp testTime) {
		this.testTime = testTime;
	}


	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	@Column(name = "heart_rate")
	public Integer getHeartRate() {
		return this.heartRate;
	}

	public void setHeartRate(Integer heartRate) {
		this.heartRate = heartRate;
	}
	public int getPressureLowResult() {
		return pressureLowResult;
	}
	public void setPressureLowResult(int pressureLowResult) {
		this.pressureLowResult = pressureLowResult;
	}
	public int getPressureHeightResult() {
		return pressureHeightResult;
	}
	public void setPressureHeightResult(int pressureHeightResult) {
		this.pressureHeightResult = pressureHeightResult;
	}
	public int getHeartRateState() {
		return heartRateState;
	}
	public void setHeartRateState(int heartRateState) {
		this.heartRateState = heartRateState;
	}

}