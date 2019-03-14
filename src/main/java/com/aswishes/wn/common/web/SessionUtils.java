package com.aswishes.wn.common.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.aswishes.wn.mvc.model.MUser;

public class SessionUtils {
	public static final String USER_KEY = "__USER_KEY";

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}
	
	public static void invalidate() {
		SecurityUtils.getSubject().logout();
	}
	
	public static void setUser(MUser user) {
		getSession().setAttribute(USER_KEY, user);
	}
	
	public static MUser getUser() {
		return (MUser) getSession().getAttribute(USER_KEY);
	}
	
	
}
