package com.jumper.hospital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="province")
public class Province extends BaseEntity {

	private static final long serialVersionUID = 5480042007714032836L;
	
	private String proName;
	
	private String remark;
	
	private String abbrevation;


	@Column(name="proname")
	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAbbrevation() {
		return this.abbrevation;
	}

	public void setAbbrevation(String abbrevation) {
		this.abbrevation = abbrevation;
	}

}