package com.aswishes.novel.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.model.MForum;
import com.aswishes.spring.Restriction;
import com.aswishes.spring.mapper.MapperHelper;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MForumDao extends SimpleJdbcDao<MForum> {

	public List<MForum> queryForumList(Long userId) {
		return getList(MapperHelper.getMapper(MForum.class), Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
	}
	
	public List<MForum> queryForumList(Long userId, int startNo, int perNo) {
		return getList(MapperHelper.getMapper(MForum.class), Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
	}
	
	public int queryForumListCount() {
		return getCount();
	}

	public void deleteForum(Long id) {
		delete(Restriction.eq("id", id));
	}
}
