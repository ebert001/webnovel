package com.aswishes.novel.pcweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.novel.common.db.PageResult;
import com.aswishes.novel.core.controller.AbstractController;
import com.aswishes.novel.core.model.MBook;
import com.aswishes.novel.core.service.BookService;

@Controller
@RequestMapping("/homepage")
public class HomePageController extends AbstractController {
	@Autowired
	private BookService bookService;

	@RequestMapping("")
	public ModelAndView toHomePage(ModelAndView mv, HttpServletRequest request) {
		PageResult<MBook> top30Page = bookService.findReadTop(1, 30);
		mv.addObject("top30", top30Page);
		mv.setViewName("frame/homepage");
		return mv;
	}
}
