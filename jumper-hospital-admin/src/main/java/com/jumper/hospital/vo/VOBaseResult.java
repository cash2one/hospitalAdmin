package com.jumper.hospital.vo;
/**
 * 基础数据返回VO类
 * @author rent
 * @date 2016-05-13
 */
public class VOBaseResult {
	/** 结果 **/
	private String result;
	/** 消息 **/
	private String message;
	
	public VOBaseResult() {
		super();
	}
	public VOBaseResult(String result, String message) {
		super();
		this.result = result;
		this.message = message;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
