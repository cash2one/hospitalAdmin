package com.jumper.hospital.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 医院小屏幕占用情况
 * @author rent
 * @date 2015-10-10
 */
@Entity
@Table(name="monitor_small_screen")
public class MonitorSmallScreen extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 医院信息 **/
	private String hospitalID;
	/** 屏幕1 **/
	private String screen_1;
	/** 屏幕2 **/
	private String screen_2;
	/** 屏幕3 **/
	private String screen_3;
	/** 屏幕4 **/
	private String screen_4;
	/** 屏幕5 **/
	private String screen_5;
	/** 屏幕6 **/
	private String screen_6;
	/** 屏幕7 **/
	private String screen_7;
	/** 屏幕8 **/
	private String screen_8;
	/** 屏幕1用户测试开始时间 **/
	private Integer screen1_start;
	/** 屏幕2用户测试开始时间 **/
	private Integer screen2_start;
	/** 屏幕3用户测试开始时间 **/
	private Integer screen3_start;
	/** 屏幕4用户测试开始时间 **/
	private Integer screen4_start;
	/** 屏幕5用户测试开始时间 **/
	private Integer screen5_start;
	/** 屏幕6用户测试开始时间 **/
	private Integer screen6_start;
	/** 屏幕7用户测试开始时间 **/
	private Integer screen7_start;
	/** 屏幕8用户测试开始时间 **/
	private Integer screen8_start;
	
	public String getHospitalID() {
		return hospitalID;
	}
	public void setHospitalID(String hospitalID) {
		this.hospitalID = hospitalID;
	}
	public String getScreen_1() {
		return screen_1;
	}
	public void setScreen_1(String screen_1) {
		this.screen_1 = screen_1;
	}
	public String getScreen_2() {
		return screen_2;
	}
	public void setScreen_2(String screen_2) {
		this.screen_2 = screen_2;
	}
	public String getScreen_3() {
		return screen_3;
	}
	public void setScreen_3(String screen_3) {
		this.screen_3 = screen_3;
	}
	public String getScreen_4() {
		return screen_4;
	}
	public void setScreen_4(String screen_4) {
		this.screen_4 = screen_4;
	}
	public String getScreen_5() {
		return screen_5;
	}
	public void setScreen_5(String screen_5) {
		this.screen_5 = screen_5;
	}
	public String getScreen_6() {
		return screen_6;
	}
	public void setScreen_6(String screen_6) {
		this.screen_6 = screen_6;
	}
	public String getScreen_7() {
		return screen_7;
	}
	public void setScreen_7(String screen_7) {
		this.screen_7 = screen_7;
	}
	public String getScreen_8() {
		return screen_8;
	}
	public void setScreen_8(String screen_8) {
		this.screen_8 = screen_8;
	}
	public Integer getScreen1_start() {
		return screen1_start;
	}
	public void setScreen1_start(Integer screen1_start) {
		this.screen1_start = screen1_start;
	}
	public Integer getScreen2_start() {
		return screen2_start;
	}
	public void setScreen2_start(Integer screen2_start) {
		this.screen2_start = screen2_start;
	}
	public Integer getScreen3_start() {
		return screen3_start;
	}
	public void setScreen3_start(Integer screen3_start) {
		this.screen3_start = screen3_start;
	}
	public Integer getScreen4_start() {
		return screen4_start;
	}
	public void setScreen4_start(Integer screen4_start) {
		this.screen4_start = screen4_start;
	}
	public Integer getScreen5_start() {
		return screen5_start;
	}
	public void setScreen5_start(Integer screen5_start) {
		this.screen5_start = screen5_start;
	}
	public Integer getScreen6_start() {
		return screen6_start;
	}
	public void setScreen6_start(Integer screen6_start) {
		this.screen6_start = screen6_start;
	}
	public Integer getScreen7_start() {
		return screen7_start;
	}
	public void setScreen7_start(Integer screen7_start) {
		this.screen7_start = screen7_start;
	}
	public Integer getScreen8_start() {
		return screen8_start;
	}
	public void setScreen8_start(Integer screen8_start) {
		this.screen8_start = screen8_start;
	}
	
}
