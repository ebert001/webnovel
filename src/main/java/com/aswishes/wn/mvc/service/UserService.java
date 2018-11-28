package com.aswishes.wn.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.wn.common.AppUtil;
import com.aswishes.wn.mvc.dao.WnUserDao;
import com.aswishes.wn.mvc.model.WnUser;

@Service
@Transactional
public class UserService extends AbstractService {
	
	public WnUser getUser(String username) {
		return userDao.getUser(username);
	}
	
	public List<WnUser> queryList(int startNo, int perNo) {
		return userDao.queryList(startNo, perNo);
	}
	
	@Transactional
	public boolean login(WnUser user, String password) {
		String username = user.getName();
		String existPassword = user.getPwd();
		String tpwd = AppUtil.getPwd(username, password);
		if (existPassword.equals(tpwd)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	public void updatePassword(WnUser user, String newPassword) {
		String username = user.getName();
		String tpwd = AppUtil.getPwd(username, newPassword);
		user.setPwd(tpwd);
	}
	
	public void save(WnUser user) {
		userDao.save(user);
	}
	
	public void update(WnUser user) {
		userDao.updateByPK(user);
	}
	
	@Autowired
	private WnUserDao userDao;
}
