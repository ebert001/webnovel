package com.aswishes.wn.spider.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.dao.AbstractJdbcDao;

@Repository
@Transactional
public class WnSpiderWebsiteDao extends AbstractJdbcDao {

	@Override
	protected void setTableName() {
		this.tableName = "wn_spider_website";
	}
	
	

}
