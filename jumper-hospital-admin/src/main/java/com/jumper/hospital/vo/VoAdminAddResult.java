package com.jumper.hospital.vo;
/**
 * 远程添加管理员返回信息Vo
 * @author rent
 * @date 2015-11-11
 */
import java.io.Serializable;

public class VoAdminAddResult implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 是否成功 **/
	private Boolean success;
	/** 消息 **/
	private String message;
	
	public VoAdminAddResult(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
