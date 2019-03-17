package com.aswishes.novel.mvc.service.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aswishes.novel.common.web.SessionUtils;
import com.aswishes.novel.mvc.model.MUser;
import com.aswishes.novel.mvc.service.UserService;

@Component
public class UserAuthorizingRealm extends AuthenticatingRealm  {
	@Autowired
	private UserService userService;
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authToken;
		String username = token.getUsername();
		String password = new String(token.getPassword());
		
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

}