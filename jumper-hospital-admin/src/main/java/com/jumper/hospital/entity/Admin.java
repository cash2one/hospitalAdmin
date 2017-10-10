package com.jumper.hospital.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity - 管理员
 */
@Entity
@Table(name = "monitor_admin")
public class Admin extends BaseEntity {

	private static final long serialVersionUID = -7519486823153844426L;

	/** 用户名 */
	private String username;

	/** 密码 */
	private String password;

	/** E-mail */
	private String email;

	/** 姓名 */
	private String name;

	/** 是否启用 */
	private Boolean isEnabled;

	/** 是否锁定 */
	private Boolean isLocked;

	/** 连续登录失败次数 */
	private Integer loginFailureCount;

	/** 锁定日期 */
	private Date lockedDate;

	/** 最后登录日期 */
	private Date loginDate;

	/** 最后登录IP */
	private String loginIp;
	/** 是否是管理用户 **/
	private Boolean isFather;
	/** 添加时间 **/
	private Timestamp addTime;

	/** 角色 **/
	private Set<Role> roles = new HashSet<Role>();

	private HospitalInfo hospitalInfo;


	// 随访账户信息
	// private VisitorAccount visitorAccount;

	/** 科室 关联表 */
	private HospitalMajorInfo hospitalMajorInfo;

	/** 科室id 增加的时候用到 */
	private Integer doctormajor_id;

	/** 科室 表 */
	private HospitalDoctorMajor hospitalDoctorMajor;

	/** 科室关联表id 增加的时候用到 */
	private Integer hospitalMajorid;

	/** 职称 增加的时候用到 */
	private Integer titleid;

	/** 职称 */
	private HospitalDoctorTitle hospitalDoctorTitle;

	/** 手机 */
	private String mobile;

	/** 医生简介 */
	private String introduction;
	
	/**当前医生是否开启孕期诊所权限*/
	private Boolean  isHospitalNst;

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Column(name = "doctormajor_id")
	public Integer getDoctormajor_id() {
		return doctormajor_id;
	}

	public void setDoctormajor_id(Integer doctormajor_id) {
		this.doctormajor_id = doctormajor_id;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "doctormajor_id", insertable = false, updatable = false)
	public HospitalDoctorMajor getHospitalDoctorMajor() {
		return hospitalDoctorMajor;
	}

	public void setHospitalDoctorMajor(HospitalDoctorMajor hospitalDoctorMajor) {
		this.hospitalDoctorMajor = hospitalDoctorMajor;
	}

	@Column(name = "major_id")
	public Integer getHospitalMajorid() {
		return hospitalMajorid;
	}

	public void setHospitalMajorid(Integer hospitalMajorid) {
		this.hospitalMajorid = hospitalMajorid;
	}

	@Column(name = "title_id")
	public Integer getTitleid() {
		return titleid;
	}

	public void setTitleid(Integer titleid) {
		this.titleid = titleid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "major_id", insertable = false, updatable = false)
	public HospitalMajorInfo getHospitalMajorInfo() {
		return hospitalMajorInfo;
	}

	public void setHospitalMajorInfo(HospitalMajorInfo hospitalMajorInfo) {
		this.hospitalMajorInfo = hospitalMajorInfo;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "title_id", insertable = false, updatable = false)
	public HospitalDoctorTitle getHospitalDoctorTitle() {
		return hospitalDoctorTitle;
	}

	public void setHospitalDoctorTitle(HospitalDoctorTitle hospitalDoctorTitle) {
		this.hospitalDoctorTitle = hospitalDoctorTitle;
	}

	// @OneToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name="visit_id")
	// public VisitorAccount getVisitorAccount() {
	// return visitorAccount;
	// }
	// /**
	// *
	// * @return
	// */
	// //@ManyToMany(fetch = FetchType.LAZY)
	// //@JoinTable(name = "monitor_hospital_visitor_account")
	// public void setVisitorAccount(VisitorAccount visitorAccount) {
	// this.visitorAccount = visitorAccount;
	// }

	/*
	 * @OneToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name="visit_id") public VisitorAccount getVisitorAccount() {
	 * return visitorAccount; }
	 */

