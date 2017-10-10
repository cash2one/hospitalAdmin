package com.jumper.hospital.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "news_message")
public class NewsMessage extends BaseEntity {

	/**
	 * 消息实体类
	 */
	private static final long serialVersionUID = 1L;
	/**  管理员ID*/
	private Admin adminId;
	/** 医院ID*/
	private HospitalInfo hospitalInfo;
	/** 标题 */
	private String title;
	/** 消息类型 0:系统消息；1:医院消息' */
	private int type;
	/** 内容 */
	private String content;
	/** 推送时间 */
	private Timestamp pushTime;
	/** 修改人 */
	private String modifyEmp;
	/** 添加人 */
	private String addEmp;
	/** 修改时间 */
	private Timestamp modifyTime;
	/** 添加时间 */
	private Timestamp addTime;
	/** 是否删除 */
	private int isDelete;
	/** 孕周 */
	private String pregnantWeek;
	
	@Column(name="pregnant_week")
	public String getPregnantWeek() {
		return pregnantWeek;
	}

	public void setPregnantWeek(String pregnantWeek) {
		this.pregnantWeek = pregnantWeek;
	}

	@Column(name="is_delete")
	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="hospital_id")
	public HospitalInfo getHospitalInfo() {
		return hospitalInfo;
	}

	public void setHospitalInfo(HospitalInfo hospitalInfo) {
		this.hospitalInfo = hospitalInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="admin_id")
	public Admin getAdminId() {
		return adminId;
	}


	public void setAdminId(Admin adminId) {
		this.adminId = adminId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="push_time")
	public Timestamp getPushTime() {
		return pushTime;
	}

	public void setPushTime(Timestamp pushTime) {
		this.pushTime = pushTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public String getModifyEmp() {
		return modifyEmp;
	}

	public void setModifyEmp(String modifyEmp) {
		this.modifyEmp = modifyEmp;
	}

	@Column(name="add_emp")
	public String getAddEmp() {
		return addEmp;
	}

	public void setAddEmp(String addEmp) {
		this.addEmp = addEmp;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name="add_time")
	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
}
