package com.jumper.hospital.vo;
/**
 * 远程监控8块屏幕用户信息Vo
 * @author rent
 * @date 2015-10-20
 */
import java.io.Serializable;

public class VoScreenUserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 屏幕编号 **/
	private Integer screenNumber;
	/** 用户ID **/
	private Integer userId;
	/** 用户名 **/
	private String userName;
	/** 用户年龄 **/
	private Integer age;
	/** 用户孕周 **/
	private Integer pregancyWeek;
	/** 监测开始时间 **/
	private String startTime;
	/** 监测持续时长 **/
	private String onlineTime;
	
	public Integer getScreenNumber() {
		return screenNumber;
	}
	public void setScreenNumber(Integer screenNumber) {
		this.screenNumber = screenNumber;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getPregancyWeek() {
		return pregancyWeek;
	}
	public void setPregancyWeek(Integer pregancyWeek) {
		this.pregancyWeek = pregancyWeek;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}
	
}
