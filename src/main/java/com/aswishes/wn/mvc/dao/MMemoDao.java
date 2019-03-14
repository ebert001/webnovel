package com.aswishes.wn.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.Restriction;
import com.aswishes.spring.dao.AbstractJdbcDao;
import com.aswishes.spring.mapper.MapperHelper;
import com.aswishes.wn.mvc.model.MMemo;

/**
 * 对应的数据库表为 wn_book
 */
@Repository
@Transactional
public class MMemoDao extends AbstractJdbcDao {

	@Override
	protected void setTableName() {
		this.tableName = "wn_memo";
	}
	
	public MMemo getMemo(Long id) {
		return getObjectBy(MapperHelper.getMapper(MMemo.class), Restriction.eq("id", id));
	}

	public List<MMemo> queryList(Long userId) {
		return getList(MapperHelper.getMapper(MMemo.class), Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
	}

	public void delete(Long id) {
		delete(Restriction.eq("id", id));
	}
	
}
