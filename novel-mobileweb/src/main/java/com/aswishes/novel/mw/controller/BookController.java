package com.aswishes.novel.mw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/book")
public class BookController extends AbstractController {

	@RequestMapping(value = "/toList")
	public ModelAndView toList(int pageNo, int pageSize, ModelAndView mv) {
		
		return mv;
	}

	
	
}
