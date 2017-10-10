package com.jumper.hospital.base;
/**
 * ajax请求处理数据统一返回�?
 * @author rent
 * @date 2015-01-07
 */
public class BaseMessage implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5936312267141657184L;
	
	/** 是否成功 **/
	private boolean success;
	
	/** 返回消息 **/
	private String message;
	
	public BaseMessage(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
