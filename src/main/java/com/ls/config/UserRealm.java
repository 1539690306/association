package com.ls.config;

import java.security.Principal;

import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.ls.pojo.User;
import com.ls.service.UserService;

public class UserRealm extends AuthorizingRealm {
	@Autowired
	UserService service;

	/*
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		info.addRole(user.getRoles());
		info.addStringPermission(user.getPerms());
		return info;
	}

	/*
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordToken tok = (UsernamePasswordToken) token;// 判断用户名

		User user = service.findByUsername(tok.getUsername());
		if (user == null) {// 如果该用户不存在
			user = new User();
		}
		if (!tok.getUsername().equals(user.getName())) {

			return null;
		}
		return new SimpleAuthenticationInfo(user, user.getPassword(), "");// 判断密码
	}

}
