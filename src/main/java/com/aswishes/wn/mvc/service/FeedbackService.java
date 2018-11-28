package com.aswishes.wn.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.wn.mvc.dao.WnFeedbackDao;
import com.aswishes.wn.mvc.model.WnFeedback;

@Service
@Transactional
public class FeedbackService extends AbstractService {

	public WnFeedback query(String id) {
		return feedbackDao.query(id);
	}

	public List<WnFeedback> queryList(String userId) {
		return feedbackDao.queryList(userId);
	}

	public void save(WnFeedback feedback) {
		feedbackDao.save(feedback);
	}

	public void update(WnFeedback feedback) {
		feedbackDao.updateByPK(feedback);
	}

	public void delete(String id) {
		feedbackDao.delete(id);
	}

	@Autowired
	private WnFeedbackDao feedbackDao;
}
