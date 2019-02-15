package com.aswishes.wn.mvc.service.realm;

import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

public class ShiroPermissionFactory extends ShiroFilterFactoryBean {

	@Override
	public void setFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap) {
//		this.getFilterChainDefinitionMap().
		super.setFilterChainDefinitionMap(filterChainDefinitionMap);
	}
}
