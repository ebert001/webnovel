package com.aswishes.novel.spider.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.mvc.dao.SimpleJdbcDao;
import com.aswishes.novel.spider.entity.MSpiderWebsite;
import com.aswishes.spring.SqlHelper.Update;

@Repository
@Transactional
public class MSpiderWebsiteDao extends SimpleJdbcDao<MSpiderWebsite> {

	public void updateState(Long id, Integer state) {
		String sql = Update.table(tableName).setColumns("state").whereColumns("id");
		update(sql, state, id);
	}
	
	public void updateRule(Long id, Long ruleId) {
		String sql = Update.table(tableName).setColumns("rule_id").whereColumns("id");
		update(sql, ruleId, id);
	}

}
