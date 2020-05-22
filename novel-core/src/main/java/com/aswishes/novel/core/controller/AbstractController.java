package com.aswishes.novel.core.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.aswishes.novel.core.common.AppConstants;
import com.aswishes.novel.core.common.AppUtil;
import com.aswishes.novel.core.common.Codes;
import com.aswishes.spring.QueryProperty;

/**
 * all action bean need to extend this abstract class. otherwise, you will get an error.
 */
public abstract class AbstractController {
	protected static final Logger logger = LoggerFactory.getLogger(AbstractController.class);
	protected static final String[] datePatterns = {
		"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "HH:mm:ss",
		"yyyy/MM/dd HH:mm:ss"
	};
	@Autowired
	protected HttpServletRequest request = null;
	@Autowired
	protected HttpServletResponse response = null;
	
	protected int pageNo = AppConstants.PAGE_NO;
	protected int pageSize = AppConstants.PAGE_SIZE;
	
	@InitBinder
    public void InitBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
        	@Override
        	public void setAsText(String text) throws IllegalArgumentException {
        		try {
					setValue(DateUtils.parseDate(text, datePatterns));
				} catch (ParseException e) {
					logger.error("Parse date error", e);
				}
        	}
        });
    } 
	
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
	
	protected List<QueryProperty> toQueryPropertyList(HttpServletRequest request) {
		return QueryProperty.toQueryProperty(AppUtil.reuqestMap(request));
	}
}
