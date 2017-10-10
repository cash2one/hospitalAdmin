package com.jumper.hospital.vo.familyDoctor;

import java.sql.Timestamp;

public class VOFamilyExamination {
	private Integer id;
	private Integer userid;//
	private Timestamp checkTime;//监测时间
	private Short result;//检查结果:0正常;1异常
	private Short state;//是否生成报告:0待审核;1已生成
	private String reportPdfUrl;//Pdf报告地址,多个用&分隔
	private String reportImgUrl;//图片地址
	private Timestamp addTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Timestamp getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Timestamp checkTime) {
		this.checkTime = checkTime;
	}
	public Short getResult() {
		return result;
	}
	public void setResult(Short result) {
		this.result = result;
	}
	public Short getState() {
		return state;
	}
	public void setState(Short state) {
		this.state = state;
	}
	public String getReportPdfUrl() {
		return reportPdfUrl;
	}
	public void setReportPdfUrl(String reportPdfUrl) {
		this.reportPdfUrl = reportPdfUrl;
	}
	public String getReportImgUrl() {
		return reportImgUrl;
	}
	public void setReportImgUrl(String reportImgUrl) {
		this.reportImgUrl = reportImgUrl;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
	
	
	 
}
