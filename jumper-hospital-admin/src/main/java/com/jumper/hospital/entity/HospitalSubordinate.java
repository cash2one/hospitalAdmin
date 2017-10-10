package com.jumper.hospital.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="hospital_subordinate")
public class HospitalSubordinate extends BaseEntity {

	private static final long serialVersionUID = 1L;
	//医院id
	private HospitalInfo hospitalId;
	//下属机构id
	private HospitalInfo subordinateId;
	
	private Timestamp createTime;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name="hospitalId", nullable=false)
	public HospitalInfo getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(HospitalInfo hospitalId) {
		this.hospitalId = hospitalId;
	}
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name="subordinateId", nullable=false)
	public HospitalInfo getSubordinateId() {
		return subordinateId;
	}

	public void setSubordinateId(HospitalInfo subordinateId) {
		this.subordinateId = subordinateId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}