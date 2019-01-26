package com.aswishes.wn.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.EventFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aswishes.wn.exception.ServiceException;

public class EnvInfo {
	private static final Logger logger = LoggerFactory.getLogger(EnvInfo.class);
	private static final String envFileName = "env.properties";
	
	public static void loadEnvironment(ServletContext servletContext) {
		logger.info("Ready load environment file.");
		Properties prop = new Properties();
		String projectName = getProjectName(servletContext);
		try {
			File envFile = new File(getWebContainerCustomProjectDir(projectName), envFileName);
			if (!envFile.exists()) {
				logger.warn("Cant find env.properties file in {}", envFile.getPath());
				envFile = getClasspathFile("/" + envFileName);
			}
			if (!envFile.exists()) {
				logger.warn("Cant find env.properties file in {}", envFile.getPath());
				throw new ServiceException("[" + projectName + "]Can't find env.properties file. ");
			}
			prop.load(new FileInputStream(envFile));
			String runningEnv = prop.getProperty("running.env", "pro");
			logger.info("[{}] running env [{}]", projectName, runningEnv);
			System.setProperty("running.env", runningEnv);
		} catch (IOException e) {
			logger.error("Load environment file error.", e);
		}
	}
	
	public static InputStream getClasspathInputStream(String name) {
		return EnvInfo.class.getResourceAsStream(name);
	}
	
	public static File getClasspathFile(String name) {
		return new File(EnvInfo.class.getResource(name).getFile());
	}
	
	public static File getWebContainerHome() {
		return new File(System.getProperty("catalina.home"));
	}
	
	public static File getWebContainerTempDir() {
		return new File(getWebContainerHome(), "temp");
	}
	
	public static File getWebContainerCustomDir() {
		return new File(getWebContainerHome(), "myconf");
	}
	
	public static File getWebContainerCustomProjectDir(String name) {
		return new File(getWebContainerCustomDir(), name);
	}
	
	public static File getUserHome() {
		return new File(System.getProperty("user.home"));
	}

	public static String getProjectName(ServletContext servletContext) {
		return servletContext.getContextPath();
	}
	
	public static String getProjectName(HttpServletRequest request) {
		return getProjectName(request.getServletContext());
	}
	
	
	
}
