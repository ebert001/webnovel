package com.aswishes.novel.core.common.prop;

import java.io.File;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aswishes.novel.core.common.EnvInfo;

public abstract class AbstractProperties {
	protected static final Logger logger = LoggerFactory.getLogger(AbstractProperties.class);
	protected PropertiesConfiguration configuration = null;
	
	protected void load() {
		String fileName = null;
		try {
			configuration = new PropertiesConfiguration();
			// 禁用值解析
			configuration.setDelimiterParsingDisabled(true);
			fileName = getFileName();
			File file = EnvInfo.getFile(fileName);
			configuration.load(file);
			// 如果文件发生改变，重新加载该文件
			configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
		} catch (Exception e) {
			logger.error("Load properties file error. File name: " + fileName, e);
		}
	}
	
	public abstract String getFileName();
	
	public String getString(String key) {
		return configuration.getString(key);
	}
}