package com.aswishes.novel.common.prop;

public class StoreProperties extends AbstractProperties {
	private static final StoreProperties instance = new StoreProperties();
	static {
		instance.load();
	}

	@Override
	public String getFileName() {
		return "store.properties";
	}
	
	public static StoreProperties get() {
		return instance;
	}
	
	public String getStoreBase() {
		return getString("store.base");
	}
	
	public String getUserAvatarDir() {
		return getString("store.user.avatar");
	}
	
	public String getBookImgDir() {
		return getString("store.book.img");
	}

}
