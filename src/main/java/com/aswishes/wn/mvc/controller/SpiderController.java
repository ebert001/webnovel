package com.aswishes.wn.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.wn.spider.entity.WnSpiderBook;
import com.aswishes.wn.spider.entity.WnSpiderWebsite;
import com.aswishes.wn.spider.service.SpiderService;

@Controller
@RequestMapping("/spider")
public class SpiderController extends AbstractController {
	@Autowired
	private SpiderService spiderService;

	@RequestMapping(value = "toSpiderWebsite")
	public ModelAndView toSpiderWebsite(ModelAndView mv) {
		
		mv.setViewName("config/spider/list_website");
		return mv;
	}
	
	@RequestMapping(value = "loopBookList")
	public ModelAndView loopBookList(ModelAndView mv, Long websiteId, boolean loopChapters) {
		WnSpiderWebsite website = spiderService.getWebsite(websiteId);
		spiderService.loopBookList(websiteId, loopChapters);
		mv.setViewName("config/spider/list_website");
		return mv;
	}
	
	@RequestMapping(value = "loopBookChapter")
	public ModelAndView loopBookChapter(ModelAndView mv, Long bookId) {
		WnSpiderBook book = spiderService.getBook(bookId);
		
		mv.setViewName("config/spider/list_website");
		return mv;
	}
	
}