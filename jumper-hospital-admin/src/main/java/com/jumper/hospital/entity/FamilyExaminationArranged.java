package com.jumper.hospital.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * FamilyExaminationArranged entity. @author MyEclipse Persistence Tools
 */
@JsonIgnoreProperties
@Entity
@Table(name = "family_examination_arranged", catalog = "jumper_anglesound")
public class FamilyExaminationArranged implements java.io.Serializable {

	// Fields
	@Transient
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer userid;
	private Integer doctorid;
	private Date homeVisitsDate;
	private Date finishTime;
	private Short state;
	

	// Constructors

	/** default constructor */
	public FamilyExaminationArranged() {
	}

	/** minimal constructor */
	public FamilyExaminationArranged(Integer userid, Integer doctorid) {
		this.userid = userid;
		this.doctorid = doctorid;
	}

	/** full constructor */
	public FamilyExaminationArranged(Integer userid, Integer doctorid,
			Date homeVisitsDate, Short state) {
		this.userid = userid;
		this.doctorid = doctorid;
		this.homeVisitsDate = homeVisitsDate;
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

	@Column(name = "userid", nullable = false)
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Column(name = "doctorid", nullable = false)
	public Integer getDoctorid() {
		return this.doctorid;
	}

	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "home_visits_date", length = 10)
	public Date getHomeVisitsDate() {
		return this.homeVisitsDate;
	}

	public void setHomeVisitsDate(Date homeVisitsDate) {
		this.homeVisitsDate = homeVisitsDate;
	}

	@Column(name = "state")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

}