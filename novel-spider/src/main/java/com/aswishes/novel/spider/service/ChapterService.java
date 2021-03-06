package com.aswishes.novel.spider.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.common.db.PageResult;
import com.aswishes.novel.common.service.SimpleService;
import com.aswishes.novel.spider.dao.MBookDao;
import com.aswishes.novel.spider.dao.MChapterDao;
import com.aswishes.novel.spider.entity.MChapter;

/**
 * 书籍相关的业务处理类
 */
@Service
@Transactional
public class ChapterService extends SimpleService<MChapter> {
	@Autowired
	private MChapterDao chapterDao;
	@Autowired
	private MBookDao bookDao;

	public List<MChapter> readCatalogs(Long bookId) {
		return chapterDao.readCatalogs(bookId);
	}

	public MChapter getChapter(Long chapterId) {
		return chapterDao.getChapter(chapterId);
	}
	
	public MChapter getChapter(Long bookId, String subject) {
		return chapterDao.getChapter(bookId, subject);
	}
	
	public int getMaxSerialNo(Long bookId) {
		return chapterDao.getMaxSerialNo(bookId);
	}
	
	public PageResult<MChapter> findChapters(Long bookId, int pageNo, int pageSize) {
		return chapterDao.findChapters(pageNo, pageSize, bookId);
	}
	
	public PageResult<MChapter> findUnauditBooks(Long bookId, int pageNo, int pageSize) {
		return chapterDao.findUnauditChapters(pageNo, pageSize, bookId, MChapter.State.UNAUDITED.getValue());
	}
	
	@Transactional
	public void addChapter(MChapter chapter) {
		chapterDao.save(chapter);
		if (chapter.getBookId() != null) {
			bookDao.updateBook(chapter.getBookId(), chapter.getInputTime());
		}
	}

	public void updateChapter(MChapter chapter) {
		chapterDao.update(chapter);
		if (chapter.getBookId() != null) {
			bookDao.updateBook(chapter.getBookId(), chapter.getInputTime());
		}
	}
	
	public void updateContent(Long id, String content) {
		chapterDao.updateContent(id, content);
	}
	
	public void deleteChapter(Long chapterId) {
		chapterDao.deleteChapter(chapterId);
	}

}
