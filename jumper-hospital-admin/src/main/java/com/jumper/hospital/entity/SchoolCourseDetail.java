package com.jumper.hospital.entity;
/**
 * 课程表安排
 * @author rent
 * @date 2016-01-25
 */
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;

import com.jumper.hospital.enums.SchoolCourseState;

@Entity
public class SchoolCourseDetail extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 课程编号 **/
	private String courseNo;
	/** 课程名称 **/
	private String courseName;
	/** 授课医生 **/
	private String courseDoctor;
	/** 课程时间(格式:年-月-日) **/
	private Date courseDate;
	/** 开始时间 **/
	private String startTime;
	/** 结束时间 **/
	private String endTime;
	/** 课程费用 **/
	private Double courseCost;
	/** 课程签到二维码地址 **/
	private String courseQrUrl;
	/** 课程可预约总人数 **/
	private Integer appointCount;
	/** 课程库ID **/
	private Integer courseId;
	/** 医院ID **/
	private Integer hospitalId;
	/** 课程表课程状态 **/
	private SchoolCourseState courseState;
	/** 课程表课程安排时间 **/
	private Timestamp addTime;
	
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
	public Date getCourseDate() {
		return courseDate;
	}
	public void setCourseDate(Date courseDate) {
		this.courseDate = courseDate;
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
	public Double getCourseCost() {
		return courseCost;
	}
	public void setCourseCost(Double courseCost) {
		this.courseCost = courseCost;
	}
	public String getCourseQrUrl() {
		return courseQrUrl;
	}
	public void setCourseQrUrl(String courseQrUrl) {
		this.courseQrUrl = courseQrUrl;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getAppointCount() {
		return appointCount;
	}
	public void setAppointCount(Integer appointCount) {
		this.appointCount = appointCount;
	}
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	public SchoolCourseState getCourseState() {
		return courseState;
	}
	public void setCourseState(SchoolCourseState courseState) {
		this.courseState = courseState;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
}
