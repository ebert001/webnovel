package com.aswishes.novel.common.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aswishes.novel.common.TempFile;

public class LocalFileManager extends FileManager {
	private static final Logger logger = LoggerFactory.getLogger(LocalFileManager.class);

	@Override
	public String storeBookImg(File file, String baseDir) {
		String storeName = TempFile.getStoreName("");
		File storeFile = new File(baseDir, storeName);
		try {
			FileUtils.forceMkdirParent(storeFile);
			FileUtils.moveFile(file, storeFile);
		} catch (IOException e) {
			logger.error("Store book image error: " + storeFile.getPath(), e);
		}
		return storeName;
	}

	@Override
	public void deleteBookImg(String baseDir, String storeName) {
		File storeFile = new File(baseDir, storeName);
		FileUtils.deleteQuietly(storeFile);
	}

	

}
