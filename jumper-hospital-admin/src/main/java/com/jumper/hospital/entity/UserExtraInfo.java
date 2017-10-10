package com.jumper.hospital.entity;
/**
 * 用户扩展信息entity
 * @author rent
 * @date 2015-06-10
 */
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_extra_info")
public class UserExtraInfo extends BaseEntity {

	private static final long serialVersionUID = 3419022790403411444L;
	
	/** 用户信息 **/
	private UserInfo userInfo;
	
	/** 登陆IP **/
	private String loginIp;
	
	/** 登陆时间 **/
	private Timestamp loginTime;
	
	/** 手机类型 0:android 1:ios **/
	private Integer mobileType;
	
	/** 身份证号 **/
	private String identification;
	
	/** 用户年龄 **/
	private Integer age;
	
	/** 真实姓名 **/
	private String realName;
	
	/** 联系电话 **/
	private String contactPhone;

	/** 用户身高 **/
	private String height;
	
	/** 用户体重 **/
	private Integer weight;
	
	/** 当前身份0:已怀孕，1：辣妈 **/
	private Byte currentIdentity;
	
	/** 宝宝生日 **/
	private Date babyBirthday;
	
	/** 宝宝性别 **/
	private Byte babySex;
	
	/** 末次月经时间 **/
	private Date lastPeriod;
	
	/** 经期天数 **/
	private Integer periodDay;
	
	/** 月经周期 **/
	private Integer periodCycle;
	
	/** 常用医院 **/
	private Integer commonHospital;

	/** 是否国内用户 **/
 	private Boolean isChinaUser;
 	/** 是否参加体重管理0：未参加  1：已参加 **/
 	private Integer checkStatus;
	
// 	private Double BMI;
 	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Timestamp getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public Integer getMobileType() {
		return this.mobileType;
	}

	public void setMobileType(Integer mobileType) {
		this.mobileType = mobileType;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	@Column(length = 20)
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Byte getCurrentIdentity() {
		return currentIdentity;
	}

	public void setCurrentIdentity(Byte currentIdentity) {
		this.currentIdentity = currentIdentity;
	}

	public Date getBabyBirthday() {
		return babyBirthday;
	}

	public void setBabyBirthday(Date babyBirthday) {
		this.babyBirthday = babyBirthday;
	}

	public Byte getBabySex() {
		return babySex;
	}

	public void setBabySex(Byte babySex) {
		this.babySex = babySex;
	}

	public Date getLastPeriod() {
		return lastPeriod;
	}

	public void setLastPeriod(Date lastPeriod) {
		this.lastPeriod = lastPeriod;
	}

	public Integer getPeriodDay() {
		return periodDay;
	}

	public void setPeriodDay(Integer periodDay) {
		this.periodDay = periodDay;
	}

	public Integer getPeriodCycle() {
		return periodCycle;
	}

	public void setPeriodCycle(Integer periodCycle) {
		this.periodCycle = periodCycle;
	}

	public Integer getCommonHospital() {
		return commonHospital;
	}

	public void setCommonHospital(Integer commonHospital) {
		this.commonHospital = commonHospital;
	}

	public Boolean getIsChinaUser() {
		return isChinaUser;
	}

	public void setIsChinaUser(Boolean isChinaUser) {
		this.isChinaUser = isChinaUser;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

//	public Double getBMI() {
//		return BMI;
//	}
//
//	public void setBMI(Double bMI) {
//		BMI = bMI;
//	}
	

}