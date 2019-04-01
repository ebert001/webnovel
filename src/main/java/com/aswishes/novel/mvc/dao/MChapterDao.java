package com.aswishes.novel.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.mvc.model.MChapter;
import com.aswishes.spring.PageResult;
import com.aswishes.spring.Restriction;
import com.aswishes.spring.SqlHelper.Select;
import com.aswishes.spring.SqlHelper.Update;
import com.aswishes.spring.mapper.MapperHelper;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MChapterDao extends SimpleJdbcDao<MChapter> {

	public List<MChapter> readCatalogs(Long bookId) {
		return getList(Select.table(tableName).columns("id,subject,book_id,write_time").where("book_id = ?").toSqlString(), 
				MapperHelper.getMapper(MChapter.class), bookId);
	}

	public MChapter getChapter(Long chapterId) {
		return getObjectBy(MapperHelper.getMapper(MChapter.class), Restriction.eq("id", chapterId));
	}
	
	public MChapter getChapter(Long bookId, String subject) {
		return getObjectBy(MapperHelper.getMapper(MChapter.class), 
				Restriction.eq("book_id", bookId), Restriction.eq("subject", subject));
	}
	
	public int getMaxSerialNo(Long bookId) {
		String sql = Select.table(tableName).columns("max(serial_no)").where("book_id = ?").toSqlString();
		return getCount(sql, bookId);
	}
	
	public PageResult<MChapter> getUnauditChapters(int pageNo, int pageSize, Long bookId, int state) {
		Select select = Select.table(tableName).columns("id,subject,book_id,state,write_time,input_time").where("book_id = ? and state = ? ");
		String countSql = select.toCountString();
		String dataSql = select.toSqlString();
		return getPage(countSql, dataSql, MapperHelper.getMapper(MChapter.class), pageNo, pageSize, 
				Restriction.eq("book_id", bookId), Restriction.eq("state", state));
	}
	
	public void updateContent(Long id, String content) {
		String sql = Update.table(tableName).setColumns("content").whereColumns("id");
		update(sql, content, id);
	}
	
	public void deleteChapter(Long chapterId) {
		delete(Restriction.eq("id", chapterId));
	}
	
}
