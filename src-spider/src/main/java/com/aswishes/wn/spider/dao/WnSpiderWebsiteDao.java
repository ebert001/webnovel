package com.aswishes.wn.spider.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.SqlHelper.Update;
import com.aswishes.spring.dao.AbstractJdbcDao;

@Repository
@Transactional
public class WnSpiderWebsiteDao extends AbstractJdbcDao {

	@Override
	protected void setTableName() {
		this.tableName = "wn_spider_website";
	}
	
	public void updateState(Long id, Integer state) {
		String sql = Update.table(tableName).setColumns("state").whereColumns("id");
		update(sql, state, id);
	}

}
