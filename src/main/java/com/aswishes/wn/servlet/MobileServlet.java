package com.aswishes.wn.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.aswishes.wn.common.AppConstants;
import com.aswishes.wn.exception.WnException;
import com.aswishes.wn.mvc.service.bean.ResponseMessage;
import com.aswishes.wn.mvc.service.bean.WnContext;
import com.aswishes.wn.mvc.service.controller.ServiceController;

/**
 * 移动能力访问入口。请求报文为json
 * @author lizhou
 *
 */
public class MobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(101, "不支持的请求类型");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InputStream inStream = request.getInputStream();
		String requestMsg = IOUtils.toString(inStream, AppConstants.CHARSET_UTF_8);
		logger.debug("request message: " + requestMsg);
		
		WnContext context = new WnContext(WnContext.FROM_MOBILE, requestMsg, request, response);
		ServiceController wrapper = new ServiceController(context);
		try {
			wrapper.doService();
		} catch (Exception e) {
			logger.error("mobile entrance has an error", e);
			String data = null;
			ResponseMessage responseMsg = null;
			if (e instanceof WnException) {
				WnException we = (WnException) e;
				responseMsg = new ResponseMessage(we.getCode(), we.getMessage());
				data = JSON.toJSONString(responseMsg);
			} else {
				responseMsg = new ResponseMessage(100, "系统错误");
				data = JSON.toJSONString(responseMsg);
			}
			IOUtils.write(data, context.getHttpServletResponse().getOutputStream(), AppConstants.CHARSET_UTF_8);
		}
	}

	private static final Logger logger = LoggerFactory.getLogger(MobileServlet.class);
}
