package com.jumper.hospital.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 频道
 * @author 薛光敏（modify）
 * @date 2015-07-14
 */
@Entity
@Table(name="news_chanels")
public class NewsChanels  extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 频道名称 **/
    private String chanelName;
    /** 频道描述 **/
    private String channelDesc;
    /** 频道版本 **/
    private Integer channelVer;
    /** 频道图片 **/
    private String imgUrl;
    /** 是否默认订阅的频道 **/
    private Boolean isDefaultSub = false;
    /** 添加时间 **/
    private Timestamp addTime;
    /** 排序下标 **/
    private Integer orderBy;
    /** 订阅的用户数量 **/
    private Integer subNum = 0;
    /** 咨询是否视频咨询 **/
    private Boolean isVideo = false;
    /** 所属医院 **/
    private Integer hospitalId;
    /** 频道状态，是否显示 **/
    private Boolean state = false;
    /** 频道下的新闻总数 **/
    private Integer newsNum = 0;
    
	public NewsChanels() {
	}

	public NewsChanels(String chanelName, Integer channelVer, Timestamp addTime, Integer orderBy, 
			Integer hospitalId, boolean isDefaultSub, boolean state) {
		this.chanelName = chanelName;
		this.channelVer = channelVer;
		this.addTime = addTime;
		this.orderBy = orderBy;
		this.hospitalId = hospitalId;
		this.isDefaultSub = isDefaultSub;
		this.state = state;
	}
	
	public String getChanelName() {
		return chanelName;
	}
	public void setChanelName(String chanelName) {
		this.chanelName = chanelName;
	}
	public String getChannelDesc() {
		return channelDesc;
	}
	public void setChannelDesc(String channelDesc) {
		this.channelDesc = channelDesc;
	}
	public Integer getChannelVer() {
		return channelVer;
	}
	public void setChannelVer(Integer channelVer) {
		this.channelVer = channelVer;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Boolean getIsDefaultSub() {
		return isDefaultSub;
	}
	public void setIsDefaultSub(Boolean isDefaultSub) {
		this.isDefaultSub = isDefaultSub;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	public Integer getSubNum() {
		return subNum;
	}
	public void setSubNum(Integer subNum) {
		this.subNum = subNum;
	}
	public Boolean getIsVideo() {
		return isVideo;
	}
	public void setIsVideo(Boolean isVideo) {
		this.isVideo = isVideo;
	}
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	@Transient
	public Integer getNewsNum() {
		return newsNum;
	}
	public void setNewsNum(Integer newsNum) {
		this.newsNum = newsNum;
	}
    
}