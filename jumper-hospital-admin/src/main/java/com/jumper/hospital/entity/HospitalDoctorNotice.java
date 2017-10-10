/**
 * 
 */
package com.jumper.hospital.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 医生短信通知名单
 *
 * 2017-7-13
 */
@Entity
@Table(name = "hospital_doctor_notice")
public class HospitalDoctorNotice extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3679418218999805261L;
	/**
	 * 医生姓名
	 */
	private String name;
	/**
	 * 医生职称
	 */
	private String titleId;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 状态：1.正在添加，2.已添加
	 */
	private Integer status;
	private Integer hospitalId;
	/**
	 * 用户名
	 */
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitleId() {
		return titleId;
	}
	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
