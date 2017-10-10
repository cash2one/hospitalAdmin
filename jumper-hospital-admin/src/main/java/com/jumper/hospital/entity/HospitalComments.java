package com.jumper.hospital.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="hospital_comments")
public class HospitalComments extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	//用户id
	private UserInfo userInfo;
	
	//医院id
	private HospitalInfo hospitalInfo;
	
	//评价内容
	private String content;
	
	//满意度(1：解答专业，2:有点帮助，3：帮助不大)
	private Integer statisfaction;
	
	//问题id
	private Integer consId;
	
	//添加时间
	private Date addTime;
	
	//（预留）类型
	private Integer type;

	@ManyToOne
	@JoinColumn(name="user_id")
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@ManyToOne
	@JoinColumn(name="hospital_id")
	public HospitalInfo getHospitalInfo() {
		return hospitalInfo;
	}

	public void setHospitalInfo(HospitalInfo hospitalInfo) {
		this.hospitalInfo = hospitalInfo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatisfaction() {
		return statisfaction;
	}

	public void setStatisfaction(Integer statisfaction) {
		this.statisfaction = statisfaction;
	}

	@Column(name="cons_id")
	public Integer getConsId() {
		return consId;
	}

	public void setConsId(Integer consId) {
		this.consId = consId;
	}

	@Column(name="add_time")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
