package com.aswishes.wn.common.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.aswishes.wn.mvc.model.WnUser;

public class SessionUtils {
	public static final String USER_KEY = "__USER_KEY";

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}
	
	public static void setUser(WnUser user) {
		getSession().setAttribute(USER_KEY, user);
	}
	
	public static WnUser getUser() {
		return (WnUser) getSession().getAttribute(USER_KEY);
	}
	
	public static void invalidate() {
		getSession().stop();
	}
}
