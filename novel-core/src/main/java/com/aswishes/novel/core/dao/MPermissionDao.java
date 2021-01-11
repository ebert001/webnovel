package com.aswishes.novel.core.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.common.db.SqlAppender;
import com.aswishes.novel.core.entity.MPermission;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MPermissionDao extends SimpleJdbcDao<MPermission> {
	
	public MPermissionDao(DataSource dataSource) {
		super(dataSource);
	}

	public List<MPermission> getPermissionByRole(Long roleId) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select distinct mp.* from m_permission mp ")
				.append("left join m_role_permission mrp ")
				.append("on mp.id = mrp.permission_id")
				.append("where mrp.role_id = :roleId", roleId);
		return getList(appender, MPermission.class);
	}
	
	public List<MPermission> getPermissionByUser(Long userId) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select distinct mp.* from m_permission mp ")
				.append("left join m_role_permission mrp ")
				.append("on mp.id = mrp.permission_id")
				.append("left join m_user_role mur ")
				.append("on mur.role_id = mrp.role_id")
				.append("where mur.user_id = :userId", userId);
		return getList(appender, MPermission.class);
		
	}
	
}
