package com.aswishes.novel.spider.dao;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.spider.entity.MSpiderRule;

@Repository
@Transactional
public class MSpiderRuleDao extends SimpleJdbcDao<MSpiderRule> {

	public MSpiderRuleDao(DataSource dataSource) {
		super(dataSource);
	}
	
	public void save(MSpiderRule entity) {
		
	}
	
	public Long saveAndGetId(MSpiderRule entity) {
		return null;
	}
	
	public void update(MSpiderRule entity) {
		
	}

}
