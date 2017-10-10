package com.jumper.hospital.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="hospital_today_recommend")
public class HospitalTodayRecommend extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private HospitalInfo hospitalInfo;//医院名称
	
	private Province province;//省份
	
	private City city;//城市
	
	private Integer orderBy;//按照位置排序
	
	private HospitalService hospitalService;//医院服务
	
	private Date addTime;//添加时间

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="hospital_id")
	public HospitalInfo getHospitalInfo() {
		return hospitalInfo;
	}

	public void setHospitalInfo(HospitalInfo hospitalInfo) {
		this.hospitalInfo = hospitalInfo;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="province_id")
	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="city_id")
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	@Column(name="order_by")
	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="service_id")
	public HospitalService getHospitalService() {
		return hospitalService;
	}

	public void setHospitalService(HospitalService hospitalService) {
		this.hospitalService = hospitalService;
	}

	@Column(name="add_time")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	
	
}
