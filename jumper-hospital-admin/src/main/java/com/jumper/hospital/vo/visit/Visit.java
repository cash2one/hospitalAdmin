package com.jumper.hospital.vo.visit;

public class Visit<T> {
	/**
	 * 0成功 其它都失败
	 */
	private String msg;
	/**
	 * 状态非0时给予相关提示
	 */
	private String msgbox;
	private Datas<T> data;

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

	public Datas<T> getData() {
		return data;
	}

	public void setData(Datas<T> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Visit [msg=" + msg + ", msgbox=" + msgbox + ", data=" + data
				+ "]";
	}


}
