package com.jumper.hospital.vo;

import java.io.Serializable;

/**
 * 基础信息返回VO类，用于返回放入Select控件的值
 * @author rent
 *
 */
public class VOBaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** key **/
	private Integer id;
	/** value **/
	private String name;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
