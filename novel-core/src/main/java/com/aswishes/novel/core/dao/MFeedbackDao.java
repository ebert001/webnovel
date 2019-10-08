package com.aswishes.novel.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.model.MFeedback;
import com.aswishes.spring.Restriction;
import com.aswishes.spring.mapper.MapperHelper;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MFeedbackDao extends SimpleJdbcDao<MFeedback> {

	public List<MFeedback> queryList(Long userId) {
		return getList(MapperHelper.getMapper(MFeedback.class), Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
	}

	public void delete(Long id) {
		delete(Restriction.eq("id", id));
	}
	
}
