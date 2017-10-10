package com.jumper.hospital.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 心电监测数据实体类
 * @author admin
 * 2016-8-8
 */
@Entity
@Table(name="user_ecg_record")
public class UserEcgRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**用户信息ID*/
	private UserInfo userInfo;
	/**网关信息*/
	private String gatewayNo;
	/**ST段*/
	private String stisnormal;
	/**心律*/
	private String isarrhythmia;
	/**波形质量*/
	private String waveform;
	/**心率值*/
	private Double avrHeartrate;
	/**ST段平均电压*/
	private Double avrStvolt;
	/**心率 状态：心率正常，心率过慢，心率稍快，心率过快，导连脱落*/
	private String heartrate;
	/**测量时间（用户真是上传数据成功的时间）*/
	private Timestamp testTime;
	/**整体波形*/
	private String wholewave;
	/**波形图像base64*/
	private String image;
	/**添加时间(插入数据库时间)*/
	private Timestamp add_time;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	@Column(name="gateway_no")
	public String getGatewayNo() {
		return gatewayNo;
	}
	public void setGatewayNo(String gatewayNo) {
		this.gatewayNo = gatewayNo;
	}
	@Column(name="stisnormal")
	public String getStisnormal() {
		return stisnormal;
	}
	public void setStisnormal(String stisnormal) {
		this.stisnormal = stisnormal;
	}
	@Column(name="isarrhythmia")
	public String getIsarrhythmia() {
		return isarrhythmia;
	}
	public void setIsarrhythmia(String isarrhythmia) {
		this.isarrhythmia = isarrhythmia;
	}
	@Column(name="waveform")
	public String getWaveform() {
		return waveform;
	}
	public void setWaveform(String waveform) {
		this.waveform = waveform;
	}
	@Column(name="avr_heartrate")
	public Double getAvrHeartrate() {
		return avrHeartrate;
	}
	public void setAvrHeartrate(Double avrHeartrate) {
		this.avrHeartrate = avrHeartrate;
	}
	@Column(name="avr_stvolt")
	public Double getAvrStvolt() {
		return avrStvolt;
	}
	public void setAvrStvolt(Double avrStvolt) {
		this.avrStvolt = avrStvolt;
	}
	@Column(name="heartrate")
	public String getHeartrate() {
		return heartrate;
	}
	public void setHeartrate(String heartrate) {
		this.heartrate = heartrate;
	}
	@Column(name="test_time")
	public Timestamp getTestTime() {
		return testTime;
	}
	public void setTestTime(Timestamp testTime) {
		this.testTime = testTime;
	}
	@Column(name="wholewave")
	public String getWholewave() {
		return wholewave;
	}
	public void setWholewave(String wholewave) {
		this.wholewave = wholewave;
	}
	@Column(name="image")
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Column(name="add_time")
	public Timestamp getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Timestamp add_time) {
		this.add_time = add_time;
	}
	
}
