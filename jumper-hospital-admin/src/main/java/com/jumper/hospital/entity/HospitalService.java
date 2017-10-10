package com.jumper.hospital.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 医院服务
 * @author gyx
 */
@Entity
@Table(name="hospital_service")
public class HospitalService extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	//关联医院：医院id
	private HospitalInfo hospitalInfo;
	//关联院方用户：修改人
	private Admin admin;
	//问诊费用
	private double servicecost;
	//服务状态：是否关闭：0关闭，1未关闭
	private Integer status;
	//开始星期
	private Integer weeklow;
	//结束星期
	private Integer weekhigh;
	//服务开始时间
	private Date beginTime;
	//服务结束时间
	private Date endTime;
	//服务类型：预留字段
	private Integer serviceType;
	//修改时间
	private Date updateTime;
	//添加时间
	private Date addTime;
	
	//服务对应医院，多对一
	@JoinColumn(name="hospital_id")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public HospitalInfo getHospitalInfo() {
		return hospitalInfo;
	}
	public void setHospitalInfo(HospitalInfo hospitalInfo) {
		this.hospitalInfo = hospitalInfo;
	}
	@JoinColumn(name="custody_id")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	@Column(name="service_cost")
	public double getServicecost() {
		return servicecost;
	}
	
	public void setServicecost(double servicecost) {
		this.servicecost = servicecost;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name="week_low")
	public Integer getWeeklow() {
		return weeklow;
	}
	public void setWeeklow(Integer weeklow) {
		this.weeklow = weeklow;
	}
	@Column(name="week_high")
	public Integer getWeekhigh() {
		return weekhigh;
	}
	public void setWeekhigh(Integer weekhigh) {
		this.weekhigh = weekhigh;
	}
	@Column(name="begin_time")
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	@Column(name="end_time")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name="service_type")
	public Integer getServiceType() {
		return serviceType;
	}
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	@Column(name="update_time")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name="add_time")
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	
	
	
}
