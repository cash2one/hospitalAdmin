package com.jumper.hospital.entity;
/**
 * @author rent
 * @date 2015-07-27
 * @desc 孕期管理首页推送
 */
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="pregnant_course_index")
public class PregnantCourseIndex extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 孕期周 **/
	private Integer pregnantWeek;
	/** 类型(1：孕早期，2：孕中期，3：孕晚期，4：0-6月，5:6月-1岁，6:1-3岁) **/
	private Integer pregnantType;
	/** 添加时间 **/
	private Timestamp addTime;
	
	private Set<NewsInformation> news = new HashSet<NewsInformation>();
	
	public Integer getPregnantWeek() {
		return pregnantWeek;
	}
	public void setPregnantWeek(Integer pregnantWeek) {
		this.pregnantWeek = pregnantWeek;
	}
	public Integer getPregnantType() {
		return pregnantType;
	}
	public void setPregnantType(Integer pregnantType) {
		this.pregnantType = pregnantType;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "pregnant_course_news", joinColumns={@JoinColumn(name = "pregnantId") }, inverseJoinColumns = {@JoinColumn(name ="newsId")})
	public Set<NewsInformation> getNews() {
		return news;
	}
	public void setNews(Set<NewsInformation> news) {
		this.news = news;
	}
	
}
