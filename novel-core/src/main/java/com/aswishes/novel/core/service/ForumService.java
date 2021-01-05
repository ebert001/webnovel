package com.aswishes.novel.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.dao.MForumDao;
import com.aswishes.novel.core.dao.MForumSubjectDao;
import com.aswishes.novel.core.entity.MForum;
import com.aswishes.novel.core.entity.MForumSubject;

@Service
@Transactional
public class ForumService extends SimpleService<MForum> {
	@Autowired
	private MForumDao forumDao;
	@Autowired
	private MForumSubjectDao forumSubjectDao;

	public List<MForum> queryForumList(Long userId) {
		return forumDao.queryForumList(userId);
	}
	
	public List<MForum> queryForumList(Long userId, int startNo, int perNo) {
		return forumDao.queryForumList(userId, startNo, perNo);
	}
	
	public int queryForumListCount() {
		return forumDao.queryForumListCount();
	}

	public void saveForum(MForum forum) {
		forumDao.save(forum);
	}

	public void updateForum(MForum forum) {
		forumDao.update(forum);
	}

	public void deleteForum(Long id) {
		forumDao.deleteById(id);
	}
	
	public void updateReadTimes(Long id) {
		forumSubjectDao.updateReadTimes(id);
	}
	
	public void updateReplyTimes(Long id) {
		forumSubjectDao.updateReplyTimes(id);
	}
	
	
	public MForumSubject queryForumSubject(Long id) {
		return forumSubjectDao.getById(id);
	}
	
	public int getForumSubjectCount(Long userId) {
		return forumSubjectDao.getForumSubjectCount(userId);
	}

	public List<MForumSubject> queryForumSubjectList(Long userId, int startNo, int perNo) {
		return forumSubjectDao.queryForumSubjectList(userId, startNo, perNo);
	}
	
	public List<MForumSubject> queryForumSubjectList(int startNo, int perNo) {
		return forumSubjectDao.queryForumSubjectList(startNo, perNo);
	}
	
	public int getForumSubjectCount() {
		return forumSubjectDao.getForumSubjectCount();
	}

	public void saveForumSubject(MForumSubject forumSubject) {
		forumSubjectDao.save(forumSubject);
	}

	public void updateForumSubject(MForumSubject forumSubject) {
		forumSubjectDao.update(forumSubject);
	}

	public void deleteForumSubject(Long id) {
		forumSubjectDao.deleteById(id);
	}

}
