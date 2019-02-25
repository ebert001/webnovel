package com.aswishes.wn.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.wn.spider.BookInfo;
import com.aswishes.wn.spider.DownloadBook;
import com.aswishes.wn.spider.DownloadBookList;
import com.aswishes.wn.spider.IBookInfo;
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
	
	@RequestMapping(value = "toSpiderWebsite")
	public ModelAndView loopWebsite(ModelAndView mv, Long id) {
		WnSpiderWebsite website = spiderService.getWebsite(id);
		String bookListUrlPrefix = website.getBookListUrlPrefix();
		int pageNo = website.getPageNo();
		String bookListUrlSuffix = website.getBookListUrlSuffix();
		DownloadBookList downloadBookList = new DownloadBookList(bookListUrlPrefix, pageNo, bookListUrlSuffix);
		downloadBookList.setBookListCharset(bookListCharset);
		downloadBookList.setBookNodePath(bookNodePath);
		downloadBookList.setBookNamePath(bookNamePath);
		downloadBookList.setBookUrlPath(bookUrlPath);
		downloadBookList.setTotalPagePath(totalPagePath);
		downloadBookList.setTotalPageExpress(totalPageExpress);
		downloadBookList.discovery(new IBookInfo() {
			@Override
			public void extract(BookInfo info) {
				DownloadBook downloadBook = new DownloadBook(info.getBookUrl());
				downloadBook.setCatalogCharset(catalogCharset);
				
			}
		});
		mv.setViewName("config/spider/list_website");
		return mv;
	}
	
	@RequestMapping(value = "toSpiderWebsite")
	public ModelAndView findBook(ModelAndView mv, Long id) {
		WnSpiderBook book = spiderService.getBook(id);
		
		mv.setViewName("config/spider/list_website");
		return mv;
	}
	
}
