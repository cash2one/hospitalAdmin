package com.jumper.hospital.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pregnant_course_news")
public class PregnantCourseNews implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/** 孕阶段 **/
	private Integer pregnantId;
	/** 资讯ID **/
	private Integer newsId;
	
	public PregnantCourseNews() {
		super();
	}
	public PregnantCourseNews(Integer pregnantId, Integer newsId) {
		super();
		this.pregnantId = pregnantId;
		this.newsId = newsId;
	}
	@Id
	public Integer getPregnantId() {
		return pregnantId;
	}
	public void setPregnantId(Integer pregnantId) {
		this.pregnantId = pregnantId;
	}
	@Id
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	
}
