package com.aswishes.novel.core.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aswishes.novel.core.common.ReflectionUtils;
import com.aswishes.novel.core.common.db.AbstractJdbcDao;
import com.aswishes.novel.core.common.db.Mapper;
import com.aswishes.novel.core.common.db.SqlAppender;

public class SimpleJdbcDao<T> extends AbstractJdbcDao {
	protected static final Logger logger = LoggerFactory.getLogger(SimpleJdbcDao.class);
	protected Class<T> entityClass;
	protected String tableName;
	
	@SuppressWarnings("unchecked")
	public SimpleJdbcDao(DataSource dataSource) {
		super(dataSource);
		this.entityClass = (Class<T>) ReflectionUtils.getSuperClassGenricType(getClass());
		if (this.tableName == null) {
			setTableName();
		}
	}

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
	
	public List<T> getList(int pageNo, int pageSize, String propName, Object propValue) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select * from ").append(tableName);
		if (propName != null) {
			appender.append("where").append(propName).append("=").append(":1", propValue);
		}
		return getList(appender, entityClass, pageNo, pageSize);
	}
	
	public List<T> getList(int pageNo, int pageSize) {
		return getList(pageNo, pageSize, null, null);
	}
	
	public T getObject(String propName, Object propValue) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select * from ").append(tableName)
				.append("where").append(propName).append("=").append(":1", propValue);
		return getObject(appender, entityClass);
	}
	
	public T getById(Long id) {
		return getObject("id", id);
	}
	
	public T getByName(String name) {
		return getObject("name", name);
	}
	
	public int getCount(String propName, Object propValue) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select count(*) from ").append(tableName);
		if (propName != null) {
			appender.append("where").append(propName).append("=").append(":1", propValue);
		}
		return getNumber(appender, 0).intValue();
	}

	public int getCount() {
		return getCount(null, null);
	}
	
	
	public void deleteById(Long id) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("delete from ").append(tableName)
				.append("where id = :id", id);
		update(appender);
	}
	
	
}
