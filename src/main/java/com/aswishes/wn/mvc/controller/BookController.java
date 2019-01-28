package com.aswishes.wn.mvc.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.wn.common.web.SessionUtils;
import com.aswishes.wn.mvc.model.WnBook;
import com.aswishes.wn.mvc.model.WnChapter;
import com.aswishes.wn.mvc.model.WnUser;
import com.aswishes.wn.mvc.model.WnVolume;
import com.aswishes.wn.mvc.service.BookService;

/**
 * 书籍的入口方法
 */
@Controller
@RequestMapping("/book")
public class BookController extends AbstractController {
	
	/** 
	 * 添加书籍 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.POST})
	public ModelAndView addBook(ModelAndView mv, String bookName, String desc) {
		WnBook book = bookService.getBook(bookName);
		if (book != null) {
			// 书籍名称已经存在
			return mv;
		}
		book = new WnBook();
		
		book.setBookName(bookName);
		book.setDescription(desc);
		
		Date cdate = new Date();
		book.setCreateTime(cdate);
		book.setUpdateTime(cdate);
		
		WnUser user = SessionUtils.getUser();
		if (user == null) {
			// 用户尚未登录
			return mv;
		}
		book.setAuthorId(user.getId());
		book.setAuthor(user.getName());
		
		bookService.addBook(book);
		return list(mv);
	}
	
	/** 
	 * 删除书籍 
	 */
	@RequestMapping(value = "/delete")
	public ModelAndView deleteBook(ModelAndView mv, Long id) {
		WnBook book = bookService.getBook(id);
		if (book == null) {
			// 书籍不存在
			return mv;
		}
		WnUser user = SessionUtils.getUser();
		if (user == null) {
			// 尚未登录
			return mv;
		}
		if (!book.getAuthorId().equals(user.getId())) {
			// 非本作者书籍不能删除
			return mv;
		}
		bookService.deleteBook(id);
		return list(mv);
	}
	
	/** 
	 * 书籍列表 
	 */
	public ModelAndView list(ModelAndView mv) {
		logger.debug("enter book list page......");
		Long userId = SessionUtils.getUser().getId();
		
//		bookService.getPage(WnBook.class, pageNo, pageSize, restrictions);
		List<WnBook> bookList = bookService.getBookList(userId);
		request.setAttribute("bookList", bookList);
		
		mv.setViewName("/config/opus/create_opus.jsp");
		return mv;
	}
	
	/** 
	 * 书籍章节列表 
	 */
	public ModelAndView listChapter(Long bookId) {
		logger.debug("enter chapter list page......");
		Long userId = SessionUtils.getUser().getId();
		String a = request.getParameter("a");
		
		List<WnChapter> chapterList = bookService.readCatalogs(userId, bookId);
		WnBook book = bookService.getBook(bookId);
		List<WnVolume> volumeList = bookService.getVolumeList(bookId);
		
		request.setAttribute("volumeList", volumeList);
		request.setAttribute("chapterList", chapterList);
		request.setAttribute("book", book);
		if ("r".equals(a)) {
			return new ModelAndView("/surface/opus/catalog.jsp");
		} else {
			return new ModelAndView("/config/opus/opus_catalog.jsp");
		}
	}
	
	/** 
	 * 去章节写作页面 
	 */
	@RequestMapping(value = "/toWritePage")
	public ModelAndView goWritePage(Long bookId) {
		Long userId = SessionUtils.getUser().getId();
		logger.debug("my book id:" + bookId);

		List<WnBook> bookList = bookService.getBookList(userId);
		request.setAttribute("bookList", bookList);
		request.setAttribute("bookId", bookId);
		return new ModelAndView("/config/opus/create_article.jsp");
	}
	
	/** 
	 * 书籍详细信息 
	 */
	public ModelAndView queryOne(Long id) {
		WnBook book = bookService.getBook(id);
		
		request.setAttribute("book", book);
		return new ModelAndView("/config/opus/opus_catalog.jsp");
	}
	

	
	/** 添加书籍分卷 
	 *  */
	public ModelAndView addVolume(Long bookId, String volumeName) {
		WnVolume volume = new WnVolume();
		volume.setBookId(bookId);
		volume.setVolumeName(volumeName);
		
		Date cdate = new Date();
		volume.setCreateTime(cdate);
		volume.setUpdateTime(cdate);
		
		bookService.addVolume(volume);
		return listChapter(bookId);
	}
	
	public ModelAndView updateVolume(Long volumeId, String volumeName) {
		WnVolume volume = bookService.getVolume(volumeId);
		volume.setVolumeName(volumeName);
		bookService.updateVolume(volume);
		return listChapter(volume.getBookId());
	}
	
	/** 添加书籍章节 
	 *  */
	public ModelAndView addChapter(ModelAndView mv, Long bookId, Long volumeId, String subject, String content, Long chapterId) {
		logger.debug("my book id:" + bookId);
		
		WnChapter chapter = null;
		if (chapterId == null) {
			chapter = new WnChapter();
			
			Date cdate = new Date();
			chapter.setInputTime(cdate);
			chapter.setWriteTime(cdate);
			
			chapter.setSubject(subject);
			chapter.setContent(content);
			chapter.setBookId(bookId);
			chapter.setVolumeId(volumeId);
			bookService.addChapter(chapter);
		} else {
			chapter = bookService.getChapter(chapterId);
			chapter.setSubject(subject);
			chapter.setContent(content);
			Date cdate = new Date();
			chapter.setInputTime(cdate);
			bookService.updateChapter(chapter);
		}
		return list(mv);
	}
	
	public ModelAndView addChapterSubject(Long bookId, Long volumeId, String subject) {
		logger.debug("my book id:" + bookId);
		
		WnChapter chapter = new WnChapter();
		
		Date cdate = new Date();
		chapter.setInputTime(cdate);
		chapter.setWriteTime(cdate);
		
		chapter.setSubject(subject);
		chapter.setBookId(bookId);
		chapter.setVolumeId(volumeId);
		
		bookService.addChapter(chapter);
		return listChapter(bookId);
	}
	
	public ModelAndView deleteChapter(Long chapterId) {
		WnChapter chapter = bookService.getChapter(chapterId);
		WnBook book = bookService.getBook(chapter.getBookId());
		
		bookService.deleteChapter(chapterId);
		return listChapter(book.getId());
	}
	
	/** 查询章节详细 
	 *  */
	public ModelAndView queryChapter(Long chapterId) {
		Long userId = SessionUtils.getUser().getId();
		
		String bookId = request.getParameter("bookId");
		logger.debug("my book id:" + bookId);

		List<WnBook> bookList = bookService.getBookList(userId);
		request.setAttribute("bookList", bookList);
		request.setAttribute("bookId", bookId);
		
		WnChapter chapter = bookService.getChapter(chapterId);
		request.setAttribute("chapter", chapter);
		
		return new ModelAndView("/config/opus/create_article.jsp");
	}
	
	public ModelAndView readChapter(Long chapterId) {
		WnChapter chapter = bookService.getChapter(chapterId);
		logger.debug(chapter.getContent());
		request.setAttribute("chapter", chapter);
		
		return new ModelAndView("/surface/opus/chapter.jsp");
	}
	
	@Autowired
	private BookService bookService;

}
