package com.aswishes.novel.common.test;

import org.junit.Assert;
import org.junit.Test;

import com.aswishes.novel.common.AppUtil;

public class AppUtilTest {

	@Test
	public void testMd5codec() {
		String str = "qwerasdfzxcvfygjhtyuxcbcxvsthgrtysdfsdfadqwer";
		String r = AppUtil.md5codec(str);
		System.out.println("长度：" + r.length() + ", " + r);
		
		str = "ebert";
		r = AppUtil.md5codec(str);
		System.out.println("长度：" + r.length() + ", " + r);
	}

	@Test
	public void testBase64Encode() {
		String str = "你好abcd1234-=-+";
		String r = AppUtil.base64Encode(str);
		Assert.assertEquals("xOO6w2FiY2QxMjM0LT0tKw==", r);
	}
	
	@Test
	public void testBase64Decode() {
		String str = "xOO6w2FiY2QxMjM0LT0tKw==";
		String r = AppUtil.base64Decode(str);
		Assert.assertEquals("你好abcd1234-=-+", r);
	}
	
	@Test
	public void testString2Hex() {
		String str = "你好abcd1234-=-+";
		String r = AppUtil.string2Hex(str);
		Assert.assertEquals("C4E3BAC361626364313233342D3D2D2B", r);
	}
	
	@Test
	public void testHex2String() {
		String str = "C4E3BAC361626364313233342D3D2D2B";
		String r = AppUtil.hex2String(str);
		Assert.assertEquals("你好abcd1234-=-+", r);
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
	
	@Test
	public void testGetUuid() {
		System.out.println(AppUtil.getUuid());
	}
}
