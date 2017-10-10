package com.jumper.hospital.vo;
/**
 * 胎心画图vo对象
 * @author rent
 * @date 2015-11-06
 */
import java.io.Serializable;

public class VoRemoteHeart implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 胎心记录ID **/
	private Integer id;
	/** JSON文件记录数据，此处以字符串形式返回 **/
	private String data;
	/** 宫缩数据，字符串形式返回 **/
	private String uterusData;
	/** 胎心音频文件 **/
	private String music;
	/** 测试时间 **/
	private String time;
	/** 测试结束时间 **/
	private String endTime;
	/** 测试总时长 **/
	private String minute;
	/** 胎动记录字符串，格式:30,47,51,99,140 **/
	private String fetalMoveData;
	
	/** 延大医院存放报告通知地址 **/
	private String title;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getFetalMoveData() {
		return fetalMoveData;
	}
	public void setFetalMoveData(String fetalMoveData) {
		this.fetalMoveData = fetalMoveData;
	}
	public String getUterusData() {
		return uterusData;
	}
	public void setUterusData(String uterusData) {
		this.uterusData = uterusData;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
