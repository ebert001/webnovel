package com.aswishes.novel.mweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.novel.common.db.PageResult;
import com.aswishes.novel.core.controller.AbstractController;
import com.aswishes.novel.core.model.MChapter;
import com.aswishes.novel.core.service.ChapterService;

@Controller
@RequestMapping("/chapter")
public class ChapterController extends AbstractController {
	@Autowired
	private ChapterService chapterService;
	
	@RequestMapping(value = "/toList")
	public ModelAndView toList(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "20") int pageSize, 
			Long bookId, ModelAndView mv) {
		PageResult<MChapter> chapterPage = chapterService.findChapters(bookId, pageNo, pageSize);
		
		mv.addObject("bookId", bookId);
		mv.addObject("page", chapterPage);
		mv.setViewName("opus/catalog");
		return mv;
	}
	
	@RequestMapping(value = "/toChapter")
	public ModelAndView toChapter(Long chapterId, ModelAndView mv) {
		MChapter chapter = chapterService.getChapter(chapterId);
		String content = chapter.getContent();
		content = content.replaceAll("\n|\r|\r\n", "<br>");
		chapter.setContent(content);
		mv.addObject("chapter", chapter);
		mv.setViewName("opus/chapter");
		return mv;
	}
	
	
	
}
