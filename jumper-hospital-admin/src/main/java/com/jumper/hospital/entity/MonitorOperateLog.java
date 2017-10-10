package com.jumper.hospital.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="monitor_operate_log")
public class MonitorOperateLog extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer hospitalInfoId;
	private Integer monitorAdminId;
	private String userName;
	private String name;
	private String module;
	private String action;
	private String object;
	private String objectContent;
	//private Timestamp operateTime;
	private Date operateTime;

	public Integer getHospitalInfoId() {
		return hospitalInfoId;
	}
	public void setHospitalInfoId(Integer hospitalInfoId) {
		this.hospitalInfoId = hospitalInfoId;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getObjectContent() {
		return objectContent;
	}
	public void setObjectContent(String objectContent) {
		this.objectContent = objectContent;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public Integer getMonitorAdminId() {
		return monitorAdminId;
	}
	public void setMonitorAdminId(Integer monitorAdminId) {
		this.monitorAdminId = monitorAdminId;
	}
	
	@Column(name="username")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
