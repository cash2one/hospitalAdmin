package com.jumper.hospital.entity;
/**
 * 医院提现表
 * @author fxl
 * @date 
 */
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Hospital_withdrawals")
public class HospitalWithdrawals {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 医生信息 **/
	private Integer hospitalId;
	/** 退款年份 **/
	private Integer year;
	/** 退款月份 **/
	private Integer month;
	/** 提现金额 **/
	private Double withdrawalsMoney;
	/** 提现状态(0：未完成，1：已完成) **/
	private Integer state;
	/** 转账操作者 **/
	private String operator;
	/** 添加时间 **/
	private Timestamp addTime;
	/** 点击结算的时间 **/
	private Timestamp settleAccountTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Double getWithdrawalsMoney() {
		return withdrawalsMoney;
	}
	public void setWithdrawalsMoney(Double withdrawalsMoney) {
		this.withdrawalsMoney = withdrawalsMoney;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public Timestamp getSettleAccountTime() {
		return settleAccountTime;
	}
	public void setSettleAccountTime(Timestamp settleAccountTime) {
		this.settleAccountTime = settleAccountTime;
	}

	
	
	
	
}
