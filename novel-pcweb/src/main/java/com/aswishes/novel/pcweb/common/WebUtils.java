package com.aswishes.novel.pcweb.common;

import com.aswishes.novel.pcweb.listener.NovelContextListener;

public class WebUtils {
	
	/**
	 * 获取spring的注入对象
	 * @param id 注入对象的id
	 * @return 对象
	 */
	public static Object getBean(String id) {
		return NovelContextListener.getContext().getBean(id);
	}
	
	public static boolean containBean(String id) {
		return NovelContextListener.getContext().containsBean(id);
	}

}
