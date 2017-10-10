package com.jumper.hospital.vo.familyDoctor;

public class VOCity {
private Integer id;
private String cityName;
public VOCity() {
	super();
	// TODO Auto-generated constructor stub
}
public VOCity(Integer id, String cityName) {
	super();
	this.id = id;
	this.cityName = cityName;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getCityName() {
	return cityName;
}
public void setCityName(String cityName) {
	this.cityName = cityName;
}

}
