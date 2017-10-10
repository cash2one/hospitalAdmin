/*
 * 
 * 
 * 
 */
package com.jumper.hospital.auth;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 登录令牌
 * 
 * 
 * 
 */
public class AuthenticationToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 5898441540965086534L;

	/** 验证码ID */
	private String captchaId;
	
	private boolean rememberMe;

	/**
	 * @param username 用户名
	 * @param password 密码
	 * @param rememberMe 记住我
	 * @param host IP
	 */
	public AuthenticationToken(String username, String password, String captcha, boolean rememberMe, String host) {
		super(username, password, rememberMe);
		this.rememberMe = rememberMe;
	}

	/**
	 * 获取验证码ID
	 * @return 验证码ID
	 */
	public String getCaptchaId() {
		return captchaId;
	}

	/**
	 * 设置验证码ID
	 * @param captchaId 验证码ID
	 */
	public void setCaptchaId(String captchaId) {
		this.captchaId = captchaId;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	
	
}