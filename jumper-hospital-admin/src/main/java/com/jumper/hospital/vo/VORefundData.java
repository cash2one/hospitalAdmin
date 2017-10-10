package com.jumper.hospital.vo;

/**
 * 微信退款请求返回数据VO类
 * @author rent
 * @date 2016-04-11
 */
public class VORefundData {
	/** 请求的响应码，为SUCCESS表示请求成功 **/
	private String return_code;
	/** 请求成功或者失败的错误信息 **/
	private String return_msg;
	/** 业务结果SUCCESS/FAIL 退款业务处理的成功与失败 **/
	private String result_code;
	/** 支付类型 **/
	private Integer pay_type;
	
	public VORefundData(){
		
	}
	
	public VORefundData(String return_code, String return_msg, String result_code, Integer pay_type){
		this.return_code = return_code;
		this.return_msg = return_msg;
		this.result_code = result_code;
		this.pay_type = pay_type;
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
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public Integer getPay_type() {
		return pay_type;
	}
	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}
	
}
