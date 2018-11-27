package com.aswishes.wn.mvc.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.wn.mvc.model.WnMemo;

import spring.persist.helper.AbstractDaoTemplate;

/**
 * 对应的数据库表为 wn_book
 */
@Repository
@Transactional
public class WnMemoDao extends AbstractDaoTemplate<WnMemo> {

	@Override
	protected void setEntity() {
		this.clazz = WnMemo.class;
	}

	@Override
	protected void setTableName() {
		this.tableName = "wn_memo";
	}
	
}
