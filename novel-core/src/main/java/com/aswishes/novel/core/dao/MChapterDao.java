package com.aswishes.novel.core.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.common.db.PageResult;
import com.aswishes.novel.core.common.db.SqlAppender;
import com.aswishes.novel.core.entity.MChapter;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MChapterDao extends SimpleJdbcDao<MChapter> {

	public MChapterDao(DataSource dataSource) {
		super(dataSource);
	}

	public List<MChapter> readCatalogs(Long bookId) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select id,subject,book_id,write_time from ").append(tableName)
				.append("where book_id = :bookId");
		return getList(appender, MChapter.class);
	}

	public MChapter getChapter(Long chapterId) {
		return getById(chapterId);
	}
	
	public MChapter getChapter(Long bookId, String subject) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select * from").append(tableName)
				.append("where book_id = :bookId")
				.append("and subject = :subject", subject);
		return getObject(appender, MChapter.class);
	}
	
	public int getMaxSerialNo(Long bookId) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select max(serial_no) from ").append(tableName)
				.append("where book_id = :book_id", bookId);
		return getNumber(appender, 0).intValue();
	}
	
	public PageResult<MChapter> findChapters(int pageNo, int pageSize, Long bookId) {
		SqlAppender countSql = SqlAppender.namedModel()
				.append("select count(*) from ").append(tableName)
				.append("where book_id = :bookId and state = :state ", bookId, MChapter.State.NORMALE.getValue());
		SqlAppender sql = SqlAppender.namedModel()
				.append("select id, subject, book_id, state, write_time, input_time from ").append(tableName)
				.append("where book_id = :bookId and state = :state ", bookId, MChapter.State.NORMALE.getValue());
		
		return getPage(countSql, sql, MChapter.class, pageNo, pageSize);
	}
	
	public PageResult<MChapter> findUnauditChapters(int pageNo, int pageSize, Long bookId, int state) {
		SqlAppender countSql = SqlAppender.namedModel()
				.append("select count(*) from ").append(tableName)
				.append("where book_id = :bookId and state = :state ", bookId, state);
		SqlAppender sql = SqlAppender.namedModel()
				.append("select id,subject,book_id,state,write_time,input_time from ").append(tableName)
				.append("where book_id = :bookId and state = :state ", bookId, state);
		
		return getPage(countSql, sql, MChapter.class, pageNo, pageSize);
	}
	
	public void updateContent(Long id, String content) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("update ").append(tableName)
				.append("set content = :content", content)
				.append("where id = :id", id);
		update(appender);
	}
	
	public void deleteChapter(Long chapterId) {
		deleteById(chapterId);
	}
	
	public void save(MChapter entity) {
		
	}
	
	public void update(MChapter entity) {
		
	}
}
