package com.jumper.hospital.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * FamilyExamination entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "family_examination", catalog = "jumper_anglesound")
public class FamilyExamination implements java.io.Serializable {

	// Fields
	@Transient
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer userid;//
	//private Timestamp checkTime;//监测时间
	private Short result;//检查结果:0正常;1异常
	private Short state;//是否生成报告:0待审核;1已生成
	private String reportPdfUrl;//Pdf报告地址,多个用&分隔
	private String detailedreportPdfUrl;//院外远程孕检报告单(各个监测明细的结果报告单)
	private Timestamp addTime;// 生产报告单的时间
	private Timestamp finishTime;// 报告完成时间
    private String reason;//生产报告时医生填写的备注
	// Constructors

	/** default constructor */
	public FamilyExamination() {
	}

	/** minimal constructor */
	public FamilyExamination(Integer userid) {
		this.userid = userid;
	}

	/** full constructor */
/*	public FamilyExamination(Integer userid, Timestamp checkTime, Short result,
			Short state, String reportPdfUrl, String reportImgUrl,
			Timestamp addTime) {
		this.userid = userid;
		this.checkTime = checkTime;
		this.result = result;
		this.state = state;
		this.reportPdfUrl = reportPdfUrl;
		this.addTime = addTime;
	}*/

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userid", nullable = false)
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

/*	@Column(name = "check_time", length = 19)
	public Timestamp getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Timestamp checkTime) {
		this.checkTime = checkTime;
	}*/

	@Column(name = "result")
	public Short getResult() {
		return this.result;
	}

	public void setResult(Short result) {
		this.result = result;
	}

	@Column(name = "state")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "report_pdf_url", length = 500)
	public String getReportPdfUrl() {
		return this.reportPdfUrl;
	}

	public void setReportPdfUrl(String reportPdfUrl) {
		this.reportPdfUrl = reportPdfUrl;
	}

 

	public String getDetailedreportPdfUrl() {
		return detailedreportPdfUrl;
	}

	public void setDetailedreportPdfUrl(String detailedreportPdfUrl) {
		this.detailedreportPdfUrl = detailedreportPdfUrl;
	}

	@Column(name = "add_time", length = 19)
	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
 
	public Timestamp getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Timestamp finishTime) {
		this.finishTime = finishTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
    
	
}