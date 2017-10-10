package com.jumper.hospital.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

/**
 * FamilyUserTemperatureRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "family_user_temperature_record", catalog = "jumper_anglesound")
public class FamilyUserTemperatureRecord implements java.io.Serializable {
	@Transient
	private static final long serialVersionUID = 3111639328738784875L;
	private Integer id;
	@NotNull
	private Integer familyUserId;
	@NotNull @Min(value=1.0)
	private Double averageValue;
	private Integer temperatureState;// 0低  1 正常  2 高   后台计算
	private Date addTime;
	private Date testTime;
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
	public FamilyUserTemperatureRecord() {
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

	@Column(name = "temperature_state")
	public Integer getTemperatureState() {
		return this.temperatureState;
	}

	public void setTemperatureState(Integer temperatureState) {
		this.temperatureState = temperatureState;
	}

	public Date getAddTime() {
		return addTime;
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