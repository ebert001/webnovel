package com.aswishes.wn.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.Restriction;
import com.aswishes.spring.SqlHelper.Select;
import com.aswishes.spring.dao.AbstractJdbcDao;
import com.aswishes.spring.mapper.MapperHelper;
import com.aswishes.wn.mvc.model.WnChapter;

/**
 * 对应的数据库表为 wn_book
 */
@Repository
@Transactional
public class WnChapterDao extends AbstractJdbcDao {

	@Override
	protected void setTableName() {
		this.tableName = "wn_chapter";
	}
	
	public void deleteChapter(Long chapterId) {
		delete(Restriction.eq("id", chapterId));
	}

	public List<WnChapter> readCatalogs(Long bookId) {
		return getList(Select.table(tableName).columns("id,subject,book_id,write_time").where("book_id = ?").toSqlString(), 
				MapperHelper.getMapper(WnChapter.class), bookId);
	}

	public WnChapter getChapter(Long chapterId) {
		return getObjectBy(MapperHelper.getMapper(WnChapter.class), Restriction.eq("id", chapterId));
	}
	
}
