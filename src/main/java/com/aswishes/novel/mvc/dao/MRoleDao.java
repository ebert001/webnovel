package com.aswishes.novel.mvc.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.mvc.model.MRole;
import com.aswishes.spring.SqlHelper.Insert;
import com.aswishes.spring.SqlHelper.Select;
import com.aswishes.spring.mapper.MapperHelper;

/**
 * 对应的数据库表为 novel_book
 */
@Repository
@Transactional
public class MRoleDao extends SimpleJdbcDao<MRole> {
	
	public List<MRole> getRole(Long userId) {
		String sql = Select.table("m_role mr").leftJoin("m_user_role mur").on("mr.id = mur.role_id").where("mur.user_id = ?").toSqlString();
		return getList(sql, MapperHelper.getMapper(MRole.class), userId);
	}
	
	@Transactional
	public void cleanPermissions(Long roleId) {
		String sql = "delete from m_role_permission where role_id = ?";
		update(sql, roleId);
	}
	
	@Transactional
	public void bindPermissions(Long roleId, List<Long> permissionIds) {
		String sql = Insert.table("m_role_permission").columns("role_id, permission_id, create_time");
		Date date = new Date();
		for (Long permissionId : permissionIds) {
			update(sql, roleId, permissionId, date);
		}
	}
}
