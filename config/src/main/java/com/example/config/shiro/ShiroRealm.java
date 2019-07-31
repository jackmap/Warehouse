package com.example.config.shiro;

import com.example.controller.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.controller.sys.entity.SysUser;


/**
 * 获取用户的角色和权限信息 Created by bamboo on 2017/5/10.
 */
public class ShiroRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

	// 一般这里都写的是servic，我省略了service的接口和实现方法直接调用的dao
	@Autowired
	private UserService userService;

	/*
	@Autowired
	private TbPermissionsDao tbPermissionsDao;
	*/

	/**
	 * 登录认证
	 *
	 * @param authenticationToken
	 *            身份验证令牌
	 * @return
	 * @throws AuthenticationException
	 *             身份验证异常
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		logger.info("验证当前Subject时获取到token为：" + token.toString());
		UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
		//根据名字查询数据库
		SysUser tbsysuser = userService.findUserByName(token.getUsername());
		ByteSource credentialsSalt = ByteSource.Util.bytes(tbsysuser.getSalt());
		SimpleAuthenticationInfo info =new SimpleAuthenticationInfo(token.getUsername(), tbsysuser.getPassword(),credentialsSalt,this.getClass().getName());
		SecurityUtils.getSubject().getSession().setAttribute("user",tbsysuser);
		return	info;
	}


	/**
	 * 权限认证
	 *
	 * @param principalCollection
	 *            主要收集
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 获取当前登录输入的用户名，等价于(String)
		// principalCollection.fromRealm(getName()).iterator().next();
		// String loginName = (String) super.getAvailablePrincipal(principalCollection);
		SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
		// 到数据库查是否有此对象
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		// user = userMapper.findByName(loginName);
		SysUser data_user = userService.findUserByName(user.getUsername());
		if (data_user != null) {
			// 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// 用户的角色集合
			// info.addRoles(user.getRoleStrlist());
			// 用户的权限集合
			// info.addStringPermissions(user.getPerminsStrlist());
			return info;
		}
		// 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
		return null;
	}

}