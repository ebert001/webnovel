package com.aswishes.wn.mvc.service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.wn.common.AppUtil;
import com.aswishes.wn.mvc.dao.WnUserDao;
import com.aswishes.wn.mvc.model.WnUser;

import spring.persist.helper.Restriction;

@Service
@Transactional
public class UserService {
	
	public WnUser getUser(String username) throws SQLException {
		return userDao.selectById(Arrays.asList(Restriction.eq("name", username)));
	}
	
	public List<WnUser> queryList(int startNo, int perNo) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(
				Restriction.orderByDesc("reg_time"));
		return userDao.select(startNo, perNo, restrictions);
	}
	
	@Transactional
	public boolean login(WnUser user, String password) throws SQLException {
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
	public void updatePassword(WnUser user, String newPassword) throws SQLException {
		String username = user.getName();
		String tpwd = AppUtil.getPwd(username, newPassword);
		user.setPwd(tpwd);
		userDao.update(user);
	}
	
	public void save(WnUser user) throws SQLException {
		userDao.save(user);
	}
	
	public void update(WnUser user) throws SQLException {
		userDao.update(user);
	}
	
	@Autowired
	private WnUserDao userDao;
}
