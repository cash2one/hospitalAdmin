package com.jumper.hospital.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 验证码存放表
 * @author rent
 * @date 2015-09-18
 */
@Entity
@Table(name="user_verified_code")
public class UserVerifiedCode extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 手机号码 **/
	private String mobile;
	/** 验证码 **/
	private String code;
	/** 验证码发送时间 **/
	private Timestamp addTime;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
}
