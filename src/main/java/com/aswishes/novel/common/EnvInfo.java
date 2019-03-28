package com.aswishes.novel.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aswishes.novel.exception.ServiceException;

public class EnvInfo {
	private static final Logger logger = LoggerFactory.getLogger(EnvInfo.class);
	private static final String RUNNING_ENV_KEY = "running.env";
	private static String contextPath = null;
	private static String projectName = null;
	private static String projectPath = null;
	/** 项目启动时间 */
	private static Date startTime;
	private static int processId;
	private static String version;
	
	public static void loadEnvironment(ServletContext servletContext) {
		startTime = new Date();
		logger.info("User home: {}", System.getProperty("user.home"));
		logger.info("Ready load environment file.");
		contextPath = servletContext.getContextPath();
		projectName = servletContext.getContextPath().substring(1);
		projectPath = servletContext.getRealPath("");

		loadProcessId();
		loadEnv();
		loadLog4j();
		loadVersion();
	}
	
	private static void loadProcessId() {
		String name = ManagementFactory.getRuntimeMXBean().getName();
		int index = name.indexOf("@");
		processId = Integer.parseInt(name.substring(0, index));
	}
	
	private static void loadEnv() {
		try {
			Properties prop = new Properties();
			File envFile = getFile("env.properties");
			prop.load(new FileInputStream(envFile));
			String runningEnv = prop.getProperty(RUNNING_ENV_KEY, "pro");
			logger.info("[{}] running env [{}]", projectName, runningEnv);
			System.setProperty(RUNNING_ENV_KEY, runningEnv);
		} catch (IOException | ServiceException e) {
			logger.error("Load environment file error.", e);
		}
	}
	
	// 加载开发人员自己的log4j文件
	private static void loadLog4j() {
		String env = System.getProperty(RUNNING_ENV_KEY);
    	String privateLog4jName = "res-" + env + "/log4j2.xml";
    	if (new File(EnvInfo.getClasspathDir(), privateLog4jName).exists()) {
    		Configurator.initialize(null, privateLog4jName);
    	}
	}
	
	private static void loadVersion() {
		try {
			version = FileUtils.readFileToString(getFile("version.txt"), StandardCharsets.UTF_8);
		} catch (IOException e) {
			logger.error("Load version.txt error", e);
		}
	}
	
	public static String getRunningEnv() {
		return System.getProperty("running.env", "pro");
	}
	
	public static InputStream getClasspathInputStream(String name) {
		return EnvInfo.class.getResourceAsStream(name);
	}
	
	public static File getClasspathFile(String name) {
		return new File(EnvInfo.class.getResource(name).getFile());
	}
	
	public static File getClasspathDir() {
		return new File(EnvInfo.class.getResource("/").getFile());
	}
	
	public static File getWebContainerHomeDir() {
		return new File(System.getProperty("catalina.home"));
	}
	
	public static File getWebContainerTempDir() {
		return new File(getWebContainerHomeDir(), "temp");
	}
	
	public static File getWebContainerConfigDir() {
		return new File(getWebContainerHomeDir(), "myconf");
	}
	
	public static File getWebContainerConfigProjectDir() {
		return new File(getWebContainerConfigDir(), projectName);
	}
	
	public static File getProjectEnvDir() {
		String env = System.getProperty(RUNNING_ENV_KEY);
		if (env == null) {
			return getClasspathDir();
		}
		return new File(getClasspathDir(), "res-" + env);
	}
	
	public static File getUserHome() {
		return new File(System.getProperty("user.home"));
	}

	public static String getProjectName() {
		return projectName;
	}
	
	public static String getProjectPath() {
		return projectPath;
	}
	
	public static String getContextPath() {
		return contextPath;
	}
	
	public static Date getStartTime() {
		return startTime;
	}
	
	public static int getProcessId() {
		return processId;
	}
	
	/**
	 * 尝试寻找文件的位置:
	 * 1.用户目录
	 * 2.Web容器目录
	 * 3.Web容器下myconf/projectname/
	 * 4.classpath下/res-env/
	 * 5.classpath
	 * @param name 文件名
	 */
	public static File getFile(String name) {
		File f = new File(System.getProperty("user.home"), name);
		if (f.exists()) {
			logger.info("Load file: {}", f.getPath());
			return f;
		}
		f = new File(getWebContainerHomeDir(), name);
		if (f.exists()) {
			logger.info("Load file: {}", f.getPath());
			return f;
		}
		f = new File(getWebContainerConfigProjectDir(), name);
		if (f.exists()) {
			logger.info("Load file: {}", f.getPath());
			return f;
		}
		f = new File(getProjectEnvDir(), name);
		if (f.exists()) {
			logger.info("Load file: {}", f.getPath());
			return f;
		}
		f = getClasspathFile(name);
		if (f.exists()) {
			logger.info("Load file: {}", f.getPath());
			return f;
		}
		throw new ServiceException("File not found. " + name);
	}
	
	public static String getVersion() {
		return version;
	}
	
}
