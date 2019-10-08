package com.aswishes.novel.core.common.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aswishes.novel.core.common.TempFile;
import com.aswishes.novel.core.common.prop.StoreProperties;

public class LocalFileManager extends FileManager {
	private static final Logger logger = LoggerFactory.getLogger(LocalFileManager.class);

	@Override
	public String storeBookImg(File file) {
		String storeName = TempFile.getStoreName("");
		File storeFile = new File(StoreProperties.get().getBookImgDir(), storeName);
		try {
			FileUtils.forceMkdirParent(storeFile);
			FileUtils.moveFile(file, storeFile);
		} catch (IOException e) {
			logger.error("Store book image error: " + storeFile.getPath(), e);
		}
		return storeName;
	}

	@Override
	public void deleteBookImg(String storeName) {
		File storeFile = new File(StoreProperties.get().getBookImgDir(), storeName);
		FileUtils.deleteQuietly(storeFile);
	}

	

}
