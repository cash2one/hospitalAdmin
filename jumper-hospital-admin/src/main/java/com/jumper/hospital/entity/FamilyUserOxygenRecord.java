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
 * FamilyUserOxygenRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "family_user_oxygen_record", catalog = "jumper_anglesound")
public class FamilyUserOxygenRecord implements java.io.Serializable {

	// Fields

	@Transient
	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotNull
	private Integer familyUserId;// 用户id
	@NotNull @NotBlank @NotEmpty
	private String oxygenFile;//血氧记录文件
	@NotNull @NotBlank @NotEmpty
	private String pulseFile;//心率记录文件
	@NotNull
	private Double averageOxygen;//平均血氧
	@NotNull
	private Integer averagePulse;//平均心率
	@NotNull
	private Integer testTimeLength;//测试时长(秒/s)
	private Date testTime;//用户测试时间
	private Date addTime;//添加时间
	private int oxygeResultState;//血氧  结果状态 0偏低  1 正常   2 偏高oxyge_result_state    oxygeResultState
	private int pulseResultState;//心率  结果状态 0偏低  1 正常   2 偏高
	
	private Double pi;
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
	public FamilyUserOxygenRecord() {
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

	@Column(name = "family_user_id")
	public Integer getFamilyUserId() {
		return this.familyUserId;
	}

	public void setFamilyUserId(Integer familyUserId) {
		this.familyUserId = familyUserId;
	}

	@Column(name = "oxygen_file", length = 200)
	public String getOxygenFile() {
		return this.oxygenFile;
	}

	public void setOxygenFile(String oxygenFile) {
		this.oxygenFile = oxygenFile;
	}

	@Column(name = "pulse_file", length = 200)
	public String getPulseFile() {
		return this.pulseFile;
	}

	public void setPulseFile(String pulseFile) {
		this.pulseFile = pulseFile;
	}

	@Column(name = "average_oxygen", precision = 22, scale = 0)
	public Double getAverageOxygen() {
		return this.averageOxygen;
	}

	public void setAverageOxygen(Double averageOxygen) {
		this.averageOxygen = averageOxygen;
	}

	@Column(name = "average_pulse")
	public Integer getAveragePulse() {
		return this.averagePulse;
	}

	public void setAveragePulse(Integer averagePulse) {
		this.averagePulse = averagePulse;
	}

	@Column(name = "test_time_length")
	public Integer getTestTimeLength() {
		return this.testTimeLength;
	}

	public void setTestTimeLength(Integer testTimeLength) {
		this.testTimeLength = testTimeLength;
	}
 

	@Column(name = "pi", precision = 22, scale = 0)
	public Double getPi() {
		return this.pi;
	}

	public void setPi(Double pi) {
		this.pi = pi;
	}


	public Date getTestTime() {
		return testTime;
	}


	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}


	public Date getAddTime() {
		return addTime;
	}


	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int getPulseResultState() {
		return pulseResultState;
	}
	public void setPulseResultState(int pulseResultState) {
		this.pulseResultState = pulseResultState;
	}
	public int getOxygeResultState() {
		return oxygeResultState;
	}
	public void setOxygeResultState(int oxygeResultState) {
		this.oxygeResultState = oxygeResultState;
	}
	 


}