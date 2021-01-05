package com.aswishes.novel.pcweb.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.novel.core.common.web.SessionUtils;
import com.aswishes.novel.core.controller.AbstractController;
import com.aswishes.novel.core.entity.MBook;
import com.aswishes.novel.core.entity.MChapter;
import com.aswishes.novel.core.entity.MUser;
import com.aswishes.novel.core.entity.MVolume;
import com.aswishes.novel.core.service.BookService;
import com.aswishes.novel.core.service.ChapterService;

/**
 * 书籍的入口方法
 */
@Controller
@RequestMapping("/book")
public class BookController extends AbstractController {
	
	@RequestMapping(value = "/view")
	public ModelAndView viewBook(ModelAndView mv, Long bookId) {
		mv.setViewName("frame/preface");
		return mv;
	}
	
	@RequestMapping(value = "/toAdd", method = {RequestMethod.POST})
	public ModelAndView toAddBook(ModelAndView mv, String bookName, String desc) {
		mv.setViewName("config/opus/create_opus");
		return mv;
	}
	
	/** 
	 * 添加书籍 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.POST})
	public ModelAndView addBook(ModelAndView mv, String bookName, String desc) {
		MBook book = bookService.getBook(bookName);
		if (book != null) {
			// 书籍名称已经存在
			return mv;
		}
		book = new MBook();
		
		book.setName(bookName);
		book.setIntroduction(desc);
		
		Date cdate = new Date();
		book.setCreateTime(cdate);
		book.setUpdateTime(cdate);
		
		MUser user = SessionUtils.getUser();
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
		MBook book = bookService.getBook(id);
		if (book == null) {
			// 书籍不存在
			return mv;
		}
		MUser user = SessionUtils.getUser();
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
	@RequestMapping("/list")
	public ModelAndView list(ModelAndView mv) {
		logger.debug("enter book list page......");
		Long userId = SessionUtils.getUser().getId();
		
//		bookService.getPage(novelBook.class, pageNo, pageSize, restrictions);
		List<MBook> bookList = bookService.getBookList(userId);
		mv.addObject("bookList", bookList);
		
		mv.setViewName("config/opus/list_opus");
		return mv;
	}
	
	/** 
	 * 书籍章节列表 
	 */
	@RequestMapping(value = "/listChapter")
	public ModelAndView listChapter(ModelAndView mv, Long bookId, 
			@RequestParam(name = "a")String action) {
		logger.debug("enter chapter list page......");
		
		List<MChapter> chapterList = chapterService.readCatalogs(bookId);
		MBook book = bookService.getBook(bookId);
		List<MVolume> volumeList = bookService.getVolumeList(bookId);
		
		mv.addObject("volumeList", volumeList);
		mv.addObject("chapterList", chapterList);
		mv.addObject("book", book);
		if ("r".equals(action)) {
			mv.setViewName("surface/opus/catalog");
		} else {
			mv.setViewName("config/opus/opus_catalog");
		}
		return mv;
	}
	
	@RequestMapping(value = "/readChapter")
	public ModelAndView readChapter(ModelAndView mv, Long chapterId) {
		MChapter chapter = chapterService.getChapter(chapterId);
		logger.debug(chapter.getContent());
		mv.addObject("chapter", chapter);
		
		mv.setViewName("/surface/opus/chapter");
		return mv;
	}
	
	/** 
	 * 去章节写作页面 
	 */
	@RequestMapping(value = "/toWritePage")
	public ModelAndView toWritePage(ModelAndView mv, Long bookId) {
		Long userId = SessionUtils.getUser().getId();
		logger.debug("my book id:" + bookId);

		List<MBook> bookList = bookService.getBookList(userId);
		mv.addObject("bookList", bookList);
		mv.addObject("bookId", bookId);
		mv.setViewName("config/opus/create_article");
		return mv;
	}
	
	/** 
	 * 书籍详细信息 
	 */
	public ModelAndView queryOne(ModelAndView mv, Long id) {
		MBook book = bookService.getBook(id);
		
		mv.addObject("book", book);
		mv.setViewName("/config/opus/opus_catalog.jsp");
		return mv;
	}
	

	
	/** 添加书籍分卷 
	 *  */
	public ModelAndView addVolume(ModelAndView mv, Long bookId, String volumeName) {
		MVolume volume = new MVolume();
		volume.setBookId(bookId);
		volume.setVolumeName(volumeName);
		
		Date cdate = new Date();
		volume.setCreateTime(cdate);
		volume.setUpdateTime(cdate);
		
		bookService.addVolume(volume);
		return listChapter(mv, bookId, "a");
	}
	
	public ModelAndView updateVolume(ModelAndView mv, Long volumeId, String volumeName) {
		MVolume volume = bookService.getVolume(volumeId);
		volume.setVolumeName(volumeName);
		bookService.updateVolume(volume);
		return listChapter(mv, volume.getBookId(), "a");
	}
	
	/** 添加书籍章节 
	 *  */
	@RequestMapping("/addChapter")
	public ModelAndView addChapter(ModelAndView mv, Long bookId, Long volumeId, String subject, String content, Long chapterId) {
		logger.debug("my book id:" + bookId);
		
		MChapter chapter = null;
		if (chapterId == null) {
			chapter = new MChapter();
			
			Date cdate = new Date();
			chapter.setInputTime(cdate);
			chapter.setWriteTime(cdate);
			
			chapter.setSubject(subject);
			chapter.setContent(content);
			chapter.setBookId(bookId);
			chapter.setVolumeId(volumeId);
			chapterService.addChapter(chapter);
		} else {
			chapter = chapterService.getChapter(chapterId);
			chapter.setSubject(subject);
			chapter.setContent(content);
			Date cdate = new Date();
			chapter.setInputTime(cdate);
			chapterService.updateChapter(chapter);
		}
		return list(mv);
	}
	
	public ModelAndView addChapterSubject(ModelAndView mv, Long bookId, Long volumeId, String subject) {
		logger.debug("my book id:" + bookId);
		
		MChapter chapter = new MChapter();
		
		Date cdate = new Date();
		chapter.setInputTime(cdate);
		chapter.setWriteTime(cdate);
		
		chapter.setSubject(subject);
		chapter.setBookId(bookId);
		chapter.setVolumeId(volumeId);
		
		chapterService.addChapter(chapter);
		return listChapter(mv, bookId, "a");
	}
	
	public ModelAndView deleteChapter(ModelAndView mv, Long chapterId) {
		MChapter chapter = chapterService.getChapter(chapterId);
		MBook book = bookService.getBook(chapter.getBookId());
		
		chapterService.deleteChapter(chapterId);
		return listChapter(mv, book.getId(), "a");
	}
	
	/** 查询章节详细 
	 *  */
	public ModelAndView queryChapter(ModelAndView mv, Long chapterId) {
		Long userId = SessionUtils.getUser().getId();
		
		String bookId = request.getParameter("bookId");
		logger.debug("my book id:" + bookId);

		List<MBook> bookList = bookService.getBookList(userId);
		mv.addObject("bookList", bookList);
		mv.addObject("bookId", bookId);
		
		MChapter chapter = chapterService.getChapter(chapterId);
		mv.addObject("chapter", chapter);
		
		mv.setViewName("/config/opus/create_article.jsp");
		return mv;
	}
	
	@RequestMapping("/search")
	public ModelAndView search(ModelAndView mv, String q) {
		
		mv.setViewName("/surface/list_search.jsp");
		return mv;
	}
	
	@Autowired
	private BookService bookService;
	@Autowired
	private ChapterService chapterService;

}
