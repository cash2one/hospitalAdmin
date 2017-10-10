package com.jumper.hospital.vo.visit;

public class EditRoleMsg {
	/**
	 * 0成功 其它都失败
	 */
	private Integer msg;
	/**
	 * 状态非0时给予相关提示
	 */
	private String msgbox;

	public Integer getMsg() {
		return msg;
	}
	public void setMsg(Integer msg) {
		this.msg = msg;
	}
	public String getMsgbox() {
		return msgbox;
	}
	public void setMsgbox(String msgbox) {
		this.msgbox = msgbox;
	}
	@Override
	public String toString() {
		return "EditRoleMsg [msg=" + msg + ", msgbox=" + msgbox + "]";
	}


}
