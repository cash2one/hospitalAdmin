package com.jumper.hospital.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user_nations")
public class UserNations extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    private String abbrev;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbrev() {
		return abbrev;
	}
	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}
    
}
