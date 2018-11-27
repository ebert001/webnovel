package com.aswishes.wn.mvc.service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.wn.mvc.dao.WnFeedbackDao;
import com.aswishes.wn.mvc.model.WnFeedback;

import spring.persist.helper.Restriction;

@Service
@Transactional
public class FeedbackService {

	public WnFeedback query(String id) throws SQLException {
		return feedbackDao.selectById(Arrays.asList(Restriction.eq("id", id)));
	}

	public List<WnFeedback> queryList(String userId) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
		return feedbackDao.select(restrictions);
	}

	public void save(WnFeedback feedback) throws SQLException {
		feedbackDao.save(feedback);
	}

	public void update(WnFeedback feedback) throws SQLException {
		feedbackDao.update(feedback);
	}

	public void delete(String id) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("id", id));
		feedbackDao.delete(restrictions);
	}

	@Autowired
	private WnFeedbackDao feedbackDao;
}
