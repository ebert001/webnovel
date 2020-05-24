package com.aswishes.novel.core.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.common.AppUtil;
import com.aswishes.novel.core.common.db.Insert;
import com.aswishes.novel.core.common.db.SqlAppender;
import com.aswishes.novel.core.model.MRole;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MRoleDao extends SimpleJdbcDao<MRole> {
	
	public MRoleDao(DataSource dataSource) {
		super(dataSource);
	}

	public List<MRole> getRole(Long userId) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select mr.* from m_role mr ")
				.append("left join m_user_role mur ")
				.append("on mr.id = mur.role_id ")
				.append("where mur.user_id = :userId", userId);
		return getList(appender, MRole.class);
	}
	
	@Transactional
	public void cleanPermissions(Long roleId) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("delete from ").append(tableName)
				.append("where role_id = :roleId", roleId);
		update(appender);
	}
	
	@Transactional
	public void bindPermissions(Long roleId, List<Long> permissionIds) {
		String sql = Insert.table("m_role_permission").columns("role_id, permission_id, create_time");
		Date date = new Date();
		List<Object[]> bargs = new ArrayList<Object[]>();
		for (Long permissionId : permissionIds) {
			bargs.add(AppUtil.toArray(roleId, permissionId, date));
		}
		jdbcTemplate.batchUpdate(sql, bargs);
	}
}
