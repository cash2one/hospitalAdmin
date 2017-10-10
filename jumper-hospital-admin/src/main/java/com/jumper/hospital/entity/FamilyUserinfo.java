package com.jumper.hospital.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * FamilyUserinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "family_userinfo", catalog = "jumper_anglesound", uniqueConstraints = @UniqueConstraint(columnNames = "mobile"))
public class FamilyUserinfo  implements java.io.Serializable {

	// Fields

	@Transient
	private static final long serialVersionUID = 1L;
	private String img="";
	private String name="";
	private Short age=0;
	private String mobile="";
	private String linkMan="";
	private String linkTel="";
	private String identity="";
	private Date lastPeriod;
	private Short height=0;
	private Double weight=0.0;
	private Date pregancyDay;
	private Integer provinceId=0;
	private Integer cityId=0;
	private String joinAdd="";
	private String detailAdd="";
	private Integer familyDoctorId=0;
	private Integer familyHospitalId=0;
	private Date regTime;
	private Date lastTime;
	private Integer id;
	private Integer state;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/** default constructor */
	public FamilyUserinfo() {
	}

	/** minimal constructor */
	public FamilyUserinfo(Integer id, String name, Short age, String mobile,
			String linkTel, Date lastPeriod) {
		this.id=id;
		this.name = name;
		this.age = age;
		this.mobile = mobile;
		this.linkTel = linkTel;
		this.lastPeriod = lastPeriod;
	}


	 
	@Column(name = "img", length = 100)
	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "age", nullable = false)
	public Short getAge() {
		return this.age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	@Column(name = "mobile", unique = true, nullable = false, length = 15)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "link_man", length = 60)
	public String getLinkMan() {
		return this.linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	@Column(name = "link_tel", nullable = false, length = 40)
	public String getLinkTel() {
		return this.linkTel;
	}

	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}

	@Column(name = "identity", length = 20)
	public String getIdentity() {
		return this.identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

 
	public Date getLastPeriod() {
		return this.lastPeriod;
	}

	public void setLastPeriod(Date lastPeriod) {
		this.lastPeriod = lastPeriod;
	}

	@Column(name = "height")
	public Short getHeight() {
		return this.height;
	}

	public void setHeight(Short height) {
		this.height = height;
	}

	@Column(name = "weight", precision = 5)
	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}


	@Column(name = "pregancy_day", length = 10)
	public Date getPregancyDay() {
		return this.pregancyDay;
	}

	public void setPregancyDay(Date pregancyDay) {
		this.pregancyDay = pregancyDay;
	}

	@Column(name = "province_id")
	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "city_id")
	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}


	@Column(name = "join_add", length = 200)
	public String getJoinAdd() {
		return this.joinAdd;
	}

	public void setJoinAdd(String joinAdd) {
		this.joinAdd = joinAdd;
	}

	@Column(name = "detail_add", length = 200)
	public String getDetailAdd() {
		return this.detailAdd;
	}

	public void setDetailAdd(String detailAdd) {
		this.detailAdd = detailAdd;
	}


	public Integer getFamilyDoctorId() {
		return this.familyDoctorId;
	}

	public void setFamilyDoctorId(Integer familyDoctorId) {
		this.familyDoctorId = familyDoctorId;
	}

	@Column(name = "family_hospital_id")
	public Integer getFamilyHospitalId() {
		return this.familyHospitalId;
	}

	public void setFamilyHospitalId(Integer familyHospitalId) {
		this.familyHospitalId = familyHospitalId;
	}

	@Column(name = "reg_time", length = 19)
	public Date getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	
	@Column(name = "last_time", length = 19)
	public Date getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	
	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "FamilyUserinfo [img=" + img + ", name=" + name + ", age=" + age
				+ ", mobile=" + mobile + ", linkMan=" + linkMan + ", linkTel="
				+ linkTel + ", identity=" + identity + ", lastPeriod="
				+ lastPeriod + ", height=" + height + ", weight=" + weight
				+ ", pregancyDay=" + pregancyDay + ", provinceId=" + provinceId
				+ ", cityId=" + cityId + ", joinAdd=" + joinAdd
				+ ", detailAdd=" + detailAdd + ", familyDoctorId="
				+ familyDoctorId + ", familyHospitalId=" + familyHospitalId
				+ ", regTime=" + regTime + ", lastTime=" + lastTime + "]";
	}
	
}