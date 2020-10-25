package com.aswishes.novel.core.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.common.db.SqlAppender;
import com.aswishes.novel.core.entity.MMemo;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MMemoDao extends SimpleJdbcDao<MMemo> {

	public MMemoDao(DataSource dataSource) {
		super(dataSource);
	}

	public List<MMemo> queryList(Long userId) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select * from ").append(tableName)
				.append("where user_id = :userId")
				.append("order by create_time desc");
		return getList(appender, MMemo.class);
	}
	
	public void save(MMemo entity) {
		
	}
	
	public void update(MMemo entity) {
		
	}
}
