package com.aswishes.wn.mvc.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.wn.mvc.model.WnForum;

import spring.persist.helper.AbstractDaoTemplate;

/**
 * 对应的数据库表为 wn_book
 */
@Repository
@Transactional
public class WnForumDao extends AbstractDaoTemplate<WnForum> {

	@Override
	protected void setEntity() {
		this.clazz = WnForum.class;
	}

	@Override
	protected void setTableName() {
		this.tableName = "wn_forum";
	}
	
}
