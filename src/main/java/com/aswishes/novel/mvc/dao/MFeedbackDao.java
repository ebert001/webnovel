package com.aswishes.novel.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.Restriction;
import com.aswishes.spring.dao.AbstractJdbcDao;
import com.aswishes.spring.mapper.MapperHelper;
import com.aswishes.novel.mvc.model.MFeedback;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MFeedbackDao extends AbstractJdbcDao {

	@Override
	protected void setTableName() {
		this.tableName = "m_feedback";
	}
	
	public MFeedback query(Long id) {
		return getObjectBy(MapperHelper.getMapper(MFeedback.class), Restriction.eq("id", id));
	}

	public List<MFeedback> queryList(Long userId) {
		return getList(MapperHelper.getMapper(MFeedback.class), Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
	}

	public void delete(Long id) {
		delete(Restriction.eq("id", id));
	}
	
}
