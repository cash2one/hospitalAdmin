package com.jumper.hospital.vo;

public class VONewsChanelAdd {
	/** 结果 **/
	private String result;
	/** 默认订阅条数 **/
	private long defaultNum;
	/** 状态为显示的条数 **/
	private long showNum;
	
	public VONewsChanelAdd() {
		super();
	}
	public VONewsChanelAdd(String result, long defaultNum, long showNum) {
		super();
		this.result = result;
		this.defaultNum = defaultNum;
		this.showNum = showNum;
	}


	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public long getDefaultNum() {
		return defaultNum;
	}
	public void setDefaultNum(long defaultNum) {
		this.defaultNum = defaultNum;
	}
	public long getShowNum() {
		return showNum;
	}
	public void setShowNum(long showNum) {
		this.showNum = showNum;
	}
	
}
