package com.aswishes.novel.common.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import com.aswishes.novel.core.common.DateUtil;

public class DateUtilTest {

	@Test
	public void testParseDate() {
		String time = "2012-02-12 12:23:42";
		Date date = DateUtil.parseDate("2012-02-12 12:23:42", DateUtil.PATTERN_SECOND);
		assertEquals(time, DateUtil.getDate(date, DateUtil.PATTERN_SECOND));
	}
}
	