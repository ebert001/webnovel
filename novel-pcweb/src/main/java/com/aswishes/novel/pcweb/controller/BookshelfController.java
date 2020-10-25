package com.aswishes.novel.pcweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.novel.core.common.db.PageResult;
import com.aswishes.novel.core.common.web.SessionUtils;
import com.aswishes.novel.core.controller.AbstractController;
import com.aswishes.novel.core.entity.MBook;
import com.aswishes.novel.core.entity.MBookshelf;
import com.aswishes.novel.core.entity.MUser;
import com.aswishes.novel.core.service.BookshelfService;

/**
 * 书架的入口方法
 */
@Controller
@RequestMapping("/bookshelf")
public class BookshelfController extends AbstractController {
	@Autowired
	private BookshelfService bookshelfService;
	
	@RequestMapping(value = "/toList")
	public ModelAndView toList(ModelAndView mv, 
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(name = "pageSize", defaultValue = "20") int pageSize) {
		MUser user = SessionUtils.getUser();
		if (user == null) {
			
		} else {
			PageResult<MBookshelf> page = bookshelfService.getBooks(pageNo, pageSize, user.getId());
			mv.addObject("page", page);
			mv.setViewName("config/shelf/list_shelf");
		}
		return mv;
	}
	
	@RequestMapping(value = "/toRead")
	public ModelAndView toRead(ModelAndView mv, Long id) {
		MBook book = bookshelfService.readBook(id);
		mv.setViewName("redirect:/book/listChapter?bookId=" + book.getId());
		return mv;
	}
	
	@RequestMapping(value = "/delete")
	public ModelAndView delete(ModelAndView mv, Long id) {
		bookshelfService.delete(id);
		toList(mv, pageNo, pageSize);
		return mv;
	}
}
