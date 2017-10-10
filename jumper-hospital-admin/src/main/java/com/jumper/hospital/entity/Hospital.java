package com.jumper.hospital.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
@Entity
public class Hospital extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private String name;

	private String introduction;

	private String imgUrl;

	private String address;

	private String orderKey;

	private Timestamp addTime;

	private Integer level;

	private String phone;


	// 是否有效
	private Integer isValid;

	// 排序方式:按照位置排序
	private Integer orderBy;

	// 是否开通远程监控
	private Integer isRemote;

	// 是否开通医院问诊
	private Integer isConsultant;
	/** 是否开通体重管理，true : 1,false : 0 **/
	private Boolean isWeight;
	/** 是否开通血糖管理， true : 1,false : 0 **/
	private Boolean isBlood;
	/** 是否开通线下课程，true : 1,false : 0 **/
	private Boolean isSchool;
	/** 是否开通课堂管理，true : 1,false : 0 **/
	private Boolean isClass;
	/** 是否开通孕妇学校视频课程 **/
	private Boolean isVideo;
	/** 是否开通线上支付 **/
	private Boolean isPayment;
	/** 是否开通网络诊室 **/
	private Boolean isNetwork;
	/** 是否屏蔽用户手机号码 **/
	// private Boolean isMobile;
	/** 是否是独立医院 0:不是 1:是独立医院 ； */
	private Integer isAutonomy;
	/** 是否开通设备租赁服务 **/
	private Boolean isLease;
	private String hospitalKey;// md5医院编码(8到17位)


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrderKey() {
		return orderKey;
	}

	public void setOrderKey(String orderKey) {
		this.orderKey = orderKey;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getIsRemote() {
		return isRemote;
	}

	public void setIsRemote(Integer isRemote) {
		this.isRemote = isRemote;
	}

	public Integer getIsConsultant() {
		return isConsultant;
	}

	public void setIsConsultant(Integer isConsultant) {
		this.isConsultant = isConsultant;
	}

	public Boolean getIsWeight() {
		return isWeight;
	}

	public void setIsWeight(Boolean isWeight) {
		this.isWeight = isWeight;
	}

	public Boolean getIsBlood() {
		return isBlood;
	}

	public void setIsBlood(Boolean isBlood) {
		this.isBlood = isBlood;
	}

	public Boolean getIsSchool() {
		return isSchool;
	}

	public void setIsSchool(Boolean isSchool) {
		this.isSchool = isSchool;
	}

	public Boolean getIsClass() {
		return isClass;
	}

	public void setIsClass(Boolean isClass) {
		this.isClass = isClass;
	}

	public Boolean getIsVideo() {
		return isVideo;
	}

	public void setIsVideo(Boolean isVideo) {
		this.isVideo = isVideo;
	}

	public Boolean getIsPayment() {
		return isPayment;
	}

	public void setIsPayment(Boolean isPayment) {
		this.isPayment = isPayment;
	}

	public Boolean getIsNetwork() {
		return isNetwork;
	}

	public void setIsNetwork(Boolean isNetwork) {
		this.isNetwork = isNetwork;
	}

	public Integer getIsAutonomy() {
		return isAutonomy;
	}

	public void setIsAutonomy(Integer isAutonomy) {
		this.isAutonomy = isAutonomy;
	}

	public Boolean getIsLease() {
		return isLease;
	}

	public void setIsLease(Boolean isLease) {
		this.isLease = isLease;
	}

	public String getHospitalKey() {
		return hospitalKey;
	}

	public void setHospitalKey(String hospitalKey) {
		this.hospitalKey = hospitalKey;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
