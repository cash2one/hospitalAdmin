package com.jumper.hospital.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 用户咨询（问诊问题）
 * 
 * @author home-admin
 *
 */
@Entity
@Table(name = "inq_hospital_consultant")
public class InqHospitalConsultant extends BaseEntity {

	private static final long serialVersionUID = 1L;
	// 用户id
	private UserInfo userInfo;
	// 医院id
	private HospitalInfo hospitalInfo;
	// 科室id
	private HospitalDoctorMajor hospitalDoctorMajor;
	// 咨询内容
	private String content;
	// 音频文件或图片文件
	private String fileUrl;
	// 问题状态(2：未回复；3：已回复；4：待处理；5：已结束；)
	private Integer status;
	// 音频时长
	private Integer length;
	// 购买记录id
	/**
	 * 此处添加注释，避免更多人踩坑，这里的id是hospital_cons_service_order表中记录的ID。也就是付费医院问诊的订单ID
	 **/
	private Integer payHospitalId;
	// 是否评价(0:未评价，1：已评价)
	private Integer evaluate;
	// 是否删除(0:未删除，1：已删除)
	private Integer isDelete;
	// 对应的运营商
	private Integer appKeyId;
	// 运营商的唯一id
	private Integer uId;
	// 添加时间
	private Date addTime;
	// 回复时间
	private Date updateTime;


	private Set<InqHospitalConsultantReply> consultReplys = new HashSet<InqHospitalConsultantReply>();

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "hospital_id")
	public HospitalInfo getHospitalInfo() {
		return hospitalInfo;
	}

	public void setHospitalInfo(HospitalInfo hospitalInfo) {
		this.hospitalInfo = hospitalInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "major_id")
	public HospitalDoctorMajor getHospitalDoctorMajor() {
		return hospitalDoctorMajor;
	}

	public void setHospitalDoctorMajor(HospitalDoctorMajor hospitalDoctorMajor) {
		this.hospitalDoctorMajor = hospitalDoctorMajor;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "file_url")
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	@Column(name = "pay_hospital_id")
	public Integer getPayHospitalId() {
		return payHospitalId;
	}

	public void setPayHospitalId(Integer payHospitalId) {
		this.payHospitalId = payHospitalId;
	}

	public Integer getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Integer evaluate) {
		this.evaluate = evaluate;
	}

	@Column(name = "is_delete")
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "appkey_id")
	public Integer getAppKeyId() {
		return appKeyId;
	}

	public void setAppKeyId(Integer appKeyId) {
		this.appKeyId = appKeyId;
	}

	@Column(name = "u_id")
	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	@Column(name = "add_time")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "update_time")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "inqHospitalConsultant")
	public Set<InqHospitalConsultantReply> getConsultReplys() {
		return consultReplys;
	}

	public void setConsultReplys(Set<InqHospitalConsultantReply> consultReplys) {
		this.consultReplys = consultReplys;
	}

}
