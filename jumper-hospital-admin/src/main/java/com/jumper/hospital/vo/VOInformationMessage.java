package com.jumper.hospital.vo;

import java.io.Serializable;

public class VOInformationMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 主键资讯ID*/
	private int id;
	/** 外键管理员对象*/
	private  int  adminId;
	/**文章标题*/
	private String  title;
	/** 推送时间*/
	private String pushTime;
	/** 内容*/
	private String content;
	/** 医院ID*/
	private  int  HospitalId ;

	/** 资讯描述*/
	private String description;
	/** 图片路径*/
	private String imageUrl;
	/** 修改人 */
	private String modifyEmp;
	/** 添加人*/
	private String addEmp;
	/** 修改时间*/
	private String modifyTime;
	/** 添加时间*/
	private Integer addTime;
	/** 是否删除 0:未删除,1:已删除 */
	private Integer isDelete;
	/** 是否删除 0:未发布,1:已发布 */
	private Integer pushStatus;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	public int getHospitalId() {
		return HospitalId;
	}
	public void setHospitalId(int hospitalId) {
		HospitalId = hospitalId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	public String getPushTime() {
		return pushTime;
	}
	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getAddTime() {
		return addTime;
	}
	public void setAddTime(Integer addTime) {
		this.addTime = addTime;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getPushStatus() {
		return pushStatus;
	}
	public void setPushStatus(Integer pushStatus) {
		this.pushStatus = pushStatus;
	}
	
}
