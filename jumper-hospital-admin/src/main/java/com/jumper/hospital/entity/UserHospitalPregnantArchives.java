package com.jumper.hospital.entity;
/**
 * 医院孕期档案Bean
 */
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_hospital_pregnant_archives")
public class UserHospitalPregnantArchives extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 档案所属用户 **/
	private UserInfo userInfo;
	/** 档案所属医院 **/
	private HospitalInfo hospitalInfo;
	/** 是否已提交(0：未提交，1：已提交) 已提交不允许再次修改 **/
	private Integer isCommit;
	/** 记录添加时间 **/
	private Timestamp addTime;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="hospital_id")
	public HospitalInfo getHospitalInfo() {
		return hospitalInfo;
	}
	public void setHospitalInfo(HospitalInfo hospitalInfo) {
		this.hospitalInfo = hospitalInfo;
	}
	@Column(name="is_commit")
	public Integer getIsCommit() {
		return isCommit;
	}
	public void setIsCommit(Integer isCommit) {
		this.isCommit = isCommit;
	}
	@Column(name="add_time")
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
}
