package com.jumper.hospital.vo;

import java.util.Date;

public class VoInqHospitalTodayConsult {
	private Integer id;
	//用户id 
	private Integer userId ;
	//用户头像
	private String imgUrl;
	//用户姓名
	private String nickName;
	//用户手机
	private String phone;
	//问诊类型
	private Integer type;
	//提问时间
	private Date askTime;
	//管理员
	private String adminName;
	//问题状态(2：未回复；3：已回复；4：待处理；5：已结束；)
	private Integer status;
	//咨询内容
	private String content;
	//是否显示结束按钮
	private int endVisit;
	//用户的消息是不是语音
	private int isVoice;
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
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getEndVisit() {
		return endVisit;
	}
	public void setEndVisit(int endVisit) {
		this.endVisit = endVisit;
	}
	public int getIsVoice() {
		return isVoice;
	}
	public void setIsVoice(int isVoice) {
		this.isVoice = isVoice;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
