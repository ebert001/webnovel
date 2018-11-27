package com.aswishes.wn.mvc.service.controller;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.aswishes.wn.common.AppConstants;
import com.aswishes.wn.common.AppUtil;
import com.aswishes.wn.exception.WnException;
import com.aswishes.wn.mvc.service.bean.RequestMessage;
import com.aswishes.wn.mvc.service.bean.ResponseMessage;
import com.aswishes.wn.mvc.service.bean.WnContext;

public class ServiceController {

	private WnContext context;

	public ServiceController(WnContext context) {
		this.context = context;
	}

	public void doService() throws Exception {
		String from = context.getFrom();
		if (!WnContext.FROM_MOBILE.equals(from)) {
			ResponseMessage responseMsg = new ResponseMessage(102, "不支持的终端类型");
			
			String data = AppUtil.toJsonString(responseMsg);
			IOUtils.write(data, context.getHttpServletResponse().getOutputStream(), AppConstants.CHARSET_UTF_8);
			return;
		}
		initServiceInstance();
	}

	private void initServiceInstance() throws Exception {
		String reqString = context.getReqString();
		
		RequestMessage requestMsg = JSON.parseObject(reqString, RequestMessage.class);
		String requestId = requestMsg.getRequestId();
		if (!AppUtil.containBean(requestId)) { // spring容器中不包含此id
			throw new WnException(103, "不存在的请求id");
		}
		Object obj = AppUtil.getBean(requestId);
		DispatcherHelper.invoke(obj, "setWnContext", context);
		DispatcherHelper.invoke(obj, requestMsg.getEventName());
	}

}