package com.aswishes.wn.mvc.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.wn.mvc.model.WnBook;

import spring.persist.helper.AbstractDaoTemplate;

/**
 * 对应的数据库表为 wn_book
 */
@Repository
@Transactional
public class WnBookDao extends AbstractDaoTemplate<WnBook> {

	@Override
	protected void setEntity() {
		this.clazz = WnBook.class;
	}

	@Override
	protected void setTableName() {
		this.tableName = "wn_book";
	}
	
}
