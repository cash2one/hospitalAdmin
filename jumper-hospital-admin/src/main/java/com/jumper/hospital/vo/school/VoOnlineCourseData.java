package com.jumper.hospital.vo.school;
import com.jumper.hospital.enums.SchoolOnlineState;

public class VoOnlineCourseData {

	/** 主键Id **/
	private Integer id;
	/** 课程编号 **/
	private String courseNo;
	/** 课程名称 **/
	private String courseName;
	/** 授课医师 **/
	private String courseDoctor;
	/** 授课医师职称 **/
	private String courseDoctorTitle;
	/** 课程状态 **/
	private SchoolOnlineState courseState;
	/** 签到总数 **/
	private Integer signCount;
	/** 分享总数 **/
	private Integer shareCount;
	
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
	public String getCourseDoctorTitle() {
		return courseDoctorTitle;
	}
	public void setCourseDoctorTitle(String courseDoctorTitle) {
		this.courseDoctorTitle = courseDoctorTitle;
	}
	public SchoolOnlineState getCourseState() {
		return courseState;
	}
	public void setCourseState(SchoolOnlineState courseState) {
		this.courseState = courseState;
	}
	public Integer getSignCount() {
		return signCount;
	}
	public void setSignCount(Integer signCount) {
		this.signCount = signCount;
	}
	public Integer getShareCount() {
		return shareCount;
	}
	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}
	
}
