package com.aswishes.wn.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.Restriction;
import com.aswishes.spring.dao.AbstractJdbcDao;
import com.aswishes.spring.mapper.MapperHelper;
import com.aswishes.wn.mvc.model.WnForumSubject;

/**
 * 对应的数据库表为 wn_book
 */
@Repository
@Transactional
public class WnForumSubjectDao extends AbstractJdbcDao {

	@Override
	protected void setTableName() {
		this.tableName = "wn_forum_subject";
	}
	
	public void updateReadTimes(String id) {
		this.jdbcTemplate.update("update wn_forum_subject set read_times = read_times + 1 where id = ?", id);
	}
	
	public void updateReplyTimes(String id) {
		this.jdbcTemplate.update("update wn_forum_subject set reply_times = reply_times + 1 where id = ?", id);
	}
	
	public WnForumSubject queryForumSubject(String id) {
		return getObjectBy(MapperHelper.getMapper(WnForumSubject.class), Restriction.eq("id", id));
	}
	
	public int getForumSubjectCount(String userId) {
		return getCount(Restriction.eq("user_id", userId));
	}

	public List<WnForumSubject> queryForumSubjectList(String userId, int startNo, int perNo) {
		return getList(MapperHelper.getMapper(WnForumSubject.class), startNo, perNo, Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
	}
	
	public List<WnForumSubject> queryForumSubjectList(int startNo, int perNo) {
		return getList(MapperHelper.getMapper(WnForumSubject.class), startNo, perNo, Restriction.orderByDesc("create_time"));
	}
	
	public int getForumSubjectCount() {
		return getCount();
	}

	public void deleteForumSubject(String id) {
		delete(Restriction.eq("id", id));
	}
}
