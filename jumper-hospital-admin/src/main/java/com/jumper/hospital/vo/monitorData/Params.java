package com.jumper.hospital.vo.monitorData;
/**
 * 接收前端参数的对象
 * @author tanqing
 *
 */
public class Params {
	/*id*/
	private String id;
	/*保健号*/
	private String healthNum;
	/*监测用户保健号*/
	private String userHealthNum;
	/*token 令牌*/
	private String token;
	/*时间标记*/
	private String timeFlag;
	/*开始时间*/
	private String startTime;
	/*结束时间*/
	private String endTime;
	/*页面图表展示类型*/
	private String showType;
	/*table页展示的类型*/
	private String tableType;
	/*分页页码*/
	private String pageNum;
	/*查询日期*/
	private String addDate;
	/*开始打印时间*/
	private String startPrintTime;
	/*监测时长*/
	private String monitorTimeLength;
	/*监测孕周*/
	private String monitorPregnant;
	/*平均血氧*/
	private String averageOxygen;
	/*平均心率*/
	private String averagePulse;
	/*心电图*/
	private String image;
	/*测试时长*/
	private String testLength;
	//新增每日用户列表查询条件
	/*姓名*/
	private String name;
	/*所监护的用户名*/
	private String monitorUserName;
	/*手册条形码*/
	private String bookCode;
	/*身份证号*/
	private String identification;
	/*年龄*/
	private String age;
	/*联系电话*/
	private String phone;
	/*当前孕周*/
	private String currentPregnant;
	/*用户id*/
	private String userId;
	/*加载样式*/
	private String cssType;
	/*档案基本信息id*/
	private Integer archiveBaseId;
	/*档案id*/
	private Integer archiveId;
	
	public Integer getArchiveBaseId() {
		return archiveBaseId;
	}
	public void setArchiveBaseId(Integer archiveBaseId) {
		this.archiveBaseId = archiveBaseId;
	}
	public Integer getArchiveId() {
		return archiveId;
	}
	public void setArchiveId(Integer archiveId) {
		this.archiveId = archiveId;
	}
	public String getCssType() {
		return cssType;
	}
	public void setCssType(String cssType) {
		this.cssType = cssType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHealthNum() {
		return healthNum;
	}
	public void setHealthNum(String healthNum) {
		this.healthNum = healthNum;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTimeFlag() {
		return timeFlag;
	}
	public void setTimeFlag(String timeFlag) {
		this.timeFlag = timeFlag;
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
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getStartPrintTime() {
		return startPrintTime;
	}
	public void setStartPrintTime(String startPrintTime) {
		this.startPrintTime = startPrintTime;
	}
	public String getMonitorTimeLength() {
		return monitorTimeLength;
	}
	public void setMonitorTimeLength(String monitorTimeLength) {
		this.monitorTimeLength = monitorTimeLength;
	}
	
	public String getMonitorPregnant() {
		return monitorPregnant;
	}
	public void setMonitorPregnant(String monitorPregnant) {
		this.monitorPregnant = monitorPregnant;
	}
	public String getAverageOxygen() {
		return averageOxygen;
	}
	public void setAverageOxygen(String averageOxygen) {
		this.averageOxygen = averageOxygen;
	}
	public String getAveragePulse() {
		return averagePulse;
	}
	public void setAveragePulse(String averagePulse) {
		this.averagePulse = averagePulse;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTestLength() {
		return testLength;
	}
	public void setTestLength(String testLength) {
		this.testLength = testLength;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getMonitorUserName() {
		return monitorUserName;
	}
	public void setMonitorUserName(String monitorUserName) {
		this.monitorUserName = monitorUserName;
	}
	public String getUserHealthNum() {
		return userHealthNum;
	}
	public void setUserHealthNum(String userHealthNum) {
		this.userHealthNum = userHealthNum;
	}
	public String getCurrentPregnant() {
		return currentPregnant;
	}
	public void setCurrentPregnant(String currentPregnant) {
		this.currentPregnant = currentPregnant;
	}
	@Override
	public String toString() {
		return "Params [id=" + id + ", healthNum=" + healthNum
				+ ", userHealthNum=" + userHealthNum + ", token=" + token
				+ ", timeFlag=" + timeFlag + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", showType=" + showType
				+ ", tableType=" + tableType + ", pageNum=" + pageNum
				+ ", addDate=" + addDate + ", startPrintTime=" + startPrintTime
				+ ", monitorTimeLength=" + monitorTimeLength
				+ ", monitorPregnant=" + monitorPregnant + ", averageOxygen="
				+ averageOxygen + ", averagePulse=" + averagePulse + ", image="
				+ image + ", testLength=" + testLength + ", name=" + name
				+ ", monitorUserName=" + monitorUserName + ", bookCode="
				+ bookCode + ", identification=" + identification + ", age="
				+ age + ", phone=" + phone + "]";
	}
	
}
