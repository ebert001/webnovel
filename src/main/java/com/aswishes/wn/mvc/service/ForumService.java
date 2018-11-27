package com.aswishes.wn.mvc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.wn.mvc.dao.WnForumDao;
import com.aswishes.wn.mvc.dao.WnForumSubjectDao;
import com.aswishes.wn.mvc.model.WnForum;
import com.aswishes.wn.mvc.model.WnForumSubject;

import spring.persist.helper.Restriction;

@Service
@Transactional
public class ForumService {

	public WnForum queryForum(String id) throws SQLException {
		return forumDao.selectById(Arrays.asList(Restriction.eq("id", id)));
	}

	public List<WnForum> queryForumList(String userId) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
		return forumDao.select(restrictions);
	}
	
	public List<WnForum> queryForumList(String userId, int startNo, int perNo) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
		return forumDao.select(startNo, perNo, restrictions);
	}
	
	public int queryForumListCount() throws SQLException {
		return forumDao.queryCount(new ArrayList<Restriction>());
	}

	public void saveForum(WnForum forum) throws SQLException {
		forumDao.save(forum);
	}

	public void updateForum(WnForum forum) throws SQLException {
		forumDao.update(forum);
	}

	public void deleteForum(String id) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("id", id));
		forumDao.delete(restrictions);
	}
	
	public void updateReadTimes(String id) {
		forumSubjectDao.updateReadTimes(id);
	}
	
	public void updateReplyTimes(String id) {
		forumSubjectDao.updateReplyTimes(id);
	}
	
	
	public WnForumSubject queryForumSubject(String id) throws SQLException {
		return forumSubjectDao.selectById(Arrays.asList(Restriction.eq("id", id)));
	}
	
	public int getForumSubjectCount(String userId) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("user_id", userId));
		return forumSubjectDao.queryCount(restrictions);
	}

	public List<WnForumSubject> queryForumSubjectList(String userId, int startNo, int perNo) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
		return forumSubjectDao.select(startNo, perNo, restrictions);
	}
	
	public List<WnForumSubject> queryForumSubjectList(int startNo, int perNo) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.orderByDesc("create_time"));
		return forumSubjectDao.select(startNo, perNo, restrictions);
	}
	
	public int getForumSubjectCount() throws SQLException {
		return forumSubjectDao.queryCount(new ArrayList<Restriction>());
	}

	public void saveForumSubject(WnForumSubject forumSubject) throws SQLException {
		forumSubjectDao.save(forumSubject);
	}

	public void updateForumSubject(WnForumSubject forumSubject) throws SQLException {
		forumSubjectDao.update(forumSubject);
	}

	public void deleteForumSubject(String id) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("id", id));
		forumSubjectDao.delete(restrictions);
	}

	@Autowired
	private WnForumDao forumDao;
	
	@Autowired
	private WnForumSubjectDao forumSubjectDao;
}
