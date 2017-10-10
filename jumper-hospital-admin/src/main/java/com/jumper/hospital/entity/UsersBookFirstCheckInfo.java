package com.jumper.hospital.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_book_first_check_info")
public class UsersBookFirstCheckInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private UserInfo userInfo;
    private Date last_menstruation_time;
    private Integer first_check_week;
    private Integer	pregnant_times ;
    private Integer	check_times;
    private Integer marriage_year;
    private Integer now_boys;
    private Integer now_girls;
    private UserMaternalHistory maternal;
	private Integer early_pregnancy_reaction_time;
	private Integer vaginal_bleeding_time;
	private Integer fetal_move_begin_week;
	private String early_pregnancy_medication;
	private String physics_harmful_substances;
	private String chemical_substances;
	private Integer physical_burden;//体力负担:0:轻；1：一般；2重
	private Double pregregnancy_weight;
	private Double current_weight;
	private	String blood_press;
	private Integer height;
    private Integer smoke;
    private Integer drinking;
    private UserHospitalPregnantArchives userHospitalPregnantArchives;

	public UsersBookFirstCheckInfo() {
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
	public Date getLast_menstruation_time() {
		return last_menstruation_time;
	}
	public void setLast_menstruation_time(Date last_menstruation_time) {
		this.last_menstruation_time = last_menstruation_time;
	}
	public Integer getFirst_check_week() {
		return first_check_week;
	}
	public void setFirst_check_week(Integer first_check_week) {
		this.first_check_week = first_check_week;
	}
	public Integer getPregnant_times() {
		return pregnant_times;
	}
	public void setPregnant_times(Integer pregnant_times) {
		this.pregnant_times = pregnant_times;
	}
	public Integer getCheck_times() {
		return check_times;
	}
	public void setCheck_times(Integer check_times) {
		this.check_times = check_times;
	}
	public Integer getMarriage_year() {
		return marriage_year;
	}
	public void setMarriage_year(Integer marriage_year) {
		this.marriage_year = marriage_year;
	}
	public Integer getNow_boys() {
		return now_boys;
	}
	public void setNow_boys(Integer now_boys) {
		this.now_boys = now_boys;
	}
	public Integer getNow_girls() {
		return now_girls;
	}
	public void setNow_girls(Integer now_girls) {
		this.now_girls = now_girls;
	}

	@JoinColumn(name="maternal_history_id")
	@OneToOne(fetch = FetchType.LAZY)
	public UserMaternalHistory getMaternal() {
		return maternal;
	}
	public void setMaternal(UserMaternalHistory maternal) {
		this.maternal = maternal;
	}
	public Integer getEarly_pregnancy_reaction_time() {
		return early_pregnancy_reaction_time;
	}
	public void setEarly_pregnancy_reaction_time(
			Integer early_pregnancy_reaction_time) {
		this.early_pregnancy_reaction_time = early_pregnancy_reaction_time;
	}
	public Integer getVaginal_bleeding_time() {
		return vaginal_bleeding_time;
	}
	public void setVaginal_bleeding_time(Integer vaginal_bleeding_time) {
		this.vaginal_bleeding_time = vaginal_bleeding_time;
	}
	public Integer getFetal_move_begin_week() {
		return fetal_move_begin_week;
	}
	public void setFetal_move_begin_week(Integer fetal_move_begin_week) {
		this.fetal_move_begin_week = fetal_move_begin_week;
	}
	public String getEarly_pregnancy_medication() {
		return early_pregnancy_medication;
	}
	public void setEarly_pregnancy_medication(String early_pregnancy_medication) {
		this.early_pregnancy_medication = early_pregnancy_medication;
	}
	public String getPhysics_harmful_substances() {
		return physics_harmful_substances;
	}
	public void setPhysics_harmful_substances(String physics_harmful_substances) {
		this.physics_harmful_substances = physics_harmful_substances;
	}
	public String getChemical_substances() {
		return chemical_substances;
	}
	public void setChemical_substances(String chemical_substances) {
		this.chemical_substances = chemical_substances;
	}
	public Integer getPhysical_burden() {
		return physical_burden;
	}
	public void setPhysical_burden(Integer physical_burden) {
		this.physical_burden = physical_burden;
	}

	public Double getPregregnancy_weight() {
		return pregregnancy_weight;
	}
	public void setPregregnancy_weight(Double pregregnancy_weight) {
		this.pregregnancy_weight = pregregnancy_weight;
	}
	public Double getCurrent_weight() {
		return current_weight;
	}
	public void setCurrent_weight(Double current_weight) {
		this.current_weight = current_weight;
	}
	public String getBlood_press() {
		return blood_press;
	}
	public void setBlood_press(String blood_press) {
		this.blood_press = blood_press;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
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