package com.jumper.hospital.vo;
/**
 * 审核完成订单Vo类
 * @author rent
 * @date 2015-11-12
 */
import java.io.Serializable;

public class VoFinishedOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 已完成的消费订单ID **/
	private Integer id;
	/** 用户姓名 **/
	private String userName;
	/** 用户手机号码 **/
	private String mobile;
	/** 用户年龄 **/
	private Integer age;
	/** 用户孕周 **/
	private Integer preganyWeek;
	/** 监护请求时间 **/
	private String applyTime;
	/** 报告生成时间 **/
	private String reportTime;
	/** PDF文件地址(完整形式，eg:http://10.0.1.67:8888/....) **/
	private String file;
	/** 用户ID **/
	private Integer userId;
	/** 订单id **/
	private Integer orderId;
	/** 第几次监测 **/
	private Integer monitorTimes;
	/** 用户购买的服务类型 **/
	private String remoteType;
	/** 医院ID **/
	private Integer hospitalId;
	/** 医院名称 **/
	private String hospitalName;
	/** 问题ID **/
	private Long questionId;
	
	/** 判读该报告的医生ID **/
	private Integer doctorId;
	/** 判读该报告的医生 名称 **/
	private String doctorName;
	
	/*下级医院是否查看报告*/
	private Boolean isViewed;
	/**添加临时表中MonitorOrderId**/
	private Integer monitorOrderId;
	
	public Integer getMonitorOrderId() {
		return monitorOrderId;
	}
	public void setMonitorOrderId(Integer monitorOrderId) {
		this.monitorOrderId = monitorOrderId;
	}
	private String address;
	
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRemoteType() {
		return remoteType;
	}
	public void setRemoteType(String remoteType) {
		this.remoteType = remoteType;
	}
	
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
	public Integer getPreganyWeek() {
		return preganyWeek;
	}
	public void setPreganyWeek(Integer preganyWeek) {
		this.preganyWeek = preganyWeek;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getMonitorTimes() {
		return monitorTimes;
	}
	public void setMonitorTimes(Integer monitorTimes) {
		this.monitorTimes = monitorTimes;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Boolean getIsViewed() {
		return isViewed;
	}
	public void setIsViewed(Boolean isViewed) {
		this.isViewed = isViewed;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
