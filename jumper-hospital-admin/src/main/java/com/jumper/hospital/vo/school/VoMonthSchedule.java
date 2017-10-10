package com.jumper.hospital.vo.school;
/**
 * 课程表后台管理界面数据包装返回
 * @author rent
 * @date 2016-02-16
 */
import java.io.Serializable;

public class VoMonthSchedule implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 当前天 **/
	private Integer day;
	/** 课程总量 **/
	private Integer courseCount;
	/** 预约总量 **/
	private Integer appointCount;
	/** 签到总量 **/
	private Integer signCount;
	
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getCourseCount() {
		return courseCount;
	}
	public void setCourseCount(Integer courseCount) {
		this.courseCount = courseCount;
	}
	public Integer getAppointCount() {
		return appointCount;
	}
	public void setAppointCount(Integer appointCount) {
		this.appointCount = appointCount;
	}
	public Integer getSignCount() {
		return signCount;
	}
	public void setSignCount(Integer signCount) {
		this.signCount = signCount;
	}
	
}
