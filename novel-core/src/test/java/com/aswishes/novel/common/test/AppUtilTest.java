package com.aswishes.novel.common.test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import com.aswishes.novel.core.common.AppUtil;

public class AppUtilTest {

	@Test
	public void testMd5codec() {
		String str = "qwerasdfzxcvfygjhtyuxcbcxvsthgrtysdfsdfadqwer";
		String r = AppUtil.md5codec(str);
		assertEquals(32, r.length());
		
		str = "ebert";
		r = AppUtil.md5codec(str);
		assertEquals(32, r.length());
	}
	
	private String msg = "你好abcd1234-=-+";

	@Test
	public void testBase64() {
		String r = AppUtil.base64Encode(msg);
		String r2 = AppUtil.base64Decode(r);
		Assert.assertEquals(msg, r2);
	}
	
	@Test
	public void testHex() {
		String r = AppUtil.string2Hex(msg);
		String r2 = AppUtil.hex2String(r);
		Assert.assertEquals(msg, r2);
	}
	
	@Test
	public void testGetHump() {
		Assert.assertEquals("uuidtar", AppUtil.getHump("_UUIDTar"));
		Assert.assertEquals("UUIDTar", AppUtil.getHump("UUIDTar"));
		Assert.assertEquals("uuidTar", AppUtil.getHump("UUID_tar"));
		Assert.assertEquals("sysUser", AppUtil.getHump("sys_user"));
		Assert.assertEquals("sysUser", AppUtil.getHump("_sys_user"));
		Assert.assertEquals("sysUserAbc", AppUtil.getHump("_sys_user_abc"));
		Assert.assertEquals("", AppUtil.getHump(""));
		Assert.assertEquals(null, AppUtil.getHump(null));
	}
	
	@Test
	public void testUpperCaseFirst() {
		Assert.assertEquals("UUID_tar", AppUtil.upperCaseFirst("UUID_tar"));
	}
	
}
