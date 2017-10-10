package com.jumper.hospital.vo.school;

import java.io.Serializable;

public class VoDetailCountResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String date;
	
	private Integer count;

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	

}
