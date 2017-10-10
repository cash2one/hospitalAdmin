package com.jumper.hospital.entity;
/**
 * 测试记录结果概要
 * @author rent
 * @date 2015-09-17
 */
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jumper.hospital.enums.RecordState;

@Entity
@Table(name="user_records_sets")
public class UserRecordsSets extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 测试数据用户 **/
    private UserInfo userInfo;
    /** 测试数据类型 **/
    private Integer recordType;
    /** 测试数据添加时间 **/
    private Timestamp addTime;
    /** 测试数据值 **/
    private String data;
    /** 测试数据对象ID **/
    private Integer objectId;
    /** 测试数据状态(正常、偏高、偏低) **/
    private RecordState recordState;
    /** 该记录是否已处理 true|false **/
    private Boolean isHandle;
    
    
    /*********************************************************getter and setter*********************************************************/
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public Integer getRecordType() {
		return recordType;
	}
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getObjectId() {
		return objectId;
	}
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	public RecordState getRecordState() {
		return recordState;
	}
	public void setRecordState(RecordState recordState) {
		this.recordState = recordState;
	}
	public Boolean getIsHandle() {
		return isHandle;
	}
	public void setIsHandle(Boolean isHandle) {
		this.isHandle = isHandle;
	}
    
}
