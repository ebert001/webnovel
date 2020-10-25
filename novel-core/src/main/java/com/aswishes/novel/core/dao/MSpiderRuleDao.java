package com.aswishes.novel.core.dao;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.entity.MSpiderRule;

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
