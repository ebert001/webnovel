package com.aswishes.wn.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.Restriction;
import com.aswishes.spring.dao.AbstractJdbcDao;
import com.aswishes.spring.mapper.MapperHelper;
import com.aswishes.wn.mvc.model.WnMemo;

/**
 * 对应的数据库表为 wn_book
 */
@Repository
@Transactional
public class WnMemoDao extends AbstractJdbcDao {

	@Override
	protected void setTableName() {
		this.tableName = "wn_memo";
	}
	
	public WnMemo getMemo(String id) {
		return getObjectBy(MapperHelper.getMapper(WnMemo.class), Restriction.eq("id", id));
	}

	public List<WnMemo> queryList(String userId) {
		return getList(MapperHelper.getMapper(WnMemo.class), Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
	}

	public void delete(String id) {
		delete(Restriction.eq("id", id));
	}
	
}
