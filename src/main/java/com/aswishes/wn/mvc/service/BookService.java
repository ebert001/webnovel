package com.aswishes.wn.mvc.service;

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

/**
 * 书籍相关的业务处理类
 */
@Service
@Transactional
public class BookService extends AbstractService {

	public List<WnBook> getBookList(Long userId) {
		return bookDao.getBookList(userId);
	}
	
	public WnBook getBook(String bookName) {
		return bookDao.getBook(bookName);
	}

	public WnBook getBook(Long bookId) {
		return bookDao.getBook(bookId);
	}
	
	public void addBook(WnBook book) {
		bookDao.save(book);
	}

	public void deleteBook(Long bookId) {
		bookDao.deleteBook(bookId);
	}
	
	public void deleteChapter(Long chapterId) {
		chapterDao.deleteChapter(chapterId);
	}

	public List<WnChapter> readCatalogs(Long userId, Long bookId) {
		return chapterDao.readCatalogs(userId, bookId);
	}

	public WnChapter getChapter(Long chapterId) {
		return chapterDao.getChapter(chapterId);
	}

	@Transactional
	public void addChapter(WnChapter chapter) {
		chapterDao.save(chapter);
		bookDao.updateBook(chapter.getBookId(), chapter.getInputTime());
	}

	public void updateChapter(WnChapter chapter) {
		chapterDao.updateByPK(chapter);
		bookDao.updateBook(chapter.getBookId(), chapter.getInputTime());
	}
	
	public List<WnVolume> getVolumeList(Long bookId) {
		return volumeDao.getVolumeList(bookId);
	}
	
	public void addVolume(WnVolume volume) {
		volumeDao.save(volume);
	}
	
	public WnVolume getVolume(Long id) {
		return volumeDao.getVolume(id);
	}
	
	public void updateVolume(WnVolume volume) {
		volumeDao.updateByPK(volume);
	}
	
	@Autowired
	private WnVolumeDao volumeDao;
	@Autowired
	private WnChapterDao chapterDao;
	@Autowired
	private WnBookDao bookDao;
}
