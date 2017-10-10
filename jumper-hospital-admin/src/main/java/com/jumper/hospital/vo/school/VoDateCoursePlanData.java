package com.jumper.hospital.vo.school;
/**
 * 某个日期课程安排详情
 * @author rent
 * @date 2016-02-17
 */
import java.io.Serializable;

import com.jumper.hospital.enums.SchoolCourseState;

public class VoDateCoursePlanData implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 课程表安排记录ID **/
	private Integer id;
	/** 课程编号 **/
	private String courseNo;
	/** 课程名称 **/
	private String courseName;
	/** 授课医生 **/
	private String courseDoctor;
	/** 课程费用 **/
	private Double courseCost;
	/** 开始时间 **/
	private String startTime;
	/** 结束时间 **/
	private String endTime;
	/** 可预约总人数 **/
	private Integer appointCount;
	/** 课程ID **/
	private Integer courseId;
	/** 预约时间 **/
	private String appointTime;
	/** 课程签到二维码地址 **/
	private String courseQrUrl;
	/** 已预约人数 **/
	private Integer hasAppoint;;
	/** 已签到人数 **/
	private Integer hasSign;
	/** 课程表课程状态 **/
	private SchoolCourseState courseState;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
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
	public Double getCourseCost() {
		return courseCost;
	}
	public void setCourseCost(Double courseCost) {
		this.courseCost = courseCost;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getAppointCount() {
		return appointCount;
	}
	public void setAppointCount(Integer appointCount) {
		this.appointCount = appointCount;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getAppointTime() {
		return appointTime;
	}
	public void setAppointTime(String appointTime) {
		this.appointTime = appointTime;
	}
	public String getCourseQrUrl() {
		return courseQrUrl;
	}
	public void setCourseQrUrl(String courseQrUrl) {
		this.courseQrUrl = courseQrUrl;
	}
	public Integer getHasAppoint() {
		return hasAppoint;
	}
	public void setHasAppoint(Integer hasAppoint) {
		this.hasAppoint = hasAppoint;
	}
	public Integer getHasSign() {
		return hasSign;
	}
	public void setHasSign(Integer hasSign) {
		this.hasSign = hasSign;
	}
	public SchoolCourseState getCourseState() {
		return courseState;
	}
	public void setCourseState(SchoolCourseState courseState) {
		this.courseState = courseState;
	}
	
}
