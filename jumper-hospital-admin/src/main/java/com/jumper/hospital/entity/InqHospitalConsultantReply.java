package com.jumper.hospital.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="inq_hospital_consultant_reply")
public class InqHospitalConsultantReply extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	//咨询id
	private InqHospitalConsultant inqHospitalConsultant;
	
	//恢复内容
	private String replyContent;
	
	//回复文件
	private String replyFileUrl;
	
	//内容对象
	private Integer talker;
	
	//添加时间
	private Date addTime;
	
	//音频时长
	private Integer length;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="inq_consultant_id")
	public InqHospitalConsultant getInqHospitalConsultant() {
		return inqHospitalConsultant;
	}

	public void setInqHospitalConsultant(InqHospitalConsultant inqHospitalConsultant) {
		this.inqHospitalConsultant = inqHospitalConsultant;
	}

	@Column(name="reply_content")
	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	@Column(name="reply_file_url")
	public String getReplyFileUrl() {
		return replyFileUrl;
	}

	public void setReplyFileUrl(String replyFileUrl) {
		this.replyFileUrl = replyFileUrl;
	}

	public Integer getTalker() {
		return talker;
	}

	public void setTalker(Integer talker) {
		this.talker = talker;
	}

	@Column(name="add_time")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
	
	
}
