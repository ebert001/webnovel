package com.aswishes.novel.mvc.service.realm;

import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroPermissionFactory extends ShiroFilterFactoryBean {
	private static final Logger logger = LoggerFactory.getLogger(ShiroPermissionFactory.class);

	@Override
	public void setFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap) {
//		this.getFilterChainDefinitionMap().
		logger.debug("Ready to load permissions.");
		super.setFilterChainDefinitionMap(filterChainDefinitionMap);
	}
}
