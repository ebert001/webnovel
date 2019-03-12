package com.aswishes.wn.mvc.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.PageResultWrapper;
import com.aswishes.spring.Restriction;
import com.aswishes.spring.dao.AbstractJdbcDao;
import com.aswishes.spring.mapper.MapperHelper;
import com.aswishes.wn.mvc.model.WnBookshelf;

/**
 * 对应的数据库表为 wn_bookshelf
 */
@Repository
@Transactional
public class WnBookshelfDao extends AbstractJdbcDao {

	@Override
	protected void setTableName() {
		this.tableName = "wn_bookshelf";
	}
	
	public WnBookshelf getBook(Long id) {
		return getObjectBy(MapperHelper.getMapper(WnBookshelf.class), Restriction.eq("id", id));
	}
	
	public WnBookshelf getBook(Long userId, Long bookId) {
		return getObjectBy(MapperHelper.getMapper(WnBookshelf.class), 
				Restriction.eq("user_id", userId), Restriction.eq("book_id", bookId));
	}
	
	public PageResultWrapper<WnBookshelf> getBooks(int pageNo, int pageSize, Long userId) {
		return getPage(MapperHelper.getMapper(WnBookshelf.class), pageNo, pageSize, 
				Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
	}
	
	public int getCount(Long userId) {
		return getCount(Restriction.eq("user_id", userId));
	}

	public void delete(Long id) {
		delete(Restriction.eq("id", id));
	}
	
}
