package com.jumper.hospital.vo;

public class VOChatHistory {
	private String sender;
	private String recevrer;
	private String busCode;
	private String startTime;
	private String endTime;
	private Integer pageNo;
	private Integer pageSize;
	private String msgId;
	private String consultantId;
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecevrer() {
		return recevrer;
	}
	public void setRecevrer(String recevrer) {
		this.recevrer = recevrer;
	}
	public String getBusCode() {
		return busCode;
	}
	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getConsultantId() {
		return consultantId;
	}
	public void setConsultantId(String consultantId) {
		this.consultantId = consultantId;
	}
	@Override
	public String toString() {
		return "VOChatHistory [sender=" + sender + ", recevrer=" + recevrer
				+ ", busCode=" + busCode + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", pageNo=" + pageNo + ", pageSize="
				+ pageSize + ", msgId=" + msgId + ", consultantId="
				+ consultantId + "]";
	}
}
