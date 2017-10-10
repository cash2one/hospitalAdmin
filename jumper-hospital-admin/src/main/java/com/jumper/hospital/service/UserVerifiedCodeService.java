package com.jumper.hospital.service;
/**
 * 验证码操作Service
 * @author rent
 * @date 2015-09-18
 */
import java.util.List;

import com.jumper.hospital.entity.UserVerifiedCode;

public interface UserVerifiedCodeService extends BaseService<UserVerifiedCode, Integer> {

	/**
	 * 校验验证码是否正确
	 * @param mobile 手机号码
	 * @param code 验证码
	 * @return boolean
	 */
	public boolean doCheckCode(String mobile, String code);
	
	/**
	 * 通过手机号码查询验证码
	 * @param mobile
	 * @return
	 */
	public List<Object> findUserVerifiedCodeList(String mobile);
}
