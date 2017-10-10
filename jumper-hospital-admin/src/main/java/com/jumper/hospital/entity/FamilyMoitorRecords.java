package com.jumper.hospital.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

 
@Entity
@JsonIgnoreProperties
@Table(name = "family_monitor_records")
public class FamilyMoitorRecords implements java.io.Serializable
{ 
	// Fields
	private static final long serialVersionUID = -1902010546476276418L;
	 
	private Integer id;
    private Integer recordId;
    private Integer recordType;
    private Integer userId;
    private Integer eid;
    private Date addTime;
    
   // 测试的类型: 1.胎心- 2.血氧- 3.心率- 	4.体温- 5.体重- 6.血压- 7.血糖- 8.胎动- 9.尿液- 10.心电- 11.血脂
    /**胎心类别**/
    public static Integer HEART_TYPE=1;
    /**血氧类别**/
    public static Integer OXYGEN_TYPE=2;
    /**血压类别**/
    public static Integer PRESSURE_TYPE=6;
    /**血糖类别**/
    public static Integer SUGAR_TYPE=7;
    /**体温类别**/
    public static Integer TEMPERATURE_TYPE=4;
    /**体重类别**/
    public static Integer WEIGHT_TYPE=5;
    
    public static Map<Integer,String> typeMap=new HashMap<Integer,String>();
    static{
    	typeMap.put(FamilyMoitorRecords.HEART_TYPE, "heartrate");
    	typeMap.put(FamilyMoitorRecords.OXYGEN_TYPE, "oxygen");
    	typeMap.put(FamilyMoitorRecords.PRESSURE_TYPE, "pressure");
    	typeMap.put(FamilyMoitorRecords.SUGAR_TYPE, "sugar");
    	typeMap.put(FamilyMoitorRecords.TEMPERATURE_TYPE, "temperature");
    	typeMap.put(FamilyMoitorRecords.WEIGHT_TYPE, "weight");
    } 
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public Integer getRecordType() {
		return recordType;
	}
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
    
}