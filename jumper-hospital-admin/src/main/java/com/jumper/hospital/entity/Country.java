package com.jumper.hospital.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="country")
public class Country extends BaseEntity {

	private static final long serialVersionUID = -5816210183337841389L;
	
	private String abbrevation;
	
	private String country;
	
	private String remark;

    public Country() {
    }

    public Country(String abbrevation, String country, String remark) {
        this.abbrevation = abbrevation;
        this.country = country;
        this.remark = remark;
    }

    public String getAbbrevation() {
        return this.abbrevation;
    }
    
    public void setAbbrevation(String abbrevation) {
        this.abbrevation = abbrevation;
    }

    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

}