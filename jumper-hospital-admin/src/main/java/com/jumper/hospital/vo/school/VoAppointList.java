package com.jumper.hospital.vo.school;
import com.jumper.hospital.enums.SchoolAppointState;
import com.jumper.hospital.enums.SchoolSignState;

public class VoAppointList {
	
	/** 课程名称 **/
	private String appointCourseName;
	/** 授课医师 **/
	private String appointCourseDoctor;
	/** 学员姓名 **/
	private String appointUserName;
	/** 孕周 **/
	private String userWeek;
	/** 预约用户手机号码 **/
	private String appointUserPhone;
	/** 用户预约时间 **/
	private String userAppointTime;
	/** 预约状态:0,预约成功，1,已取消，2,已完成，3,已过期 **/
	private SchoolAppointState appointState;
	/** 用户签到时间 **/
	private String userSignTime;
	/** 签到状态：0，未签到，1，已签到 **/
	private SchoolSignState signState;
	/** 缴费状态：0，未缴费，1，已缴费 ，2，已退费**/
	private String payState;
	
	public VoAppointList(String payState) {
		this.payState = payState;
	}
	public VoAppointList() {
		
	}
	
	public String getAppointUserName() {
		return appointUserName;
	}
	public void setAppointUserName(String appointUserName) {
		this.appointUserName = appointUserName;
	}
	public String getUserWeek() {
		return userWeek;
	}
	public void setUserWeek(String userWeek) {
		this.userWeek = userWeek;
	}
	public String getAppointUserPhone() {
		return appointUserPhone;
	}
	public void setAppointUserPhone(String appointUserPhone) {
		this.appointUserPhone = appointUserPhone;
	}
	public String getUserAppointTime() {
		return userAppointTime;
	}
	public void setUserAppointTime(String userAppointTime) {
		this.userAppointTime = userAppointTime;
	}
	public SchoolAppointState getAppointState() {
		return appointState;
	}
	public void setAppointState(SchoolAppointState appointState) {
		this.appointState = appointState;
	}
	public String getUserSignTime() {
		return userSignTime;
	}
	public void setUserSignTime(String userSignTime) {
		this.userSignTime = userSignTime;
	}
	public SchoolSignState getSignState() {
		return signState;
	}
	public void setSignState(SchoolSignState signState) {
		this.signState = signState;
	}
	public String getPayState() {
		return payState;
	}
	public void setPayState(String payState) {
		this.payState = payState;
	}
	public String getAppointCourseName() {
		return appointCourseName;
	}
	public void setAppointCourseName(String appointCourseName) {
		this.appointCourseName = appointCourseName;
	}
	public String getAppointCourseDoctor() {
		return appointCourseDoctor;
	}
	public void setAppointCourseDoctor(String appointCourseDoctor) {
		this.appointCourseDoctor = appointCourseDoctor;
	}
	
}
