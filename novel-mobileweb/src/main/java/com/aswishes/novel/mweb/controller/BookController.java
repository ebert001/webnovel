package com.aswishes.novel.mweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.novel.common.db.PageResult;
import com.aswishes.novel.core.controller.AbstractController;
import com.aswishes.novel.core.model.MBook;
import com.aswishes.novel.core.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController extends AbstractController {
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/toList")
	public ModelAndView toList(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "20") int pageSize, ModelAndView mv) {
		logger.debug("to book list...");
		PageResult<MBook> page = bookService.findReadTop(pageNo, pageSize);
		mv.addObject("page", page);
		mv.setViewName("opus/book_list");
		return mv;
	}

	
	
}
