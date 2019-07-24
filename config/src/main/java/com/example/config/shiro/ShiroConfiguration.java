package com.example.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean(name = "hashedCredentialsMatcher")
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		// 指定加密方式方式
		credentialsMatcher.setHashAlgorithmName("MD5");
		// 加密的次数
		credentialsMatcher.setHashIterations(1024);
		// 存储凭证十六进制编码
		credentialsMatcher.setStoredCredentialsHexEncoded(true);
		return credentialsMatcher;
	}


	@Bean(name = "shiroRealm")
	@DependsOn("lifecycleBeanPostProcessor")
	public ShiroRealm shiroRealm(HashedCredentialsMatcher matcher) {
		ShiroRealm realm = new ShiroRealm();
		realm.setCredentialsMatcher(matcher);// 密码校验实现
		return realm;
	}


	@Bean(name = "ehCacheManager")
	@DependsOn("lifecycleBeanPostProcessor")
	public EhCacheManager ehCacheManager() {
		EhCacheManager ehCacheManager = new EhCacheManager();
		// ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache.xml");//配置文件路径
		return ehCacheManager;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置配置
		securityManager.setRealm(shiroRealm(matcher));
		// 设置缓存
		securityManager.setCacheManager(ehCacheManager());// 用户授权/认证信息Cache, 采用EhCache 缓存
		// 设置rememberMe管理器
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}
	
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 过滤器链定义管理器 filterChainDefinitionManager
		Map<String, String> filterChainDefinitionManager = new LinkedHashMap<>();
		filterChainDefinitionManager.put("/logout", "logout");// 登出
		// 登陆页面
		filterChainDefinitionManager.put("/login/*", "anon");// anon 可以理解为不拦截
		filterChainDefinitionManager.put("/js/**", "anon");// 静态资源不拦截
		filterChainDefinitionManager.put("/images/**", "anon");// 静态资源不拦截
		filterChainDefinitionManager.put("/css/**", "anon");// 静态资源不拦截
		filterChainDefinitionManager.put("/lib/**", "anon");// 静态资源不拦截

		filterChainDefinitionManager.put("/*", "authc");//表示需要认证才可以访问
		filterChainDefinitionManager.put("/**", "authc");//表示需要认证才可以访问

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);
		// 登入路径
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登陆成功后的路径
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 没有权限页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");

		return shiroFilterFactoryBean;
	}

	@Bean
	@ConditionalOnMissingBean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager);
		return aasa;
	}
	@Bean
	public SimpleCookie rememberMeCookie() {
		// 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		// <!-- 记住我cookie生效时间30天 ,单位秒;-->
		simpleCookie.setMaxAge(2592000);

		return simpleCookie;
	}
	@Bean
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		// 记住cokie
		cookieRememberMeManager.setCookie(rememberMeCookie());
		// rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
		cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
		return cookieRememberMeManager;
	}

}
