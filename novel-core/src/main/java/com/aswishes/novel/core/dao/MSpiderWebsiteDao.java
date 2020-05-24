package com.aswishes.novel.core.dao;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.common.db.SqlAppender;
import com.aswishes.novel.core.model.MSpiderWebsite;

@Repository
@Transactional
public class MSpiderWebsiteDao extends SimpleJdbcDao<MSpiderWebsite> {

	public MSpiderWebsiteDao(DataSource dataSource) {
		super(dataSource);
	}

	public void updateState(Long id, Integer state) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("update ").append(tableName).append("set")
				.append("state = :state", state)
				.append("where id = :id", id);
		update(appender);
	}
	
	public void updateRule(Long id, Long ruleId) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("update ").append(tableName).append("set")
				.append("rule_id = :rule_id", ruleId)
				.append("where id = :id", id);
		update(appender);
	}

}
