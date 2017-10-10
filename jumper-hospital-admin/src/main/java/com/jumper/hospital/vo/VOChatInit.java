package com.jumper.hospital.vo;

public class VOChatInit {

	/** 发送ID **/
	private String fromUserId;
	private String fromNickName;
	private String fromHeadUrl;
	private Integer fromUserType;
	private String toUserId;
	private String toHeadUrl;
	private String toNickName;
	private Integer toUserType;
	private Integer userType;
	private Integer color;
	private Integer busCode;
	private String consultantId;
	
	public String getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}
	public String getFromNickName() {
		return fromNickName;
	}
	public void setFromNickName(String fromNickName) {
		this.fromNickName = fromNickName;
	}
	public String getFromHeadUrl() {
		return fromHeadUrl;
	}
	public void setFromHeadUrl(String fromHeadUrl) {
		this.fromHeadUrl = fromHeadUrl;
	}
	public Integer getFromUserType() {
		return fromUserType;
	}
	public void setFromUserType(Integer fromUserType) {
		this.fromUserType = fromUserType;
	}
	public String getToUserId() {
		return toUserId;
	}
	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	public String getToHeadUrl() {
		return toHeadUrl;
	}
	public void setToHeadUrl(String toHeadUrl) {
		this.toHeadUrl = toHeadUrl;
	}
	public String getToNickName() {
		return toNickName;
	}
	public void setToNickName(String toNickName) {
		this.toNickName = toNickName;
	}
	public Integer getToUserType() {
		return toUserType;
	}
	public void setToUserType(Integer toUserType) {
		this.toUserType = toUserType;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getColor() {
		return color;
	}
	public void setColor(Integer color) {
		this.color = color;
	}
	public Integer getBusCode() {
		return busCode;
	}
	public void setBusCode(Integer busCode) {
		this.busCode = busCode;
	}
	public String getConsultantId() {
		return consultantId;
	}
	public void setConsultantId(String consultantId) {
		this.consultantId = consultantId;
	}
	
}
