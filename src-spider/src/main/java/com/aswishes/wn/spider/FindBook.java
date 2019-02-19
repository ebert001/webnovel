package com.aswishes.wn.spider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 以url为唯一识别标记
 * 只识别<a href="#"></a>标记，如果连接的实际处理是使用js完成，则忽略此标记
 * @author lizhou
 */
@Component
public class FindBook {
	private static final Logger logger = LoggerFactory.getLogger(FindBook.class);
	private String baseUrl;
	/** 只找本站 */
	private boolean onlyFindBaseWeb = true;
	
	public FindBook() {
	}
	
	public void find() {
		try {
			
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
}
