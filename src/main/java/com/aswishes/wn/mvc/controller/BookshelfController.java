package com.aswishes.wn.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 书架的入口方法
 */
@Controller
@RequestMapping("/bookshelf")
public class BookshelfController extends AbstractController {
	
	@RequestMapping(value = "/toList")
	public ModelAndView toList(ModelAndView mv) {
		mv.setViewName("config/shelf/list_shelf");
		return mv;
	}
}
