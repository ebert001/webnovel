package com.aswishes.wn.common.test;

import org.junit.Test;

import com.aswishes.wn.common.DateUtil;

public class DateUtilTest {

	@Test
	public void testParseDate() {
		System.out.println(DateUtil.parseDate("2012-02-12 12:23:42", DateUtil.PATTERN_SECOND));
	}
}
