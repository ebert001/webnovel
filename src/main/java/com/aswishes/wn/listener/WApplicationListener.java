package com.aswishes.wn.listener;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.aswishes.wn.common.web.PageSupport;

/**
 * 应用程序监听器，初始化ApplicationCotnext对象。
 */
public class WApplicationListener extends ContextLoaderListener {
	private static final Logger logger = LoggerFactory.getLogger(WApplicationListener.class);
	private static ApplicationContext appContext = null;
	
	public void contextInitialized(ServletContextEvent event) {
		logger.info("Start WebNovel System...");
		event.getServletContext().setAttribute("psupport", PageSupport.getInstance());
		
		super.contextInitialized(event);
		appContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		
		logger.info("Destroy WebNovel System...");
	}
	
	public static ApplicationContext getContext() {
		return appContext;
	}
}
