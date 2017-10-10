package com.jumper.hospital.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * 尿液监测数据实体类
 * @author admin
 * 2016-8-8
 */
@Entity
@Table(name="user_urine_record")
public class UserUrineRecord extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	/** 用户信息 */
	private UserInfo userInfo;
	/** 记录添加时间 */
	private Timestamp add_time;
	/** 白细胞 */
	private String leu;
	/** 亚硝酸盐 */
	private String nit;
	/** 尿胆 */
	private String ubg;
	/** 尿蛋 */
	private String pro;
	/** PH */
	private String ph;
	/** 潜血 */
	private String bld;
	/** 尿比 */
	private String sg;
	/** 胴体 */
	private String ket;
	/** 胆红 */
	private String bil;
	/** 尿糖 */
	private String glu;
	/** 维生素C */
	private String vc;
	private String urineCode;
	/** 一日三餐类型 1早餐 2中餐 3 晚餐 */
	private Integer threeType;
	/** 更新时间 */
	private Timestamp updateTime;
	/** 是否异常(改为前端判断 0:正常，1异常)*/
	private Integer isException;
	
	private String pregnant;//监测孕周 （方便前端显示，加的字段）

	private Integer size;//当天测量次数（方便前端显示，加的字段）
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getLeu() {
		return leu;
	}
	public void setLeu(String leu) {
		this.leu = leu;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getUbg() {
		return ubg;
	}
	public void setUbg(String ubg) {
		this.ubg = ubg;
	}
	public String getPro() {
		return pro;
	}
	public void setPro(String pro) {
		this.pro = pro;
	}
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	public String getBld() {
		return bld;
	}
	public void setBld(String bld) {
		this.bld = bld;
	}
	public String getSg() {
		return sg;
	}
	public void setSg(String sg) {
		this.sg = sg;
	}
	public String getKet() {
		return ket;
	}
	public void setKet(String ket) {
		this.ket = ket;
	}
	public String getBil() {
		return bil;
	}
	public void setBil(String bil) {
		this.bil = bil;
	}
	public String getGlu() {
		return glu;
	}
	public void setGlu(String glu) {
		this.glu = glu;
	}
	public String getVc() {
		return vc;
	}
	public void setVc(String vc) {
		this.vc = vc;
	}
	@Column(name="urine_code")
	public String getUrineCode() {
		return urineCode;
	}
	public void setUrineCode(String urineCode) {
		this.urineCode = urineCode;
	}
	@Column(name="three_type")
	public Integer getThreeType() {
		return threeType;
	}
	public void setThreeType(Integer threeType) {
		this.threeType = threeType;
	}
	@Column(name="update_time")
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
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
	@Transient
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Timestamp getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Timestamp add_time) {
		this.add_time = add_time;
	}
	
	
}
