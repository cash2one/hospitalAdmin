package com.jumper.hospital.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * UsersInfo entity. @author MyEclipse Persistence Tools
 */
/**
 * @author tiedan200
 *
 */
@Entity
@Table(name="user_book_husband_info")
public class UsersBookHusbandInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private UserInfo userInfo;
    private String name;
    private Date birthday;
    private Integer	blood_type ;//血型：0：A；1：B；2：AB;3:O
    private String job;
    private Country country;
    private UserNations userNations;
	private Integer height;
	private Double weight;
    private UserEducation userEducation;
    private Integer ID_type;
    private String ID_no;
    private String mobile;
    private Province privince;
    private City city;
    private Integer resident_type;
    private String work_unit;
    private String disease_history;
    private String phone;
    private Integer smoke;
    private Integer drinking;
    private UserHospitalPregnantArchives userHospitalPregnantArchives;

	public UsersBookHusbandInfo() {
		super();
	}
	@JoinColumn(name="user_id")
	@OneToOne(fetch = FetchType.LAZY)
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getBlood_type() {
		return blood_type;
	}
	public void setBlood_type(Integer blood_type) {
		this.blood_type = blood_type;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}

	@JoinColumn(name="nation_id")
	@OneToOne(fetch = FetchType.LAZY)
	public UserNations getUserNations() {
		return userNations;
	}
	public void setUserNations(UserNations userNations) {
		this.userNations = userNations;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@JoinColumn(name="education_id")
	@OneToOne(fetch = FetchType.LAZY)
	public UserEducation getUserEducation() {
		return userEducation;
	}
	public void setUserEducation(UserEducation userEducation) {
		this.userEducation = userEducation;
	}
	public Integer getID_type() {
		return ID_type;
	}
	public void setID_type(Integer iD_type) {
		ID_type = iD_type;
	}
	public String getID_no() {
		return ID_no;
	}
	public void setID_no(String iD_no) {
		ID_no = iD_no;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

    @JoinColumn(name="privince_id")
	@OneToOne(fetch = FetchType.LAZY)
	public Province getPrivince() {
		return privince;
	}
	public void setPrivince(Province privince) {
		this.privince = privince;
	}

    @JoinColumn(name="city_id")
	@OneToOne(fetch = FetchType.LAZY)
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Integer getResident_type() {
		return resident_type;
	}
	public void setResident_type(Integer resident_type) {
		this.resident_type = resident_type;
	}
	public String getWork_unit() {
		return work_unit;
	}
	public void setWork_unit(String work_unit) {
		this.work_unit = work_unit;
	}
	public String getDisease_history() {
		return disease_history;
	}
	public void setDisease_history(String disease_history) {
		this.disease_history = disease_history;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getSmoke() {
		return smoke;
	}
	public void setSmoke(Integer smoke) {
		this.smoke = smoke;
	}
	public Integer getDrinking() {
		return drinking;
	}
	public void setDrinking(Integer drinking) {
		this.drinking = drinking;
	}

    @JoinColumn(name="country_id")
	@OneToOne(fetch = FetchType.LAZY)
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="archives_id")
	public UserHospitalPregnantArchives getUserHospitalPregnantArchives() {
		return userHospitalPregnantArchives;
	}
	public void setUserHospitalPregnantArchives(
			UserHospitalPregnantArchives userHospitalPregnantArchives) {
		this.userHospitalPregnantArchives = userHospitalPregnantArchives;
	}
	

	
}