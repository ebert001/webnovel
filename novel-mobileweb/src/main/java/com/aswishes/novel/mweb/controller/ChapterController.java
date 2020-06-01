package com.aswishes.novel.mweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.novel.core.common.db.PageResult;
import com.aswishes.novel.core.controller.AbstractController;
import com.aswishes.novel.core.model.MChapter;
import com.aswishes.novel.core.service.ChapterService;

@Controller
@RequestMapping("/chapter")
public class ChapterController extends AbstractController {
	@Autowired
	private ChapterService chapterService;
	
	@RequestMapping(value = "/toList")
	public ModelAndView toList(int pageNo, int pageSize, Long bookId, ModelAndView mv) {
		PageResult<MChapter> chapterPage = chapterService.findChapters(bookId, pageNo, pageSize);
		
		mv.addObject("page", chapterPage);
		mv.setViewName("opus/catalog");
		return mv;
	}
	
	@RequestMapping(value = "/toChapter")
	public ModelAndView toChapter(Long chapterId, ModelAndView mv) {
		
		mv.setViewName("opus/chapter");
		return mv;
	}
	
	
	
}
