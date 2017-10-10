package com.jumper.hospital.vo.school;
/**
 * 线上课程导出excel表vo
 * @author rent
 * @date 2016-02-23
 */
public class VoOnlineSignData {

	/** 课程名称 **/
	private String courseName;
	/** 授课医师 **/
	private String courseDoctor;
	/** 签到用户名 **/
	private String signUserName;
	/** 孕周 **/
	private String userWeek;
	/** 签到用户电话 **/
	private String signUserPhone;
	/** 用户签到时间 **/
	private String userSignTime;
	
	public VoOnlineSignData() {
		super();
	}
	public VoOnlineSignData(String userSignTime) {
		this.userSignTime = userSignTime;
	}
	public String getSignUserName() {
		return signUserName;
	}
	public void setSignUserName(String signUserName) {
		this.signUserName = signUserName;
	}
	public String getSignUserPhone() {
		return signUserPhone;
	}
	public void setSignUserPhone(String signUserPhone) {
		this.signUserPhone = signUserPhone;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseDoctor() {
		return courseDoctor;
	}
	public void setCourseDoctor(String courseDoctor) {
		this.courseDoctor = courseDoctor;
	}
	public String getUserSignTime() {
		return userSignTime;
	}
	public void setUserSignTime(String userSignTime) {
		this.userSignTime = userSignTime;
	}
	public String getUserWeek() {
		return userWeek;
	}
	public void setUserWeek(String userWeek) {
		this.userWeek = userWeek;
	}
	
}
