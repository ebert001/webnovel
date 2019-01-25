package com.aswishes.wn.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aswishes.wn.common.Codes;

/**
 * all action bean need to extend this abstract class. otherwise, you will get an error.
 */
public abstract class AbstractController {
	protected static final Logger log = LoggerFactory.getLogger(AbstractController.class);
	
	protected HttpServletRequest request = null;
	protected HttpServletResponse response = null;
	protected HttpSession session = null;
	
	/**
	 * 设置响应消息.取值方式request.getAttrbute("");
	 * @param msg
	 */
	protected void setResponseMessage(String msg) {
		request.setAttribute(Codes.RESPONSE_MESSAGE, msg);
	}
	
	/**
	 * 从HttpSession中取值，并转换成目标类型。
	 * @param <T> 目标类型
	 * @param session HttpSession
	 * @param key 键
	 * @return 目标类型对象
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getAttribute(HttpSession session, String key) {
		Object obj = session.getAttribute(key);
		return obj == null ? null : (T) obj;
	}
}
