package com.jumper.hospital.vo.visit;

public class VoCloudVisitInfo<T> {
	// 科室编码
	private String deptCode;
	// 科室名称
	private String deptName;
	// 生成随访账户的id
	private String sourseId;
	// 生成随访账户的名称
	private String account;
	//建海医院代码
	private String hospCode;
	
	public String getHospCode() {
		return hospCode;
	}
	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getSourseId() {
		return sourseId;
	}
	public void setSourseId(String sourseId) {
		this.sourseId = sourseId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "VoCloudVisitInfo [deptCode=" + deptCode + ", deptName="
				+ deptName + ", sourseId=" + sourseId + ", account=" + account
				+ "]";
	}
	
}
