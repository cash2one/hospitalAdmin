package com.jumper.hospital.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 用户胎心报告文件 实体类
 * @author tanqing
 *
 */
@Entity
@Table(name = "user_heartrate_report_file")
public class UserHeartrateReportFile implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	/**用户id*/
	@Column(name="user_id")
	private Integer userId;
	
	/**胎心报告文件地址*/
	@Column(name="report_file")
	private String reportFile;
	
	/**胎心报告类型
	 * 1: 1厘米 2: 2厘米 3： 3厘米 20: 20分钟 30: 30分钟
	 * */
	@Column(name="type")
	private Integer type;
	
	/**胎心记录id*/
	@Column(name="heart_id")
	private Integer heartId;
	
	
	/**添加时间*/
	@Column(name="add_time")
	private Timestamp addTime;
	
	/**所监测的孕妇名称*/
	@Column(name="monitor_user_name")
	private String monitorUserName;
	
	/**区间打印对应的区间段*/
	@Column(name="offsets")
	private String offsets;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getReportFile() {
		return reportFile;
	}
	public void setReportFile(String reportFile) {
		this.reportFile = reportFile;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public Integer getHeartId() {
		return heartId;
	}
	public void setHeartId(Integer heartId) {
		this.heartId = heartId;
	}
	public String getMonitorUserName() {
		return monitorUserName;
	}
	public void setMonitorUserName(String monitorUserName) {
		this.monitorUserName = monitorUserName;
	}
	public String getOffsets() {
		return offsets;
	}
	public void setOffsets(String offsets) {
		this.offsets = offsets;
	}
	
}
