package com.jumper.hospital.enums;
/**
 * 打印模式
 * @author rent
 * @date 2015-09-15
 */
public enum PrintType {
	//常规类型,监护曲线模;
	ROUTINE(0, "常规"),
	MONITORINGCURVE(1,"监护曲线");

	
	private int type;
	private String name;
	
	private PrintType(int type, String name) {
		this.type = type;
		this.name = name;
		
	}

	





	public int getType() {
		return type;
	}







	public void setType(int type) {
		this.type = type;
	}







	public String getName(int index) {
		PrintType[] docs = PrintType.values();
		for(PrintType type : docs){
			if(type.type == index){
				return type.name;
			}
		}
		return null;
	}



	public void setName(String name) {
		this.name = name;
	}



	
	
	
	
}
