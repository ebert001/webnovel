package com.aswishes.novel.core.dao;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.model.MSpiderRule;

@Repository
@Transactional
public class MSpiderRuleDao extends SimpleJdbcDao<MSpiderRule> {

	public MSpiderRuleDao(DataSource dataSource) {
		super(dataSource);
	}


}
