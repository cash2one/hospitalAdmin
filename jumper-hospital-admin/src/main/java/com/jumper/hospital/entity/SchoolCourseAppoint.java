package com.jumper.hospital.entity;
/**
 * 课程预约签到
 * @author rent
 * @date 2016-01-25
 */
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jumper.hospital.enums.SchoolAppointState;
import com.jumper.hospital.enums.SchoolPayState;
import com.jumper.hospital.enums.SchoolSignState;

@Entity
@Table(name="school_course_appoint")
public class SchoolCourseAppoint extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 预约课程订单ID(生成规则：DT+年月日+id) **/
	private String appointOrderId;
	/** 预约人姓名 **/
	private String appointUserName;
	/** 预约用户手机号码 **/
	private String appointUserPhone;
	/** 预约用户ID **/
	private Integer appointUserId;
	/** 预约课程名称 **/
	private String appointCourseName;
	/** 预约课程授课医生姓名 **/
	private String appointCourseDoctor;
	/** 所对应的课程表课程安排ID **/
	private Integer courseDetailId;
	/** 预约时间，这里为方便后面查询，已经改为了课程表的安排时间，可以很方便统计当天预约人数，特此说明 **/
	private Timestamp appointTime;
	/** 预约状态:0,预约成功，1,已取消，2,已完成，3,已过期 **/
	private SchoolAppointState appointState;
	/** 签到时间 **/
	private Timestamp signTime;
	/** 签到状态：0，未签到，1，已签到 **/
	private SchoolSignState signState;
	/** 缴费状态：0，未缴费，1，已缴费 ，2，已退费**/
	private SchoolPayState payState;
	/** 医院ID **/
	private Integer hospitalId;
	/** 添加时间 **/
	private Timestamp addTime;
	/** 用户孕周 **/
	private String userWeek;
	
	public String getAppointOrderId() {
		return appointOrderId;
	}
	public void setAppointOrderId(String appointOrderId) {
		this.appointOrderId = appointOrderId;
	}
	public String getAppointUserName() {
		return appointUserName;
	}
	public void setAppointUserName(String appointUserName) {
		this.appointUserName = appointUserName;
	}
	public String getAppointUserPhone() {
		return appointUserPhone;
	}
	public void setAppointUserPhone(String appointUserPhone) {
		this.appointUserPhone = appointUserPhone;
	}
	public Integer getAppointUserId() {
		return appointUserId;
	}
	public void setAppointUserId(Integer appointUserId) {
		this.appointUserId = appointUserId;
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
	public Integer getCourseDetailId() {
		return courseDetailId;
	}
	public void setCourseDetailId(Integer courseDetailId) {
		this.courseDetailId = courseDetailId;
	}
	public Timestamp getAppointTime() {
		return appointTime;
	}
	public void setAppointTime(Timestamp appointTime) {
		this.appointTime = appointTime;
	}
	public SchoolAppointState getAppointState() {
		return appointState;
	}
	public void setAppointState(SchoolAppointState appointState) {
		this.appointState = appointState;
	}
	public Timestamp getSignTime() {
		return signTime;
	}
	public void setSignTime(Timestamp signTime) {
		this.signTime = signTime;
	}
	public SchoolSignState getSignState() {
		return signState;
	}
	public void setSignState(SchoolSignState signState) {
		this.signState = signState;
	}
	public SchoolPayState getPayState() {
		return payState;
	}
	public void setPayState(SchoolPayState payState) {
		this.payState = payState;
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
	@Transient
	public String getUserWeek() {
		return userWeek;
	}
	public void setUserWeek(String userWeek) {
		this.userWeek = userWeek;
	}
	
}
