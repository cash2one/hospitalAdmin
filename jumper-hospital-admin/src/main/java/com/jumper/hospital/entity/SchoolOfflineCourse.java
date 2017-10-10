package com.jumper.hospital.entity;
/**
 * 线下课程
 * @author rent
 * @date 2016-01-25
 */
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;

@Entity
public class SchoolOfflineCourse extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 课程编号 **/
	private String courseNo;
	/** 网络课程图片 **/
	private String courseImage;
	/** 课程名称 **/
	private String courseName;
	/** 授课医师 **/
	private String courseDoctor;
	/** 授课医师职称 **/
	private String courseDoctorTitle;
	/** 预约总人数 **/
	private Integer appointCount;
	/** 课程费用 **/
	private Double courseCost;
	/** 课程上课地址 **/
	private String courseAddress;
	/** 课程简介 **/
	private String courseDesc;
	/** 注意事项 **/
	private String courseNotice;
	/** 医院ID **/
	private Integer hospitalId;
	/** 添加时间 **/
	private Timestamp addTime;
	
	
	public Double getCourseCost() {
		return courseCost;
	}
	public void setCourseCost(Double courseCost) {
		this.courseCost = courseCost;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public String getCourseImage() {
		return courseImage;
	}
	public void setCourseImage(String courseImage) {
		this.courseImage = courseImage;
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
	public String getCourseDoctorTitle() {
		return courseDoctorTitle;
	}
	public void setCourseDoctorTitle(String courseDoctorTitle) {
		this.courseDoctorTitle = courseDoctorTitle;
	}
	public Integer getAppointCount() {
		return appointCount;
	}
	public void setAppointCount(Integer appointCount) {
		this.appointCount = appointCount;
	}
	public String getCourseAddress() {
		return courseAddress;
	}
	public void setCourseAddress(String courseAddress) {
		this.courseAddress = courseAddress;
	}
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	public String getCourseNotice() {
		return courseNotice;
	}
	public void setCourseNotice(String courseNotice) {
		this.courseNotice = courseNotice;
	}
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
}
