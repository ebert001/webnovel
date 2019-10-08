package com.aswishes.novel.core.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aswishes.novel.core.common.ReflectionUtils;
import com.aswishes.spring.PageResult;
import com.aswishes.spring.QueryProperty;
import com.aswishes.spring.Restriction;
import com.aswishes.spring.dao.AbstractJdbcDao;
import com.aswishes.spring.mapper.Mapper;
import com.aswishes.spring.mapper.MapperHelper;

public class SimpleJdbcDao<T> extends AbstractJdbcDao {
	protected static final Logger logger = LoggerFactory.getLogger(SimpleJdbcDao.class);
	protected Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public SimpleJdbcDao() {
		showSql = true;
		this.entityClass = (Class<T>) ReflectionUtils.getSuperClassGenricType(getClass());
		if (this.tableName == null) {
			setTableName();
		}
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
		this.tableName = mapper.tableName();
	}
	
	public T getById(Long id) {
		return getObjectBy(MapperHelper.getMapper(entityClass), Restriction.eq("id", id));
	}
	
	public T getByName(String name) {
		logger.debug("Entity class: {}", entityClass);
		return getObjectBy(MapperHelper.getMapper(entityClass), Restriction.eq("name", name));
	}
	
	public List<T> getList(List<QueryProperty> params) {
		return getList(MapperHelper.getMapper(entityClass), QueryProperty.toRestrictions(params));
	}
	
	public PageResult<T> getPage(int pageNo, int pageSize, List<QueryProperty> params) {
		return getPage(MapperHelper.getMapper(entityClass), pageNo, pageSize, QueryProperty.toRestrictions(params));
	}
	
}
