package com.aswishes.wn.mvc.service.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WnContext {
	/** 请求来源：移动终端 */
	public static final String FROM_MOBILE = "MOBILE";
	/** 请求来源：浏览器 */
	public static final String FROM_BROWSER = "BROWSER";
	
	// 请求来源
	private String from;
	// 请求报文
	private String reqString;
	
	private HttpServletRequest httpServletRequest;
	private HttpServletResponse httpServletResponse;
	
	public WnContext() {}
	
	public WnContext(String from, HttpServletRequest request, HttpServletResponse response) {
		this.from = from;
		this.httpServletRequest = request;
		this.httpServletResponse = response;
	}
	
	public WnContext(String from, String reqString, HttpServletRequest request, HttpServletResponse response) {
		this(from, request, response);
		this.reqString = reqString;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	public HttpServletResponse getHttpServletResponse() {
		return httpServletResponse;
	}

	public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}

	public String getReqString() {
		return reqString;
	}

	public void setReqString(String reqString) {
		this.reqString = reqString;
	}
	
	
}
