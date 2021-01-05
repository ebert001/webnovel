package com.aswishes.novel.spider.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.common.Helper;
import com.aswishes.novel.common.db.PageResult;
import com.aswishes.novel.common.db.SqlAppender;
import com.aswishes.novel.spider.entity.MUser;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MUserDao extends SimpleJdbcDao<MUser> {

	public MUserDao(DataSource dataSource) {
		super(dataSource);
	}
	
	public PageResult<MUser> findPage(int pageNo, int pageSize, String name, String email, String phoneNo) {
		SqlAppender countSql = SqlAppender.namedModel()
				.append("select count(*) from ").append(tableName).append("where 1")
				.appendLikeIfNotBlank("name like :name", name)
				.appendLikeIfNotBlank("email like :email", email)
				.appendLikeIfNotBlank("phone like :phone", phoneNo);
		SqlAppender sql = SqlAppender.namedModel()
				.append("select * from ").append(tableName).append("where 1")
				.appendLikeIfNotBlank("name like :name", name)
				.appendLikeIfNotBlank("email like :email", email)
				.appendLikeIfNotBlank("phone like :phone", phoneNo);
		return getPage(countSql, sql, MUser.class, pageNo, pageSize);
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
			args.add(Helper.toArray(userId, roleId, date));
		}
		jdbcTemplate.batchUpdate(sb.toString(), args);
	}
	
	public void save(MUser entity) {
		
	}
	
	public void update(MUser entity) {
		
	}
}
