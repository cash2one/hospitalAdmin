/*
 * @文件名： FamilyUserinfo.java
 * @创建人: aaron
 * @创建时间: 2016-4-5
 * @包名： com.jumper.hospital.vo.familyDoctor
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.vo.familyDoctor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jumper.hospital.entity.FamilyDoctorinfo;

/**
 * 类名称： 
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-4-5 上午10:13:32
 * 修改人：aaron
 * 修改时间：2016-4-5 上午10:13:32
 * 修改备注：
 * @version  1.0
 */
public class VOFamilyExaminationResult implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userid;//用户id
	
	private String img="";
	private String name="";
	private short age=0;
	private String mobile="";
	private String linkMan="";
	private String linkTel="";
	private String identity="";
	private Date lastPeriod;
	private Short height=0;
	private Double weight=0.0;
	private Short pregnantWeek=0;
	private Date pregancyDay;
	private Integer provinceId=0;
	private Integer cityId=0;
	private Integer districtId;
	private String joinAdd="";
	private String detailAdd="";
	private Integer familyDoctorId=0;
	private Integer familyHospitalId=0;
	private Date regTime;
	private Date lastTime;
	/*private Integer nextArrangedId=0;*/
	private String splicingAddress="";
	private  String nextArrangedTime="";
	private Integer gestationalWeek;
	private String  yunzhou;//字符串的几周几天
	private String heartStartTime;//胎心开始检测时间
	private String heartEndTime;//胎心结束检测时间
	private String timeStrBySeconds;//胎心的测试时长转为几小时几分 几秒
	private FamilyDoctorinfo familyDoctorinfo;// 家庭医生
	
	//------文件相关数据----------
	private String recordData;//胎心文件数据
	private String recordFiles;//胎心json文件地址
	private String rawFiles;// raw  wav MP3 文件地址
	private String fetalMoveValue;//上传的胎动数据
	//----------------------------------------------------
	private List<MonitoringResults> list;// 封装展示到页面的结果
	//---------------报告单相关------------------------
	private Integer examinationId;//报告单的id
	private Date checkTime;//报告单的addTime 就是检测时间
	private String reason;//生成报告单是医生填写的备注信息
	private String sysTime;
	private String username;//登陆的医生姓名
	
	
	 
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSysTime() {
		return sysTime;
	}
	public void setSysTime(String sysTime) {
		this.sysTime = sysTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public short getAge() {
		return age;
	}
	public void setAge(short age) {
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getLinkTel() {
		return linkTel;
	}
	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public Date getLastPeriod() {
		return lastPeriod;
	}
	public void setLastPeriod(Date lastPeriod) {
		this.lastPeriod = lastPeriod;
	}
	public Short getHeight() {
		return height;
	}
	public void setHeight(Short height) {
		this.height = height;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Short getPregnantWeek() {
		return pregnantWeek;
	}
	public void setPregnantWeek(Short pregnantWeek) {
		this.pregnantWeek = pregnantWeek;
	}
	public Date getPregancyDay() {
		return pregancyDay;
	}
	public void setPregancyDay(Date pregancyDay) {
		this.pregancyDay = pregancyDay;
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
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public String getJoinAdd() {
		return joinAdd;
	}
	public void setJoinAdd(String joinAdd) {
		this.joinAdd = joinAdd;
	}
	public String getDetailAdd() {
		return detailAdd;
	}
	public void setDetailAdd(String detailAdd) {
		this.detailAdd = detailAdd;
	}
	public Integer getFamilyDoctorId() {
		return familyDoctorId;
	}
	public void setFamilyDoctorId(Integer familyDoctorId) {
		this.familyDoctorId = familyDoctorId;
	}
	public Integer getFamilyHospitalId() {
		return familyHospitalId;
	}
	public void setFamilyHospitalId(Integer familyHospitalId) {
		this.familyHospitalId = familyHospitalId;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getSplicingAddress() {
		return splicingAddress;
	}
	public void setSplicingAddress(String splicingAddress) {
		this.splicingAddress = splicingAddress;
	}
	public String getNextArrangedTime() {
		return nextArrangedTime;
	}
	public void setNextArrangedTime(String nextArrangedTime) {
		this.nextArrangedTime = nextArrangedTime;
	}
	public Integer getGestationalWeek() {
		return gestationalWeek;
	}
	public void setGestationalWeek(Integer gestationalWeek) {
		this.gestationalWeek = gestationalWeek;
	}
	public String getRecordData() {
		return recordData;
	}
	public void setRecordData(String recordData) {
		this.recordData = recordData;
	}
	public String getRecordFiles() {
		return recordFiles;
	}
	public void setRecordFiles(String recordFiles) {
		this.recordFiles = recordFiles;
	}
	public String getRawFiles() {
		return rawFiles;
	}
	public void setRawFiles(String rawFiles) {
		this.rawFiles = rawFiles;
	}
	public String getFetalMoveValue() {
		return fetalMoveValue;
	}
	public void setFetalMoveValue(String fetalMoveValue) {
		this.fetalMoveValue = fetalMoveValue;
	}
	public Integer getExaminationId() {
		return examinationId;
	}
	public void setExaminationId(Integer examinationId) {
		this.examinationId = examinationId;
	}
	public List<MonitoringResults> getList() {
		return list;
	}
	public void setList(List<MonitoringResults> list) {
		this.list = list;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public String getHeartStartTime() {
		return heartStartTime;
	}
	public void setHeartStartTime(String heartStartTime) {
		this.heartStartTime = heartStartTime;
	}
	public String getHeartEndTime() {
		return heartEndTime;
	}
	public void setHeartEndTime(String heartEndTime) {
		this.heartEndTime = heartEndTime;
	}
	public String getTimeStrBySeconds() {
		return timeStrBySeconds;
	}
	public void setTimeStrBySeconds(String timeStrBySeconds) {
		this.timeStrBySeconds = timeStrBySeconds;
	}
	public FamilyDoctorinfo getFamilyDoctorinfo() {
		return familyDoctorinfo;
	}
	public void setFamilyDoctorinfo(FamilyDoctorinfo familyDoctorinfo) {
		this.familyDoctorinfo = familyDoctorinfo;
	}
	public String getYunzhou() {
		return yunzhou;
	}
	public void setYunzhou(String yunzhou) {
		this.yunzhou = yunzhou;
	}
    
    
}
