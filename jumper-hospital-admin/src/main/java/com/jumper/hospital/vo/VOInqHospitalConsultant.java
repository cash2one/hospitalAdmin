package com.jumper.hospital.vo;

import java.util.Date;

public class VOInqHospitalConsultant {
	private Integer id;
	// 用户头像
	private Integer userId;
	// 用户id
	private String imgUrl;
	// 用户姓名
	private String nickName;
	// 用户手机
	private String phone;
	// 问诊类型
	private Integer type;
	// 提问时间
	private Date askTime;
	// 结束时间
	private Date endTime;
	// 评价内容
	private String comment;
	// 管理员
	private String adminName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getAskTime() {
		return askTime;
	}

	public void setAskTime(Date askTime) {
		this.askTime = askTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
