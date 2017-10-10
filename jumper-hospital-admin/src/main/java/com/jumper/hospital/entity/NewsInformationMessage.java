package com.jumper.hospital.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="news_information_message")
public class NewsInformationMessage extends BaseEntity {
     
	/**
	 * 资讯实体类
	 */
	private static final long serialVersionUID = 1L;
	/** 管理员ID*/
	private Admin adminId;
	/**文章标题*/
	private String title;
	/** 推送时间*/
	private Timestamp pushTime;
	/** 内容*/
	private String content;
	/** 医院ID*/
	private HospitalInfo hospitalInfo;
	/** 资讯描述*/
	private String description;
	/** 图片路径*/
	private String imageUrl;
	/** 修改人 */
	private String modifyEmp;
	/** 添加人*/
	private String addEmp;
	/** 修改时间*/
	private Timestamp modifyTime;
	/** 添加时间*/
	private Timestamp addTime;
	/** 是否删除，0：未删除，1：已删除*/
	private Integer isDelete;
	/** 是否发布，0：未发布，1：已发布*/
	private Integer pushStatus;
	/** 是否推送至Banner，0：未推送，1：已推送*/
	private Integer isBanner;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="admin_id")
	public Admin getAdminId() {
		return adminId;
	}
	public void setAdminId(Admin adminId) {
		this.adminId = adminId;
	}
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="hospital_id")
	public HospitalInfo getHospitalInfo() {
		return hospitalInfo;
	}
	public void setHospitalInfo(HospitalInfo hospitalInfo) {
		this.hospitalInfo = hospitalInfo;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getModifyEmp() {
		return modifyEmp;
	}
	public void setModifyEmp(String modifyEmp) {
		this.modifyEmp = modifyEmp;
	}
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
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getPushStatus() {
		return pushStatus;
	}
	public void setPushStatus(Integer pushStatus) {
		this.pushStatus = pushStatus;
	}
	public Integer getIsBanner() {
		return isBanner;
	}
	public void setIsBanner(Integer isBanner) {
		this.isBanner = isBanner;
	}

	
}
