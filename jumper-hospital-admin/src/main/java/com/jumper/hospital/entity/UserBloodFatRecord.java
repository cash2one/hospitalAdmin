package com.jumper.hospital.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 血脂
 * @author admin
 * 2016-8-6
 */
@Entity
@Table(name="user_blood_fat_record")
public class UserBloodFatRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 测试用户 **/
	private UserInfo userInfo;
	/** 高密度脂蛋白 */
	private String hdl;
	/** 胆固醇 */
	private String chol;
	/** 甘油三酯 */
	private String trig;
	/** 低密度脂蛋白 */
	private String ldl;
	/**胆固醇-高密度脂蛋白*/
	private String cholHdl;
	/** 记录添加时间 */
	private Timestamp add_time;
	/** 是否异常(改为前端判断 0:正常，1异常)*/
	private Integer isException;
	private String pregnant;//监测孕周（方便前端页面展示，所加字段）

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Column(name="hdl")
	public String getHdl() {
		return hdl;
	}

	public void setHdl(String hdl) {
		this.hdl = hdl;
	}

	@Column(name="chol")
	public String getChol() {
		return chol;
	}

	public void setChol(String chol) {
		this.chol = chol;
	}

	@Column(name="trig")
	public String getTrig() {
		return trig;
	}

	public void setTrig(String trig) {
		this.trig = trig;
	}

	@Column(name="ldl")
	public String getLdl() {
		return ldl;
	}

	public void setLdl(String ldl) {
		this.ldl = ldl;
	}

	@Column(name="chol_hdl")
	public String getCholHdl() {
		return cholHdl;
	}

	public void setCholHdl(String cholHdl) {
		this.cholHdl = cholHdl;
	}

	@Column(name="add_time")
	public Timestamp getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Timestamp add_time) {
		this.add_time = add_time;
	}
	
	@Column(name="is_exception")
	public Integer getIsException() {
		return isException;
	}

	

	public void setIsException(Integer isException) {
		this.isException = isException;
	}

	@Transient
	public String getPregnant() {
		return pregnant;
	}

	public void setPregnant(String pregnant) {
		this.pregnant = pregnant;
	}

}
