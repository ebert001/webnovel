package com.aswishes.novel.mvc.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.mvc.model.MBook;
import com.aswishes.spring.PageResultWrapper;
import com.aswishes.spring.Restriction;
import com.aswishes.spring.SqlHelper;
import com.aswishes.spring.dao.AbstractJdbcDao;
import com.aswishes.spring.mapper.MapperHelper;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MBookDao extends AbstractJdbcDao {

	@Override
	protected void setTableName() {
		this.tableName = "m_book";
	}
	
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
	
	public PageResultWrapper<MBook> getUnauditBooks(int pageNo, int pageSize, Restriction[] restrictions) {
		return getPage(MapperHelper.getMapper(MBook.class), pageNo, pageSize, 
				restrictions);
	}
	
	public void deleteBook(Long bookId) {
		delete(Restriction.eq("id", bookId));
	}
	
	public void updateBook(Long bookId, Date updateTime) {
		update(SqlHelper.update(tableName).setColumns("update_time").whereColumns("id"), updateTime, bookId);
	}

	
}
