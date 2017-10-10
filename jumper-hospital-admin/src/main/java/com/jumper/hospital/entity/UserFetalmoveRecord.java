package com.jumper.hospital.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 胎动记录entity
 * @author hg
 */
@Entity
@Table(name="user_fetalmove_record_new")
public class UserFetalmoveRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 测试用户 **/
	private UserInfo userInfo;
	/** 胎动次数 **/
	private Integer fetal_move_value;
	/** 胎动测试时长（秒/s）**/
	private String fetal_move_file;
	/** 胎动记录文件 **/
	/*private Integer fetal_move_time;*/
	
	/** 记录保存时间 **/
	private Timestamp add_time;
	/** 用户测试时间 **/
	private Timestamp start_time;
	/** 监测时间 **/
	private Integer three_type;
	/** 结束时间 **/
	private Timestamp end_time;
	/** 业务类型 **/
	private Integer business_type;
	/** 效验码 **/
	private Integer token;
	/** 服务器添加时间 **/
	private Timestamp server_add_time;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public Integer getFetal_move_value() {
		return fetal_move_value;
	}
	public void setFetal_move_value(Integer fetalMoveValue) {
		fetal_move_value = fetalMoveValue;
	}
	public String getFetal_move_file() {
		return fetal_move_file;
	}
	public void setFetal_move_file(String fetalMoveFile) {
		fetal_move_file = fetalMoveFile;
	}
	/*public Integer getFetal_move_time() {
		return fetal_move_time;
	}
	public void setFetal_move_time(Integer fetalMoveTime) {
		fetal_move_time = fetalMoveTime;
	}*/
	public Timestamp getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Timestamp addTime) {
		add_time = addTime;
	}
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(Timestamp startTime) {
		start_time = startTime;
	}
	public Integer getThree_type() {
		return three_type;
	}
	public void setThree_type(Integer three_type) {
		this.three_type = three_type;
	}
	public Timestamp getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	public Integer getBusiness_type() {
		return business_type;
	}
	public void setBusiness_type(Integer business_type) {
		this.business_type = business_type;
	}
	public Integer getToken() {
		return token;
	}
	public void setToken(Integer token) {
		this.token = token;
	}
	public Timestamp getServer_add_time() {
		return server_add_time;
	}
	public void setServer_add_time(Timestamp server_add_time) {
		this.server_add_time = server_add_time;
	}
	
}
