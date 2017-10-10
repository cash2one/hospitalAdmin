package com.jumper.hospital.vo.visit;

public class Msg {
	private Integer msg;
	private String msgBox;

	public Integer getMsg() {
		return msg;
	}

	public void setMsg(Integer msg) {
		this.msg = msg;
	}

	public String getMsgBox() {
		return msgBox;
	}

	public void setMsgBox(String msgBox) {
		this.msgBox = msgBox;
	}

	@Override
	public String toString() {
		return "Msg [msg=" + msg + ", msgBox=" + msgBox + "]";
	}

}
