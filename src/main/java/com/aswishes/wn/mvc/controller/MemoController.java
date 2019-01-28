package com.aswishes.wn.mvc.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.wn.common.web.SessionUtils;
import com.aswishes.wn.mvc.model.WnMemo;
import com.aswishes.wn.mvc.service.MemoService;

/**
 * 备忘录的入口方法
 */
@Controller
@RequestMapping("/memo")
public class MemoController extends AbstractController {
	
	/** 备忘录列表 
	 * @throws SQLException */
	public ModelAndView list() {
		logger.debug("enter memo list page......");
		Long userId = SessionUtils.getUser().getId();
		
		List<WnMemo> memoList = memoService.queryList(userId);
		request.setAttribute("memoList", memoList);
		return new ModelAndView("/config/memo/create_memo.jsp");
	}
	
	/** 备忘录详细信息 
	 * @throws SQLException */
	public ModelAndView queryOne(Long id) {
		WnMemo memo = memoService.getMemo(id);
		request.setAttribute("memo", memo);
		return new ModelAndView("/config/memo/edit_memo.jsp");
	}
	
	/** 增加备忘 
	 * @throws SQLException */
	public ModelAndView addMemo() {
		WnMemo memo = new WnMemo();
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
	
	/** 更新备忘 
	 * @throws SQLException */
	public ModelAndView updateMemo(Long id) {
		WnMemo memo = new WnMemo();
		
		memo.setId(id);
		memo.setTitle(request.getParameter("title"));
		memo.setContent(request.getParameter("content"));
		memo.setUpdateTime(new Date());
		
		memoService.update(memo);
		return list();
	}
	
	/** 删除备忘 
	 * @throws SQLException */
	public ModelAndView deleteMemo(Long id) {
		memoService.delete(id);
		return list();
	}
	
	@Autowired
	private MemoService memoService;

}
