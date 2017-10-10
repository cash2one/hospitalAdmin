package com.jumper.hospital.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.jumper.hospital.service.RSAService;
import com.jumper.hospital.utils.MD5EncryptUtils;

/**
 * Service - RSA安全
 */
@Service
public class RSAServiceImpl implements RSAService {

	@Transactional(readOnly = true)
	public String decryptParameter(String name, HttpServletRequest request) {
		Assert.notNull(request);
		if (name != null) {
			String parameter = request.getParameter(name);
			if (StringUtils.isNotEmpty(parameter)) {
				return MD5EncryptUtils.getMd5Value(parameter);
			}
		}
		return null;
	}

}