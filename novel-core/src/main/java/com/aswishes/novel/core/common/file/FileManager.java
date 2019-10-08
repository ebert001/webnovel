package com.aswishes.novel.core.common.file;

import java.io.File;

public abstract class FileManager {
	private static FileManager instance = null;
	static {
		instance = new LocalFileManager();
	}
	
	public static FileManager get() {
		return instance;
	}

	public abstract String storeBookImg(File file);
	
	public abstract void deleteBookImg(String storeName);
}
