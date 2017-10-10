package com.jumper.hospital.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jumper.hospital.service.UserVerifiedCodeService;

/**
 * 用户验证码Controller
 * @author qinxiaowei
 *
 */
@Controller
@RequestMapping("code")
public class UserVerifiedCodeController {
	
	@Autowired
	private UserVerifiedCodeService userVerifiedCodeService;
	
	/**
	 * 通过手机号码查询验证码
	 * @return
	 */
	@RequestMapping("findUserVerifiedCode")
	public String findUserVerifiedCode(HttpServletRequest request) {
		//获取手机号码
		String mobile = request.getParameter("mobile");
		if(mobile != null && !mobile.equals("")) {
			List<Object> list = userVerifiedCodeService.findUserVerifiedCodeList(mobile);
			request.setAttribute("list", list);
			request.setAttribute("mobile", mobile);
		}
		return "user/userVerifiedCode";
	}
}
