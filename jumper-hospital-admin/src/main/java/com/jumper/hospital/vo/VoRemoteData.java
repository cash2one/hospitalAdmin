package com.jumper.hospital.vo;
/**
 * 监测管理返回数据vo
 * @author rent
 * @date 2015-11-06
 */
import java.io.Serializable;

import com.jumper.hospital.entity.HospitalInfo;

public class VoRemoteData implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 已消费订单ID **/
	private Integer consumerId;
	/** 订单ID **/
	private Integer orderId;
	/** 消费次数 **/
	private Integer monitorTimes;
	/** 用户ID **/
	private Integer userId;
	/** 用户名 **/
	private String userName;
	/** 用户年龄 **/
	private Integer userAge;
	/** 孕周 **/
	private String preganyWeek;
	/** 电话号码 **/
	private String mobile;
	/** 预产期 **/
	private String preganyDate;
	/** 胎心图对象 **/
	private VoRemoteHeart heart;
	/** 用户购买的服务类型 **/
	private String remoteType;
	/** 用户购买的问题id **/
	private Long questionId;
	/**用户备注信息  **/
	private String commentInfo;
	/** 本次胎心监护测试记录ID，表monitor_heartrate_record **/
	private Integer recordId;
	public String getRemoteType() {
		return remoteType;
	}
	public void setRemoteType(String remoteType) {
		this.remoteType = remoteType;
	}
	// 医院
	private HospitalInfo hospitalInfo;
	public Integer getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(Integer consumerId) {
		this.consumerId = consumerId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getMonitorTimes() {
		return monitorTimes;
	}
	public void setMonitorTimes(Integer monitorTimes) {
		this.monitorTimes = monitorTimes;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	public String getPreganyWeek() {
		return preganyWeek;
	}
	public void setPreganyWeek(String preganyWeek) {
		this.preganyWeek = preganyWeek;
	}
	public VoRemoteHeart getHeart() {
		return heart;
	}
	public void setHeart(VoRemoteHeart heart) {
		this.heart = heart;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPreganyDate() {
		return preganyDate;
	}
	public void setPreganyDate(String preganyDate) {
		this.preganyDate = preganyDate;
	}
	public HospitalInfo getHospitalInfo() {
		return hospitalInfo;
	}
	public void setHospitalInfo(HospitalInfo hospitalInfo) {
		this.hospitalInfo = hospitalInfo;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long integer) {
		this.questionId = integer;
	}
	public String getCommentInfo() {
		return commentInfo;
	}
	public void setCommentInfo(String commentInfo) {
		this.commentInfo = commentInfo;
	}
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
}
