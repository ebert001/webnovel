package com.aswishes.wn.common.test;

import org.junit.Test;

import com.aswishes.wn.common.Codes;
import com.aswishes.wn.common.FileUtil;

public class FileUtilTest {

	@Test
	public void testGetHash() {
		long t1 = System.currentTimeMillis();
		System.out.println(FileUtil.getHash("d:/virtual.doc", Codes.HASH_MD5));
		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);
		
//		FileUtil.getHash2("d:/加勒比海盗4.BD1280高清中字.rmvb", Codes.HASH_MD5);
//		long t3 = System.currentTimeMillis();
//		System.out.println(t3 - t2);
	}

}
