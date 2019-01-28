package com.aswishes.wn.mvc.service;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.service.AbstractService;
import com.aswishes.wn.common.AppConstants;
import com.aswishes.wn.common.AppUtil;
import com.aswishes.wn.mvc.dao.WnUserDao;
import com.aswishes.wn.mvc.model.WnUser;

@Service
@Transactional
public class UserService extends AbstractService {
	@Autowired
	private WnUserDao userDao;
	
	public WnUser getUser(String username) {
		return userDao.getUser(username);
	}
	
	public List<WnUser> queryList(int startNo, int perNo) {
		return userDao.queryList(startNo, perNo);
	}
	
	public String calPassword(WnUser user, String password) {
		String salt = user.getSalt();
		if (salt == null) {
			return Base64.encodeBase64String(DigestUtils.sha256(password.getBytes(AppConstants.CHARSET_UTF_8)));
		}
    	return Base64.encodeBase64String(DigestUtils.sha256((password + salt).getBytes(AppConstants.CHARSET_UTF_8)));
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

	@Override
	public void setDao() {
		this.dao = userDao;
	}
	
	
}
