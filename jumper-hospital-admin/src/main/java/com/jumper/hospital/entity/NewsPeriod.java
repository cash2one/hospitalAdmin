package com.jumper.hospital.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="news_period")
public class NewsPeriod extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private NewsInformation newsId; /** 资讯ID **/
	
	private Integer period; /** 对应人群 **/
	
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="news_id")
	public NewsInformation getNewsId() {
		return newsId;
	}
	public void setNewsId(NewsInformation newsId) {
		this.newsId = newsId;
	}
}
