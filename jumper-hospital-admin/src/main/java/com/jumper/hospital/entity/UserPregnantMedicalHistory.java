package com.jumper.hospital.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity     
@Table(name="user_pregnant_medical_history")
public class UserPregnantMedicalHistory extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private UserInfo userInfo;
	
	private Integer type;		//类型 病史类型：	0：遗传病史	1：既往病史	2：孕产史
	
	private Integer objectId;
	
	private Timestamp addTime;

	@JoinColumn(name="user_id")
	@OneToOne(fetch = FetchType.LAZY)
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name="object_id")
	public Integer getObjectId() {
		return objectId;
	}
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	@Column(name="add_time")
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
}
