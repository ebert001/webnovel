package com.aswishes.wn.mvc.service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.wn.mvc.dao.WnBookDao;
import com.aswishes.wn.mvc.dao.WnChapterDao;
import com.aswishes.wn.mvc.dao.WnVolumeDao;
import com.aswishes.wn.mvc.model.WnBook;
import com.aswishes.wn.mvc.model.WnChapter;
import com.aswishes.wn.mvc.model.WnVolume;

import spring.persist.helper.NameValuePair;
import spring.persist.helper.Restriction;

/**
 * 书籍相关的业务处理类
 */
@Service
@Transactional
public class BookService {

	public List<WnBook> getBookList(String userId) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("user_id", userId));
		return bookDao.select(restrictions);
	}

	public WnBook getBook(String userId, String bookId) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("user_id", userId), Restriction.eq("book_id", bookId));
		return bookDao.selectById(restrictions);
	}
	
	public void addBook(WnBook book) throws SQLException {
		bookDao.save(book);
	}

	public void deleteBook(String bookId) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("book_id", bookId));
		bookDao.delete(restrictions);
	}
	
	public void deleteChapter(String chapterId) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("id", chapterId));
		chapterDao.delete(restrictions);
	}

	public List<WnChapter> readCatalogs(String userId, String bookId) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("book_id", bookId));
		return chapterDao.select(restrictions);
	}

	public WnChapter getChapter(String chapterId) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("chapter_id", chapterId));
		return chapterDao.selectById(restrictions);
	}

	@Transactional
	public void addChapter(WnChapter chapter) throws SQLException {
		chapterDao.save(chapter);

		List<NameValuePair> pairs = Arrays.asList(new NameValuePair("input_time", chapter.getInputTime()));
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("book_id", chapter.getBookId()));
		bookDao.update(pairs, restrictions);
	}

	public void updateChapter(WnChapter chapter) throws SQLException {
		chapterDao.update(chapter);

		List<NameValuePair> pairs = Arrays.asList(new NameValuePair("input_time", chapter.getInputTime()));
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("book_id", chapter.getBookId()));
		bookDao.update(pairs, restrictions);
	}
	
	public List<WnVolume> getVolumeList(String bookId) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("book_id", bookId));
		return volumeDao.select(restrictions);
	}
	
	public void addVolume(WnVolume volume) throws SQLException {
		volumeDao.save(volume);
	}
	
	public WnVolume getVolume(String id) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("id", id));
		return volumeDao.selectById(restrictions);
	}
	
	public void updateVolume(WnVolume volume) throws SQLException {
		volumeDao.update(volume);
	}
	
	@Autowired
	private WnVolumeDao volumeDao;

	@Autowired
	private WnChapterDao chapterDao;

	@Autowired
	private WnBookDao bookDao;
}
