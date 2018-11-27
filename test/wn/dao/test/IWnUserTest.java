package com.aswishes.wn.mvc.dao.test;

import java.util.Date;
import java.util.HashMap;

import com.aswishes.wn.common.AppUtil;
import com.aswishes.wn.dao.WnUserMapper;
import com.aswishes.wn.dao.model.WnUser;

public class IWnUserTest extends Spring {

	private WnUserMapper dao = null;
	
	protected void setUp() throws Exception {
		super.setUp();
		dao = sqlSession.getMapper(WnUserMapper.class);
	}
	
	public void testAddUser() {
		WnUser wnUser = new WnUser();
		wnUser.setName("test_1");
		wnUser.setPwd(AppUtil.getPwd("test_1", "123"));
		wnUser.setAlias("test_1");
		wnUser.setAliasUsed(1);
		wnUser.setRegTime(new Date());
		dao.addUser(wnUser);
	}
	
	public void testGetCount() {
		int pwd = dao.getCount();
		System.out.println(pwd);
	}
	
	public void testGetUserList() {
		dao.getUserList(new HashMap<String, Object>());
	}
}