	/**
	 * 
	 * @return
	 */
	// @ManyToMany(fetch = FetchType.LAZY)
	// @JoinTable(name = "monitor_hospital_visitor_account")
	/*
	 * public void setVisitorAccount(VisitorAccount visitorAccount) {
	 * this.visitorAccount = visitorAccount; }
	 */

	/**
	 * 获取用户名
	 * 
	 * @return 用户名
	 */
	@Column(nullable = false, updatable = false, unique = true, length = 100)
	public String getUsername() {
		return username;
	}

	/**
	 * 设置用户名
	 * 
	 * @param username
	 *            用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取密码
	 * 
	 * @return 密码
	 */
	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码
	 * 
	 * @param password
	 *            密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取E-mail
	 * 
	 * @return E-mail
	 */
	@Column(nullable = false)
	public String getEmail() {
		return email;
	}

	/**
	 * 设置E-mail
	 * 
	 * @param email
	 *            E-mail
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取姓名
	 * 
	 * @return 姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置姓名
	 * 
	 * @param name
	 *            姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取是否启用
	 * 
	 * @return 是否启用
	 */
	@Column(nullable = false)
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	/**
	 * 设置是否启用
	 * 
	 * @param isEnabled
	 *            是否启用
	 */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * 获取是否锁定
	 * 
	 * @return 是否锁定
	 */
	@Column(nullable = false)
	public Boolean getIsLocked() {
		return isLocked;
	}

	/**
	 * 设置是否锁定
	 * 
	 * @param isLocked
	 *            是否锁定
	 */
	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	/**
	 * 获取连续登录失败次数
	 * 
	 * @return 连续登录失败次数
	 */
	@Column(nullable = false)
	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	/**
	 * 设置连续登录失败次数
	 * 
	 * @param loginFailureCount
	 *            连续登录失败次数
	 */
	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	/**
	 * 获取锁定日期
	 * 
	 * @return 锁定日期
	 */
	public Date getLockedDate() {
		return lockedDate;
	}

	/**
	 * 设置锁定日期
	 * 
	 * @param lockedDate
	 *            锁定日期
	 */
	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	/**
	 * 获取最后登录日期
	 * 
	 * @return 最后登录日期
	 */
	public Date getLoginDate() {
		return loginDate;
	}

	/**
	 * 设置最后登录日期
	 * 
	 * @param loginDate
	 *            最后登录日期
	 */
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	/**
	 * 获取最后登录IP
	 * 
	 * @return 最后登录IP
	 */
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * 设置最后登录IP
	 * 
	 * @param loginIp
	 *            最后登录IP
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	/**
	 * 获取角色
	 * 
	 * @return 角色
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "monitor_admin_role")
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * 设置角色
	 * 
	 * @param roles
	 *            角色
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "hospital_id", nullable = false)
	public HospitalInfo getHospitalInfo() {
		return hospitalInfo;
	}

	public void setHospitalInfo(HospitalInfo hospitalInfo) {
		this.hospitalInfo = hospitalInfo;
	}

	public Boolean getIsFather() {
		return isFather;
	}

	public void setIsFather(Boolean isFather) {
		this.isFather = isFather;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Boolean getIsHospitalNst() {
		return isHospitalNst;
	}

	public void setIsHospitalNst(Boolean isHospitalNst) {
		this.isHospitalNst = isHospitalNst;
	}

	@Override
	public String toString() {
		return "Admin [username=" + username + ", password=" + password
				+ ", email=" + email + ", name=" + name + ", isEnabled="
				+ isEnabled + ", isLocked=" + isLocked + ", loginFailureCount="
				+ loginFailureCount + ", lockedDate=" + lockedDate
				+ ", loginDate=" + loginDate + ", loginIp=" + loginIp
				+ ", isFather=" + isFather + ", addTime=" + addTime
				+ ", roles=" + roles + ", hospitalInfo=" + hospitalInfo
				+ ", hospitalMajorInfo=" + hospitalMajorInfo
				+ ", doctormajor_id=" + doctormajor_id
				+ ", hospitalDoctorMajor=" + hospitalDoctorMajor
				+ ", hospitalMajorid=" + hospitalMajorid + ", titleid="
				+ titleid + ", hospitalDoctorTitle=" + hospitalDoctorTitle
				+ ", mobile=" + mobile + ", introduction=" + introduction + "]";
	}

}