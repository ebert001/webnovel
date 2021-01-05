package com.aswishes.novel.spider.common.prop;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aswishes.novel.spider.common.EnvInfo;

public abstract class AbstractProperties {
	protected static final Logger logger = LoggerFactory.getLogger(AbstractProperties.class);
	protected PropertiesConfiguration configuration = null;
	
	protected void load() {
		String fileName = null;
		try {
			ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration> builder =
				    new ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration>(PropertiesConfiguration.class);
			PropertiesBuilderParameters params = new Parameters().properties();
			
			fileName = getFileName();
			File file = EnvInfo.getFile(fileName);
	        params.setFile(file);
	        params.setThrowExceptionOnMissing(true);
	        params.setListDelimiterHandler(new DefaultListDelimiterHandler(';'));
	        params.setIncludesAllowed(false);
			builder.configure(params);
			
			PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(builder.getReloadingController(),
				    null, 1, TimeUnit.MINUTES);
			trigger.start();
			
			configuration = builder.getConfiguration();
		} catch (Exception e) {
			logger.error("Load properties file error. File name: " + fileName, e);
		}
	}
	
	public abstract String getFileName();
	
	public String getString(String key) {
		return configuration.getString(key);
	}
}
