package com.aswishes.novel.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.model.MPermission;
import com.aswishes.spring.SqlHelper.Select;
import com.aswishes.spring.mapper.MapperHelper;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MPermissionDao extends SimpleJdbcDao<MPermission> {
	
	public List<MPermission> getPermissionByRole(Long roleId) {
		String sql = Select.table("m_permission mp").columns("distinct mp.*")
				.leftJoin("m_role_permission mrp").on("mp.id = mrp.permission_id")
				.where("mrp.role_id = ?").toSqlString();
		return getList(sql, MapperHelper.getMapper(MPermission.class), roleId);
	}
	
	public List<MPermission> getPermissionByUser(Long userId) {
		String sql = Select.table("m_permission mp").columns("distinct mp.*")
				.leftJoin("m_role_permission mrp").on("mp.id = mrp.permission_id")
				.leftJoin("m_user_role mur").on("mur.role_id = mrp.role_id")
				.where("mur.user_id = ?").toSqlString();
		return getList(sql, MapperHelper.getMapper(MPermission.class), userId);
	}
	
}
