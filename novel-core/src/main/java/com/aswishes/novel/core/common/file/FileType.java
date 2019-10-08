package com.aswishes.novel.core.common.file;

public enum FileType {
	IMG("img");
	
	private String type;
	private FileType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
}
