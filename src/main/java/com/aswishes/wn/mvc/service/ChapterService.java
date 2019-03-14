package com.aswishes.wn.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.service.AbstractService;
import com.aswishes.wn.mvc.dao.MBookDao;
import com.aswishes.wn.mvc.dao.MChapterDao;
import com.aswishes.wn.mvc.model.MChapter;

/**
 * 书籍相关的业务处理类
 */
@Service
@Transactional
public class ChapterService extends AbstractService {
	@Autowired
	private MChapterDao chapterDao;
	@Autowired
	private MBookDao bookDao;

	public void deleteChapter(Long chapterId) {
		chapterDao.deleteChapter(chapterId);
	}

	public List<MChapter> readCatalogs(Long bookId) {
		return chapterDao.readCatalogs(bookId);
	}

	public MChapter getChapter(Long chapterId) {
		return chapterDao.getChapter(chapterId);
	}
	
	public MChapter getChapter(Long bookId, String subject) {
		return chapterDao.getChapter(bookId, subject);
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
	
	@Override
	public void setDao() {
		this.dao = chapterDao;
	}
}
