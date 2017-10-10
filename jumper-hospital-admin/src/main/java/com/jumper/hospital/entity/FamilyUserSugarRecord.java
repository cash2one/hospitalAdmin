package com.jumper.hospital.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

/**
 * FamilyUserSugarRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "family_user_sugar_record", catalog = "jumper_anglesound")
public class FamilyUserSugarRecord implements java.io.Serializable {
	@Transient
	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotNull @NotBlank @NotEmpty
	private Integer familyUserId;/** 测试用户 **/
	@NotNull @NotBlank @NotEmpty
	private Double averageValue;/** 血糖平均值 **/
	 
	private Integer sugarState;/** 血糖状态(0:偏低，1：正常，2：偏高) **/
	@NotNull @NotBlank @NotEmpty
	private Integer testTimeState;/** 测试时间状态(默认餐前空腹状态 0) **/
	private Date addTime;/** 添加时间 **/
	private Date testTime;/** app测试时间 **/
	private String flag;
    @Transient
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}



	/** default constructor */
	public FamilyUserSugarRecord() {
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

	@Column(name = "family_user_id")
	public Integer getFamilyUserId() {
		return this.familyUserId;
	}

	public void setFamilyUserId(Integer familyUserId) {
		this.familyUserId = familyUserId;
	}

	 
	@Column(name = "average_value", precision = 22, scale = 0)
	public Double getAverageValue() {
		return this.averageValue;
	}

	public void setAverageValue(Double averageValue) {
		this.averageValue = averageValue;
	}

	@Column(name = "sugar_state")
	public Integer getSugarState() {
		return this.sugarState;
	}

	public void setSugarState(Integer sugarState) {
		this.sugarState = sugarState;
	}

	@Column(name = "test_time_state")
	public Integer getTestTimeState() {
		return this.testTimeState;
	}

	public void setTestTimeState(Integer testTimeState) {
		this.testTimeState = testTimeState;
	}

	@Column(name = "add_time", length = 19)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}



	public Date getTestTime() {
		return testTime;
	}



	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}

	 


}