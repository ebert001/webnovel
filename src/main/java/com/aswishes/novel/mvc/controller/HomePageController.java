package com.aswishes.novel.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.novel.mvc.model.MBook;
import com.aswishes.novel.mvc.service.BookService;
import com.aswishes.spring.PageResult;

@Controller
@RequestMapping("/homepage")
public class HomePageController extends AbstractController {
	@Autowired
	private BookService bookService;

	@RequestMapping("")
	public ModelAndView toHomePage(ModelAndView mv, HttpServletRequest request) {
		PageResult<MBook> top30Page = bookService.findReadTop(1, 30, toQueryPropertyList(request));
		mv.addObject("top30", top30Page);
		mv.setViewName("frame/homepage");
		return mv;
	}
}
