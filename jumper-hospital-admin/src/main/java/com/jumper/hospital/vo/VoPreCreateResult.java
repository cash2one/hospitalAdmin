package com.jumper.hospital.vo;

import java.io.Serializable;

/**
 * 统一下单接口请求返回接口Vo类
 * @author rent
 * @date 2016-07-27
 */
public class VoPreCreateResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 商户订单号 **/
	private String out_trade_no;
	/** 微信待支付ID **/
	private String prepay_id;
	/** 二维码路径 **/
	private String qr_code;
	/** 请求响应码 **/
	private String return_code;
	/** 请求响应信息 **/
	private String return_msg;
	
	public VoPreCreateResult() {
		
	}
	public VoPreCreateResult(String out_trade_no, String prepay_id, String qr_code, String return_code, String return_msg) {
		this.out_trade_no = out_trade_no;
		this.prepay_id = prepay_id;
		this.qr_code = qr_code;
		this.return_code = return_code;
		this.return_msg = return_msg;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
	public String getQr_code() {
		return qr_code;
	}
	public void setQr_code(String qr_code) {
		this.qr_code = qr_code;
	}
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	
}
