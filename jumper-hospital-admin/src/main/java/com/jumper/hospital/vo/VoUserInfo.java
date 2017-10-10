package com.jumper.hospital.vo;
/**
 * 医院绑定用户Vo类
 * @author rent
 * @date 2015-11-12
 */
import java.io.Serializable;

public class VoUserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 用户ID **/
	private Integer id;
	/** 真实姓名 **/
	private String userName;
	/** 孕周 **/
	private String preganyWeek;
	/** 年龄 **/
	private Integer age;
	/** 手机号码 **/
	private String mobile;
	/** 预产期 **/
	private String preganyDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPreganyWeek() {
		return preganyWeek;
	}
	public void setPreganyWeek(String preganyWeek) {
		this.preganyWeek = preganyWeek;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPreganyDate() {
		return preganyDate;
	}
	public void setPreganyDate(String preganyDate) {
		this.preganyDate = preganyDate;
	}

}
