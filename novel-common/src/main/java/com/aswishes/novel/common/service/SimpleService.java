package com.aswishes.novel.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.common.ReflectionUtils;

@Transactional
public abstract class SimpleService<T> {
	protected static final Logger logger = LoggerFactory.getLogger(SimpleService.class);
	protected Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public SimpleService() {
		this.entityClass = (Class<T>) ReflectionUtils.getSuperClassGenricType(getClass());
	}

}
