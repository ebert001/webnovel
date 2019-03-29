package com.aswishes.novel.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.mvc.dao.MBookDao;
import com.aswishes.novel.mvc.dao.MChapterDao;
import com.aswishes.novel.mvc.model.MChapter;
import com.aswishes.spring.PageResultWrapper;

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
	
	public PageResultWrapper<MChapter> findUnauditBooks(Long bookId, int pageNo, int pageSize) {
		return chapterDao.getUnauditChapters(pageNo, pageSize, bookId, MChapter.State.UNAUDITED.getValue());
	}
	
	@Transactional
	public void addChapter(MChapter chapter) {
		chapterDao.save(chapter);
		if (chapter.getBookId() != null) {
			bookDao.updateBook(chapter.getBookId(), chapter.getInputTime());
		}
	}

	public void updateChapter(MChapter chapter) {
		chapterDao.updateByPK(chapter, true);
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

	@Autowired
	@Override
	public void setDao() {
		this.dao = chapterDao;
	}
}
