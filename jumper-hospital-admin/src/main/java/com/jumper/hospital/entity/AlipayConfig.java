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
@Table(name = "pay_alipay_info")
public class AlipayConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column
	private Integer id;
	// 商户账号
	private  String sellerId = "webmaster@jumper-medical.com";
	/** 医院id**/
	private Integer hospitalId;
	// 支付宝分给商户的应用APPID
	private  String appid = "2015121700999648";
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	private  String partner = "2088612712988352";
	// 字符编码格式 目前支持 gbk 或 utf-8
	private  String inputCharset = "utf-8";
	// 商户的私钥
	private  String mchPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKCSj/P8bw72Cb+EZnGAwupgmlXt8Fp2+9LqYwxoZr9ZPt+3323MKo3KxzYP1Kuzp8qMH5w0brrG6CdN9w5TV0inaIMQcTZp4ORI8mwlClkslIPbQUHPC2R85A7w2otfKFrvsJS7YpI8lxp4uoiyG2dt/34IAF0P7pHB9CkHtXCjAgMBAAECgYAHUsfn+9Jg303Qg0xczt+U6vT/CEwXBCg2FWSRE03kWkCo2W0CR/aE4wO07oHnltkHPt7ONAH82MeLy12CNMDdR+w3mjTmX6Y9Okx5Ggel+ZPoz744467B2s2aYW4k50qqsHhZeytDNvTE7IGL2RkSfjAywWNaCmglIeZKS6OSyQJBANGmBUwkB1FlYukim5ojaRh+1+IZOHbjU6Lok9FjcjCBphLAw+HW7QQ8OBddmMFQ6A3GTBkPpKh5Ld+BdbPDFW0CQQDEEt6DFLFReJqYG5QIT68jbOnBOcwHa9o/Ac2ZNfpP1WLNFrQy+7mV4iG8Xa2COiGR1vZbtAuWomT+gRKS6KRPAkEAg1av4Oh9USfkFEvjEWh3sandUz8bmZeG0PtFh5r2cZ73TK5IyRYdfFbBiRnQ1eL6zY5aJCUhsY3hCDGRbGGd7QJAY1fAH5/7W3EEuu62KSPec895/CnBgz0U/fl25xqfMZXHEmxfbrl1xUgmmZ7JC4NSgObuOXZWrMKo8v4IoCB5JwJBAIJpoQ+0m74xFlhl05qf1GG8uSZY/flyhjYVDX8TXTkvpMeoA498hEcwYKVQ15OIOGDLGO6oHKKOxGmicUNlbZc=";
	// 商户的公钥，无需修改该值
	private  String mchPublicKey  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgko/z/G8O9gm/hGZxgMLqYJpV7fBadvvS6mMMaGa/WT7ft99tzCqNysc2D9Srs6fKjB+cNG66xugnTfcOU1dIp2iDEHE2aeDkSPJsJQpZLJSD20FBzwtkfOQO8NqLXyha77CUu2KSPJcaeLqIshtnbf9+CABdD+6RwfQpB7VwowIDAQAB";
	// 支付宝集成KEY
	private  String alipayKey = "wuwuk2noe5fbj8tvmg9q9ymuqbv2yibz";
	//开放平台支付宝的公钥 (当面付、服务窗产品)
	private  String aliOpenPublicKey  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
	//合作伙伴支付宝的公钥 (其他产品APP支付等)   
	private  String aliAppPublicKey  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getInputCharset() {
		return inputCharset;
	}
	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}
	public String getMchPrivateKey() {
		return mchPrivateKey;
	}
	public void setMchPrivateKey(String mchPrivateKey) {
		this.mchPrivateKey = mchPrivateKey;
	}
	public String getMchPublicKey() {
		return mchPublicKey;
	}
	public void setMchPublicKey(String mchPublicKey) {
		this.mchPublicKey = mchPublicKey;
	}
	public String getAlipayKey() {
		return alipayKey;
	}
	public void setAlipayKey(String alipayKey) {
		this.alipayKey = alipayKey;
	}
	public String getAliOpenPublicKey() {
		return aliOpenPublicKey;
	}
	public void setAliOpenPublicKey(String aliOpenPublicKey) {
		this.aliOpenPublicKey = aliOpenPublicKey;
	}
	public String getAliAppPublicKey() {
		return aliAppPublicKey;
	}
	public void setAliAppPublicKey(String aliAppPublicKey) {
		this.aliAppPublicKey = aliAppPublicKey;
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
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
}