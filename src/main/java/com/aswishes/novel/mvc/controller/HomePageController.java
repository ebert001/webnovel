package com.aswishes.novel.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/homepage")
public class HomePageController extends AbstractController {

	@RequestMapping("")
	public ModelAndView toHomePage(ModelAndView mv) {
		mv.setViewName("frame/homepage");
		return mv;
	}
}
