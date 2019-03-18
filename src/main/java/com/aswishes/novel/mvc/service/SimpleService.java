package com.aswishes.novel.mvc.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.common.ReflectionUtils;
import com.aswishes.novel.mvc.dao.SimpleJdbcDao;
import com.aswishes.spring.PageResultWrapper;
import com.aswishes.spring.QueryProperty;

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
	
	public List<T> getList(List<QueryProperty> params) {
		return dao.getList(params);
	}
	
	public PageResultWrapper<T> getPage(int pageNo, int pageSize, List<QueryProperty> params) {
		return dao.getPage(pageNo, pageSize, params);
	}
	
	public Long saveAndGetId(T t) {
		return dao.saveAndGetId(t);
	}
	
	public void save(T t) {
		dao.save(t);
	}
	
	public void update(T t) {
		dao.updateByPK(t, false);
	}
	
	public void delete(Long id) {
		dao.delete(id);
	}
	
}
