package com.aswishes.novel.mvc.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.dao.AbstractJdbcDao;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MCommentDao extends AbstractJdbcDao {

	@Override
	protected void setTableName() {
		this.tableName = "m_comment";
	}
	
}
