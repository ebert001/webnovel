package com.aswishes.novel.core.dao;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.common.db.PageResult;
import com.aswishes.novel.core.common.db.SqlAppender;
import com.aswishes.novel.core.model.MBookshelf;

/**
 * 对应的数据库表为 novel_bookshelf
 */
@Repository
@Transactional
public class MBookshelfDao extends SimpleJdbcDao<MBookshelf> {

	public MBookshelfDao(DataSource dataSource) {
		super(dataSource);
	}

	public MBookshelf getBook(Long userId, Long bookId) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select * from ").append(tableName)
				.append("where user_id = :userId", userId)
				.append("and book_id = :bookId", bookId);
		return getObject(appender, MBookshelf.class); 
	}
	
	public PageResult<MBookshelf> getBooks(int pageNo, int pageSize, Long userId) {
		SqlAppender countSql = SqlAppender.namedModel()
				.append("select count(*) from ").append(tableName)
				.append("where user_id = :userId", userId);
		SqlAppender sql = SqlAppender.namedModel()
				.append("select * from ").append(tableName)
				.append("where user_id = :userId")
				.append("order by create_time desc");
		
		return getPage(countSql, sql, MBookshelf.class, pageNo, pageSize);
	}
	
	public int getCount(Long userId) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select count(*) from ").append(tableName)
				.append("where user_id = :userId", userId);
		return getNumber(appender, 0).intValue();
	}

}
