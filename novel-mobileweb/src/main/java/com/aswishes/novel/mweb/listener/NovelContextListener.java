package com.aswishes.novel.mweb.listener;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import com.aswishes.novel.core.common.EnvInfo;
import com.aswishes.novel.core.common.web.PageSupport;

public class NovelContextListener extends ContextLoaderListener  {
	private static final Logger logger = LoggerFactory.getLogger(NovelContextListener.class);
	
	@Override
    public void contextInitialized(ServletContextEvent sce) {
		EnvInfo.loadEnvironment(sce.getServletContext());
    	logger.debug("Spring context initialize...");
    	super.contextInitialized(sce);
    	logger.debug("Spring context initialize finished...");
    	sce.getServletContext().setAttribute("psupport", PageSupport.getInstance());
    	// 加载资源
    	PageSupport.getInstance().loadProp("app.prop");
    	
    }

	@Override
    public void contextDestroyed(ServletContextEvent sce) {
    	try {
	        logger.debug("begin contextDestroy...");
    	} catch (Exception e) {
    		logger.error("context destroy error", e);
    	}
        super.contextDestroyed(sce);
        // 强制退出
        System.exit(1);
    }
}
