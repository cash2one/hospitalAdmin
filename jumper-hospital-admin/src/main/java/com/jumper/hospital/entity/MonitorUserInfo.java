package com.jumper.hospital.entity;
/**
 * 远程监控下单用户信息，PC医院后台监护系统录入，第一次购买录入，第二次则直接绑定该表
 * @author rent
 * @date 2015-09-16
 */
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "monitor_user_info", uniqueConstraints = {@UniqueConstraint(columnNames="mobile")})
public class MonitorUserInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 用户真实姓名 **/
	private String realName;
	/** 用户手机号码 **/
	private String mobile;
	/** 用户年龄 **/
	private Integer age;
	/** 关联用户表 **/
	private UserInfo userId;
	/** 用户预产期 **/
	private Date edc;
	/** 用户所购买的远程监控订单列表 **/
	private List<MonitorOrder> orderList;
	/*院内用户地址*/
	private String address;
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@OneToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	public UserInfo getUserId() {
		return userId;
	}
	public void setUserId(UserInfo userId) {
		this.userId = userId;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "monitorUserId")
	public List<MonitorOrder> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<MonitorOrder> orderList) {
		this.orderList = orderList;
	}
	public Date getEdc() {
		return edc;
	}
	public void setEdc(Date edc) {
		this.edc = edc;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
