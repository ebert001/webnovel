package com.aswishes.wn.mvc.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.Restriction;
import com.aswishes.spring.SqlHelper.Update;
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
	
	public WnUser getUser(Long userId) {
		return getObjectBy(MapperHelper.getMapper(WnUser.class), Restriction.eq("id", userId));
	}
	
	public WnUser getUser(String username) {
		return getObjectBy(MapperHelper.getMapper(WnUser.class), Restriction.eq("name", username));
	}
	
	public List<WnUser> queryList(int pageNo, int pageSize) {
		return getList(MapperHelper.getMapper(WnUser.class), Restriction.orderByDesc("reg_time"));
	}
	
	public void updatePassword(Long id, String password) {
		update(Update.table(tableName).setColumns("pwd").whereColumns("id"), password, id);
	}
	
	public void updateLastLoginTime(Long id, Date loginTime) {
		update(Update.table(tableName).setColumns("last_login_time").whereColumns("id"), loginTime, id);
	}
}
