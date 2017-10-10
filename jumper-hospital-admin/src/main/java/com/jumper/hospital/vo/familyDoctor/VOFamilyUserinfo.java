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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
public class VOFamilyUserinfo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String img="";
	private String name="";
	private Short age=0;
	private String mobile="";
	private String linkMan="";
	private String linkTel="";
	private String identity="";
	private String lastPeriod;
	private Short height=0;
	private Double weight=0.0;
	private Short pregnantWeek=0;
	private String pregancyDay;
	private Integer provinceId=0;
	private Integer cityId=0;
	private Integer districtId;
	private String joinAdd="";
	private String detailAdd="";
	private Integer familyDoctorId=0;
	private Integer familyHospitalId=0;
	private String regTime;
	private String lastTime;
	/*private Integer nextArrangedId=0;*/
	private String splicingAddress="";
	private  String nextArrangedTime="";
	private Integer gestationalWeek;
	
	private List<MonitoringResults> list;// 封装展示到页面的结果
	
	

	private Date checkTime;
	
   public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		 this.checkTime=checkTime;
		 
	}

	/* public Integer getNextArrangedId() {
		return nextArrangedId;
	}
	public void setNextArrangedId(Integer nextArrangedId) {
		this.nextArrangedId = nextArrangedId;
	}*/
	//存储排期明细的list
    private List<VOFamilyExaminationArranged> task;

public VOFamilyUserinfo() {
	}

public VOFamilyUserinfo(Integer id, String img, String name, Short age,
		String mobile, String identity, Date lastPeriod, Date pregancyDay,
		Integer provinceId, Integer cityId, String detailAdd,
		Integer familyDoctorId, Integer familyHospitalId, Date regTime,
		Date lastTime ) {
	super();
	this.id = id;
	this.img = img;
	this.name = name;
	this.age = age;
	this.mobile = mobile;
	this.identity = identity;
	this.setLastPeriod(lastPeriod);
	this.setPregancyDay(pregancyDay);
	this.provinceId = provinceId;
	this.cityId = cityId;
	this.detailAdd = detailAdd;
	this.familyDoctorId = familyDoctorId;
	this.familyHospitalId = familyHospitalId;
	this.setRegTime(regTime);
	this.setLastTime(lastTime);
}


public VOFamilyUserinfo(String name,short age, String mobile,Date lastPeriod,Integer id,String joinAdd, Date pregancyDay){
	  this.name = name;
	    this.age = age;
	    this.setLastPeriod(lastPeriod);
	    this.mobile = mobile;
	    this.id = id;
	    this.setPregancyDay(pregancyDay);
	    this.joinAdd=joinAdd;
}

public VOFamilyUserinfo(String name,Integer id, String mobile,short age, Date lastPeriod, String splicingAddress)
{
    this.id = id;
    this.name = name;
    this.age = age;
    this.setLastPeriod(lastPeriod);
    this.mobile = mobile;
    this.splicingAddress = splicingAddress;
}
public VOFamilyUserinfo(String name,Integer id, String mobile,short age,  Date lastPeriod,Date pregancyDay)
{
	this.id = id;
	this.name = name;
	this.age = age;
	/*this.lastPeriod=new SimpleDateFormat("yyyy-MM-dd").format(lastPeriod);*/
	this.setLastPeriod(lastPeriod);
	this.mobile = mobile;
	/*this.pregancyDay=pregancyDay;*/
	this.setPregancyDay(pregancyDay);
}
 




public List<VOFamilyExaminationArranged> getTask() {
	return task;
}




public void setTask(List<VOFamilyExaminationArranged> task) {
	this.task = task;
}




public Integer getId()
{
    return id;
}




public void setId(Integer id)
{
    this.id = id;
}




public String getImg()
{
    return img;
}




public void setImg(String img)
{
    this.img = img;
}




public String getName()
{
    return name;
}




public void setName(String name)
{
    this.name = name;
}




public Short getAge()
{
    return age;
}




public void setAge(Short age)
{
    this.age = age;
}




public String getMobile()
{
    return mobile;
}




public void setMobile(String mobile)
{
    this.mobile = mobile;
}




public String getLinkMan()
{
    return linkMan;
}




public void setLinkMan(String linkMan)
{
    this.linkMan = linkMan;
}




public String getLinkTel()
{
    return linkTel;
}




public void setLinkTel(String linkTel)
{
    this.linkTel = linkTel;
}




public String getIdentity()
{
    return identity;
}




public void setIdentity(String identity)
{
    this.identity = identity;
}




public String getLastPeriod()
{
    return lastPeriod;
}




public void setLastPeriod(Date lastPeriod)
{
    this.lastPeriod =Date2String(lastPeriod,"yyyy-MM-dd");
}




public Short getHeight()
{
    return height;
}




public void setHeight(Short height)
{
    this.height = height;
}




public Double getWeight()
{
    return weight;
}




public void setWeight(Double weight)
{
    this.weight = weight;
}




public Short getPregnantWeek()
{
    return pregnantWeek;
}




public void setPregnantWeek(Short pregnantWeek)
{
    this.pregnantWeek = pregnantWeek;
}




public String getPregancyDay()
{
    return pregancyDay;
}




public void setPregancyDay(Date pregancyDay)
{
    this.pregancyDay = Date2String(pregancyDay,"yyyy-MM-dd");
}




public Integer getProvinceId()
{
    return provinceId;
}




public void setProvinceId(Integer provinceId)
{
    this.provinceId = provinceId;
}




public Integer getCityId()
{
    return cityId;
}




public void setCityId(Integer cityId)
{
    this.cityId = cityId;
}




public Integer getDistrictId()
{
    return districtId;
}




public void setDistrictId(Integer districtId)
{
    this.districtId = districtId;
}




public String getJoinAdd()
{
    return joinAdd;
}




public void setJoinAdd(String joinAdd)
{
    this.joinAdd = joinAdd;
}




public String getDetailAdd()
{
    return detailAdd;
}




public void setDetailAdd(String detailAdd)
{
    this.detailAdd = detailAdd;
}



public Integer getFamilyDoctorId()
{
    return familyDoctorId;
}




public void setFamilyDoctorId(Integer familyDoctorId)
{
    this.familyDoctorId = familyDoctorId;
}




public Integer getFamilyHospitalId()
{
    return familyHospitalId;
}




public void setFamilyHospitalId(Integer familyHospitalId)
{
    this.familyHospitalId = familyHospitalId;
}




public String getRegTime()
{
    return regTime;
}




public void setRegTime(Date regTime)
{
    this.regTime =Date2String(regTime,"yyyy-MM-dd");
}




public String getLastTime()
{
    return lastTime;
}




public void setLastTime(Date lastTime)
{
    this.lastTime = Date2String(lastTime,"yyyy-MM-dd");
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

public String Date2String(Date date, String format){
	if(null!=date){
		return new SimpleDateFormat(format).format(date);
	}else{
		return "";
	}
}

public List<MonitoringResults> getList() {
	return list;
}

public void setList(List<MonitoringResults> list) {
	this.list = list;
}

public Integer getGestationalWeek() {
	return gestationalWeek;
}

public void setGestationalWeek(Integer gestationalWeek) {
	this.gestationalWeek = gestationalWeek;
}
}
