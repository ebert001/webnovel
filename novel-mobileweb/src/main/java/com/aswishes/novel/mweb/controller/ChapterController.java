package com.aswishes.novel.mweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chapter")
public class ChapterController {

	@RequestMapping(value = "/toList")
	public ModelAndView toList(int pageNo, int pageSize, Long bookId, ModelAndView mv) {
		
		return mv;
	}
	
	
}
