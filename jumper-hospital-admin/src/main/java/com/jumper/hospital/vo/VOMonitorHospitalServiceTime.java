package com.jumper.hospital.vo;

public class VOMonitorHospitalServiceTime {
	/*id*/
	private Integer id;
	/*一周的第几天 0：周一、1：周二、2：周三、3：周四、4：周五、5：周六、6：周天*/
	private Integer dayOfWeek;
	/*医院id外键*/
	private Integer hospitalId;
	/*某一天服务开启时间0~23之间*/
	private String startTime;
	/*某一天服务结束时间时间0~23之间*/
	private String endTime;
	/*是否为当前天的第一条数据*/
	private String isFirstItem;
	/*是否为当天最后一条数据*/
	private String isLastItem;
	/*当前数据的状态    persisent：未改变  update:修改    add：页面新添加    delete：页面已删除   */
	private String dataState;
	/*当前日期是否生效*/
	private Boolean isEnabled;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getIsFirstItem() {
		return isFirstItem;
	}
	public void setIsFirstItem(String isFirstItem) {
		this.isFirstItem = isFirstItem;
	}
	public String getIsLastItem() {
		return isLastItem;
	}
	public void setIsLastItem(String isLastItem) {
		this.isLastItem = isLastItem;
	}
	public String getDataState() {
		return dataState;
	}
	public void setDataState(String dataState) {
		this.dataState = dataState;
	}
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	@Override
	public String toString() {
		return "VOMonitorHospitalServiceTime [id=" + id + ", dayOfWeek="
				+ dayOfWeek + ", hospitalId=" + hospitalId + ", startTime="
				+ startTime + ", endTime=" + endTime + ", isFirstItem="
				+ isFirstItem + ", isLastItem=" + isLastItem + ", dataState="
				+ dataState + "]";
	}
	
}
