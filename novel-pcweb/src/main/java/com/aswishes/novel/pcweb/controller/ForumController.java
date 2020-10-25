package com.aswishes.novel.pcweb.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.novel.core.common.AppUtil;
import com.aswishes.novel.core.common.web.SessionUtils;
import com.aswishes.novel.core.controller.AbstractController;
import com.aswishes.novel.core.entity.MForum;
import com.aswishes.novel.core.entity.MForumSubject;
import com.aswishes.novel.core.service.ForumService;

/**
 * 备忘录的入口方法
 */
@Controller
@RequestMapping("/forum")
public class ForumController extends AbstractController {
	
	/** 
	 * 所有的帖子列表 
	 */
	public ModelAndView list() {
		logger.debug("enter forum subject list page......");
		int perCount = 20;
		int no = AppUtil.calStartNo(getStartPage(request), perCount);
		
		List<MForumSubject> forumSubjectList = forumMapper.queryForumSubjectList(no, perCount);
		
		int count = forumMapper.getForumSubjectCount();
		int pageCount = AppUtil.calPageCount(count, perCount);
		request.setAttribute("forumSubjectList", forumSubjectList);
		request.setAttribute("pageCount", pageCount);
		return new ModelAndView("/surface/bbs/list_bbs.jsp");
	}
	
	/** 
	 * 用户的帖子列表 
	 */
	public ModelAndView listByUser() {
		Long userId = SessionUtils.getUser().getId();
		int perCount = 20;
		int no = AppUtil.calStartNo(getStartPage(request), perCount);
		
		List<MForumSubject> forumSubjectList = forumMapper.queryForumSubjectList(userId, no, perCount);
		
		int count = forumMapper.getForumSubjectCount(userId);
		int pageCount = AppUtil.calPageCount(count, perCount);
		request.setAttribute("forumSubjectList", forumSubjectList);
		request.setAttribute("pageCount", pageCount);
		return new ModelAndView("/surface/bbs/list_bbs.jsp");
	}
	
	/** 
	 * 帖子详细信息，带主帖 
	 */
	public ModelAndView queryOne(Long id) {
		int perCount = 20;
		int startPage = getStartPage(request);
		int no = AppUtil.calStartNo(startPage, perCount);
		
		MForumSubject forumSubject = forumMapper.queryForumSubject(id);
		
		List<MForum> forumList = forumMapper.queryForumList(id, no, perCount);
		
		int count = forumMapper.queryForumListCount();
		int pageCount = AppUtil.calPageCount(count, perCount);
		request.setAttribute("forumSubject", forumSubject);
		request.setAttribute("forumList", forumList);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageNo", startPage);
		
		forumMapper.updateReadTimes(id);
		return new ModelAndView("/surface/bbs/view_bbs.jsp");
	}
	
	/** 
	 * 帖子详细信息，不带主帖 
	 */
	public ModelAndView getForum(Long id) {
		int perCount = 20;
		int no = AppUtil.calStartNo(getStartPage(request), perCount);
		
		List<MForum> forumList = forumMapper.queryForumList(id, no, perCount);
		
		int count = forumMapper.queryForumListCount();
		int pageCount = AppUtil.calPageCount(count, perCount);
		request.setAttribute("forumList", forumList);
		request.setAttribute("pageCount", pageCount);
		
		forumMapper.updateReadTimes(id);
		return new ModelAndView("/surface/bbs/view_bbs.jsp");
	}
	
	/** 
	 * 添加帖子信息 
	 */
	public ModelAndView addForumSubject() {
		MForumSubject forumSubject = new MForumSubject();
		forumSubject.setId(AppUtil.getUuid());
		forumSubject.setSubject(request.getParameter("subject"));
		forumSubject.setContent(request.getParameter("content"));
		forumSubject.setType(Integer.parseInt(request.getParameter("type") == null ? "0" : request.getParameter("type")));
		forumSubject.setStatus(MForumSubject.Status.OPEN);
		
		Date cdate = new Date();
		forumSubject.setCreateTime(cdate);
		forumSubject.setUpdateTime(cdate);
		
		forumSubject.setReadTimes(0);
		forumSubject.setReplyTimes(0);
		
		Long userId = SessionUtils.getUser().getId();
		forumSubject.setUserId(userId);
		
		forumMapper.saveForumSubject(forumSubject);
		return list();
	}
	
	/** 
	 * 回复帖子 
	 */
	public ModelAndView replyForum(Long subjectId) {
		MForum forum = new MForum();
		forum.setContent(request.getParameter("content"));
		
		forum.setSubjectId(subjectId);
		forum.setStatus(MForumSubject.Status.OPEN);
		
		Date cdate = new Date();
		forum.setCreateTime(cdate);
		
		Long userId = SessionUtils.getUser().getId();
		forum.setUserId(userId);
		
		forumMapper.saveForum(forum);
		// 回复 数量 + 1
		forumMapper.updateReplyTimes(subjectId);
		return queryOne(subjectId);
	}
	
	/** 
	 * 更新帖子信息，帖子类型和状态 
	 */
	public ModelAndView updateForumSubject(Long id) {
		MForumSubject forumSubject = forumMapper.queryForumSubject(id);
		
		forumSubject.setType(Integer.parseInt(request.getParameter("type") == null ? "0" : request.getParameter("type")));
		forumSubject.setStatus(MForumSubject.Status.OPEN);
		
		forumMapper.updateForumSubject(forumSubject);
		return list();
	}
	
	/** 
	 * 删除帖子信息，回帖信息 
	 */
	public ModelAndView deleteForumSubject(Long id) {
		forumMapper.deleteForum(id);
		return list();
	}
	
	private int getStartPage(HttpServletRequest request) {
		String page = request.getParameter("page");
		if (page == null || "".equals(page.trim())) {
			page = "1";
		}
		return Integer.parseInt(page);
	}
	
	@Autowired
	private ForumService forumMapper;
}
