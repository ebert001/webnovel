package com.aswishes.wn.mvc.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.wn.common.AppUtil;
import com.aswishes.wn.common.web.SessionUtils;
import com.aswishes.wn.mvc.model.MFeedback;
import com.aswishes.wn.mvc.service.FeedbackService;

@Controller
@RequestMapping("/feedback")
public class FeedbackController extends AbstractController {
	
	/** 反馈列表 */
	public ModelAndView list() {
//		List<WnFeedback> feedbackList = feedbackMapper.queryList();
//		request.setAttribute("feedbackList", feedbackList);
		return new ModelAndView("/config/feedback/list_feedback.jsp");
	}
	
	public ModelAndView listByUser() {
		Long userId = SessionUtils.getUser().getId();
		List<MFeedback> feedbackList = feedbackService.queryList(userId);
		request.setAttribute("feedbackList", feedbackList);
		return new ModelAndView("/config/feedback/list_feedback.jsp");
	}
	
	/** 
	 * 反馈详细信息 
	 */
	public ModelAndView queryOne(Long id) {
		MFeedback feedback = feedbackService.query(id);
		request.setAttribute("feedback", feedback);
		return new ModelAndView("/config/feedback/list_feedback.jsp");
	}
	
	/** 
	 * 增加新反馈 
	 */
	public ModelAndView addFeedback() {
		MFeedback feedback = new MFeedback();
		feedback.setId(AppUtil.getUuid());
		feedback.setTitle(request.getParameter("title"));
		feedback.setAdvice(request.getParameter("advice"));
		feedback.setCreateTime(new Date());
		
		feedbackService.save(feedback);
		return list();
	}
	
	/** 
	 * 更新反馈信息 
	 */
	public ModelAndView updateFeedback(Long id) {
		String status = request.getParameter("status");
		MFeedback feedback = feedbackService.query(id);
		feedback.setStatus(Integer.parseInt(status));
		feedbackService.update(feedback);
		return list();
	}

	@Autowired
	private FeedbackService feedbackService;
	
}
