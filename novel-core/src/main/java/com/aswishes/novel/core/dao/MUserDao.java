package com.aswishes.novel.core.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.common.db.SqlAppender;
import com.aswishes.novel.core.model.MUser;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MUserDao extends SimpleJdbcDao<MUser> {

	public MUserDao(DataSource dataSource) {
		super(dataSource);
	}

	public void updatePassword(Long id, String password) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("update ").append(tableName).append("set")
				.append("pwd = :pwd")
				.append("where id = :id", id);
		update(appender);
	}
	
	public void updateLastLoginTime(Long id, Date loginTime) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("update ").append(tableName).append("set")
				.append("last_login_time = :1", loginTime)
				.append("where id = :id", id);
		update(appender);
	}
	
	@Transactional
	public void cleanRoles(Long userId) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("delete from ").append(tableName)
				.append("where user_id = :id", userId);
		update(appender);
	}
	
	@Transactional
	public void bindRoles(Long userId, List<Long> roles) {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into ").append(tableName).append("(user_id, role_id, create_time)").append("values (?,?,?)");
		
		Date date = new Date();
		List<Object[]> args = new ArrayList<Object[]>();
		for (Long roleId : roles) {
			Object[] aas = new Object[3];
			aas[0] = userId;
			aas[1] = roleId;
			aas[2] = date;
			args.add(aas);
		}
		jdbcTemplate.batchUpdate(sb.toString(), args);
	}
}
