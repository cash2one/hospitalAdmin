package com.jumper.hospital.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "monitor_hospital_visitor_account")
public class CloudVisitorAccount implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 0成功 其它都失败
	 */
	@Transient
	private String msg;
	/**
	 * 状态非0时给予相关提示
	 */
	@Transient
	private String msgbox;
	
	@Id
	private Integer monitorAdminId;

	/**
	 * 医院id
	 */
	private Integer hostpitalId;

	/**
	 * 健海医院编码
	 */
	private String jhHospCode;

	/**
	 * 医生工号
	 */
	private String doctorWorkNum;

	/**
	 * 云随访角色
	 */
	private Integer visitorRole;

	/**
	 * 科室编码
	 */
	private String deptCode;

	/**
	 * 科室名称
	 */
	private String deptName;

	/**
	 * 生成随访账户的id
	 */
	private String sourseId;

	/**
	 * 生成随访账户的名称
	 */
	private String account;
	@Column(nullable = false)
	public Integer getHostpitalId() {
		return hostpitalId;
	}

	public Integer getMonitorAdminId() {
		return monitorAdminId;
	}

	public void setMonitorAdminId(Integer monitorAdminId) {
		this.monitorAdminId = monitorAdminId;
	}

	public void setHostpitalId(Integer hostpitalId) {
		this.hostpitalId = hostpitalId;
	}
	@Column(nullable = false)
	public String getJhHospCode() {
		return jhHospCode;
	}

	public void setJhHospCode(String jhHospCode) {
		this.jhHospCode = jhHospCode;
	}
	@Column(nullable = false, updatable = false, unique = true, length = 100)
	public String getDoctorWorkNum() {
		return doctorWorkNum;
	}

	public void setDoctorWorkNum(String doctorWorkNum) {
		this.doctorWorkNum = doctorWorkNum;
	}
	@Column(nullable = false)
	public Integer getVisitorRole() {
		return visitorRole;
	}

	public void setVisitorRole(Integer visitorRole) {
		this.visitorRole = visitorRole;
	}
	@Column(nullable = false)
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	@Column(nullable = false)
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Column(nullable = false)
	public String getSourseId() {
		return sourseId;
	}

	public void setSourseId(String string) {
		this.sourseId = string;
	}
	@Column(nullable = false)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsgbox() {
		return msgbox;
	}

	public void setMsgbox(String msgbox) {
		this.msgbox = msgbox;
	}

	@Override
	public String toString() {
		return "CloudVisitorAccount [msg=" + msg + ", msgbox=" + msgbox
				+ ", monitorAdminId=" + monitorAdminId + ", hostpitalId="
				+ hostpitalId + ", jhHospCode=" + jhHospCode
				+ ", doctorWorkNum=" + doctorWorkNum + ", visitorRole="
				+ visitorRole + ", deptCode=" + deptCode + ", deptName="
				+ deptName + ", sourseId=" + sourseId + ", account=" + account
				+ "]";
	}

}
