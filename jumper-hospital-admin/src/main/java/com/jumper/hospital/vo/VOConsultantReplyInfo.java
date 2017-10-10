package com.jumper.hospital.vo;

public class VOConsultantReplyInfo {

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getTalker() {
		return talker;
	}

	public void setTalker(int talker) {
		this.talker = talker;
	}

	public String[] getImg_url() {
		return img_url;
	}

	public void setImg_url(String[] img_url) {
		this.img_url = img_url;
	}

	public String getUser_img() {
		return user_img;
	}

	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getIsVoice() {
		return isVoice;
	}

	public void setIsVoice(Integer isVoice) {
		this.isVoice = isVoice;
	}

	public String getSendChatId() {
		return sendChatId;
	}

	public void setSendChatId(String sendChatId) {
		this.sendChatId = sendChatId;
	}

	public String getRecevrerChatId() {
		return recevrerChatId;
	}

	public void setRecevrerChatId(String recevrerChatId) {
		this.recevrerChatId = recevrerChatId;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public UrlMsg getUrlmsg() {
		return urlmsg;
	}

	public void setUrlmsg(UrlMsg urlmsg) {
		this.urlmsg = urlmsg;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public int getDoctorer() {
		return doctorer;
	}

	public void setDoctorer(int doctorer) {
		this.doctorer = doctorer;
	}

	private String content;
	private String msgContent; // 内容
	private String time; // 时间
	private int talker; // 是否是回复 0:用户；1：医生
	private int doctorer;// 医生
	private String[] img_url; // 提问图片
	private String user_img; // 用户/医生图片
	private String userName; // 用户/医生名字
	private Integer isVoice = 0; // 是否是语音消息 1为语音消息 0则不是
	private UrlMsg urlmsg;
	// 发送者id
	private String sendChatId;
	// 接受者id
	private String recevrerChatId;
	// 消息时间
	private String sendTime;
	// 消息时间
	private String msgType;
	// 医生姓名
	private String doctorName;

}
