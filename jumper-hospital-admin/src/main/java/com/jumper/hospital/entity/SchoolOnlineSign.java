package com.jumper.hospital.entity;
/**
 * 线下课程签到
 * @author rent
 * @date 2016-01-25
 */
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class SchoolOnlineSign extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 签到用户名 **/
	private String signUserName;
	/** 签到用户电话 **/
	private String signUserPhone;
	/** 签到用户ID **/
	private Integer signUserId;
	/** 签到课程ID **/
	private Integer signCourseId;
	/** 签到时间 **/
	private Timestamp signTime;
	/** 用户孕周 **/
	private String userWeek;
	
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
	public Integer getSignUserId() {
		return signUserId;
	}
	public void setSignUserId(Integer signUserId) {
		this.signUserId = signUserId;
	}
	public Integer getSignCourseId() {
		return signCourseId;
	}
	public void setSignCourseId(Integer signCourseId) {
		this.signCourseId = signCourseId;
	}
	public Timestamp getSignTime() {
		return signTime;
	}
	public void setSignTime(Timestamp signTime) {
		this.signTime = signTime;
	}
	@Transient
	public String getUserWeek() {
		return userWeek;
	}
	public void setUserWeek(String userWeek) {
		this.userWeek = userWeek;
	}
	
}
