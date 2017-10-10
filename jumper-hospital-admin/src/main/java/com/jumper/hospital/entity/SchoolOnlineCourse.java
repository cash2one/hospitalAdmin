package com.jumper.hospital.entity;
/**
 * 线上课程
 * @author rent
 * @date 2016-01-25
 */
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;

import com.jumper.hospital.enums.SchoolOnlineState;

@Entity
public class SchoolOnlineCourse extends BaseEntity implements Serializable {

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
	/** 课程地址(第三方视频) **/
	private String courseUrl;
	/** 课程视频类型 */
	private Integer courseType;
	/** 课程描述 **/
	private String courseDesc;
	/** 医院ID **/
	private Integer hospitalId;
	/** 课程状态 **/
	private SchoolOnlineState courseState;
	/** 课程分享次数 **/
	private Integer shareCount;
	/** 添加时间 **/
	private Timestamp addTime;
	/** 是否发布 **/
	private Boolean isPublish;
	
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
	public String getCourseUrl() {
		return courseUrl;
	}
	public void setCourseUrl(String courseUrl) {
		this.courseUrl = courseUrl;
	}
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	public SchoolOnlineState getCourseState() {
		return courseState;
	}
	public void setCourseState(SchoolOnlineState courseState) {
		this.courseState = courseState;
	}
	public Integer getShareCount() {
		return shareCount;
	}
	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public Boolean getIsPublish() {
		return isPublish;
	}
	public void setIsPublish(Boolean isPublish) {
		this.isPublish = isPublish;
	}
	public Integer getCourseType() {
		return courseType;
	}
	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}
	
}
