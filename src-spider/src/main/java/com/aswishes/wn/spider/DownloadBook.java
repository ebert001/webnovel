package com.aswishes.wn.spider;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.fluent.Request;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aswishes.wn.exception.WnException;

/**
 * 爬取网络书籍
 */
public class DownloadBook implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(DownloadBook.class);
	private String catalogUrl;
	private String catalogCharset = "UTF-8";
	private String catalogChapterNodePath;
	private String catalogChapterUrlPath;
	
	private String chapterCharset = "UTF-8";
	private String chapterNodePath;
	
	private List<String> weeds = new ArrayList<String>();
	
	private boolean loadChapter = true;
	private boolean showDebug = false;
	private WorkState workState = WorkState.RUNNING; 
	
	private IChapterInfo chapterInfo;
	
	public DownloadBook(String catalogUrl) {
		this.catalogUrl = catalogUrl;
	}
	
	@Override
	public void run() {
		discovery();
	}
	
	public DownloadBook discovery() {
		try {
			URI catalogURI = URI.create(catalogUrl);
			String originCatalog = new String(Request.Get(catalogURI).execute().returnContent().asBytes(), catalogCharset);
			List<Node> nodes = HtmlTools.findFromHtml(originCatalog, catalogChapterNodePath, showDebug);
			for (Node tnode : nodes) {
				if (workState == WorkState.STOP) {
					return this;
				} else if (workState == WorkState.PAUSE) {
					Thread.sleep(10 * 1000);
				}
				
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
				
				if (loadChapter) {
					String content = loadChapter(chapterUrl);
					info.setChapterContent(content);
				}
				if (chapterInfo == null) {
					continue;
				}
				chapterInfo.extract(info);
			}
		} catch (Exception e) {
			logger.error("Load book error: " + catalogUrl, e);
		}
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
				sb.append(n.asXML());
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
			r = r.replaceAll(weeds.get(i), weeds.get(i + 1));
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
	
	public DownloadBook setLoadChapter(boolean loadChapter) {
		this.loadChapter = loadChapter;
		return this;
	}
	
	public DownloadBook setShowDebug(boolean showDebug) {
		this.showDebug = showDebug;
		return this;
	}
	
	public DownloadBook setChapterWeeds(String...words) {
		if (words.length % 2 != 0) {
			throw new IllegalArgumentException("Replace key words are not key pairs.");
		}
		this.weeds.addAll(Arrays.asList(words));
		return this;
	}
	
	public DownloadBook setWorkState(WorkState workState) {
		this.workState = workState;
		if (this.workState == WorkState.PAUSE) {
			Thread.interrupted();
		}
		return this;
	}
	
	public DownloadBook setChapterInfo(IChapterInfo chapterInfo) {
		this.chapterInfo = chapterInfo;
		return this;
	}
}
