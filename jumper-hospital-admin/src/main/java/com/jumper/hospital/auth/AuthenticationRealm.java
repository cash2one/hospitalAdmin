package com.jumper.hospital.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.Authority;
import com.jumper.hospital.service.AdminService;
import com.jumper.hospital.service.MonitorOperateLogServices;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.vo.Principal;

/**
 * 权限认证
 */
public class AuthenticationRealm extends AuthorizingRealm {

	@Autowired
	private AdminService adminServiceImpl;
	
	@Autowired
	private MonitorOperateLogServices monitorOperateLogServices;

	/**
	 * 获取认证信息
	 * @param token 令牌
	 * @return 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) {
		AuthenticationToken authenticationToken = (AuthenticationToken) token;
		String username = authenticationToken.getUsername();
		String password = new String(authenticationToken.getPassword());
		String ip = authenticationToken.getHost();
		if (username != null && password != null) {
			Admin admin = adminServiceImpl.findByUsername(username);
			if (admin == null) {
				throw new UnknownAccountException();
			}
			if (!admin.getIsEnabled()) {
				throw new DisabledAccountException();
			}
			if (admin.getIsLocked()) {
				Date lockedDate = admin.getLockedDate();
				Date unlockDate = DateUtils.addMinutes(lockedDate, Consts.loginFailureLockTime);
				if (new Date().after(unlockDate)) {
					admin.setLoginFailureCount(0);
					admin.setIsLocked(false);
					admin.setLockedDate(null);
					adminServiceImpl.save(admin);
				} else {
					throw new LockedAccountException();
				}
			}
			if (!password.equalsIgnoreCase(admin.getPassword())) {
				int loginFailureCount = admin.getLoginFailureCount() + 1;
				if (loginFailureCount >= Consts.accountLockCount) {
					admin.setIsLocked(true);
					admin.setLockedDate(new Date());
				}
				admin.setLoginFailureCount(loginFailureCount);
				adminServiceImpl.save(admin);
				throw new IncorrectCredentialsException();
			}
			admin.setLoginIp(ip);
			admin.setLoginDate(new Date());
			admin.setLoginFailureCount(0);
			adminServiceImpl.save(admin);
			//添加日志===>登陆日志
			monitorOperateLogServices.addOperateLog(admin, "登录", "登录", "系统", "账号登录系统");
			return new SimpleAuthenticationInfo(new Principal(admin.getId(), username, admin.getHospitalInfo().getId()), password, getName());
		}
		throw new UnknownAccountException();
	}

	/**
	 * 获取授权信息
	 * @param principals principals
	 * @return 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
		if (principal != null) {
			List<Authority> modules = adminServiceImpl.findAuthorities(principal.getId());
			List<String> module_list = new ArrayList<String>();
			if(modules != null && modules.size() > 0){
				for(Authority module : modules){
					module_list.add(module.getAuthority());
				}
			}
			if (modules != null) {
				SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
				authorizationInfo.addStringPermissions(module_list);
				return authorizationInfo;
			}
		}
		return null;
	}

}