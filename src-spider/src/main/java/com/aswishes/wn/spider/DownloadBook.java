package com.aswishes.wn.spider;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Request;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aswishes.wn.exception.WnException;

/**
 * 爬取网络书籍
 */
public class DownloadBook extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(DownloadBook.class);
	/** 目录页地址 */
	private String catalogUrl;
	private String catalogCharset = "UTF-8";
	/** 目录页标题 */
	private String catalogSubjectPath;
	private String catalogStatePath;
	private String catalogAuthorPath;
	private String catalogLastUpdateTimePath;
	private String catalogLastUpdateChapterPath;
	private String catalogImgPath;
	
	private String catalogChapterNodePath;
	private String catalogChapterUrlPath;
	
	private String chapterCharset = "UTF-8";
	private String chapterNodePath;
	
	private List<String> weeds = new ArrayList<String>();
	
	private boolean showDebug = false;
	private WorkState workState = WorkState.RUNNING; 
	
	private IChapterInfo chapterInfo;
	private int lastSerialNo = -1;
	
	public DownloadBook(String catalogUrl) {
		this.catalogUrl = catalogUrl;
	}
	
	@Override
	public void run() {
		discovery();
	}
	
	public DownloadBook discovery() {
		try {
			logger.debug("Load catalog. url: {}", catalogUrl);
			URI catalogURI = URI.create(catalogUrl);
			String originCatalog = new String(Request.Get(catalogURI).execute().returnContent().asBytes(), catalogCharset);
			List<Node> nodes = HtmlTools.findFromHtml(originCatalog, catalogChapterNodePath, showDebug);
			for (int i = 0; i < nodes.size(); i++) {
				if (workState == WorkState.STOP) {
					return this;
				} else if (workState == WorkState.PAUSE) {
					i--;
					Thread.sleep(10 * 1000);
					continue;
				}
				if (lastSerialNo != -1 && i < lastSerialNo) {
					continue;
				}
				Node tnode = nodes.get(i);
				ChapterInfo info = new ChapterInfo();

				String title = tnode.getText().trim();
				info.setChapterTitle(replace(title));
				
				String chapterUrl = tnode.selectSingleNode(catalogChapterUrlPath).getText().trim();
				String scheme = catalogURI.getScheme();
				// 说明是项目路径，需要处理
				if (!chapterUrl.startsWith(scheme)) {
					// REST风格地址，直接拼接在后面
					if (catalogUrl.endsWith("/")) {
						chapterUrl = catalogUrl + chapterUrl;
					} else {
						logger.error("Unkown chapter url: {}, catalog url: {}", chapterUrl, catalogUrl);
						return this;
					}
				}
				info.setChapterUrl(chapterUrl);
				info.setSerialNo(i + 1);
				if (chapterInfo == null) {
					continue;
				}
				boolean loadChapter = chapterInfo.extract(info);
				if (loadChapter) {
					logger.debug("Load chapter. name: {}, url: {}", title, chapterUrl);
					String content = replace(loadChapter(chapterUrl));
					info.setChapterContent(content);
					chapterInfo.extractContent(info, content);
				}
			}
		} catch (Exception e) {
			logger.error("Load book error: " + catalogUrl, e);
		}
		workState = WorkState.STOP;
		return this;
	}
	
	private String loadChapter(String chapterUrl) {
		try {
			String originContent = new String(Request.Get(chapterUrl).execute().returnContent().asBytes(), chapterCharset);
			List<Node> nodess = HtmlTools.findFromHtml(originContent, chapterNodePath, showDebug);
			if (nodess == null || nodess.isEmpty()) {
				throw new WnException("Retrive content xpath error.");
			}
			StringBuilder sb = new StringBuilder();
			for (Node n : nodess) {
				sb.append(n.getStringValue());
			}
			return sb.toString();
		} catch (Exception e) {
			throw new WnException(e.getCause());
		}
	}
	
	private String replace(String str) {
		if (weeds.size() == 0) {
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
	
	public DownloadBook setCatalogCharset(String catalogCharset) {
		this.catalogCharset = catalogCharset;
		return this;
	}
	
	public DownloadBook setCatalogChapterNodePath(String catalogChapterNodePath) {
		this.catalogChapterNodePath = catalogChapterNodePath;
		return this;
	}
	
	public DownloadBook setCatalogChapterUrlPath(String catalogChapterUrlPath) {
		this.catalogChapterUrlPath = catalogChapterUrlPath;
		return this;
	}
	
	public DownloadBook setChapterNodePath(String chapterNodePath) {
		this.chapterNodePath = chapterNodePath;
		return this;
	}
	
	public DownloadBook setChapterCharset(String chapterCharset) {
		this.chapterCharset = chapterCharset;
		return this;
	}
	
	public DownloadBook setShowDebug(boolean showDebug) {
		this.showDebug = showDebug;
		return this;
	}
	
	public DownloadBook setChapterWeeds(String...words) {
		if (words == null) {
			return this;
		}
		List<String> list = Arrays.asList(words);
		this.weeds.addAll(list);
		if (words.length % 2 != 0) {
			this.weeds.add("");
		}
		return this;
	}
	
	public DownloadBook setWorkState(WorkState workState) {
		this.workState = workState;
		if (this.workState == WorkState.PAUSE) {
			Thread.interrupted();
		}
		return this;
	}
	
	public WorkState getWorkState() {
		return workState;
	}
	
	public DownloadBook setChapterInfo(IChapterInfo chapterInfo) {
		this.chapterInfo = chapterInfo;
		return this;
	}

	public void setCatalogSubjectPath(String catalogSubjectPath) {
		this.catalogSubjectPath = catalogSubjectPath;
	}

	public void setCatalogStatePath(String catalogStatePath) {
		this.catalogStatePath = catalogStatePath;
	}

	public void setCatalogAuthorPath(String catalogAuthorPath) {
		this.catalogAuthorPath = catalogAuthorPath;
	}

	public void setCatalogLastUpdateTimePath(String catalogLastUpdateTimePath) {
		this.catalogLastUpdateTimePath = catalogLastUpdateTimePath;
	}

	public void setCatalogLastUpdateChapterPath(String catalogLastUpdateChapterPath) {
		this.catalogLastUpdateChapterPath = catalogLastUpdateChapterPath;
	}

	public void setCatalogImgPath(String catalogImgPath) {
		this.catalogImgPath = catalogImgPath;
	}
	
	public void setLastSerialNo(int lastSerialNo) {
		this.lastSerialNo = lastSerialNo;
	}
	
}
