package com.jumper.hospital.vo.monitorData;
/**
 * 用户打印信息
 * @author tanqing
 *
 */
public class UserPrintVO {
	/*档案号*/
	private String healthNum;
	/*姓名*/
	private String name;
	/*监护时间*/
	private String monitorTime;
	/*年龄*/
	private String age;
	/*孕周*/
	private String pregnant;
	/*开始打印时间*/
	private String startPrintTime;
	/*选择段落的起始位置，用于截取胎心数据*/
	private String offset= "0";
	/*胎心数据字符串*/
	private String heartData;
	/*胎心数据数组*/
	private int[] heartArr;
	/*胎动数据字符串*/
	private String fetalMoveData;
	/*胎动数据数组*/
	private int[] fetalMoveArra;
	/*宫缩数据字符串*/
	private String uterusData;
	/*宫缩数据数组*/
	private int[] uterusArra;
	/*走纸速度*/
	private String paperSpeed;
	/*胎心记录id*/
	private String heartId;
	public String getHealthNum() {
		return healthNum;
	}
	public void setHealthNum(String healthNum) {
		this.healthNum = healthNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMonitorTime() {
		return monitorTime;
	}
	public void setMonitorTime(String monitorTime) {
		this.monitorTime = monitorTime;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPregnant() {
		return pregnant;
	}
	public void setPregnant(String pregnant) {
		this.pregnant = pregnant;
	}
	public String getStartPrintTime() {
		return startPrintTime;
	}
	public void setStartPrintTime(String startPrintTime) {
		this.startPrintTime = startPrintTime;
	}
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	public String getHeartData() {
		return heartData;
	}
	public void setHeartData(String heartData) {
		this.heartData = heartData;
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
	public int[] getHeartArr() {
		return heartArr;
	}
	public void setHeartArr(int[] heartArr) {
		this.heartArr = heartArr;
	}
	public int[] getFetalMoveArra() {
		return fetalMoveArra;
	}
	public void setFetalMoveArra(int[] fetalMoveArra) {
		this.fetalMoveArra = fetalMoveArra;
	}
	public int[] getUterusArra() {
		return uterusArra;
	}
	public void setUterusArra(int[] uterusArra) {
		this.uterusArra = uterusArra;
	}
	public String getPaperSpeed() {
		return paperSpeed;
	}
	public void setPaperSpeed(String paperSpeed) {
		this.paperSpeed = paperSpeed;
	}
	public String getHeartId() {
		return heartId;
	}
	public void setHeartId(String heartId) {
		this.heartId = heartId;
	}
	
}
