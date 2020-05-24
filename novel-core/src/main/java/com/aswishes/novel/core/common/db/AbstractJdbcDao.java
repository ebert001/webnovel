package com.aswishes.novel.core.common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.common.db.PageResult.Mode;
import com.aswishes.novel.core.exception.RSqlException;

public abstract class AbstractJdbcDao {
	protected static final Logger logger = LoggerFactory.getLogger(AbstractJdbcDao.class);
	protected JdbcTemplate jdbcTemplate;
	protected NamedParameterJdbcTemplate namedTemplate;
	
	public AbstractJdbcDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedTemplate = new NamedParameterJdbcTemplate(dataSource);
		
	}

	/**
	 * @param <E> the class of the object
	 * @param sql Select SQL, the result only contain one field
	 * @param requiredType 只能是基本类型的包装类型
	 * @param args Condition arguments
	 * @return 基本类型的数据对象
	 */
	@Transactional(noRollbackFor = {EmptyResultDataAccessException.class})
	public <E> E getSingleObject(String sql, Map<String, Object> paramMap, Class<E> requiredType) {
		try {
			return namedTemplate.queryForObject(sql, paramMap, requiredType);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Transactional(noRollbackFor = {EmptyResultDataAccessException.class})
	public <E> E getSingleObject(String sql, Object[] args, Class<E> requiredType) {
		try {
			return jdbcTemplate.queryForObject(sql, args, requiredType);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public <E> E getSingleObject(SqlAppender appender, Class<E> requiredType) {
		if (appender.isNamedModel()) {
			return getSingleObject(appender.getSql(), appender.getParamMap(), requiredType);
		} else {
			return getSingleObject(appender.getSql(), appender.getParamArray(), requiredType);
		}
	}
	
	public Number getNumber(String sql, Map<String, Object> paramMap, Number dv) {
		Number n = getSingleObject(sql, paramMap, Number.class);
		if (n == null) {
			return dv;
		}
		return n;
	}
	
	public Number getNumber(String sql, Object[] args, Number dv) {
		Number n = getSingleObject(sql, args, Number.class);
		if (n == null) {
			return dv;
		}
		return n;
	}
	
	public Number getNumber(SqlAppender appender, Number dv) {
		if (appender.isNamedModel()) {
			return getNumber(appender.getSql(), appender.getParamMap(), dv);
		} else {
			return getNumber(appender.getSql(), appender.getParamArray(), dv);
		}
	}
	
	@Transactional(noRollbackFor = {EmptyResultDataAccessException.class})
	public <E> E getObject(String sql, Map<String, Object> paramMap, RowMapper<E> rowMapper) {
		try {
			return namedTemplate.queryForObject(sql, paramMap, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Transactional(noRollbackFor = {EmptyResultDataAccessException.class})
	public <E> E getObject(String sql, Object[] args, RowMapper<E> rowMapper) {
		try {
			return jdbcTemplate.queryForObject(sql, args, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public <E> E getObject(SqlAppender appender, RowMapper<E> rowMapper) {
		if (appender.isNamedModel()) {
			return getObject(appender.getSql(), appender.getParamMap(), rowMapper);
		} else {
			return getObject(appender.getSql(), appender.getParamArray(), rowMapper);
		}
	}
	
	public <E> E getObject(String sql, Map<String, Object> paramMap, Class<E> clazz) {
		return getObject(sql, paramMap, BeanPropertyRowMapper.newInstance(clazz));
	}
	
	public <E> E getObject(String sql, Object[] args, Class<E> clazz) {
		return getObject(sql, args, BeanPropertyRowMapper.newInstance(clazz));
	}
	
	public <E> E getObject(SqlAppender appender, Class<E> clazz) {
		if (appender.isNamedModel()) {
			return getObject(appender.getSql(), appender.getParamMap(), clazz);
		} else {
			return getObject(appender.getSql(), appender.getParamArray(), clazz);
		}
	}
	
	public <E> List<E> getList(String sql, Map<String, Object> paramMap, RowMapper<E> rowMapper) {
		return namedTemplate.query(sql, paramMap, rowMapper);
	}
	
	public <E> List<E> getList(String sql, Object[] args, RowMapper<E> rowMapper) {
		return jdbcTemplate.query(sql, args, rowMapper);
	}
	
	public <E> List<E> getList(SqlAppender appender, RowMapper<E> rowMapper) {
		if (appender.isNamedModel()) {
			return getList(appender.getSql(), appender.getParamMap(), rowMapper);
		} else {
			return getList(appender.getSql(), appender.getParamArray(), rowMapper);
		}
	}
	
	public <E> List<E> getList(String sql, Map<String, Object> paramMap, Class<E> clazz) {
		return getList(sql, paramMap, BeanPropertyRowMapper.newInstance(clazz));
	}
	
	public <E> List<E> getList(String sql, Object[] args, Class<E> clazz) {
		return getList(sql, args, BeanPropertyRowMapper.newInstance(clazz));
	}
	
	public <E> List<E> getList(SqlAppender appender, Class<E> clazz) {
		if (appender.isNamedModel()) {
			return getList(appender.getSql(), appender.getParamMap(), clazz);
		} else {
			return getList(appender.getSql(), appender.getParamArray(), clazz);
		}
	}
	
	public <E> List<E> getList(String sql, Object paramObj, RowMapper<E> rowMapper) {
		return namedTemplate.query(sql, new BeanPropertySqlParameterSource(paramObj), rowMapper);
	}
	
	public <E> List<E> getList(String sql, Object paramObj, Class<E> clazz) {
		return getList(sql, paramObj, BeanPropertyRowMapper.newInstance(clazz));
	}
	
	public <E> List<E> getList(String sql, Map<String, Object> paramMap, RowMapper<E> rowMapper, long pageNo, int pageSize) {
		sql += getLimitSql(pageNo, pageSize);
		return namedTemplate.query(sql, paramMap, rowMapper);
	}
	
	public <E> List<E> getList(String sql, Object[] args, RowMapper<E> rowMapper, long pageNo, int pageSize) {
		sql += getLimitSql(pageNo, pageSize);
		return jdbcTemplate.query(sql, args, rowMapper);
	}
	
	public <E> List<E> getList(SqlAppender appender, RowMapper<E> rowMapper, long pageNo, int pageSize) {
		if (appender.isNamedModel()) {
			return getList(appender.getSql(), appender.getParamMap(), rowMapper, pageNo, pageSize);
		} else {
			return getList(appender.getSql(), appender.getParamArray(), rowMapper, pageNo, pageSize);
		}
	}
	
	public <E> List<E> getList(String sql, Map<String, Object> paramMap, Class<E> clazz, long pageNo, int pageSize) {
		return getList(sql, paramMap, BeanPropertyRowMapper.newInstance(clazz), pageNo, pageSize);
	}
	
	public <E> List<E> getList(String sql, Object[] args, Class<E> clazz, long pageNo, int pageSize) {
		return getList(sql, args, BeanPropertyRowMapper.newInstance(clazz), pageNo, pageSize);
	}
	
	public <E> List<E> getList(SqlAppender appender, Class<E> clazz, long pageNo, int pageSize) {
		if (appender.isNamedModel()) {
			return getList(appender.getSql(), appender.getParamMap(), clazz, pageNo, pageSize);
		} else {
			return getList(appender.getSql(), appender.getParamArray(), clazz, pageNo, pageSize);
		}
	}

	public <E> PageResult<E> getPage(String countSql, String sql, Map<String, Object> paramMap, final RowMapper<E> bean, long pageNo, int pageSize) {
		PageResult<E> result = new PageResult<E>(pageNo, pageSize) {
			@Override
			public long queryCount() throws Exception {
				return getNumber(countSql, paramMap, 0).longValue();
			}
			@Override
			public List<E> query(long startIndex, long pageNo, int pageSize) throws Exception {
				return getList(sql, paramMap, bean, pageNo, pageSize);
			}
		};
		try {
			result.paging();
		} catch (Exception e) {
			throw new RSqlException(e);
		}
		return result;
	}
	
	public <E> PageResult<E> getPage(SqlAppender countSql, SqlAppender sql, final RowMapper<E> bean, long pageNo, int pageSize) {
		PageResult<E> result = new PageResult<E>(pageNo, pageSize) {
			@Override
			public long queryCount() throws Exception {
				return getNumber(countSql, 0).longValue();
			}
			@Override
			public List<E> query(long startIndex, long pageNo, int pageSize) throws Exception {
				return getList(sql, bean, pageNo, pageSize);
			}
		};
		try {
			result.paging();
		} catch (Exception e) {
			throw new RSqlException(e);
		}
		return result;
	}
	
	public <E> PageResult<E> getPage(SqlAppender countSql, SqlAppender sql, Class<E> clazz, long pageNo, int pageSize) {
		return getPage(countSql, sql, BeanPropertyRowMapper.newInstance(clazz), pageNo, pageSize);
	}
	
	public <E> PageResult<E> getPage(SqlAppender sql, final RowMapper<E> bean, long pageNo, int pageSize) {
		PageResult<E> result = new PageResult<E>(pageNo, pageSize) {
			@Override
			public long queryCount() throws Exception {
				return -1;
			}
			@Override
			public List<E> query(long startIndex, long pageNo, int pageSize) throws Exception {
				return getList(sql, bean, pageNo, pageSize);
			}
		}.setMode(Mode.COUNT);
		try {
			result.paging();
		} catch (Exception e) {
			throw new RSqlException(e);
		}
		return result;
	}
	
	public <E> PageResult<E> getPage(SqlAppender sql, Class<E> clazz, long pageNo, int pageSize) {
		return getPage(sql, BeanPropertyRowMapper.newInstance(clazz), pageNo, pageSize);
	}

	protected String getLimitSql(long pageNo, long pageSize) {
		return " limit " + getStartIndex(pageNo, pageSize) + "," + pageSize;
	}

	protected long getStartIndex(long pageNo, long pageSize) {
		return (pageNo - 1) * pageSize;
	}
	
	@Transactional
	public void update(SqlAppender appender) {
		if (appender.isNamedModel()) {
			namedTemplate.update(appender.getSql(), appender.getParamMap());
		} else {
			jdbcTemplate.update(appender.getSql(), appender.getParamArray());
		}
	}
	
	@Transactional
	public void save(String sql, Map<String, Object> paramMap) {
		namedTemplate.update(sql, paramMap);
	}
	
	@Transactional
	public Number saveAndGet(String sql, Map<String, Object> paramMap) {
		KeyHolder holder = new GeneratedKeyHolder();
		int r = namedTemplate.update(sql, new MapSqlParameterSource(paramMap), holder);
		if (r > 0) {
			return holder.getKey();
		}
		return -1;
	}
	
	@Transactional
	public void save(String sql, Object...args) {
		jdbcTemplate.update(sql, args);
	}
	
	@Transactional
	public Number saveAndGet(String sql, Object[] args) {
		KeyHolder holder = new GeneratedKeyHolder();
		int r = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				for (int i = 0; i < args.length; i++) {
					ps.setObject(i + 1, args[i]);
				}
				return ps;
			}
		}, holder);
		if (r > 0) {
			return holder.getKey();
		}
		return -1;
	}

	@Transactional
	public void save(SqlAppender appender) {
		if (appender.isNamedModel()) {
			save(appender.getSql(), appender.getParamMap());
		} else {
			save(appender.getSql(), appender.getParamArray());
		}
	}
	
	@Transactional
	public Number saveAndGet(SqlAppender appender) {
		if (appender.isNamedModel()) {
			return saveAndGet(appender.getSql(), appender.getParamMap());
		} else {
			return saveAndGet(appender.getSql(), appender.getParamArray());
		}
	}
}
