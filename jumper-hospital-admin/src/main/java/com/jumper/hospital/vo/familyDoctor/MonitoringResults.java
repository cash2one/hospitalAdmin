package com.jumper.hospital.vo.familyDoctor;

public class MonitoringResults {
private String project;// 项目名称
private String result;// 结果 展示页面的内容
private String area;// 区间
private String resultState;//数据库存储的结果代号0 1 2






public MonitoringResults(String project, String result, String area,
		String resultState) {
	super();
	this.project = project;
	this.result = result;
	this.area = area;
	this.resultState = resultState;
}
public MonitoringResults() {
	super();
	// TODO Auto-generated constructor stub
}
public String getArea() {
	return area;
}
public void setArea(String area) {
	this.area = area;
}
public String getProject() {
	return project;
}
public void setProject(String project) {
	this.project = project;
}
public String getResult() {
	return result;
}
public void setResult(String result) {
	this.result = result;
}
public String getResultState() {
	return resultState;
}
public void setResultState(String resultState) {
	this.resultState = resultState;
}

}
