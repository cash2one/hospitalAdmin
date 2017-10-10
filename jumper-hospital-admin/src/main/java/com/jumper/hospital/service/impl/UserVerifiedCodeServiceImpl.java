package com.jumper.hospital.service.impl;
/**
 * 验证码操作Service实现类
 * @author rent
 * @date 2015-09-18
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.UserVerifiedCodeDao;
import com.jumper.hospital.entity.UserVerifiedCode;
import com.jumper.hospital.service.UserVerifiedCodeService;

@Service
public class UserVerifiedCodeServiceImpl extends BaseServiceImpl<UserVerifiedCode, Integer> implements UserVerifiedCodeService {

	@Autowired
	private UserVerifiedCodeDao userVerifiedCodeDaoImpl;
	
	@Override
	public BaseDao<UserVerifiedCode, Integer> getBaseDAO() {
		return userVerifiedCodeDaoImpl;
	}

	@Override
	public boolean doCheckCode(String mobile, String code) {
		boolean flag = userVerifiedCodeDaoImpl.doCheckCode(mobile, code);
		return flag;
	}
	
	/**
	 * 通过手机号码查询验证码
	 * @param mobile
	 * @return
	 */
	public List<Object> findUserVerifiedCodeList(String mobile) {
		return userVerifiedCodeDaoImpl.findUserVerifiedCodeList(mobile);
	}
}
