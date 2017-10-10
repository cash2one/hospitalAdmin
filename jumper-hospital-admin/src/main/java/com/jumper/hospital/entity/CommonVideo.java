package com.jumper.hospital.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * InteractionPostSearchKeyword entity. @author MyEclipse Persistence Tools
 */
@Entity
public class CommonVideo extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String title;//视频名称
	private String url;//视频地址
	private Integer used;//引用次数
	private String desc;//视频描述
	private Timestamp addTime;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getUsed() {
		return used;
	}
	public void setUsed(Integer used) {
		this.used = used;
	}
	@Column(name = "descript")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
}