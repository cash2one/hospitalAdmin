package com.jumper.hospital.vo.familyDoctor;

import java.util.Date;

public class VOFamilyDoctorInfo {
	 private Integer id;
	    private Integer hospitalId=0;
	    private Integer provinceId=0;
	    private Integer cityId=0;
	    private Integer districtId=0;
	    private String detailAdd="";
	    private Integer majorId=0;
	    private String position="";
	    private String phone="";
	    private String remark="";
	    private String username="";
	    private String password="";
	    private String enabled="";
	    private String responsibleArea="";
	    private String doctorName="";
	    private Date addDate;
	    
	    private String province;// 省份名称
	    private String city;// 市名称
	    
	    
		public VOFamilyDoctorInfo(Integer id, Integer hospitalId,
				Integer provinceId, Integer cityId, Integer districtId,
				String detailAdd, Integer majorId, String position, String phone,
				String remark, String username, String password,
				String enabled, String responsibleArea, String doctorName
				) {
			this.id = id;
			this.hospitalId = hospitalId;
			this.provinceId = provinceId;
			this.cityId = cityId;
			this.districtId = districtId;
			this.detailAdd = detailAdd;
			this.majorId = majorId;
			this.position = position;
			this.phone = phone;
			this.remark = remark;
			this.username = username;
			this.password = password;
			this.enabled = enabled;
			this.responsibleArea = responsibleArea;
			this.doctorName = doctorName;
		}
		public VOFamilyDoctorInfo() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getHospitalId() {
			return hospitalId;
		}
		public void setHospitalId(Integer hospitalId) {
			this.hospitalId = hospitalId;
		}
		public Integer getProvinceId() {
			return provinceId;
		}
		public void setProvinceId(Integer provinceId) {
			this.provinceId = provinceId;
		}
		public Integer getCityId() {
			return cityId;
		}
		public void setCityId(Integer cityId) {
			this.cityId = cityId;
		}
		public Integer getDistrictId() {
			return districtId;
		}
		public void setDistrictId(Integer districtId) {
			this.districtId = districtId;
		}
		public String getDetailAdd() {
			return detailAdd;
		}
		public void setDetailAdd(String detailAdd) {
			this.detailAdd = detailAdd;
		}
		public Integer getmajorId() {
			return majorId;
		}
		public void setMajor(Integer majorId) {
			this.majorId = majorId;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getEnabled() {
			return enabled;
		}
		public void setEnabled(String enabled) {
			this.enabled = enabled;
		}
		public String getResponsibleArea() {
			return responsibleArea;
		}
		public void setResponsibleArea(String responsibleArea) {
			this.responsibleArea = responsibleArea;
		}
		public String getDoctorName() {
			return doctorName;
		}
		public void setDoctorName(String doctorName) {
			this.doctorName = doctorName;
		}
		public Date getAddDate() {
			return addDate;
		}
		public void setAddDate(Date addDate) {
			this.addDate = addDate;
		}
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
	    
}
