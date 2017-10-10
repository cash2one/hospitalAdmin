package com.jumper.hospital.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="hospital_notice")
public class HospitalNotice extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private HospitalInfo hospitalInfo;//医院id
	
	private Date addTime;//添加时间
	
	private String notice;//公告内容
	
	private Date endTime;//截止时间
	
	private int status;//发布状态
	
	private int isDelete;//删除状态

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="hospital_id")
	public HospitalInfo getHospitalInfo() {
		return hospitalInfo;
	}

	public void setHospitalInfo(HospitalInfo hospitalInfo) {
		this.hospitalInfo = hospitalInfo;
	}

	@Column(name="add_time")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	@Column(name="end_time")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name="is_delete")
	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
