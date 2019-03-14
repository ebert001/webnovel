package com.aswishes.wn.mvc.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.PageResultWrapper;
import com.aswishes.spring.Restriction;
import com.aswishes.spring.SqlHelper;
import com.aswishes.spring.SqlHelper.Select;
import com.aswishes.spring.dao.AbstractJdbcDao;
import com.aswishes.spring.mapper.MapperHelper;
import com.aswishes.wn.mvc.model.MBook;

/**
 * 对应的数据库表为 wn_book
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
	
	public int getMaxSerialNo(Long id) {
		String sql = Select.table(tableName).columns("max(serial_no)").where("id = ?").toSqlString();
		return getCount(sql, id);
	}
	
	public PageResultWrapper<MBook> getUnauditBooks(int pageNo, int pageSize, int state) {
		return getPage(MapperHelper.getMapper(MBook.class), pageNo, pageSize, 
				Restriction.eq("state", state));
	}
	
	public void deleteBook(Long bookId) {
		delete(Restriction.eq("id", bookId));
	}
	
	public void updateBook(Long bookId, Date updateTime) {
		update(SqlHelper.update(tableName).setColumns("update_time").whereColumns("id"), updateTime, bookId);
	}

	
}
