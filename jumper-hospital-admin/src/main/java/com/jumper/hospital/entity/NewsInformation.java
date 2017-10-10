package com.jumper.hospital.entity;
/**
 * 资讯新闻
 * @author rent
 * @date 2016-05-10
 */
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="news_information")
public class NewsInformation extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 所属频道 **/
    private Integer channelId;
    /** 新闻title **/
    private String title;
    /** 新闻内容 **/
    private String content;
    /** 新闻链接 **/
    private String newsUrl;
    /** 图片地址 **/
    private String imageUrl;
    /** 焦点图 **/
    private Integer isFocusImage;
    /** 添加时间 **/
    private Timestamp addTime;
    /** 内容简介 **/
    private String introduct;
    /** 是否推送，默认0未推送到首页，1，已推送 **/
    private Integer isPush;
    /** 新闻所属怀孕阶段   1：孕早期     2：孕中期     3：孕晚期     4：0-6个月     5:6月-1岁     6:1-3岁 支持多值，用"分号"隔开 **/
    private String period;
    /** 资讯被收藏数量 **/
    private Integer praise;
    /** 来源 **/
    private String sourceFrom;
    /** 来源头像 **/
    private String fromLogoUrl;
    /** 文章点击数 **/
    private Integer clicks;
    /** 文章分享数 **/
    private Integer shareNum;
    /** 是否发布 **/
    private Boolean isPublish;
    /** 关键字, 用逗号作为分隔符 **/
    private String keywords;
    /** 资讯新闻评论数 **/
    private Integer commentNum;
    /** 资讯发布时间 **/
    private Timestamp publishTime;
    /** banner排序 **/
    private Timestamp top;
    /** 频道名称 **/
    private String chanelName;
    /** 医院ID **/
    private Integer hospitalId;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNewsUrl() {
		return newsUrl;
	}
	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getIsFocusImage() {
		return isFocusImage;
	}
	public void setIsFocusImage(Integer isFocusImage) {
		this.isFocusImage = isFocusImage;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public String getIntroduct() {
		return introduct;
	}
	public void setIntroduct(String introduct) {
		this.introduct = introduct;
	}
	public Integer getIsPush() {
		return isPush;
	}
	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Integer getPraise() {
		return praise;
	}
	public void setPraise(Integer praise) {
		this.praise = praise;
	}
	public String getSourceFrom() {
		return sourceFrom;
	}
	public void setSourceFrom(String sourceFrom) {
		this.sourceFrom = sourceFrom;
	}
	public String getFromLogoUrl() {
		return fromLogoUrl;
	}
	public void setFromLogoUrl(String fromLogoUrl) {
		this.fromLogoUrl = fromLogoUrl;
	}
	public Integer getClicks() {
		return clicks;
	}
	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}
	public Integer getShareNum() {
		return shareNum;
	}
	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}
	public Boolean getIsPublish() {
		return isPublish;
	}
	public void setIsPublish(Boolean isPublish) {
		this.isPublish = isPublish;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	@Transient
	public Integer getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
	public Timestamp getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	@Transient
	public String getChanelName() {
		return chanelName;
	}
	public void setChanelName(String chanelName) {
		this.chanelName = chanelName;
	}
	public Timestamp getTop() {
		return top;
	}
	public void setTop(Timestamp top) {
		this.top = top;
	}
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
    
}