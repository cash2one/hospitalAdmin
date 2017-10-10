package com.jumper.hospital.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;
/**
 * entity
 * @author wt
 */
@Entity
@Table(name="family_user_weight_record")
public class FamilyUserWeightRecord extends BaseEntity{
	@Transient
	private static final long serialVersionUID = 1L;
	
	@NotNull @Min(value=1)
	private double averageValue;	/** 体重测试平均值 **/
	@NotNull
	private Integer userId;// 用户id
	private Integer weightState;	/** 体重测试状态(0：偏轻，1：正常，2：偏重) **/
	private Date testTime;			/** 测试时间 **/
	private Date addTime;			/** 记录添加时间 **/
	private String flag;
    @Transient
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public double getAverageValue() {
		return averageValue;
	}
	public void setAverageValue(double averageValue) {
		this.averageValue = averageValue;
	}
	public Integer getWeightState() {
		return weightState;
	}
	public void setWeightState(Integer weightState) {
		this.weightState = weightState;
	}
	public Date getTestTime() {
		return testTime;
	}
	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}

