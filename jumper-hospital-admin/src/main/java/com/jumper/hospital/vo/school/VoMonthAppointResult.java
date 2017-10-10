package com.jumper.hospital.vo.school;
/**
 * 用户预约及签到一个月记录查询
 */
import java.io.Serializable;

public class VoMonthAppointResult implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 日期 **/
	private String date;
	/** 预约总数 **/
	private Integer appoint;
	/** 签到总数 **/
	private Integer sign;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getAppoint() {
		return appoint;
	}
	public void setAppoint(Integer appoint) {
		this.appoint = appoint;
	}
	public Integer getSign() {
		return sign;
	}
	public void setSign(Integer sign) {
		this.sign = sign;
	}

}
