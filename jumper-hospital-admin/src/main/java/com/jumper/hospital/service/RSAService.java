package com.jumper.hospital.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Service - RSA安全
 */
public interface RSAService {

	/**
	 * 解密参数
	 * @param name 参数名称
	 * @param request httpServletRequest
	 * @return 解密内容
	 */
	String decryptParameter(String name, HttpServletRequest request);

}