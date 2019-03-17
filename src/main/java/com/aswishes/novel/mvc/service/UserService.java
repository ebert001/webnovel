package com.aswishes.novel.mvc.service;

import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.PageResultWrapper;
import com.aswishes.spring.mapper.MapperHelper;
import com.aswishes.spring.service.AbstractService;
import com.aswishes.novel.common.AppConstants;
import com.aswishes.novel.common.web.SessionUtils;
import com.aswishes.novel.mvc.dao.MUserDao;
import com.aswishes.novel.mvc.model.MUser;

@Service
@Transactional
public class UserService extends AbstractService {
	@Autowired
	private MUserDao userDao;
	
	public MUser getUser(Long userId) {
		return userDao.getUser(userId);
	}
	
	public MUser getUser(String username) {
		return userDao.getUser(username);
	}
	
	public PageResultWrapper<MUser> queryPage(int pageNo, int pageSize) {
		return userDao.getPage(MapperHelper.getMapper(MUser.class), pageNo, pageSize);
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
		
		// 更新最近登录时间
		MUser user = SessionUtils.getUser();
		userDao.updateLastLoginTime(user.getId(), new Date());
		
		return true;
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

	@Override
	public void setDao() {
		this.dao = userDao;
	}
	
	
}