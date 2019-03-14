package com.aswishes.novel.spider.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.dao.AbstractJdbcDao;

@Repository
@Transactional
public class MSpiderRuleDao extends AbstractJdbcDao {

	@Override
	protected void setTableName() {
		this.tableName = "m_spider_rule";
	}
	
	

}
