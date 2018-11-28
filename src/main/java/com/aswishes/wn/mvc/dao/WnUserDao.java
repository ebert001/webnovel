package com.aswishes.wn.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.Restriction;
import com.aswishes.spring.dao.AbstractJdbcDao;
import com.aswishes.spring.mapper.MapperHelper;
import com.aswishes.wn.mvc.model.WnUser;

/**
 * 对应的数据库表为 wn_book
 */
@Repository
@Transactional
public class WnUserDao extends AbstractJdbcDao {

	@Override
	protected void setTableName() {
		this.tableName = "wn_user";
	}
	
	public WnUser getUser(String username) {
		return getObjectBy(MapperHelper.getMapper(WnUser.class), Restriction.eq("name", username));
	}
	
	public List<WnUser> queryList(int startNo, int perNo) {
		return getList(MapperHelper.getMapper(WnUser.class), Restriction.orderByDesc("reg_time"));
	}
	
}
