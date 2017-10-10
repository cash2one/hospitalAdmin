package com.jumper.hospital.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 用户entity
 * @author rent
 */
@Entity
@Table(name="user_info")
public class UserInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Country country;
	
	private City city;
	
	private Province province;
	
	private String mobile;
	
	private String email;
	
	private String nickName;
	
	private String userImg;
	
	private Date expectedDateOfConfinement;
	
	private Integer status;
	
	private Timestamp regTime;
	
	private String password;

	private Integer isSwitchPushMsg;
	
	private String openId;
	
	private Integer userType;
	
	private UserExtraInfo userExitInfo;
	/** 用户金币 **/
	private Integer gold;
	/** 孕期/宝宝大小阶段 **/
	private Integer pregnantStage;
	/** 孕期周 **/
	private Integer pregnantWeek;

	@JoinColumn(name="city")
	@OneToOne(fetch = FetchType.EAGER)
	public City getCity() {
		return this.city;
	}

	@JoinColumn(name="country")
	@OneToOne(fetch = FetchType.EAGER)
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@JoinColumn(name="province")
	@OneToOne(fetch = FetchType.EAGER)
	public Province getProvince() {
		return this.province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="nick_name")
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name="user_img")
	public String getUserImg() {
		return this.userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	@Column(name="expected_date_of_confinement")
	public Date getExpectedDateOfConfinement() {
		return this.expectedDateOfConfinement;
	}

	public void setExpectedDateOfConfinement(Date expectedDateOfConfinement) {
		this.expectedDateOfConfinement = expectedDateOfConfinement;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="reg_time")
	public Timestamp getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="is_switch_push_msg")
	public Integer getIsSwitchPushMsg() {
		return this.isSwitchPushMsg;
	}

	public void setIsSwitchPushMsg(Integer isSwitchPushMsg) {
		this.isSwitchPushMsg = isSwitchPushMsg;
	}

	@Column(name="open_id")
	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name="user_type")
	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@OneToOne(mappedBy = "userInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public UserExtraInfo getUserExitInfo() {
		return userExitInfo;
	}

	public void setUserExitInfo(UserExtraInfo userExitInfo) {
		this.userExitInfo = userExitInfo;
	}

	@Column(nullable = true, columnDefinition = "INT default 0")
	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	@Column(columnDefinition = "INT default 1")
	public Integer getPregnantStage() {
		return pregnantStage;
	}

	public void setPregnantStage(Integer pregnantStage) {
		this.pregnantStage = pregnantStage;
	}

	@Column(columnDefinition = "INT default 1")
	public Integer getPregnantWeek() {
		return pregnantWeek;
	}

	public void setPregnantWeek(Integer pregnantWeek) {
		this.pregnantWeek = pregnantWeek;
	}

}