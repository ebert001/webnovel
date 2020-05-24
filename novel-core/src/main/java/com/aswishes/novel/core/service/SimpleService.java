package com.aswishes.novel.core.service;

import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.common.ReflectionUtils;
import com.aswishes.novel.core.dao.SimpleJdbcDao;

@Transactional
public abstract class SimpleService<T> {
	protected Class<T> entityClass;
	
	protected SimpleJdbcDao<T> dao;

	public abstract void setDao();
	
	@SuppressWarnings("unchecked")
	public SimpleService() {
		this.entityClass = (Class<T>) ReflectionUtils.getSuperClassGenricType(getClass());
	}

	public T getById(Long id) {
		return dao.getById(id);
	}
	
	public T getByName(String name) {
		return dao.getByName(name);
	}
	
	
}
