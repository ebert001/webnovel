package com.aswishes.wn.mvc.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.wn.common.AppUtil;
import com.aswishes.wn.common.Codes;
import com.aswishes.wn.mvc.model.WnBook;
import com.aswishes.wn.mvc.model.WnChapter;
import com.aswishes.wn.mvc.model.WnVolume;
import com.aswishes.wn.mvc.service.BookService;

/**
 * 书籍的入口方法
 */
@Controller
@RequestMapping("/book")
public class BookController extends AbstractController {
	
	/** 书籍列表 
	 * @throws SQLException */
	public ModelAndView list() {
		log.debug("enter book list page......");
		String userId = (String) session.getAttribute(Codes.SESSION_USER);
		
		List<WnBook> bookList = bookMapper.getBookList(userId);
		request.setAttribute("bookList", bookList);
		return new ModelAndView("/config/opus/create_opus.jsp");
	}
	
	/** 书籍章节列表 
	 * @throws SQLException */
	public ModelAndView listChapter() {
		log.debug("enter chapter list page......");
		String userId = (String) session.getAttribute(Codes.SESSION_USER);
		String bookId = request.getParameter("bookId");
		String a = request.getParameter("a");
		
		List<WnChapter> chapterList = bookMapper.readCatalogs(userId, bookId);
		WnBook book = bookMapper.getBook(userId, bookId);
		List<WnVolume> volumeList = bookMapper.getVolumeList(bookId);
		
		request.setAttribute("volumeList", volumeList);
		request.setAttribute("chapterList", chapterList);
		request.setAttribute("book", book);
		if ("r".equals(a)) {
			return new ModelAndView("/surface/opus/catalog.jsp");
		} else {
			return new ModelAndView("/config/opus/opus_catalog.jsp");
		}
	}
	
	/** 去章节写作页面 
	 * @throws SQLException */
	public ModelAndView goWritePage() {
		String userId = (String) session.getAttribute(Codes.SESSION_USER);
		String bookId = request.getParameter("bookId");
		log.debug("my book id:" + bookId);

		List<WnBook> bookList = bookMapper.getBookList(userId);
		request.setAttribute("bookList", bookList);
		request.setAttribute("bookId", bookId);
		return new ModelAndView("/config/opus/create_article.jsp");
	}
	
	/** 书籍详细信息 
	 * @throws SQLException */
	public ModelAndView queryOne() {
		String id = request.getParameter("id");
		String userId = "";
		WnBook book = bookMapper.getBook(userId, id);
		
		request.setAttribute("book", book);
		return new ModelAndView("/config/opus/opus_catalog.jsp");
	}
	
	/** 添加书籍 
	 * @throws SQLException */
	public ModelAndView addBook() {
		WnBook book = new WnBook();
		book.setId(AppUtil.getUuid());
		book.setBookName(request.getParameter("bookName"));
		book.setDesc(request.getParameter("desc"));
		
		Date cdate = new Date();
		book.setCreateTime(cdate);
		book.setUpdateTime(cdate);
		
		String userId = (String) session.getAttribute(Codes.SESSION_USER);
		book.setAuthorId(userId);
		
		bookMapper.addBook(book);
		return list();
	}
	
	/** 添加书籍分卷 
	 * @throws SQLException */
	public ModelAndView addVolume() {
		WnVolume volume = new WnVolume();
		volume.setId(AppUtil.getUuid());
		volume.setBookId(request.getParameter("bookId"));
		volume.setVolumeName(request.getParameter("volumeName"));
		
		Date cdate = new Date();
		volume.setCreateTime(cdate);
		volume.setUpdateTime(cdate);
		
		bookMapper.addVolume(volume);
		return listChapter();
	}
	
	public ModelAndView updateVolume() {
		String volumeId = request.getParameter("volumeId");
		if (volumeId == null) {
			return addVolume();
		} else {
			WnVolume volume = bookMapper.getVolume(volumeId);
			volume.setVolumeName(request.getParameter("volumeName"));
			bookMapper.updateVolume(volume);
		}
		return listChapter();
	}
	
	/** 添加书籍章节 
	 * @throws SQLException */
	public ModelAndView addChapter() {
		String bookId = request.getParameter("bookId");
		String volumeId = request.getParameter("volumeId");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String chapterId = request.getParameter("chapterId");
		log.debug("my book id:" + bookId);
		
		WnChapter chapter = null;
		if (chapterId == null || "".equals(chapterId.trim())) {
			chapter = new WnChapter();
			chapter.setId(AppUtil.getUuid());
			
			Date cdate = new Date();
			chapter.setInputTime(cdate);
			chapter.setWriteTime(cdate);
			
			chapter.setSubject(subject);
			chapter.setContent(content);
			chapter.setBookId(bookId);
			chapter.setVolumeId(volumeId);
			bookMapper.addChapter(chapter);
		} else {
			chapter = bookMapper.getChapter(chapterId);
			chapter.setSubject(subject);
			chapter.setContent(content);
			Date cdate = new Date();
			chapter.setInputTime(cdate);
			bookMapper.updateChapter(chapter);
		}
		return list();
	}
	
	public ModelAndView addChapterSubject() {
		String bookId = request.getParameter("bookId");
		String volumeId = request.getParameter("volumeId");
		String subject = request.getParameter("subject");
		log.debug("my book id:" + bookId);
		
		WnChapter chapter = new WnChapter();
		chapter.setId(AppUtil.getUuid());
		
		Date cdate = new Date();
		chapter.setInputTime(cdate);
		chapter.setWriteTime(cdate);
		
		chapter.setSubject(subject);
		chapter.setBookId(bookId);
		chapter.setVolumeId(volumeId);
		
		bookMapper.addChapter(chapter);
		return listChapter();
	}
	
	public ModelAndView deleteChapter() {
		bookMapper.deleteChapter(request.getParameter("chatperId"));
		return listChapter();
	}
	
	/** 查询章节详细 
	 * @throws SQLException */
	public ModelAndView queryChapter() {
		String userId = (String) session.getAttribute(Codes.SESSION_USER);
		
		String bookId = request.getParameter("bookId");
		log.debug("my book id:" + bookId);

		List<WnBook> bookList = bookMapper.getBookList(userId);
		request.setAttribute("bookList", bookList);
		request.setAttribute("bookId", bookId);
		
		String chapterId = request.getParameter("chapterId");
		WnChapter chapter = bookMapper.getChapter(chapterId);
		request.setAttribute("chapter", chapter);
		
		return new ModelAndView("/config/opus/create_article.jsp");
	}
	
	public ModelAndView readChapter() {
		String chapterId = request.getParameter("chapterId");
		WnChapter chapter = bookMapper.getChapter(chapterId);
		log.debug(chapter.getContent());
		request.setAttribute("chapter", chapter);
		
		return new ModelAndView("/surface/opus/chapter.jsp");
	}
	
	/** 删除书籍 
	 * @throws SQLException */
	public ModelAndView deleteBook() {
			bookMapper.deleteBook(request.getParameter("id"));
		return list();
	}

	@Autowired
	private BookService bookMapper;

}
