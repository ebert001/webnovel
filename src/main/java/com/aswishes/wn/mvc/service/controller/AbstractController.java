package com.aswishes.wn.mvc.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aswishes.wn.mvc.service.bean.WnContext;

public abstract class AbstractController {
	
	protected WnContext context;
	
	protected HttpServletRequest reuquest;
	
	protected HttpServletResponse response;
	
	protected HttpSession session;

	public final void setWnContext(WnContext context) {
		this.context = context;
		this.reuquest = context.getHttpServletRequest();
		this.response = context.getHttpServletResponse();
		this.session = this.reuquest.getSession();
	}
}
