package com.jumper.hospital.vo.pay;

public class VoLinePayInfo {

	/** 响应码 **/
	private Integer return_code;
	/** 响应消息 **/
	private String return_msg;
	/** 订单号 **/
	private String order_no;
	/** 支付金额 **/
	private double total_fee;
	/** 支付方式 **/
	private Integer pay_channels;
	/** 二维码图片路径 **/
	private String qr_code;
	
	public VoLinePayInfo() {
		
	}
	public VoLinePayInfo(Integer return_code, String return_msg) {
		this.return_code = return_code;
		this.return_msg = return_msg;
	}
	
	public Integer getReturn_code() {
		return return_code;
	}
	public void setReturn_code(Integer return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public double getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(double total_fee) {
		this.total_fee = total_fee;
	}
	public Integer getPay_channels() {
		return pay_channels;
	}
	public void setPay_channels(Integer pay_channels) {
		this.pay_channels = pay_channels;
	}
	public String getQr_code() {
		return qr_code;
	}
	public void setQr_code(String qr_code) {
		this.qr_code = qr_code;
	}
	
	
}
