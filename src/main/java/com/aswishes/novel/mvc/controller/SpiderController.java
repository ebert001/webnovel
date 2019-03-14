package com.aswishes.novel.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.novel.spider.entity.MSpiderRule;
import com.aswishes.novel.spider.entity.MSpiderWebsite;
import com.aswishes.novel.spider.service.SpiderService;
import com.aswishes.spring.PageResultWrapper;

@Controller
@RequestMapping("/spider")
public class SpiderController extends AbstractController {
	@Autowired
	private SpiderService spiderService;

	@RequestMapping(value = "/toSpiderWebsite")
	public ModelAndView toSpiderWebsite(ModelAndView mv, 
			@RequestParam(defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = "20") int pageSize) {
		PageResultWrapper<MSpiderWebsite> page = spiderService.getSpiderWebsite(pageNo, pageSize);
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
		spiderService.loopWebsite(id);
		mv.setViewName("config/spider/list_website");
		return mv;
	}
	
	@RequestMapping(value = "/toUnauditBooks")
	public ModelAndView toUnauditBooks(ModelAndView mv, Long id) {
		spiderService.loopWebsite(id);
		mv.setViewName("config/spider/list_website");
		return mv;
	}
	
	@RequestMapping(value = "/loopBookList")
	public ModelAndView loopBookList(ModelAndView mv, Long websiteId, boolean loopChapters) {
		MSpiderWebsite website = spiderService.getWebsite(websiteId);
		spiderService.loopBookList(websiteId, loopChapters);
		mv.setViewName("config/spider/list_website");
		return mv;
	}
	
}
