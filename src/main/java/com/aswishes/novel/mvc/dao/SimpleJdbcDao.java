package com.aswishes.novel.mvc.dao;

import java.util.List;

import com.aswishes.novel.common.ReflectionUtils;
import com.aswishes.spring.PageResultWrapper;
import com.aswishes.spring.QueryProperty;
import com.aswishes.spring.Restriction;
import com.aswishes.spring.dao.AbstractJdbcDao;
import com.aswishes.spring.mapper.Mapper;
import com.aswishes.spring.mapper.MapperHelper;

public class SimpleJdbcDao<T> extends AbstractJdbcDao {
	protected Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public SimpleJdbcDao() {
		this.entityClass = (Class<T>) ReflectionUtils.getSuperClassGenricType(getClass());
	}

	@Override
	protected void setTableName() {
		if (entityClass == null) {
			return;
		}
		Mapper mapper = entityClass.getAnnotation(Mapper.class);
		if (mapper == null) {
			return;
		}
		this.tableName = mapper.name();
	}
	
	public T getById(Long id) {
		return getObjectBy(MapperHelper.getMapper(entityClass), Restriction.eq("id", id));
	}
	
	public T getByName(String name) {
		return getObjectBy(MapperHelper.getMapper(entityClass), Restriction.eq("name", name));
	}
	
	public List<T> getList(List<QueryProperty> params) {
		return getList(MapperHelper.getMapper(entityClass), QueryProperty.toRestrictions(params));
	}
	
	public PageResultWrapper<T> getPage(int pageNo, int pageSize, List<QueryProperty> params) {
		return getPage(MapperHelper.getMapper(entityClass), pageNo, pageSize, QueryProperty.toRestrictions(params));
	}
	
}