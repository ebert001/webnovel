package com.aswishes.novel.common.file;

import java.io.File;

public abstract class FileManager {
	private static FileManager instance = null;
	static {
		instance = new LocalFileManager();
		instance.init("");
	}
	
	public static FileManager get() {
		return instance;
	}

	public abstract void init(String baseDir);
	
	public abstract String storeBookImg(File file);
	
	public abstract void deleteBookImg(String storeName);
}
