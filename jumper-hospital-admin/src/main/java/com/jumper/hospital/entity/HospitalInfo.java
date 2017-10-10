package com.jumper.hospital.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jumper.hospital.enums.PrintType;

@Entity
@Table(name="hospital_info")
public class HospitalInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private PrintType printType;
	
	private City city;   
	
	private Province province;
	
	private String name;
	
	private String introduction;
	
	private String imgUrl;
	
	private String address;
	
	private String orderKey;
	
	private Timestamp addTime;
	
	private Integer level;
	
	private String phone;
	
	private HospitalInfo hospitalInfo;
	
	//是否有效
	private Integer isValid;
	
	//排序方式:按照位置排序
	private Integer orderBy;
	
	//是否开通远程监控
	private Integer isRemote;
	
	//是否开通医院问诊
	private Integer isConsultant;
	/** 是否开通体重管理，true : 1,false : 0 **/
	private Boolean isWeight;
	/** 是否开通血糖管理， true : 1,false : 0 **/
	private Boolean isBlood;
	/** 是否开通线下课程，true : 1,false : 0 **/
	private Boolean isSchool;
	/** 是否开通课堂管理，true : 1,false : 0 **/
	private Boolean isClass;
	/** 是否开通孕妇学校视频课程 **/
	private Boolean isVideo;
	/** 是否开通线上支付 **/
	private Boolean isPayment;
	/**是否开通网络诊室**/
	private Boolean isNetwork;
	/**是否屏蔽用户手机号码 **/
	//private Boolean isMobile;
	/**是否是独立医院  0:不是    1:是独立医院 ；*/
	private Integer isAutonomy;
	/**是否开通设备租赁服务**/
	private Boolean isLease;
	private String hospitalKey;//md5医院编码(8到17位)
	private Set<HospitalInfo> childrens = new HashSet<HospitalInfo>();
	
	
	/*是否允许该医院的医生开通胎心判读  1:是 0:否*/
	private Boolean isDoctorNst;

	@JoinColumn(name="city_id")
	@OneToOne(fetch = FetchType.EAGER)
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@JoinColumn(name="province_id")
	@OneToOne(fetch = FetchType.EAGER)
	public Province getProvince() {
		return this.province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	@Column(name="img_url")
	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="add_time")
	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	@Column(name="order_key")
	public String getOrderKey() {
		return orderKey;
	}

	public void setOrderKey(String orderKey) {
		this.orderKey = orderKey;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="parent_id", columnDefinition = "INT default 0")
	public HospitalInfo getHospitalInfo() {
		return hospitalInfo;
	}

	public void setHospitalInfo(HospitalInfo hospitalInfo) {
		this.hospitalInfo = hospitalInfo;
	}

	@OneToMany(mappedBy = "hospitalInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<HospitalInfo> getChildrens() {
		return childrens;
	}

	public void setChildrens(Set<HospitalInfo> childrens) {
		this.childrens = childrens;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name="is_valid")
	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	@Column(name="order_by")
	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	@Column(name="is_remote")
	public Integer getIsRemote() {
		return isRemote;
	}

	public void setIsRemote(Integer isRemote) {
		this.isRemote = isRemote;
	}
	@Column(name="is_consultant")
	public Integer getIsConsultant() {
		return isConsultant;
	}

	public void setIsConsultant(Integer isConsultant) {
		this.isConsultant = isConsultant;
	}

	public Boolean getIsWeight() {
		return isWeight;
	}

	public void setIsWeight(Boolean isWeight) {
		this.isWeight = isWeight;
	}
	public Boolean getIsSchool() {
		return isSchool;
	}
	public void setIsSchool(Boolean isSchool) {
		this.isSchool = isSchool;
	}

	public Boolean getIsBlood() {
		return isBlood;
	}

	public void setIsBlood(Boolean isBlood) {
		this.isBlood = isBlood;
	}

	public Boolean getIsClass() {
		return isClass;
	}

	public void setIsClass(Boolean isClass) {
		this.isClass = isClass;
	}

	public Boolean getIsVideo() {
		return isVideo;
	}

	public void setIsVideo(Boolean isVideo) {
		this.isVideo = isVideo;
	}
	/**
	* isNetwork
	*是否开通网络诊室
	* @return  the isNetwork true : 1,false : 0
	*/
	
	public Boolean getIsNetwork() {
		return isNetwork;
	}

	/*
	* @param isNetwork 是否开通网络诊室
	*/
	public void setIsNetwork(Boolean isNetwork) {
		this.isNetwork = isNetwork;
	}
	public Boolean getIsPayment() {
		return isPayment;
	}

	public void setIsPayment(Boolean isPayment) {
		this.isPayment = isPayment;
	}

	/*public Boolean getIsMobile() {
		return isMobile;
	}

	public void setIsMobile(Boolean isMobile) {
		this.isMobile = isMobile;
	}*/
	public Integer getIsAutonomy() {
		return isAutonomy;
	}

	public void setIsAutonomy(Integer isAutonomy) {
		this.isAutonomy = isAutonomy;
	}

	public Boolean getIsLease() {
		return isLease;
	}

	public void setIsLease(Boolean isLease) {
		this.isLease = isLease;
	}

	@Column(name="hospital_key")
	public String getHospitalKey() {
		return hospitalKey;
	}

	public void setHospitalKey(String hospitalKey) {
		this.hospitalKey = hospitalKey;
	}

	public Boolean getIsDoctorNst() {
		return isDoctorNst;
	}

	public void setIsDoctorNst(Boolean isDoctorNst) {
		this.isDoctorNst = isDoctorNst;
	}
	
	public PrintType getPrintType() {
		return printType;
	}

	public void setPrintType(PrintType printType) {
		this.printType = printType;
	}
}