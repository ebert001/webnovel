package com.aswishes.novel.pcweb.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.novel.core.common.web.SessionUtils;
import com.aswishes.novel.core.controller.AbstractController;
import com.aswishes.novel.core.entity.MMemo;
import com.aswishes.novel.core.service.MemoService;

/**
 * 备忘录的入口方法
 */
@Controller
@RequestMapping("/memo")
public class MemoController extends AbstractController {
	
	/** 
	 * 备忘录列表 
	 */
	@RequestMapping("/list")
	public ModelAndView list() {
		logger.debug("enter memo list page......");
		Long userId = SessionUtils.getUser().getId();
		
		List<MMemo> memoList = memoService.queryList(userId);
		request.setAttribute("memoList", memoList);
		return new ModelAndView("config/memo/create_memo");
	}
	
	/** 
	 * 备忘录详细信息 
	 */
	public ModelAndView queryOne(Long id) {
		MMemo memo = memoService.getMemo(id);
		request.setAttribute("memo", memo);
		
		return new ModelAndView("/config/memo/edit_memo.jsp");
	}
	
	/** 
	 * 增加备忘 
	 */
	public ModelAndView addMemo() {
		MMemo memo = new MMemo();
		memo.setTitle(request.getParameter("title"));
		memo.setContent(request.getParameter("content"));
		
		Date cdate = new Date();
		memo.setCreateTime(cdate);
		memo.setUpdateTime(cdate);
		
		Long userId = SessionUtils.getUser().getId();
		memo.setUserId(userId);
		
		memoService.save(memo);
		return list();
	}
	
	/** 
	 * 更新备忘 
	 */
	public ModelAndView updateMemo(Long id) {
		MMemo memo = new MMemo();
		
		memo.setId(id);
		memo.setTitle(request.getParameter("title"));
		memo.setContent(request.getParameter("content"));
		memo.setUpdateTime(new Date());
		
		memoService.update(memo);
		return list();
	}
	
	/** 
	 * 删除备忘 
	 */
	public ModelAndView deleteMemo(Long id) {
		memoService.delete(id);
		return list();
	}
	
	@Autowired
	private MemoService memoService;

}
