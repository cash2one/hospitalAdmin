package com.jumper.hospital.vo.visit;

public class Index<T> {
	/**
	 * 0成功 其它都失败
	 */
	private String msg;
	/**
	 * 状态非0时给予相关提示
	 */
	private String msgbox;
	private CloudVisitIndex data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsgbox() {
		return msgbox;
	}

	public void setMsgbox(String msgbox) {
		this.msgbox = msgbox;
	}

	public CloudVisitIndex getData() {
		return data;
	}

	public void setData(CloudVisitIndex data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Index [msg=" + msg + ", msgbox=" + msgbox + ", data=" + data
				+ "]";
	}

}
