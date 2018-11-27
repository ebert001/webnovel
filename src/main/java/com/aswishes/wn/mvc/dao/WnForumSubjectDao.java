package com.aswishes.wn.mvc.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.wn.mvc.model.WnForumSubject;

import spring.persist.helper.AbstractDaoTemplate;

/**
 * 对应的数据库表为 wn_book
 */
@Repository
@Transactional
public class WnForumSubjectDao extends AbstractDaoTemplate<WnForumSubject> {

	@Override
	protected void setEntity() {
		this.clazz = WnForumSubject.class;
	}

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
}
