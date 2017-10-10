package com.jumper.hospital.vo;

public class VOChatRecord {
	
	/**业务代码*/
	private String busCode; 
	/**userId */
	private String userId; 
	/**接受者医院ID*/
	private String hospitalId; 
	/**开始时间*/
	private String startTime; 
	/**结束时间*/
	private String endTime; 
	/**分页参数*/
	private String pageNo; 
	/**分页条数*/
	private String pageSize; 
	/**信息id*/
	private Integer msgId; 
	/**用户姓名*/
	private String  userName ; 
	/**用户头像*/
	private String  userHeadUrl  ;
	/**医院的信息 可以直接从admin里面拿 */
	
	private String sender;
	
	private String recevrer;
	
	private String consultantId;
	
	public String getConsultantId() {
		return consultantId;
	}
	public void setConsultantId(String consultantId) {
		this.consultantId = consultantId;
	}
	public String getRecevrer() {
		return recevrer;
	}
	public void setRecevrer(String recevrer) {
		this.recevrer = recevrer;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
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
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getMsgId() {
		return msgId;
	}
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserHeadUrl() {
		return userHeadUrl;
	}
	public void setUserHeadUrl(String userHeadUrl) {
		this.userHeadUrl = userHeadUrl;
	} 
	public String getBusCode() {
		return busCode;
	}
	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}
	@Override
	public String toString() {
		return "VOChatRecord [busCode=" + busCode + ", userId=" + userId
				+ ", hospitalId=" + hospitalId + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", pageNo=" + pageNo + ", pageSize="
				+ pageSize + ", msgId=" + msgId + ", userName=" + userName
				+ ", userHeadUrl=" + userHeadUrl + ", sender=" + sender
				+ ", recevrer=" + recevrer + ", consultantId=" + consultantId
				+ "]";
	}
	
}
