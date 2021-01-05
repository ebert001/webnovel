package com.aswishes.novel.core.service.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aswishes.novel.core.common.web.SessionUtils;
import com.aswishes.novel.core.entity.MUser;
import com.aswishes.novel.core.service.UserService;

@Component("myRealm")
public class UserAuthorizingRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(UserAuthorizingRealm.class);
	@Autowired
	private UserService userService;
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authToken;
		String username = token.getUsername();
		String password = new String(token.getPassword());
		logger.debug("User name: {}, password: {}", username, password);
		MUser user = userService.getUser(username);
		if (user == null) {
			throw new AuthenticationException("用户名或密码错误");
		}
		String tempPassword = userService.calPassword(user, password);
		if (!tempPassword.equals(user.getPwd())) {
			throw new AuthenticationException("用户名或密码错误");
		}
		SessionUtils.setUser(user);
		SimpleAuthenticationInfo authInfo = new SimpleAuthenticationInfo(user, password, this.getName());
		return authInfo;
	}
	
	/**
	 * 本例中该方法的调用时机为需授权资源被访问时
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.debug("authorization info: {}", principals.asList());
//		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		info.addRoles(roles);
//		info.addObjectPermissions(permissions);
		return null;
	}

}
