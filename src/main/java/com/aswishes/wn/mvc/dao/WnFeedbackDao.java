package com.aswishes.wn.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.Restriction;
import com.aswishes.spring.dao.AbstractJdbcDao;
import com.aswishes.spring.mapper.MapperHelper;
import com.aswishes.wn.mvc.model.WnFeedback;

/**
 * 对应的数据库表为 wn_book
 */
@Repository
@Transactional
public class WnFeedbackDao extends AbstractJdbcDao {

	@Override
	protected void setTableName() {
		this.tableName = "wn_feedback";
	}
	
	public WnFeedback query(String id) {
		return getObjectBy(MapperHelper.getMapper(WnFeedback.class), Restriction.eq("id", id));
	}

	public List<WnFeedback> queryList(String userId) {
		return getList(MapperHelper.getMapper(WnFeedback.class), Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
	}

	public void delete(String id) {
		delete(Restriction.eq("id", id));
	}
	
}
