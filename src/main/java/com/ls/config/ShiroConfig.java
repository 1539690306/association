package com.ls.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

//自定义的过滤器导入这个包
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

	/**
	 * 创建ShiroFilterFactoryBean
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(
			@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 添加shiro内置过滤器
		/*
		 * 常用过滤器： anon:无需认证（登录）可以访问 authc:必须认证才可以访问 user: 如果使用rememberMe的功能可以直接访问
		 * perms:该资源必须得到资源权限才可以访问 role: 该资源必须得到角色权限才可以访问
		 */
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		// 设置自定义filter
		Map<String, Filter> filterMap = new LinkedHashMap<>();
		filterMap.put("roleOrFilter", new CustomRolesOrAuthorizationFilter());

		/*
		 * filterChainDefinitionMap.put("/add", "authc");
		 * filterChainDefinitionMap.put("/update", "authc");
		 */
		filterChainDefinitionMap.put("/toRegist", "anon");
		filterChainDefinitionMap.put("/regist", "anon");	
		filterChainDefinitionMap.put("/sendEmail", "anon");
		filterChainDefinitionMap.put("/toForgotPwd", "anon");
		filterChainDefinitionMap.put("/sendEmailPwd", "anon");
		filterChainDefinitionMap.put("/forgotPwd", "anon");
		
		//活动相关
		filterChainDefinitionMap.put("/findAllAct", "anon");
		filterChainDefinitionMap.put("/regAct", "anon");
		filterChainDefinitionMap.put("/guestRegAct", "anon");
		filterChainDefinitionMap.put("/findActByTitle", "anon");

		filterChainDefinitionMap.put("/mail", "anon");
		filterChainDefinitionMap.put("/login", "anon");// 要将登陆的接口放出来，不然没权限访问登陆的接口
		// 授权过滤器
		filterChainDefinitionMap.put("/findUserByDept", "roleOrFilter[管理员,超级管理员]");
		filterChainDefinitionMap.put("/toAddUser", "roleOrFilter[管理员,超级管理员]");
		filterChainDefinitionMap.put("/toAddNotice", "roleOrFilter[管理员,超级管理员]");
		filterChainDefinitionMap.put("/toAddGoods", "roleOrFilter[管理员,超级管理员]");
		filterChainDefinitionMap.put("/toAddDept", "roleOrFilter[管理员,超级管理员]");
		filterChainDefinitionMap.put("/deleteUser", "roleOrFilter[管理员,超级管理员]");
		filterChainDefinitionMap.put("/*", "authc");
		filterChainDefinitionMap.put("/logout", "logout");
		shiroFilterFactoryBean.setFilters(filterMap);
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		shiroFilterFactoryBean.setLoginUrl("/toLogin");
		shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

		return shiroFilterFactoryBean;
	}

	/**
	 * 创建DefaultWebSecurityManager
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 关联realm
		securityManager.setRealm(userRealm);

		return securityManager;
	}

	@Bean("userRealm")
	public UserRealm getUserRealm() {

		return new UserRealm();
	}

	/*
	 * 配置 ShiroDialect
	 */
	@Bean
	public ShiroDialect getShiroDialect() {

		return new ShiroDialect();
	}

}
