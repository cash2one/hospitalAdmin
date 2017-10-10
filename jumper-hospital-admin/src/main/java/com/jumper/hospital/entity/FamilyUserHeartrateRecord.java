package com.jumper.hospital.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

/**
 * 
 * FamilyUserHeartrateRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "family_user_heartrate_record", catalog = "jumper_anglesound")
public class FamilyUserHeartrateRecord implements java.io.Serializable {
	@Transient
	private static final long serialVersionUID = -873092035934811708L;
	private Integer id;
	@NotNull
	private Integer familyUserId;//用户ID
	private String fetalMoveFiles;//胎动详细记录(POST传值) 
	@NotNull
	private String recordFiles;//json文件地址
	private Date addTime;//测试时间
	private Date appTestTime;//测试时间
	@NotNull @NotBlank @NotEmpty
	private String rawFiles;// raw  wav MP3 文件地址
	@NotNull @NotBlank @NotEmpty
	private String title;//记录文件的名称
	@NotNull
	private Integer type;//测试模式
	@NotNull
	private Double averageRate;//平均心率
	@NotNull @NotBlank @NotEmpty
	private String fetalMoveValue;//上传的胎动数据
	@NotNull
	private Integer fetalMoveTimes; //胎动次数
	@NotNull
	private Integer testTime; /** 胎心测试时长(单位：秒) **/
	
	private int resultState;//结果  0偏低 1 正常 2 偏高
	 

	
	private String flag;
    @Transient
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

	// Constructors

	/** default constructor */
	public FamilyUserHeartrateRecord() {
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

	@Column(name = "family_user_id", nullable = false)
	public Integer getFamilyUserId() {
		return this.familyUserId;
	}

	public void setFamilyUserId(Integer familyUserId) {
		this.familyUserId = familyUserId;
	}


	@Column(name = "fetal_move_files", length = 500)
	public String getFetalMoveFiles() {
		return this.fetalMoveFiles;
	}

	public void setFetalMoveFiles(String fetalMoveFiles) {
		this.fetalMoveFiles = fetalMoveFiles;
	}

	@Column(name = "record_files", length = 500)
	public String getRecordFiles() {
		return this.recordFiles;
	}

	public void setRecordFiles(String recordFiles) {
		this.recordFiles = recordFiles;
	}


	@Column(name = "raw_files", length = 500)
	public String getRawFiles() {
		return this.rawFiles;
	}

	public void setRawFiles(String rawFiles) {
		this.rawFiles = rawFiles;
	}

	@Column(name = "title", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "average_rate", precision = 22, scale = 0)
	public Double getAverageRate() {
		return this.averageRate;
	}

	public void setAverageRate(Double averageRate) {
		this.averageRate = averageRate;
	}

	@Column(name = "fetal_move_value", length = 65535)
	public String getFetalMoveValue() {
		return this.fetalMoveValue;
	}

	public void setFetalMoveValue(String fetalMoveValue) {
		this.fetalMoveValue = fetalMoveValue;
	}

	@Column(name = "fetal_move_times")
	public Integer getFetalMoveTimes() {
		return this.fetalMoveTimes;
	}

	public void setFetalMoveTimes(Integer fetalMoveTimes) {
		this.fetalMoveTimes = fetalMoveTimes;
	}

	@Column(name = "test_time")
	public Integer getTestTime() {
		return this.testTime;
	}

	public void setTestTime(Integer testTime) {
		this.testTime = testTime;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getAppTestTime() {
		return appTestTime;
	}

	public void setAppTestTime(Date appTestTime) {
		this.appTestTime = appTestTime;
	}
	public int getResultState() {
		return resultState;
	}
	public void setResultState(int resultState) {
		this.resultState = resultState;
	}

}