package com.jumper.hospital.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user_genetic_history")
public class UserGeneticHistory extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
}
