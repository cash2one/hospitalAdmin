package com.jumper.hospital.vo.familyDoctor;

import java.util.Date;

public class VOFamilyExaminationArranged {
	private Integer id;
	private Integer userid;
	private Integer doctorid;
	private Date homeVisitsDate;
	private Short state;
	public VOFamilyExaminationArranged() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VOFamilyExaminationArranged(Integer id, Integer userid,
			Integer doctorid, Date homeVisitsDate, Short state) {
		super();
		this.id = id;
		this.userid = userid;
		this.doctorid = doctorid;
		this.homeVisitsDate = homeVisitsDate;
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}
	public Date getHomeVisitsDate() {
		return homeVisitsDate;
	}
	public void setHomeVisitsDate(Date homeVisitsDate) {
		this.homeVisitsDate = homeVisitsDate;
	}
	public Short getState() {
		return state;
	}
	public void setState(Short state) {
		this.state = state;
	}
	
}
