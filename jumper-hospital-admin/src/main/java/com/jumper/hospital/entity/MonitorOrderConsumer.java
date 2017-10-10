package com.jumper.hospital.entity;
/**
 * 订单消费详情(一条订单可进行多次监控，这里记录每次监控信息)
 * @author rent
 * @date 2015-09-16
 */
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jumper.hospital.enums.ReportState;

@Entity
@Table(name="monitor_order_consumer")
public class MonitorOrderConsumer extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 对应医院购买的监控订单 **/
	private MonitorOrder monitorOrderId;
	/** 订单消费状态(报告单是否已出) **/
	private ReportState state;
	/** 本次消费订单的订单号 **/
	private String orderId;
	/** 订单消费用户，此处方便查询用 **/
	private UserInfo userId;
	/** 本次订单消费下单时间 **/
	private Timestamp applyTime;
	/** 本次监测订单监测PDF报告结构URL **/
	private String reportUrl;
	/** 本次监测订单PDF报告的图片文件，用户客户端查看报告结果 **/
	private String reportImg;
	/** 本次监测订单报告生成时间 **/
	private Timestamp reportTime;
	/** 本次胎心监护测试记录ID，表monitor_heartrate_record **/
	private Integer recordId;
	/** 本次胎心监护测试记录ID，表问题ID **/
	private Long questionId;
	/** 判读该报告的医生ID **/
	private Integer doctorId;
	/**用户备注信息  **/
	private String commentInfo;
	
	/** 报告是否被查看   0：未查看   1：已经查看 **/
	private Boolean isViewed;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="monitor_order_id")
	public MonitorOrder getMonitorOrderId() {
		return monitorOrderId;
	}
	public void setMonitorOrderId(MonitorOrder monitorOrderId) {
		this.monitorOrderId = monitorOrderId;
	}
	public ReportState getState() {
		return state;
	}
	public void setState(ReportState state) {
		this.state = state;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name="userId")
	public UserInfo getUserId() {
		return userId;
	}
	public void setUserId(UserInfo userId) {
		this.userId = userId;
	}
	public Timestamp getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}
	public String getReportUrl() {
		return reportUrl;
	}
	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}
	public String getReportImg() {
		return reportImg;
	}
	public void setReportImg(String reportImg) {
		this.reportImg = reportImg;
	}
	public Timestamp getReportTime() {
		return reportTime;
	}
	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public String getCommentInfo() {
		return commentInfo;
	}
	public void setCommentInfo(String commentInfo) {
		this.commentInfo = commentInfo;
	}
	public Boolean getIsViewed() {
		return isViewed;
	}
	public void setIsViewed(Boolean isViewed) {
		this.isViewed = isViewed;
	}
}
