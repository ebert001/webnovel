package com.aswishes.novel.common.test;

import org.junit.Test;

import com.aswishes.novel.core.common.DateUtil;

public class DateUtilTest {

	@Test
	public void testParseDate() {
		System.out.println(DateUtil.parseDate("2012-02-12 12:23:42", DateUtil.PATTERN_SECOND));
	}
}
	