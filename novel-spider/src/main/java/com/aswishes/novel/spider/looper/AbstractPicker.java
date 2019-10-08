package com.aswishes.novel.spider.looper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Node;

public abstract class AbstractPicker extends Thread {
	protected boolean showDebug = false;
	protected List<String> weeds = new ArrayList<String>();
	
	public AbstractPicker() {
		this.setDaemon(true);
	}
	
	@Override
	public void run() {
		discovery();
	}
	
	protected abstract void discovery();
	
	protected String findInfo(Node node, String nodePath) {
		if (StringUtils.isBlank(nodePath)) {
			return null;
		}
		Node tnode = node.selectSingleNode(nodePath);
		if (tnode == null) {
			return null;
		}
		String value = tnode.getText();
		if (value == null) {
			return null;
		}
		return value.trim();
	}
	
	public void setWeeds(String...words) {
		if (words == null) {
			return;
		}
		List<String> list = Arrays.asList(words);
		this.weeds.addAll(list);
		if (words.length % 2 != 0) {
			this.weeds.add("");
		}
	}
	
	public void addWeeds(List<String> weeds) {
		this.weeds.addAll(weeds);
	}
	
	protected String replace(String str) {
		if (weeds.size() == 0 || StringUtils.isBlank(str)) {
			return str;
		}
		String r = str;
		for (int i = 0; i < weeds.size(); i += 2) {
			String key = weeds.get(i);
			if (StringUtils.isEmpty(key)) {
				continue;
			}
			String value = weeds.get(i + 1);
			if (value == null) {
				value = "";
			}
			r = r.replaceAll(key.trim(), value);
		}
		return r;
	}
	
	public void setShowDebug(boolean showDebug) {
		this.showDebug = showDebug;
	}
}
