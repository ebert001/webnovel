package com.aswishes.novel.core.dao;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.entity.MComment;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MCommentDao extends SimpleJdbcDao<MComment> {

	public MCommentDao(DataSource dataSource) {
		super(dataSource);
	}

}
