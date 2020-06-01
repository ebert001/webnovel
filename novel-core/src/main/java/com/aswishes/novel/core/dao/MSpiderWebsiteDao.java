package com.aswishes.novel.core.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.common.db.PageResult;
import com.aswishes.novel.core.common.db.SqlAppender;
import com.aswishes.novel.core.model.MSpiderWebsite;

@Repository
@Transactional
public class MSpiderWebsiteDao extends SimpleJdbcDao<MSpiderWebsite> {

	public MSpiderWebsiteDao(DataSource dataSource) {
		super(dataSource);
	}
	
	public PageResult<MSpiderWebsite> findPage(int pageNo, int pageSize) {
		SqlAppender countSql = SqlAppender.namedModel()
				.append("select count(*) from ").append(tableName)
				.append("order by id desc");
		SqlAppender sql = SqlAppender.namedModel()
				.append("select * from ").append(tableName)
				.append("order by id desc");
		return getPage(countSql, sql, MSpiderWebsite.class, pageNo, pageSize);
	}
	
	public List<MSpiderWebsite> findOpenedWebsite() {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select * from ").append(tableName)
				.append("where state = :state", MSpiderWebsite.State.OPENED.getValue())
				.append("order by id desc");
		return getList(appender, MSpiderWebsite.class);
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

	public void save(MSpiderWebsite entity) {
		
	}
	
	public void update(MSpiderWebsite entity) {
		
	}
}
