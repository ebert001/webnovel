package com.aswishes.wn.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class TempFile {
	private static StoreNameBean storeNameBean = new StoreNameBean();
	
	public static String getStoreName(String fileType, String suffix) {
		if (StringUtils.isBlank(fileType)) {
			return storeNameBean.getStoreName(suffix);
		}
		return fileType + "/" + storeNameBean.getStoreName(suffix);
	}
	
	public static String getStoreName(String suffix) {
		return getStoreName(null, suffix);
	}
	
	public static File getTempFile(byte[] data) {
		File f = getTempFile();
		try {
			FileUtils.writeByteArrayToFile(f, data);
		} catch (IOException e) {
		}
		return f;
	}

	public static File getTempDir() {
		String path = System.getProperty("catalina.home");
		String dayDir = DateFormatUtils.format(new Date(), "yyyyMMdd");
		File dir = null;
		if (StringUtils.isNotBlank(path)) {
			dir = new File(path, "temp/" + dayDir);
		} else {
			dir = new File(System.getProperty("java.io.tmpdir"), dayDir);
		}
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	/** 获取java临时文件。用完记得删除 */
	public static File getTempFile() {
		return new File(getTempDir(), getUUIDString() + ".tmp");
	}

	public static File getTempFile(String prefix, String suffixWithDot) {
		return new File(getTempDir(), prefix + "_" + getUUIDString() + suffixWithDot);
	}
	
	public static String getUUIDString() {
		return UUID.randomUUID().toString();
	}

	private static class StoreNameBean {
		int count = 0;
		public synchronized String getFileName(String suffix) {
			if (count > 99999) {
				count = 0;
			}
			return format("yyyyMMddHHmmss") + "-" + String.format("%05d", (count++)) + suffix;
		}
		public String getFileDirName() {
			Date date = new Date();
			return format(date, "yyyyMM") + "/";
		}
		public String getStoreName(String suffix) {
			return getFileDirName() + getFileName(suffix);
		}
		private String format(Date date, String pattern) {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.format(date);
		}
		private String format(String pattern) {
			return format(new Date(), pattern);
		}
	}
}
