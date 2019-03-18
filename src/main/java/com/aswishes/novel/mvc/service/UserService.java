package com.aswishes.novel.mvc.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.common.AppConstants;
import com.aswishes.novel.common.web.SessionUtils;
import com.aswishes.novel.mvc.dao.MUserDao;
import com.aswishes.novel.mvc.model.MRole;
import com.aswishes.novel.mvc.model.MUser;

@Service
@Transactional
public class UserService extends SimpleService<MUser> {
	@Autowired
	private MUserDao userDao;
	@Autowired
	private RoleService roleService;
	
	public MUser getUser(String username) {
		return userDao.getByName(username);
	}
	
	public String calPassword(MUser user, String password) {
		String salt = user.getSalt();
		return calPassword(salt, password);
	}
	
	public String calPassword(String salt, String password) {
		if (salt == null) {
			return Base64.encodeBase64String(DigestUtils.sha256(password.getBytes(AppConstants.CHARSET_UTF_8)));
		}
    	return Base64.encodeBase64String(DigestUtils.sha256((password + salt).getBytes(AppConstants.CHARSET_UTF_8)));
	}
	
	@Transactional
	public boolean login(String username, String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		if (subject.isAuthenticated()) {
			return false;
		}
		MUser user = SessionUtils.getUser();
		// 加载权限信息
		loadPermissions(user.getId());
		
		// 更新最近登录时间
		userDao.updateLastLoginTime(user.getId(), new Date());
		return true;
	}
	
	public void loadPermissions(Long userId) {
		
	}
	
	public void logout() {
		SessionUtils.invalidate();
	}
	
	@Transactional
	public void updatePassword(MUser user, String newPassword) {
		String tpwd = calPassword(user, newPassword);
		userDao.updatePassword(user.getId(), tpwd);
	}
	
	public void save(MUser user) {
		userDao.save(user);
	}
	
	public void update(MUser user) {
		userDao.updateByPK(user, true);
	}
	
	@Transactional
	public void cleanRoles(Long userId) {
		userDao.cleanRoles(userId);
	}
	
	@Transactional
	public void bindRoles(Long userId, List<Long> roles) {
		userDao.bindRoles(userId, roles);
	}
	
	public List<MRole> loadRoles(Long userId) {
		return roleService.getRole(userId);
	}
	
	@Override
	public void setDao() {
		this.dao = userDao;
	}
}
