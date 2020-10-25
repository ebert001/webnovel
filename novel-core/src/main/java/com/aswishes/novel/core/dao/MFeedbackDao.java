package com.aswishes.novel.core.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.common.db.SqlAppender;
import com.aswishes.novel.core.entity.MFeedback;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MFeedbackDao extends SimpleJdbcDao<MFeedback> {

	public MFeedbackDao(DataSource dataSource) {
		super(dataSource);
	}

	public List<MFeedback> queryList(Long userId) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("selecdt * from ").append(tableName)
				.append("where user_id = :userId", userId)
				.append("order by create_time desc");
		return getList(appender, MFeedback.class);
	}

	public void save(MFeedback entity) {
		
	}
	
	public void update(MFeedback entity) {
		
	}
}
