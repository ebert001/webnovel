package com.aswishes.novel.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.mvc.model.MForumSubject;
import com.aswishes.spring.Restriction;
import com.aswishes.spring.mapper.MapperHelper;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MForumSubjectDao extends SimpleJdbcDao<MForumSubject> {

	@Override
	protected void setTableName() {
		this.tableName = "m_forum_subject";
	}
	
	public void updateReadTimes(Long id) {
		this.jdbcTemplate.update("update novel_forum_subject set read_times = read_times + 1 where id = ?", id);
	}
	
	public void updateReplyTimes(Long id) {
		this.jdbcTemplate.update("update novel_forum_subject set reply_times = reply_times + 1 where id = ?", id);
	}
	
	public MForumSubject queryForumSubject(Long id) {
		return getObjectBy(MapperHelper.getMapper(MForumSubject.class), Restriction.eq("id", id));
	}
	
	public int getForumSubjectCount(Long userId) {
		return getCount(Restriction.eq("user_id", userId));
	}

	public List<MForumSubject> queryForumSubjectList(Long userId, int startNo, int perNo) {
		return getList(MapperHelper.getMapper(MForumSubject.class), startNo, perNo, Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
	}
	
	public List<MForumSubject> queryForumSubjectList(int startNo, int perNo) {
		return getList(MapperHelper.getMapper(MForumSubject.class), startNo, perNo, Restriction.orderByDesc("create_time"));
	}
	
	public int getForumSubjectCount() {
		return getCount();
	}

	public void deleteForumSubject(Long id) {
		delete(Restriction.eq("id", id));
	}
}
