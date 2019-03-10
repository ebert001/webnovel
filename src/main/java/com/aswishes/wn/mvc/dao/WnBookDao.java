package com.aswishes.wn.mvc.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.Restriction;
import com.aswishes.spring.SqlHelper;
import com.aswishes.spring.dao.AbstractJdbcDao;
import com.aswishes.spring.mapper.MapperHelper;
import com.aswishes.wn.mvc.model.WnBook;

/**
 * 对应的数据库表为 wn_book
 */
@Repository
@Transactional
public class WnBookDao extends AbstractJdbcDao {

	@Override
	protected void setTableName() {
		this.tableName = "wn_book";
	}
	
	public List<WnBook> getBookList(Long userId) {
		return getList(MapperHelper.getMapper(WnBook.class), Restriction.eq("author_id", userId));
	}

	public WnBook getBook(Long bookId) {
		return getObjectBy(MapperHelper.getMapper(WnBook.class), Restriction.eq("id", bookId));
	}
	
	public WnBook getBook(String bookName) {
		return getObjectBy(MapperHelper.getMapper(WnBook.class), Restriction.eq("name", bookName));
	}
	
	public WnBook getBook(String bookName, Long websiteId) {
		return getObjectBy(MapperHelper.getMapper(WnBook.class), 
				Restriction.eq("name", bookName), Restriction.eq("website_id", websiteId));
	}
	
	public void deleteBook(Long bookId) {
		delete(Restriction.eq("id", bookId));
	}
	
	public void updateBook(Long bookId, Date updateTime) {
		update(SqlHelper.update(tableName).setColumns("update_time").whereColumns("id"), updateTime, bookId);
	}

	
}
