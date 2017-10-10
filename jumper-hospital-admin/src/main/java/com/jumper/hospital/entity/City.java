package com.jumper.hospital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="city")
public class City extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String cityName;
	
	private String abbrev;
	
	private Province province;
	

	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	public City(int id,String cityName) {
		super.setId(id);
		this.cityName = cityName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="proid")
	public Province getProvince() {
		return this.province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	@Column(name="cityname")
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAbbrev() {
		return this.abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}
}