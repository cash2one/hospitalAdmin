package com.jumper.hospital.vo.monitorData;
/**
 * 用户前端相关显示数据 VO
 * @author tanqing
 *
 */
public class UserViewVO {
	private Integer id;
	/*姓名*/
	private String name;
	/*年龄*/
	private String age;
	/*孕前BMI*/
	private Double BMI;
	/*孕前体重状态*/
	private String weightState;
	/*末次月经*/
	private String lastPeriod;
	/*预产期*/
	private String expectedDate;
	/*当前孕周*/
	private Integer pregnantWeek;
	/*当前孕天*/
	private Integer pregnantDay;
	/*总测量次数*/
	private Integer totalTestCount;
	/*测量间隔平均时间*/
	private Integer averageTesttime;
	/*最近体重状态*/
	private String state;
	/*联系电话*/
	private String phone;
	/*监测时间*/
	private String monitorTime;
	/*当日的健康体重区间 的最小值*/
	private String healthMin;
	/*当日的健康体重区间 的最大值*/
	private String healthMax;
	/*监测结果*/
	private Double value;
	/*早餐前血糖*/
	private Double beforeBreakfast;
	/*早餐后血糖*/
	private Double afterBreakfast;
	/*午餐前血糖*/
	private Double beforeLunch;
	/*午餐后血糖*/
	private Double afterLunch;
	/*晚餐前血糖*/
	private Double beforeDinner;
	/*晚餐后血糖*/
	private Double afterDinner;
	/*睡前血糖*/
	private Double beforeSleep;
	/*收缩压*/
	private Integer systolicPressure;
	/*舒张压*/
	private Integer diastoliPressure;
	/*监测时长*/
	private String testTime;
	/*监测开始时间*/
	private String startTime;
	/*监测时长-以秒为单位*/
	private String testTimeLength;
	/*平均血氧*/
	private Double averageOxygen;
	/*平均心率*/
	private Integer averagePulse;
	/*平均体温*/
	private Double averageTemperature;
	/*监测次数*/
	private Integer monitorCount;
	/*心电st段*/
	private Double st;
	/*心电图片文件*/
	private String image;
	/*心电心率*/
	private Double ecgPulse;
	/*当前孕周和孕天*/
	private String currentPregnant;
	/**用户在医院建档的保健号*/
	private String health_num;
	/*胎心添加时间*/
	private String addTime;
	/****/
	private int monitorAm;
	private int monitorPm;
	private int monitorNight;
	
	
	public int getMonitorAm() {
		return monitorAm;
	}
	public void setMonitorAm(int monitorAm) {
		this.monitorAm = monitorAm;
	}
	public int getMonitorPm() {
		return monitorPm;
	}
	public void setMonitorPm(int monitorPm) {
		this.monitorPm = monitorPm;
	}
	public int getMonitorNight() {
		return monitorNight;
	}
	public void setMonitorNight(int monitorNight) {
		this.monitorNight = monitorNight;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Double getBMI() {
		return BMI;
	}
	public void setBMI(Double bMI) {
		BMI = bMI;
	}
	public String getWeightState() {
		return weightState;
	}
	public void setWeightState(String weightState) {
		this.weightState = weightState;
	}
	public String getLastPeriod() {
		return lastPeriod;
	}
	public void setLastPeriod(String lastPeriod) {
		this.lastPeriod = lastPeriod;
	}
	public String getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}
	public Integer getPregnantWeek() {
		return pregnantWeek;
	}
	public void setPregnantWeek(Integer pregnantWeek) {
		this.pregnantWeek = pregnantWeek;
	}
	public Integer getPregnantDay() {
		return pregnantDay;
	}
	public void setPregnantDay(Integer pregnantDay) {
		this.pregnantDay = pregnantDay;
	}
	public Integer getTotalTestCount() {
		return totalTestCount;
	}
	public void setTotalTestCount(Integer totalTestCount) {
		this.totalTestCount = totalTestCount;
	}
	public Integer getAverageTesttime() {
		return averageTesttime;
	}
	public void setAverageTesttime(Integer averageTesttime) {
		this.averageTesttime = averageTesttime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMonitorTime() {
		return monitorTime;
	}
	public void setMonitorTime(String monitorTime) {
		this.monitorTime = monitorTime;
	}
	public String getHealthMin() {
		return healthMin;
	}
	public void setHealthMin(String healthMin) {
		this.healthMin = healthMin;
	}
	public String getHealthMax() {
		return healthMax;
	}
	public void setHealthMax(String healthMax) {
		this.healthMax = healthMax;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Double getBeforeBreakfast() {
		return beforeBreakfast;
	}
	public void setBeforeBreakfast(Double beforeBreakfast) {
		this.beforeBreakfast = beforeBreakfast;
	}
	public Double getAfterBreakfast() {
		return afterBreakfast;
	}
	public void setAfterBreakfast(Double afterBreakfast) {
		this.afterBreakfast = afterBreakfast;
	}
	public Double getBeforeLunch() {
		return beforeLunch;
	}
	public void setBeforeLunch(Double beforeLunch) {
		this.beforeLunch = beforeLunch;
	}
	public Double getAfterLunch() {
		return afterLunch;
	}
	public void setAfterLunch(Double afterLunch) {
		this.afterLunch = afterLunch;
	}
	public Double getBeforeDinner() {
		return beforeDinner;
	}
	public void setBeforeDinner(Double beforeDinner) {
		this.beforeDinner = beforeDinner;
	}
	public Double getAfterDinner() {
		return afterDinner;
	}
	public void setAfterDinner(Double afterDinner) {
		this.afterDinner = afterDinner;
	}
	public Double getBeforeSleep() {
		return beforeSleep;
	}
	public void setBeforeSleep(Double beforeSleep) {
		this.beforeSleep = beforeSleep;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSystolicPressure() {
		return systolicPressure;
	}
	public void setSystolicPressure(Integer systolicPressure) {
		this.systolicPressure = systolicPressure;
	}
	public Integer getDiastoliPressure() {
		return diastoliPressure;
	}
	public void setDiastoliPressure(Integer diastoliPressure) {
		this.diastoliPressure = diastoliPressure;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getTestTime() {
		return testTime;
	}
	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getTestTimeLength() {
		return testTimeLength;
	}
	public void setTestTimeLength(String testTimeLength) {
		this.testTimeLength = testTimeLength;
	}
	public Double getAverageOxygen() {
		return averageOxygen;
	}
	public void setAverageOxygen(Double averageOxygen) {
		this.averageOxygen = averageOxygen;
	}
	public Integer getAveragePulse() {
		return averagePulse;
	}
	public void setAveragePulse(Integer averagePulse) {
		this.averagePulse = averagePulse;
	}
	public Double getAverageTemperature() {
		return averageTemperature;
	}
	public void setAverageTemperature(Double averageTemperature) {
		this.averageTemperature = averageTemperature;
	}
	public Integer getMonitorCount() {
		return monitorCount;
	}
	public void setMonitorCount(Integer monitorCount) {
		this.monitorCount = monitorCount;
	}
	public Double getSt() {
		return st;
	}
	public void setSt(Double st) {
		this.st = st;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getEcgPulse() {
		return ecgPulse;
	}
	public void setEcgPulse(Double ecgPulse) {
		this.ecgPulse = ecgPulse;
	}
	public String getHealth_num() {
		return health_num;
	}
	public void setHealth_num(String health_num) {
		this.health_num = health_num;
	}
	public String getCurrentPregnant() {
		return currentPregnant;
	}
	public void setCurrentPregnant(String currentPregnant) {
		this.currentPregnant = currentPregnant;
	}
	
}
