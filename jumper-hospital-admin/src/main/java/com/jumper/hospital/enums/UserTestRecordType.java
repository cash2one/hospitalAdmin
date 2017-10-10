package com.jumper.hospital.enums;

public enum UserTestRecordType {

	FETALHEART(1, "胎心", 1),OXYGEN(2, "血氧", 8),FEATRATE(3, "心率", 7), BODYTEMPERATURE(4, "体温", 6),BODYWEIGHT(5, "体重", 5),BLOODPRESSURE(6, "血压", 3)
	, BLOODGLUCOSE(7, "血糖", 4), FETALMOVEMENT(8, "胎动", 2);
	
	private int type;
	private String name;
	private int order;
	
	private UserTestRecordType(int type, String name, int order) {
		this.type = type;
		this.name = name;
		this.order = order;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public static String getName(int index){
		UserTestRecordType[] docs = UserTestRecordType.values();
		for(UserTestRecordType type : docs){
			if(type.type == index){
				return type.name;
			}
		}
		return null;
	}
	
	public static Integer getOrder(int index){
		UserTestRecordType[] docs = UserTestRecordType.values();
		for(UserTestRecordType type : docs){
			if(type.type == index){
				return type.order;
			}
		}
		return null;
	}
	
}
