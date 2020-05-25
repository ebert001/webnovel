package com.aswishes.novel.core.common.web;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aswishes.novel.core.common.EnvInfo;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * 页面支持类。主要是工具方法支持。
 * @author lizhou
 */
public class PageSupport {
	protected Logger logger = LoggerFactory.getLogger(PageSupport.class);
	private static PageSupport instance = new PageSupport();
	public static PageSupport getInstance() { return instance; }
	private Config conf = null;

	public void loadProp(String name) {
		File file = EnvInfo.getFile(name);
		conf = ConfigFactory.parseFile(file);
	}
	
	public String get(String name) {
		return conf.getString(name);
	}
}
