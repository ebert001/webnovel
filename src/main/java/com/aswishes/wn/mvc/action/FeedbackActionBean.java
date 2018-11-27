package com.aswishes.wn.mvc.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.wn.common.AppUtil;
import com.aswishes.wn.common.Codes;
import com.aswishes.wn.mvc.model.WnFeedback;
import com.aswishes.wn.mvc.service.FeedbackService;

public class FeedbackActionBean extends AbstractActionBean {
	
	/** 反馈列表 */
	public ModelAndView list() {
//		List<WnFeedback> feedbackList = feedbackMapper.queryList();
//		request.setAttribute("feedbackList", feedbackList);
		return new ModelAndView("/config/feedback/list_feedback.jsp");
	}
	
	public ModelAndView listByUser() throws SQLException {
		String userId = getAttribute(session, Codes.SESSION_USER);
		List<WnFeedback> feedbackList = feedbackService.queryList(userId);
		request.setAttribute("feedbackList", feedbackList);
		return new ModelAndView("/config/feedback/list_feedback.jsp");
	}
	
	/** 反馈详细信息 
	 * @throws SQLException */
	public ModelAndView queryOne() throws SQLException {
		String id = request.getParameter("id");
		WnFeedback feedback = feedbackService.query(id);
		request.setAttribute("feedback", feedback);
		return new ModelAndView("/config/feedback/list_feedback.jsp");
	}
	
	/** 增加新反馈 
	 * @throws SQLException */
	public ModelAndView addFeedback() throws SQLException {
		WnFeedback feedback = new WnFeedback();
		feedback.setId(AppUtil.getUuid());
		feedback.setTitle(request.getParameter("title"));
		feedback.setAdvice(request.getParameter("advice"));
		feedback.setCreateTime(new Date());
		
		feedbackService.save(feedback);
		return list();
	}
	
	/** 更新反馈信息 
	 * @throws SQLException */
	public ModelAndView updateFeedback() throws SQLException {
		String status = request.getParameter("status");
		WnFeedback feedback = feedbackService.query(request.getParameter("id"));
		feedback.setStatus(Integer.parseInt(status));
		feedbackService.update(feedback);
		return list();
	}

	@Autowired
	private FeedbackService feedbackService;
	
}
