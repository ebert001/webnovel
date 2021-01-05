package com.aswishes.novel.core.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.common.db.SqlAppender;
import com.aswishes.novel.core.model.MForum;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MForumDao extends SimpleJdbcDao<MForum> {

	public MForumDao(DataSource dataSource) {
		super(dataSource);
	}

	public List<MForum> queryForumList(Long userId) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select * from ").append(tableName)
				.append("where user_id = :userId", userId)
				.append("order by create_time desc");
		return getList(appender, MForum.class);
	}
	
	public List<MForum> queryForumList(Long userId, int pageNo, int pageSize) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select * from ").append(tableName)
				.append("where user_id = :userId", userId)
				.append("order by create_time desc");
		return getList(appender, MForum.class, pageNo, pageSize);
	}
	
	public int queryForumListCount() {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select count(*) from ").append(tableName);
		return getNumber(appender, 0).intValue();
	}

	public void save(MForum entity) {
		
	}
	
	public void update(MForum entity) {
		
	}
}
