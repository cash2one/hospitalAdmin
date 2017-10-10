package com.jumper.hospital.entity;

import java.util.Date;

import javax.persistence.Column;
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
@Table(name="user_book_basic_info")
public class UsersBookBasicInfo extends BaseEntity {

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
    private String job;
    private UserNations userNations;
    private UserEducation userEducation;
    private Integer ID_type;
    private String ID_no;
    private String mobile;
    private Province privince;
    private City city;
    private Integer resident_type;
    private Integer resident;
    private Integer stay_year;
    private Integer stay_month;
    private City now_stay_city;
    private District nowStayDistrict;
    private String now_stay_address;
    private String work_unit;
    private City postpartum_cultivation_city_id;
    private District postpartumCultivationDistrictId;
    private String postpartum_cultivation_address;
    private String contact_phone;
    private Integer menarche;
    private Integer week;
    private Integer menstruation;
    private UserPastHistory pastHistoryId;
    private UserGeneticHistory geneticHistoryId;
    private Date addTime;
    private UserHospitalPregnantArchives userHospitalPregnantArchives;
    
	public UsersBookBasicInfo() {
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

	@JoinColumn(name="education_id")
	@OneToOne(fetch = FetchType.LAZY)
	public UserEducation getUserEducation() {
		return userEducation;
	}
	public void setUserEducation(UserEducation userEducation) {
		this.userEducation = userEducation;
	}

	@Column(name="ID_type")
	public Integer getID_type() {
		return ID_type;
	}
	public void setID_type(Integer iD_type) {
		ID_type = iD_type;
	}

	@Column(name="ID_no")
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

	@Column(name="resident_type")
	public Integer getResident_type() {
		return resident_type;
	}
	public void setResident_type(Integer resident_type) {
		this.resident_type = resident_type;
	}
	public Integer getResident() {
		return resident;
	}
	public void setResident(Integer resident) {
		this.resident = resident;
	}

	@Column(name="stay_year")
	public Integer getStay_year() {
		return stay_year;
	}
	public void setStay_year(Integer stay_year) {
		this.stay_year = stay_year;
	}

	@Column(name="stay_month")
	public Integer getStay_month() {
		return stay_month;
	}
	public void setStay_month(Integer stay_month) {
		this.stay_month = stay_month;
	}
    @JoinColumn(name="now_stay_city")
	@OneToOne(fetch = FetchType.LAZY)
	public City getNow_stay_city() {
		return now_stay_city;
	}
	public void setNow_stay_city(City now_stay_city) {
		this.now_stay_city = now_stay_city;
	}
    

    @JoinColumn(name="now_stay_district")
	@OneToOne(fetch = FetchType.LAZY)
	public District getNowStayDistrict() {
		return nowStayDistrict;
	}
	public void setNowStayDistrict(District nowStayDistrict) {
		this.nowStayDistrict = nowStayDistrict;
	}

	public String getNow_stay_address() {
		return now_stay_address;
	}
	public void setNow_stay_address(String now_stay_address) {
		this.now_stay_address = now_stay_address;
	}
	public String getWork_unit() {
		return work_unit;
	}
	public void setWork_unit(String work_unit) {
		this.work_unit = work_unit;
	}

    @JoinColumn(name="postpartum_cultivation_city_id")
	@OneToOne(fetch = FetchType.LAZY)
	public City getPostpartum_cultivation_city_id() {
		return postpartum_cultivation_city_id;
	}
	public void setPostpartum_cultivation_city_id(
			City postpartum_cultivation_city_id) {
		this.postpartum_cultivation_city_id = postpartum_cultivation_city_id;
	}

    @JoinColumn(name="postpartum_cultivation_district_id")
	@OneToOne(fetch = FetchType.LAZY)
	public District getPostpartumCultivationDistrictId() {
		return postpartumCultivationDistrictId;
	}
	public void setPostpartumCultivationDistrictId(
			District postpartumCultivationDistrictId) {
		this.postpartumCultivationDistrictId = postpartumCultivationDistrictId;
	}
	public String getPostpartum_cultivation_address() {
		return postpartum_cultivation_address;
	}
	public void setPostpartum_cultivation_address(
			String postpartum_cultivation_address) {
		this.postpartum_cultivation_address = postpartum_cultivation_address;
	}
	public String getContact_phone() {
		return contact_phone;
	}
	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}
	public Integer getMenarche() {
		return menarche;
	}
	public void setMenarche(Integer menarche) {
		this.menarche = menarche;
	}
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	public Integer getMenstruation() {
		return menstruation;
	}
	public void setMenstruation(Integer menstruation) {
		this.menstruation = menstruation;
	}

    @JoinColumn(name="past_history_id")
	@OneToOne(fetch = FetchType.LAZY)
	public UserPastHistory getPastHistoryId() {
		return pastHistoryId;
	}
	public void setPastHistoryId(UserPastHistory pastHistoryId) {
		this.pastHistoryId = pastHistoryId;
	}

    @JoinColumn(name="genetic_history_id")
	@OneToOne(fetch = FetchType.LAZY)
	public UserGeneticHistory getGeneticHistoryId() {
		return geneticHistoryId;
	}
	public void setGeneticHistoryId(UserGeneticHistory geneticHistoryId) {
		this.geneticHistoryId = geneticHistoryId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Column(name="add_time")
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
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