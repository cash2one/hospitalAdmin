package com.jumper.hospital.entity;
/**
 * @author tanqing
 * @date 2015-10-20
 * @desc 医院描述
 */
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="hospital_description")
public class HospitalDescription extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	/** 描述内容 **/
	private String content;
	/**
	 * 描述类型
	 * 0：科室特色，1：环境设备
	 */
	private Integer type;
	
	private Timestamp addTime;
	
	private HospitalInfo hospitalId;
	
	private String imgUrl;
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name="hospital_id", nullable=false)
	public HospitalInfo getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(HospitalInfo hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/*@Override
	public String toString() {
		return "[content=" + content + ", type=" + type
				+ ", addTime=" + addTime + ", hospitalId=" + hospitalId
				+ ", imgUrl=" + imgUrl + "]";
	}*/
	
}
