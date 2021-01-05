package com.aswishes.novel.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.dao.MFeedbackDao;
import com.aswishes.novel.core.entity.MFeedback;

@Service
@Transactional
public class FeedbackService extends SimpleService<MFeedback> {
	@Autowired
	private MFeedbackDao feedbackDao;
	
	public MFeedback get(Long id) {
		return feedbackDao.getById(id);
	}

	public List<MFeedback> queryList(Long userId) {
		return feedbackDao.queryList(userId);
	}

	public void save(MFeedback feedback) {
		feedbackDao.save(feedback);
	}

	public void update(MFeedback feedback) {
		feedbackDao.update(feedback);
	}

	public void delete(Long id) {
		feedbackDao.deleteById(id);
	}

	
}
