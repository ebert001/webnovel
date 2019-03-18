package com.aswishes.novel.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.mvc.dao.MRoleDao;
import com.aswishes.novel.mvc.model.MRole;

@Service
@Transactional
public class RoleService extends SimpleService<MRole> {
	
	@Transactional
	public void cleanPermissions(Long roleId) {
		roleDao.cleanPermissions(roleId);
	}
	
	@Transactional
	public void bindPermissions(Long roleId, List<Long> permissionIds) {
		roleDao.bindPermissions(roleId, permissionIds);
	}

	@Autowired
	private MRoleDao roleDao;

	@Override
	public void setDao() {
		this.dao = roleDao;
	}
}
