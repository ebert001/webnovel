package com.aswishes.wn.common.prop;

import com.aswishes.wn.common.EnvInfo;

public class StoreProperties extends AbstractProperties {
	private static final StoreProperties instance = new StoreProperties();
	static {
		instance.load();
	}

	@Override
	public String getFileName() {
		return "res-" + EnvInfo.getRunningEnv() + "/store.properties";
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
