package com.aswishes.novel.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.mvc.dao.MFeedbackDao;
import com.aswishes.novel.mvc.model.MFeedback;

@Service
@Transactional
public class FeedbackService extends SimpleService<MFeedback> {

	public List<MFeedback> queryList(Long userId) {
		return feedbackDao.queryList(userId);
	}

	public void save(MFeedback feedback) {
		feedbackDao.save(feedback);
	}

	public void update(MFeedback feedback) {
		feedbackDao.updateByPK(feedback, true);
	}

	public void delete(Long id) {
		feedbackDao.delete(id);
	}

	@Autowired
	private MFeedbackDao feedbackDao;

	@Override
	public void setDao() {
		this.dao = feedbackDao;
	}
}
