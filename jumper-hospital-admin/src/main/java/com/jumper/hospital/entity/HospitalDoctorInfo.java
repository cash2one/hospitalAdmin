package com.jumper.hospital.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - 医生信息
 */
@Entity
@Table(name = "hospital_doctor_info")
public class HospitalDoctorInfo extends BaseEntity {

	private static final long serialVersionUID = -7519486823153844426L;

	/** 医院ID*/
	private Integer hospitalId;

	/** 密码 */
	private String password;

	/** 简介 */
	private String introduction;

	/** 姓名 */
	private String name;
	
	/** 头像url*/
	private String imgUrl;
	
	/** 添加时间 **/
	private Timestamp addTime;

	/** hospital_doctor_fans id */
	private Integer fansNumber;

	/** 类型*/
	private Integer type;

	/**职能说明*/
	private String expert;

	/** 职称 */
	private Integer titleId;

	/** 手机号码 */
	private String phone;
	/** 科室id **/
	private Integer majorid;
	
	/** doctor_id  */
	private Integer doctorId;

	/**qr_code_url*/
	private String qrCodeUrl;
	
	/** certification_url **/
	private String certificationUrl;
	
	/** physician_pratice_license_url **/
	private String physiciaPraticeLicenseUrl;
	
	/** title **/
	private String title;
	
	/** achievement **/
	private String majorPhone;
	
	/** major_phone **/
	private String achievement;
	
	/** major_phone **/
	private String education;
	
	/** apply_date **/
	private Timestamp applyDate;
	
	
	/** pass_time**/
	private Timestamp passTime;
	
	/** apply_times  */
	private Integer applyTimes;
	
	/** status  */
	private Integer status;
	
	/** app端 当前医生是否开启孕期诊所权限*/
	private Boolean  isHospitalNst;
	
	
	@Column(name="hospital_id")
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="img_url")
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	@Column(name="add_time")
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	@Column(name="fans_number")
	public Integer getFansNumber() {
		return fansNumber;
	}
	public void setFansNumber(Integer fansNumber) {
		this.fansNumber = fansNumber;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getExpert() {
		return expert;
	}
	public void setExpert(String expert) {
		this.expert = expert;
	}
	@Column(name="title_id")
	public Integer getTitleId() {
		return titleId;
	}
	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name="major_id")
	public Integer getMajorid() {
		return majorid;
	}
	public void setMajorid(Integer majorid) {
		this.majorid = majorid;
	}
	@Column(name="doctor_id")
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	@Column(name="qr_code_url")
	public String getQrCodeUrl() {
		return qrCodeUrl;
	}
	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}
	@Column(name="certification_url")
	public String getCertificationUrl() {
		return certificationUrl;
	}
	public void setCertificationUrl(String certificationUrl) {
		this.certificationUrl = certificationUrl;
	}
	@Column(name="physician_pratice_license_url")
	public String getPhysiciaPraticeLicenseUrl() {
		return physiciaPraticeLicenseUrl;
	}
	public void setPhysiciaPraticeLicenseUrl(String physiciaPraticeLicenseUrl) {
		this.physiciaPraticeLicenseUrl = physiciaPraticeLicenseUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="major_phone")
	public String getMajorPhone() {
		return majorPhone;
	}
	public void setMajorPhone(String majorPhone) {
		this.majorPhone = majorPhone;
	}
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	@Column(name="apply_date")
	public Timestamp getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Timestamp applyDate) {
		this.applyDate = applyDate;
	}
	@Column(name="pass_time")
	public Timestamp getPassTime() {
		return passTime;
	}
	public void setPassTime(Timestamp passTime) {
		this.passTime = passTime;
	}
	@Column(name="apply_times")
	public Integer getApplyTimes() {
		return applyTimes;
	}
	public void setApplyTimes(Integer applyTimes) {
		this.applyTimes = applyTimes;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Boolean getIsHospitalNst() {
		return isHospitalNst;
	}
	public void setIsHospitalNst(Boolean isHospitalNst) {
		this.isHospitalNst = isHospitalNst;
	}
}