package com.jumper.hospital.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FamilyQuestionnaireResults entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "family_questionnaire_results", catalog = "jumper_anglesound")
public class FamilyQuestionnaireResults implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private Timestamp addTime;
	private String resultUrl;
	private Integer resultValue;
	private Integer state;

	// Constructors

	/** default constructor */
	public FamilyQuestionnaireResults() {
	}

	/** full constructor */
	public FamilyQuestionnaireResults(Integer userId, Timestamp addTime,
			String resultUrl, Integer resultValue, Integer state) {
		this.userId = userId;
		this.addTime = addTime;
		this.resultUrl = resultUrl;
		this.resultValue = resultValue;
		this.state = state;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "add_time", length = 19)
	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	@Column(name = "result_url", length = 200)
	public String getResultUrl() {
		return this.resultUrl;
	}

	public void setResultUrl(String resultUrl) {
		this.resultUrl = resultUrl;
	}

	@Column(name = "result_value")
	public Integer getResultValue() {
		return this.resultValue;
	}

	public void setResultValue(Integer resultValue) {
		this.resultValue = resultValue;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}