package com.aswishes.novel.mvc.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.mvc.model.MUser;
import com.aswishes.spring.SqlHelper.Insert;
import com.aswishes.spring.SqlHelper.Update;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MUserDao extends SimpleJdbcDao<MUser> {

	public void updatePassword(Long id, String password) {
		update(Update.table(tableName).setColumns("pwd").whereColumns("id"), password, id);
	}
	
	public void updateLastLoginTime(Long id, Date loginTime) {
		update(Update.table(tableName).setColumns("last_login_time").whereColumns("id"), loginTime, id);
	}
	
	@Transactional
	public void cleanRoles(Long userId) {
		String sql = "delete from m_user_role where user_id = ?";
		update(sql, userId);
	}
	
	@Transactional
	public void bindRoles(Long userId, List<Long> roles) {
		String sql = Insert.table("m_user_role").columns("user_id, role_id, create_time");
		Date date = new Date();
		for (Long roleId : roles) {
			update(sql, userId, roleId, date);
		}
	}
}
