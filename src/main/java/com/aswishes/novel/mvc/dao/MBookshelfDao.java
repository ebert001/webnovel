package com.aswishes.novel.mvc.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.mvc.model.MBookshelf;
import com.aswishes.spring.PageResultWrapper;
import com.aswishes.spring.Restriction;
import com.aswishes.spring.mapper.MapperHelper;

/**
 * 对应的数据库表为 novel_bookshelf
 */
@Repository
@Transactional
public class MBookshelfDao extends SimpleJdbcDao<MBookshelf> {

	public MBookshelf getBook(Long id) {
		return getObjectBy(MapperHelper.getMapper(MBookshelf.class), Restriction.eq("id", id));
	}
	
	public MBookshelf getBook(Long userId, Long bookId) {
		return getObjectBy(MapperHelper.getMapper(MBookshelf.class), 
				Restriction.eq("user_id", userId), Restriction.eq("book_id", bookId));
	}
	
	public PageResultWrapper<MBookshelf> getBooks(int pageNo, int pageSize, Long userId) {
		return getPage(MapperHelper.getMapper(MBookshelf.class), pageNo, pageSize, 
				Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
	}
	
	public int getCount(Long userId) {
		return getCount(Restriction.eq("user_id", userId));
	}

	public void delete(Long id) {
		delete(Restriction.eq("id", id));
	}
	
}
