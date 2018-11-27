package com.aswishes.wn.mvc.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.wn.common.AppUtil;
import com.aswishes.wn.common.Codes;
import com.aswishes.wn.mvc.model.WnForum;
import com.aswishes.wn.mvc.model.WnForumSubject;
import com.aswishes.wn.mvc.service.ForumService;

/**
 * 备忘录的入口方法
 */
public class ForumActionBean extends AbstractActionBean {
	
	/** 所有的帖子列表 
	 * @throws SQLException */
	public ModelAndView list() throws SQLException {
		log.debug("enter forum subject list page......");
		int perCount = 20;
		int no = AppUtil.calStartNo(getStartPage(request), perCount);
		
		List<WnForumSubject> forumSubjectList = forumMapper.queryForumSubjectList(no, perCount);
		
		int count = forumMapper.getForumSubjectCount();
		int pageCount = AppUtil.calPageCount(count, perCount);
		request.setAttribute("forumSubjectList", forumSubjectList);
		request.setAttribute("pageCount", pageCount);
		return new ModelAndView("/surface/bbs/list_bbs.jsp");
	}
	
	/** 用户的帖子列表 
	 * @throws SQLException */
	public ModelAndView listByUser() throws SQLException {
		String userId = (String) session.getAttribute(Codes.SESSION_USER);
		int perCount = 20;
		int no = AppUtil.calStartNo(getStartPage(request), perCount);
		
		List<WnForumSubject> forumSubjectList = forumMapper.queryForumSubjectList(userId, no, perCount);
		
		int count = forumMapper.getForumSubjectCount(userId);
		int pageCount = AppUtil.calPageCount(count, perCount);
		request.setAttribute("forumSubjectList", forumSubjectList);
		request.setAttribute("pageCount", pageCount);
		return new ModelAndView("/surface/bbs/list_bbs.jsp");
	}
	
	/** 帖子详细信息，带主帖 
	 * @throws SQLException */
	public ModelAndView queryOne() throws SQLException {
		String id = request.getParameter("id");
		int perCount = 20;
		int startPage = getStartPage(request);
		int no = AppUtil.calStartNo(startPage, perCount);
		
		WnForumSubject forumSubject = forumMapper.queryForumSubject(id);
		
		List<WnForum> forumList = forumMapper.queryForumList(id, no, perCount);
		
		int count = forumMapper.queryForumListCount();
		int pageCount = AppUtil.calPageCount(count, perCount);
		request.setAttribute("forumSubject", forumSubject);
		request.setAttribute("forumList", forumList);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageNo", startPage);
		
		forumMapper.updateReadTimes(id);
		return new ModelAndView("/surface/bbs/view_bbs.jsp");
	}
	
	/** 帖子详细信息，不带主帖 
	 * @throws SQLException */
	public ModelAndView getForum() throws SQLException {
		String id = request.getParameter("id");
		int perCount = 20;
		int no = AppUtil.calStartNo(getStartPage(request), perCount);
		
		List<WnForum> forumList = forumMapper.queryForumList(id, no, perCount);
		
		int count = forumMapper.queryForumListCount();
		int pageCount = AppUtil.calPageCount(count, perCount);
		request.setAttribute("forumList", forumList);
		request.setAttribute("pageCount", pageCount);
		
		forumMapper.updateReadTimes(id);
		return new ModelAndView("/surface/bbs/view_bbs.jsp");
	}
	
	/** 添加帖子信息 
	 * @throws SQLException */
	public ModelAndView addForumSubject() throws SQLException {
		WnForumSubject forumSubject = new WnForumSubject();
		forumSubject.setId(AppUtil.getUuid());
		forumSubject.setSubject(request.getParameter("subject"));
		forumSubject.setContent(request.getParameter("content"));
		forumSubject.setType(Integer.parseInt(request.getParameter("type") == null ? "0" : request.getParameter("type")));
		forumSubject.setStatus(WnForumSubject.Status.OPEN);
		
		Date cdate = new Date();
		forumSubject.setCreateTime(cdate);
		forumSubject.setUpdateTime(cdate);
		
		forumSubject.setReadTimes(0);
		forumSubject.setReplyTimes(0);
		
		String userId = (String) session.getAttribute(Codes.SESSION_USER);
		forumSubject.setUserId(userId);
		
		forumMapper.saveForumSubject(forumSubject);
		return list();
	}
	
	/** 回复帖子 
	 * @throws SQLException */
	public ModelAndView replyForum() throws SQLException {
		WnForum forum = new WnForum();
		forum.setId(AppUtil.getUuid());
		forum.setContent(request.getParameter("content"));
		
		String subjectId = request.getParameter("id");
		forum.setSubjectId(subjectId);
		forum.setStatus(WnForumSubject.Status.OPEN);
		
		Date cdate = new Date();
		forum.setCreateTime(cdate);
		
		String userId = (String) session.getAttribute(Codes.SESSION_USER);
		forum.setUserId(userId);
		
		forumMapper.saveForum(forum);
		// 回复 数量 + 1
		forumMapper.updateReplyTimes(subjectId);
		return queryOne();
	}
	
	/** 更新帖子信息，帖子类型和状态 
	 * @throws SQLException */
	public ModelAndView updateForumSubject() throws SQLException {
		WnForumSubject forumSubject = forumMapper.queryForumSubject(request.getParameter("id"));
		
		forumSubject.setType(Integer.parseInt(request.getParameter("type") == null ? "0" : request.getParameter("type")));
		forumSubject.setStatus(WnForumSubject.Status.OPEN);
		
		forumMapper.updateForumSubject(forumSubject);
		return list();
	}
	
	/** 删除帖子信息，回帖信息 
	 * @throws SQLException */
	public ModelAndView deleteForumSubject() throws SQLException {
		forumMapper.deleteForum(request.getParameter("id"));
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

	private static final Logger log = LoggerFactory.getLogger(ForumActionBean.class);
}
