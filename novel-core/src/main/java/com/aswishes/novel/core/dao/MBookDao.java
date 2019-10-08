package com.aswishes.novel.core.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.model.MBook;
import com.aswishes.novel.core.model.MBook.RetriveState;
import com.aswishes.spring.PageResult;
import com.aswishes.spring.Restriction;
import com.aswishes.spring.SqlAppender;
import com.aswishes.spring.SqlHelper;
import com.aswishes.spring.mapper.MapperHelper;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MBookDao extends SimpleJdbcDao<MBook> {

	public List<MBook> getBookList(Long userId) {
		return getList(MapperHelper.getMapper(MBook.class), Restriction.eq("author_id", userId));
	}

	public MBook getBook(Long bookId) {
		return getObjectBy(MapperHelper.getMapper(MBook.class), Restriction.eq("id", bookId));
	}
	
	public MBook getBook(String bookName) {
		return getObjectBy(MapperHelper.getMapper(MBook.class), Restriction.eq("name", bookName));
	}
	
	public MBook getBook(String bookName, Long websiteId) {
		return getObjectBy(MapperHelper.getMapper(MBook.class), 
				Restriction.eq("name", bookName), Restriction.eq("website_id", websiteId));
	}
	
	public PageResult<MBook> getUnauditBooks(int pageNo, int pageSize, Restriction[] restrictions) {
		return getPage(MapperHelper.getMapper(MBook.class), pageNo, pageSize, 
				restrictions);
	}
	
	public void deleteBook(Long bookId) {
		delete(Restriction.eq("id", bookId));
	}
	
	public void updateBook(Long bookId, Date updateTime) {
		update(SqlHelper.update(tableName).setColumns("update_time").whereColumns("id"), updateTime, bookId);
	}

	public void startPick(String bookName, Long websiteId) {
		SqlAppender appender = SqlAppender.create("update ").append(tableName).append(" set ")
				.append("retrive_state = ?, ", RetriveState.RETRIVING)
				.append("retrive_start_time = ?, ", new Date())
				.append("retrive_stop_time = null, ")
				.append("retrive_count = retrive_count + 1 ")
				.appendValues("where name = ? and website_id = ? ", bookName, websiteId);
		update(appender.getSql(), appender.getParamArray());
	}
	
	public void stopPick(String bookName, Long websiteId) {
		SqlAppender appender = SqlAppender.create("update ").append(tableName).append(" set ")
				.append("retrive_state = ?, ", RetriveState.FINISHED)
				.append("retrive_stop_time = ?, ", new Date())
				.appendValues("where name = ? and website_id = ? ", bookName, websiteId);
		update(appender.getSql(), appender.getParamArray());
	}
}