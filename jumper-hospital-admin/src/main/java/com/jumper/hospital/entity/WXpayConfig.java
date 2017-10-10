package com.jumper.hospital.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity - 管理员
 */
@Entity
@Table(name = "pay_wxpay_info")
public class WXpayConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column
	private Integer id;
	/** 医院id**/
	private Integer hospitalId;
	/** 微信集成APPID **/
	public static String appid = "wx6b4e648d236bdc9d";
	/** 微信集成appsecret **/
	public static String appsecret = "994d34968632fb898412c3eb5763edf5";
	/** 微信商户号 **/
	public static String mchid = "1232960901";
	/** 微信集成KEY **/
	public static String wxpayKey = "ec3ef76c991ea5622e80eea7fa9ec071";
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String inputCharset = "utf-8";
	// 证书地址
	public static String keyFileUrl = "/E:/Users/Jkh/workspace/JumperPayment/WebRoot/WEB-INF/classes/";
	
	public static String getAppid() {
		return appid;
	}
	public static void setAppid(String appid) {
		WXpayConfig.appid = appid;
	}
	public static String getAppsecret() {
		return appsecret;
	}
	public static void setAppsecret(String appsecret) {
		WXpayConfig.appsecret = appsecret;
	}
	public static String getMchid() {
		return mchid;
	}
	public static void setMchid(String mchid) {
		WXpayConfig.mchid = mchid;
	}
	public static String getWxpayKey() {
		return wxpayKey;
	}
	public static void setWxpayKey(String wxpayKey) {
		WXpayConfig.wxpayKey = wxpayKey;
	}
	public static String getInputCharset() {
		return inputCharset;
	}
	public static void setInputCharset(String inputCharset) {
		WXpayConfig.inputCharset = inputCharset;
	}
	public static String getKeyFileUrl() {
		return keyFileUrl;
	}
	public static void setKeyFileUrl(String keyFileUrl) {
		WXpayConfig.keyFileUrl = keyFileUrl;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}