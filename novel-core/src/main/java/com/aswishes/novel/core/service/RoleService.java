package com.aswishes.novel.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.dao.MRoleDao;
import com.aswishes.novel.core.model.MRole;

@Service
@Transactional
public class RoleService extends SimpleService<MRole> {
	@Autowired
	private MRoleDao roleDao;
	
	public List<MRole> getRole(Long userId) {
		return roleDao.getRole(userId);
	}
	
	@Transactional
	public void cleanPermissions(Long roleId) {
		roleDao.cleanPermissions(roleId);
	}
	
	@Transactional
	public void bindPermissions(Long roleId, List<Long> permissionIds) {
		roleDao.bindPermissions(roleId, permissionIds);
	}

	
}
