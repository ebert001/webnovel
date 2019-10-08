package com.aswishes.novel.core.common;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class AppConstants {
	/** 项目通用编码类型 */
	public static final Charset CHARSET_UTF_8 = StandardCharsets.UTF_8;
	
	public static final String ALG_SHA256 = "SHA256";
	
	public static final String[] DATE_PATTERNS = {
		"yyyy-MM-dd HH:mm:ss",
		"yyyy/MM/dd HH:mm:ss",
		"yyyy-MM-dd",
		"yyyy/MM/dd",
	};
	
	/** 单位：毫秒 */
	public static final int CONNECT_TIMEOUT = 3 * 1000;
	/** 单位：毫秒 */
	public static final int SO_TIMEOUT = 10 * 1000;
	
	public static final int PAGE_NO = 1;
	public static final int PAGE_SIZE = 20;
}
