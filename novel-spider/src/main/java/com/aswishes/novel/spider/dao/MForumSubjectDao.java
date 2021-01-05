package com.aswishes.novel.spider.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.common.db.SqlAppender;
import com.aswishes.novel.spider.entity.MForumSubject;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MForumSubjectDao extends SimpleJdbcDao<MForumSubject> {

	public MForumSubjectDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	protected void setTableName() {
		this.tableName = "m_forum_subject";
	}
	
	public void updateReadTimes(Long id) {
		this.jdbcTemplate.update("update novel_forum_subject set read_times = read_times + 1 where id = ?", id);
	}
	
	public void updateReplyTimes(Long id) {
		this.jdbcTemplate.update("update novel_forum_subject set reply_times = reply_times + 1 where id = ?", id);
	}
	
	public int getForumSubjectCount(Long userId) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select count(*) from ").append(tableName)
				.append("where user_id = :userId", userId);
		return getNumber(appender, 0).intValue();
	}

	public List<MForumSubject> queryForumSubjectList(Long userId, int pageNo, int pageSize) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select * from ").append(tableName)
				.append("where user_id = :userId", userId)
				.append("order by create_time desc");
		return getList(appender, MForumSubject.class, pageNo, pageSize);
	}
	
	public List<MForumSubject> queryForumSubjectList(int pageNo, int pageSize) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select * from ").append(tableName)
				.append("order by create_time desc");
		return getList(appender, MForumSubject.class, pageNo, pageSize);
	}
	
	public int getForumSubjectCount() {
		return getCount();
	}
	
	public void save(MForumSubject entity) {
		
	}
	
	public void update(MForumSubject entity) {
		
	}
}
