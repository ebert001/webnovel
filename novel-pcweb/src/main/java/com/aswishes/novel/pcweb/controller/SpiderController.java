package com.aswishes.novel.pcweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.novel.core.common.db.PageResult;
import com.aswishes.novel.core.controller.AbstractController;
import com.aswishes.novel.core.entity.MBook;
import com.aswishes.novel.core.entity.MChapter;
import com.aswishes.novel.core.entity.MSpiderRule;
import com.aswishes.novel.core.entity.MSpiderWebsite;
import com.aswishes.novel.core.service.BookService;
import com.aswishes.novel.core.service.ChapterService;
import com.aswishes.novel.core.service.SpiderService;

@Controller
@RequestMapping("/spider")
public class SpiderController extends AbstractController {
	@Autowired
	private SpiderService spiderService;
	@Autowired
	private BookService bookService;
	@Autowired
	private ChapterService chapterService;
	

	@RequestMapping(value = "/toSpiderWebsite")
	public ModelAndView toSpiderWebsite(ModelAndView mv, 
			@RequestParam(defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = "20") int pageSize) {
		PageResult<MSpiderWebsite> page = spiderService.getSpiderWebsite(pageNo, pageSize);
		mv.addObject("page", page);
		mv.setViewName("config/spider/list_website");
		return mv;
	}
	
	@RequestMapping(value = "/toAddWebsite")
	public ModelAndView toAddWebsite(ModelAndView mv) {
		mv.setViewName("config/spider/add_website");
		return mv;
	}
	
	@RequestMapping(value = "/addWebsite")
	public ModelAndView addWebsite(ModelAndView mv, MSpiderWebsite website) {
		spiderService.addSpiderWebsite(website);
		mv.setViewName("forward:/spider/toSpiderWebsite");
		return mv;
	}
	
	@RequestMapping(value = "/deleteWebsite")
	public ModelAndView deleteWebsite(ModelAndView mv, Long id) {
		spiderService.deleteWebsite(id);
		return toSpiderWebsite(mv, pageNo, pageSize);
	}
	
	@RequestMapping(value = "/closeWebsite")
	public ModelAndView closeWebsite(ModelAndView mv, Long id) {
		spiderService.closeWebsite(id);
		return toSpiderWebsite(mv, pageNo, pageSize);
	}
	
	@RequestMapping(value = "/openWebsite")
	public ModelAndView openWebsite(ModelAndView mv, Long id) {
		spiderService.openWebsite(id);
		return toSpiderWebsite(mv, pageNo, pageSize);
	}
	
	@RequestMapping(value = "/toAddRule")
	public ModelAndView toAddRule(ModelAndView mv, Long id) {
		MSpiderWebsite website = spiderService.getWebsite(id);
		MSpiderRule rule = spiderService.getRule(website.getRuleId());
		
		mv.addObject("website", website);
		mv.addObject("rule", rule);
		mv.setViewName("/config/spider/add_rule");
		return mv;
	}
	
	@RequestMapping(value = "/addRule")
	public ModelAndView addRule(ModelAndView mv, Long websiteId, MSpiderRule rule) {
		spiderService.saveSpiderRule(websiteId, rule);
		mv.setViewName("redirect:/spider/toSpiderWebsite");
		return mv;
	}
	
	@RequestMapping(value = "/loopWebsite")
	public ModelAndView loopWebsite(ModelAndView mv, Long id) {
//		spiderService.loopWebsite(id);
		mv.setViewName("config/spider/list_website");
		return mv;
	}
	
	@RequestMapping(value = "/loopChapters")
	public ModelAndView loopChapters(ModelAndView mv, Long bookId) {
//		spiderService.loopChapters(bookId, false);
		mv.setViewName("config/spider/list_unaudit_books");
		return mv;
	}
	
	@RequestMapping(value = "/toUnauditBooks")
	public ModelAndView toUnauditBooks(ModelAndView mv, HttpServletRequest request,
			@RequestParam(defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = "20") int pageSize) {
		PageResult<MBook> page = bookService.findUnauditBooks(pageNo, pageSize);
		mv.addObject("page", page);
		mv.setViewName("config/spider/list_unaudit_books");
		return mv;
	}
	
	@RequestMapping(value = "/toUnauditChapters")
	public ModelAndView toUnauditChapters(ModelAndView mv, Long bookId,
			@RequestParam(defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = "20") int pageSize) {
		MBook book = bookService.getBook(bookId);
		PageResult<MChapter> page = chapterService.findUnauditBooks(bookId, pageNo, pageSize);
		mv.addObject("page", page);
		mv.addObject("book", book);
		mv.setViewName("config/spider/list_unaudit_chapters");
		return mv;
	}
	
	@RequestMapping(value = "/toUnauditChapter")
	public ModelAndView toUnauditChapter(ModelAndView mv, Long chapterId) {
		MChapter chapter = chapterService.getChapter(chapterId);
		mv.addObject("chapter", chapter);
		mv.setViewName("config/spider/unaudit_chapter");
		return mv;
	}
	
}
