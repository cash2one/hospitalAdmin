package com.jumper.hospital.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class VOMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 医院ID*/
	private String hospitalId ;
	/** 标题 */
	private String title;
	/** 消息类型 0:系统消息；1:医院消息' */
	private int type;
	/** 内容 */
	private String content;
	/** 推送时间 */
	private Timestamp pushTime;
	/** 修改人 */
	private String modifyEmp;
	/** 添加人 */
	private String addEmp;
	/** 修改时间 */
	private Timestamp modifyTime;
	/** 添加时间 */
	private Timestamp addTime;

	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getPushTime() {
		return pushTime;
	}
	public void setPushTime(Timestamp pushTime) {
		this.pushTime = pushTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getModifyEmp() {
		return modifyEmp;
	}
	public void setModifyEmp(String modifyEmp) {
		this.modifyEmp = modifyEmp;
	}
	public String getAddEmp() {
		return addEmp;
	}
	public void setAddEmp(String addEmp) {
		this.addEmp = addEmp;
	}
	public Timestamp getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
	
}
