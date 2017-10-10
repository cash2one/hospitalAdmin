package com.jumper.hospital.entity;

import java.io.Serializable;
import java.util.Date;

public class PregnantArchiveInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String archivesNum;
	private String type;
	private Integer communityId;
	private Integer hospitalId;
	private String healthCode;
	private String socialSecurityCode;
	private Integer memberId;
	private Integer archiveId;
	private Integer day;
	/** 用户ID */
	private Integer userId;
	/** 孕妇名字 */
	private String name;
	/** 出生日期 */
	private String birthday;
	/** 职业 */
	private String job;
	/** 民族 */
	private Integer nationId;
	/** 文化程度 */
	private Integer educationId;
	/** 证件类型：1：身份证；2：护照；3：军官照；4：港澳居民通行证 */
	private Integer idType;
	/** 证件号 */
	private String idNo;
	/** 手机号码 */
	private String mobile;
	/** 籍贯省份 */
	private Integer provinceId;
	/** 籍贯城市 */
	private Integer cityId;
	/** 户籍类型:1:常住；2：暂住；3：流动户籍 */
	private Integer residentType;
	/** 户籍：默认0；1：城镇户口,2：农村户口，3：其他 */
	private Integer resident;
	/** 来深年数 */
	private Integer stayYear;
	/** 来深月数 */
	private Integer stayMonth;
	/** 现居住省份 */
	private Integer nowStayProvince;
	/** 现居住城市 */
	private Integer nowStayCity;
	/** 现居住地区 */
	private Integer nowStayDistrict;
	/** 现居住地址 */
	private String nowStayAddress;
	/** 工作单位 */
	private String workUnit;
	/** 产后修养城市 */
	private Integer postpartumCultivationCityId;
	/** 产后修养地区 */
	private Integer postpartumCultivationDistrictId;
	/** 后修养地址 */
	private String postpartumCultivationAddress;
	/** 联系电话 */
	private String contactPhone;
	/** 初潮来的岁数 */
	private Integer menarche;
	/** 周期(天数) */
	private Integer week;
	/** 月经量：0：多；1：中；2：少 */
	private Integer menstruation;
	/** 既往史ID */
	private Integer pastHistoryId;
	/** 遗传病史ID */
	private Integer geneticHistoryId;
	/** 添加时间 */
	private String addTime;
	/** 档案表ID */
	private Integer archivesId;
	/** 孕妇年龄 */
	private Integer age;
	/** 国籍id */
	private Integer countryId;
	/** 血型(默认0，1：A，2：B，3：O，4：AB,5：不详，6：其他) */
	private Integer bloodType;
	/** RH血型(默认0，1:阴性，2：阳性，3：不详，4：未查) */
	private Integer rh;
	/** 婚姻状况(默认0，10:已婚，20：未婚，21：初婚，22：再婚，23：复婚 ，30：丧偶，40：离婚，90：未说明的婚姻状况 --国家标准) */
	private Integer maritalStatus;
	/** 结婚日期 */
	private String marryDate;
	/** 出生地id */
	private Integer birthAddressId;
	/** 户主姓名 */
	private String householderName;
	/** 与户主关系（默认为0，1.本人，2：夫妻，3：父子，4：母子，5：其他） */
	private Integer householderRelation;
	/** 紧急联系人姓名 */
	private String emergencyName;
	/** 紧急联系人电话 */
	private String emergencyPhone;
	/** 所属社区id */
	/* private Integer communityId; */
	/** 现住址管辖派出所详细地址 */
	private String nowPoliceStationAddress;
	/** 职业id */
	private Integer professionId;
	/** 与医院关联id */
	private Integer hospitalMappingId;
	/** 修改时间 */
	private Date modifyTime;
	/** 社保卡号 */
	/* private String socialSecurityCode; */
	/** 社保卡编号 */
	private String socialSecurityNum;
	/** 健康卡号 */
	/* private String healthCode; */
	/** 就诊卡号 */
	private String medicalCardNum;
	/** 孕妇头像 */
	private String userImg;
	/** 是否已经是医生审核确认的档案 0：否（预建档），1：是（已建档） */
	private Integer isFinished;
	/** 户籍省份 */
	private Integer domicoleProvince;
	/** 户籍城市 */
	private Integer domicoleCity;
	/** 基本信息完成的百分比（1代表100%） */
	private Double finishPercent;
	/** 档案状态（1：正常，2异常，默认正常） */
	private Integer statue;
	/** 所属医院id */
	private String lastPeriod;
	/** 怀孕状态 */
	private Integer pregnancyState;
	/** 随访状态*/
	private Integer followUpState;
	/** 分娩日期 */
	private String deliveryTime;
	/** 怀孕状态 */
	/*
	 * private Integer hospitalId;
	 *//** 档案编号 */
	/*
	 * private String archivesNum;
	 *//**
	 * 建档类型 1：孕产妇，2：儿童，3：普通人群，4：老年人，5：高血糖，6：高血压，7：重性精神病患者，8：肿瘤病患者，9：残疾 多种用逗号分隔
	 */
	/*
	 * private String type;
	 */
	/** 预产期 */
	private String pregnantDate;
	/** 性别 默认1,0：男，1：女，2：其他 */
	private Integer sex;

	// 孕妇信息
	private String contactWay;// 固定电话
	private String areaCode;
	private String phoneNo;
	
	 //丈夫信息
    private Integer husbandUserId;
    /**孕妇名称*/
    private String husbandName;
    /**出生日期*/
    private String  husbandBirthday;
    /**职业*/
    private String husbandJob;
    /**民族*/
    private Integer husbandNationId;
    /**文化程度*/
    private Integer husbandEducationId;
    /**证件类型：1：身份证；2：护照；3：军官证；4：港澳居民通行证*/
    private Integer husbandIdType;
    /**证件号码*/
    private String husbandIdNo;
    /**籍贯省份*/
    private Integer husbandProvinceId;
    /**籍贯城市*/
    private Integer husbandCityId;
    /**户籍类型:1:深圳户籍；2：暂住；3：流动户籍*/
    private Integer husbandResidentType;
    /** 户籍：默认0；1：城镇户口,2：农村户口，3：其他 */
	private Integer husbandResident;
    /**手机号码*/
    private String husbandMobile;
    /**住宅电话*/
    private String husbandPhone;
    /**工作单位*/
    private String husbandWorkUnit;
    /**国籍*/
    private Integer husbandCountryId;
    /**丈夫年龄*/
    private Integer husbandAge;
    /**职业id*/
    private Integer husbandProfessionId;
    /**户籍省份*/
    private Integer husbandDomicoleProvince;
    /**户籍城市*/
    private Integer husbandDomicoleCity;
    /**现居住省份*/
    private Integer husbandNowStayProvince;
    /**现居住城市*/
    private Integer husbandNowStayCity;
    /**现居住地区*/
    private Integer husbandNowStayDistrict;
    /**现居住地址*/
    private String husbandNowStayAddress;

    private String startTime;
    private String endTime;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArchivesNum() {
		return archivesNum;
	}

	public void setArchivesNum(String archivesNum) {
		this.archivesNum = archivesNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHealthCode() {
		return healthCode;
	}

	public void setHealthCode(String healthCode) {
		this.healthCode = healthCode;
	}

	public String getSocialSecurityCode() {
		return socialSecurityCode;
	}

	public void setSocialSecurityCode(String socialSecurityCode) {
		this.socialSecurityCode = socialSecurityCode;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getArchiveId() {
		return archiveId;
	}

	public void setArchiveId(Integer archiveId) {
		this.archiveId = archiveId;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getNationId() {
		return nationId;
	}

	public void setNationId(Integer nationId) {
		this.nationId = nationId;
	}

	public Integer getEducationId() {
		return educationId;
	}

	public void setEducationId(Integer educationId) {
		this.educationId = educationId;
	}

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getResidentType() {
		return residentType;
	}

	public void setResidentType(Integer residentType) {
		this.residentType = residentType;
	}

	public Integer getResident() {
		return resident;
	}

	public void setResident(Integer resident) {
		this.resident = resident;
	}

	public Integer getStayYear() {
		return stayYear;
	}

	public void setStayYear(Integer stayYear) {
		this.stayYear = stayYear;
	}

	public Integer getStayMonth() {
		return stayMonth;
	}

	public void setStayMonth(Integer stayMonth) {
		this.stayMonth = stayMonth;
	}

	public Integer getNowStayProvince() {
		return nowStayProvince;
	}

	public void setNowStayProvince(Integer nowStayProvince) {
		this.nowStayProvince = nowStayProvince;
	}

	public Integer getNowStayCity() {
		return nowStayCity;
	}

	public void setNowStayCity(Integer nowStayCity) {
		this.nowStayCity = nowStayCity;
	}

	public Integer getNowStayDistrict() {
		return nowStayDistrict;
	}

	public void setNowStayDistrict(Integer nowStayDistrict) {
		this.nowStayDistrict = nowStayDistrict;
	}

	public String getNowStayAddress() {
		return nowStayAddress;
	}

	public void setNowStayAddress(String nowStayAddress) {
		this.nowStayAddress = nowStayAddress;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public Integer getPostpartumCultivationCityId() {
		return postpartumCultivationCityId;
	}

	public void setPostpartumCultivationCityId(
			Integer postpartumCultivationCityId) {
		this.postpartumCultivationCityId = postpartumCultivationCityId;
	}

	public Integer getPostpartumCultivationDistrictId() {
		return postpartumCultivationDistrictId;
	}

	public void setPostpartumCultivationDistrictId(
			Integer postpartumCultivationDistrictId) {
		this.postpartumCultivationDistrictId = postpartumCultivationDistrictId;
	}

	public String getPostpartumCultivationAddress() {
		return postpartumCultivationAddress;
	}

	public void setPostpartumCultivationAddress(
			String postpartumCultivationAddress) {
		this.postpartumCultivationAddress = postpartumCultivationAddress;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
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

	public Integer getPastHistoryId() {
		return pastHistoryId;
	}

	public void setPastHistoryId(Integer pastHistoryId) {
		this.pastHistoryId = pastHistoryId;
	}

	public Integer getGeneticHistoryId() {
		return geneticHistoryId;
	}

	public void setGeneticHistoryId(Integer geneticHistoryId) {
		this.geneticHistoryId = geneticHistoryId;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getArchivesId() {
		return archivesId;
	}

	public void setArchivesId(Integer archivesId) {
		this.archivesId = archivesId;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getBloodType() {
		return bloodType;
	}

	public void setBloodType(Integer bloodType) {
		this.bloodType = bloodType;
	}

	public Integer getRh() {
		return rh;
	}

	public void setRh(Integer rh) {
		this.rh = rh;
	}

	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMarryDate() {
		return marryDate;
	}

	public void setMarryDate(String marryDate) {
		this.marryDate = marryDate;
	}

	public Integer getBirthAddressId() {
		return birthAddressId;
	}

	public void setBirthAddressId(Integer birthAddressId) {
		this.birthAddressId = birthAddressId;
	}

	public String getHouseholderName() {
		return householderName;
	}

	public void setHouseholderName(String householderName) {
		this.householderName = householderName;
	}

	public Integer getHouseholderRelation() {
		return householderRelation;
	}

	public void setHouseholderRelation(Integer householderRelation) {
		this.householderRelation = householderRelation;
	}

	public String getEmergencyName() {
		return emergencyName;
	}

	public void setEmergencyName(String emergencyName) {
		this.emergencyName = emergencyName;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getNowPoliceStationAddress() {
		return nowPoliceStationAddress;
	}

	public void setNowPoliceStationAddress(String nowPoliceStationAddress) {
		this.nowPoliceStationAddress = nowPoliceStationAddress;
	}

	public Integer getProfessionId() {
		return professionId;
	}

	public void setProfessionId(Integer professionId) {
		this.professionId = professionId;
	}

	public Integer getHospitalMappingId() {
		return hospitalMappingId;
	}

	public void setHospitalMappingId(Integer hospitalMappingId) {
		this.hospitalMappingId = hospitalMappingId;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getSocialSecurityNum() {
		return socialSecurityNum;
	}

	public void setSocialSecurityNum(String socialSecurityNum) {
		this.socialSecurityNum = socialSecurityNum;
	}

	public String getMedicalCardNum() {
		return medicalCardNum;
	}

	public void setMedicalCardNum(String medicalCardNum) {
		this.medicalCardNum = medicalCardNum;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public Integer getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(Integer isFinished) {
		this.isFinished = isFinished;
	}

	public Integer getDomicoleProvince() {
		return domicoleProvince;
	}

	public void setDomicoleProvince(Integer domicoleProvince) {
		this.domicoleProvince = domicoleProvince;
	}

	public Integer getDomicoleCity() {
		return domicoleCity;
	}

	public void setDomicoleCity(Integer domicoleCity) {
		this.domicoleCity = domicoleCity;
	}

	public Double getFinishPercent() {
		return finishPercent;
	}

	public void setFinishPercent(Double finishPercent) {
		this.finishPercent = finishPercent;
	}

	public Integer getStatue() {
		return statue;
	}

	public void setStatue(Integer statue) {
		this.statue = statue;
	}

	public String getPregnantDate() {
		return pregnantDate;
	}

	public void setPregnantDate(String pregnantDate) {
		this.pregnantDate = pregnantDate;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getHusbandUserId() {
		return husbandUserId;
	}

	public void setHusbandUserId(Integer husbandUserId) {
		this.husbandUserId = husbandUserId;
	}

	public String getHusbandName() {
		return husbandName;
	}

	public void setHusbandName(String husbandName) {
		this.husbandName = husbandName;
	}


	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHusbandBirthday() {
		return husbandBirthday;
	}

	public void setHusbandBirthday(String husbandBirthday) {
		this.husbandBirthday = husbandBirthday;
	}

	public String getHusbandJob() {
		return husbandJob;
	}

	public void setHusbandJob(String husbandJob) {
		this.husbandJob = husbandJob;
	}

	public Integer getHusbandNationId() {
		return husbandNationId;
	}

	public void setHusbandNationId(Integer husbandNationId) {
		this.husbandNationId = husbandNationId;
	}

	public Integer getHusbandEducationId() {
		return husbandEducationId;
	}

	public void setHusbandEducationId(Integer husbandEducationId) {
		this.husbandEducationId = husbandEducationId;
	}

	public Integer getHusbandIdType() {
		return husbandIdType;
	}

	public void setHusbandIdType(Integer husbandIdType) {
		this.husbandIdType = husbandIdType;
	}

	public String getHusbandIdNo() {
		return husbandIdNo;
	}

	public void setHusbandIdNo(String husbandIdNo) {
		this.husbandIdNo = husbandIdNo;
	}


	public Integer getHusbandProvinceId() {
		return husbandProvinceId;
	}

	public void setHusbandProvinceId(Integer husbandProvinceId) {
		this.husbandProvinceId = husbandProvinceId;
	}

	public Integer getHusbandCityId() {
		return husbandCityId;
	}

	public void setHusbandCityId(Integer husbandCityId) {
		this.husbandCityId = husbandCityId;
	}

	public Integer getHusbandResidentType() {
		return husbandResidentType;
	}

	public void setHusbandResidentType(Integer husbandResidentType) {
		this.husbandResidentType = husbandResidentType;
	}

	public String getHusbandMobile() {
		return husbandMobile;
	}

	public void setHusbandMobile(String husbandMobile) {
		this.husbandMobile = husbandMobile;
	}

	public String getHusbandPhone() {
		return husbandPhone;
	}

	public void setHusbandPhone(String husbandPhone) {
		this.husbandPhone = husbandPhone;
	}

	public String getHusbandWorkUnit() {
		return husbandWorkUnit;
	}

	public void setHusbandWorkUnit(String husbandWorkUnit) {
		this.husbandWorkUnit = husbandWorkUnit;
	}

	public Integer getHusbandCountryId() {
		return husbandCountryId;
	}

	public void setHusbandCountryId(Integer husbandCountryId) {
		this.husbandCountryId = husbandCountryId;
	}

	public Integer getHusbandAge() {
		return husbandAge;
	}

	public void setHusbandAge(Integer husbandAge) {
		this.husbandAge = husbandAge;
	}

	public Integer getHusbandProfessionId() {
		return husbandProfessionId;
	}

	public void setHusbandProfessionId(Integer husbandProfessionId) {
		this.husbandProfessionId = husbandProfessionId;
	}

	public Integer getHusbandDomicoleProvince() {
		return husbandDomicoleProvince;
	}

	public void setHusbandDomicoleProvince(Integer husbandDomicoleProvince) {
		this.husbandDomicoleProvince = husbandDomicoleProvince;
	}

	public Integer getHusbandDomicoleCity() {
		return husbandDomicoleCity;
	}

	public void setHusbandDomicoleCity(Integer husbandDomicoleCity) {
		this.husbandDomicoleCity = husbandDomicoleCity;
	}

	public Integer getHusbandNowStayProvince() {
		return husbandNowStayProvince;
	}

	public void setHusbandNowStayProvince(Integer husbandNowStayProvince) {
		this.husbandNowStayProvince = husbandNowStayProvince;
	}

	public Integer getHusbandNowStayCity() {
		return husbandNowStayCity;
	}

	public void setHusbandNowStayCity(Integer husbandNowStayCity) {
		this.husbandNowStayCity = husbandNowStayCity;
	}

	public Integer getHusbandNowStayDistrict() {
		return husbandNowStayDistrict;
	}

	public void setHusbandNowStayDistrict(Integer husbandNowStayDistrict) {
		this.husbandNowStayDistrict = husbandNowStayDistrict;
	}

	public String getHusbandNowStayAddress() {
		return husbandNowStayAddress;
	}

	public void setHusbandNowStayAddress(String husbandNowStayAddress) {
		this.husbandNowStayAddress = husbandNowStayAddress;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getHusbandResident() {
		return husbandResident;
	}

	public void setHusbandResident(Integer husbandResident) {
		this.husbandResident = husbandResident;
	}

	public String getLastPeriod() {
		return lastPeriod;
	}

	public void setLastPeriod(String lastPeriod) {
		this.lastPeriod = lastPeriod;
	}

	public Integer getPregnancyState() {
		return pregnancyState;
	}

	public void setPregnancyState(Integer pregnancyState) {
		this.pregnancyState = pregnancyState;
	}

	public Integer getFollowUpState() {
		return followUpState;
	}

	public void setFollowUpState(Integer followUpState) {
		this.followUpState = followUpState;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
