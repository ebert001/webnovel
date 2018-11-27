package com.aswishes.wn.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 页面支持类。主要是工具方法支持。
 * @author lizhou
 */
public class PageSupport {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static PageSupport instance = new PageSupport();
	public static PageSupport getInstance() { return instance; }

	
}
