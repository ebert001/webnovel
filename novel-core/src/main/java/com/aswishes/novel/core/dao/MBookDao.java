package com.aswishes.novel.core.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.common.db.PageResult;
import com.aswishes.novel.core.common.db.SqlAppender;
import com.aswishes.novel.core.model.MBook;
import com.aswishes.novel.core.model.MBook.RetriveState;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MBookDao extends SimpleJdbcDao<MBook> {

	public MBookDao(DataSource dataSource) {
		super(dataSource);
	}

	public List<MBook> getBookList(Long userId) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select * from ").append(tableName)
				.append("where author_id = :userId", userId);
		return getList(appender, MBook.class);
	}

	public MBook getBook(String bookName, Long websiteId) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select * from ").append(tableName)
				.append("where name = :name", bookName)
				.append("and website_id = :websiteId", websiteId);
		return getObject(appender, MBook.class);
	}
	
	public PageResult<MBook> getUnauditBooks(int pageNo, int pageSize) {
		SqlAppender countSql = SqlAppender.namedModel()
				.append("select count(*) from ").append(tableName)
				.append("where state = :state", MBook.State.UNAUDITED.getValue());
		SqlAppender sql = SqlAppender.namedModel()
				.append("select * from ").append(tableName)
				.append("where state = :state", MBook.State.UNAUDITED.getValue());
		
		return getPage(countSql, sql, MBook.class, pageNo, pageSize);
	}
	
	public PageResult<MBook> getClickTop(int pageNo, int pageSize) {
		SqlAppender sql = SqlAppender.namedModel()
				.append("select * from ").append(tableName)
				.append("where state = :state", MBook.State.NORMALE.getValue())
				.append("order by click_times desc ");
		return getPage(sql, MBook.class, pageNo, pageSize);
	}
	
	public void updateBook(Long bookId, Date updateTime) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("update m_book set ")
				.append("update_time = :updateTime, ", updateTime)
				.append("where id = :id", bookId);
		update(appender);
	}

	public void startPick(String bookName, Long websiteId) {
		SqlAppender appender = SqlAppender.create("update ").append(tableName).append(" set ")
				.append("retrive_state = ?, ", RetriveState.RETRIVING)
				.append("retrive_start_time = ?, ", new Date())
				.append("retrive_stop_time = null, ")
				.append("retrive_count = retrive_count + 1 ")
				.append("where name = ? and website_id = ? ", bookName, websiteId);
		update(appender);
	}
	
	public void stopPick(String bookName, Long websiteId) {
		SqlAppender appender = SqlAppender.create("update ").append(tableName).append(" set ")
				.append("retrive_state = ?, ", RetriveState.FINISHED)
				.append("retrive_stop_time = ?, ", new Date())
				.append("where name = ? and website_id = ? ", bookName, websiteId);
		update(appender);
	}
}
