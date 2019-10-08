package com.aswishes.novel.pcweb.listener;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.aswishes.novel.core.common.EnvInfo;
import com.aswishes.novel.core.common.web.PageSupport;

/**
 * 应用程序监听器，初始化ApplicationCotnext对象。
 */
public class NovelContextListener extends ContextLoaderListener {
	private static final Logger logger = LoggerFactory.getLogger(NovelContextListener.class);
	private static ApplicationContext appContext = null;
	
	public void contextInitialized(ServletContextEvent event) {
		logger.info("Start WebNovel System...");
		EnvInfo.loadEnvironment(event.getServletContext());
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
