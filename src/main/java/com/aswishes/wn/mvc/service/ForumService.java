package com.aswishes.wn.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.service.AbstractService;
import com.aswishes.wn.mvc.dao.WnForumDao;
import com.aswishes.wn.mvc.dao.WnForumSubjectDao;
import com.aswishes.wn.mvc.model.WnForum;
import com.aswishes.wn.mvc.model.WnForumSubject;

@Service
@Transactional
public class ForumService extends AbstractService {

	public WnForum queryForum(Long id) {
		return forumDao.queryForum(id);
	}

	public List<WnForum> queryForumList(Long userId) {
		return forumDao.queryForumList(userId);
	}
	
	public List<WnForum> queryForumList(Long userId, int startNo, int perNo) {
		return forumDao.queryForumList(userId, startNo, perNo);
	}
	
	public int queryForumListCount() {
		return forumDao.queryForumListCount();
	}

	public void saveForum(WnForum forum) {
		forumDao.save(forum);
	}

	public void updateForum(WnForum forum) {
		forumDao.updateByPK(forum);
	}

	public void deleteForum(Long id) {
		forumDao.deleteForum(id);
	}
	
	public void updateReadTimes(Long id) {
		forumSubjectDao.updateReadTimes(id);
	}
	
	public void updateReplyTimes(Long id) {
		forumSubjectDao.updateReplyTimes(id);
	}
	
	
	public WnForumSubject queryForumSubject(Long id) {
		return forumSubjectDao.queryForumSubject(id);
	}
	
	public int getForumSubjectCount(Long userId) {
		return forumSubjectDao.getForumSubjectCount(userId);
	}

	public List<WnForumSubject> queryForumSubjectList(Long userId, int startNo, int perNo) {
		return forumSubjectDao.queryForumSubjectList(userId, startNo, perNo);
	}
	
	public List<WnForumSubject> queryForumSubjectList(int startNo, int perNo) {
		return forumSubjectDao.queryForumSubjectList(startNo, perNo);
	}
	
	public int getForumSubjectCount() {
		return forumSubjectDao.getForumSubjectCount();
	}

	public void saveForumSubject(WnForumSubject forumSubject) {
		forumSubjectDao.save(forumSubject);
	}

	public void updateForumSubject(WnForumSubject forumSubject) {
		forumSubjectDao.updateByPK(forumSubject);
	}

	public void deleteForumSubject(Long id) {
		forumSubjectDao.deleteForumSubject(id);
	}

	@Autowired
	private WnForumDao forumDao;
	
	@Autowired
	private WnForumSubjectDao forumSubjectDao;

	@Override
	public void setDao() {
		this.dao = forumDao;
	}
}
