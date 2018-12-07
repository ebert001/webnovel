package com.aswishes.wn.spider;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.fluent.Request;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aswishes.wn.exception.WnException;

/**
 * 爬取网络书籍
 */
public class DownloadBook {
	private static final Logger logger = LoggerFactory.getLogger(DownloadBook.class);
	private String catalogUrl;
	private String catalogCharset = "UTF-8";
	private String chapterNodePath;
	private String chapterUrlNode;
	
	private String contentCharset = "UTF-8";
	private String contentNodePath;
	
	private List<String> excludeAttrs = new ArrayList<String>();
	private List<ChapterInfo> chapters = new ArrayList<ChapterInfo>();
	private List<String> replaceKeywords = new ArrayList<String>();
	
	private boolean showDebug = false;
	
	public DownloadBook(String catalogUrl) {
		this.catalogUrl = catalogUrl;
	}
	
	public DownloadBook discovery() {
		try {
			loadCatalog();
			for (ChapterInfo info : chapters) {
				logger.debug("Loading chapter: {}, {}", info.getChapterUrl(), info.getChapterTitle());
				String content = loadContent(info.getChapterUrl());
				info.setChapterContent(content);
			}
		} catch (Exception e) {
			logger.error("Load book error: " + catalogUrl, e);
		}
		logger.debug("chapters: {}", chapters);
		return this;
	}
	
	private void loadCatalog() {
		try {
			URI catalogURI = URI.create(catalogUrl);
			String originCatalog = new String(Request.Get(catalogURI).execute().returnContent().asBytes(), catalogCharset);
			List<Node> nodes = HtmlTools.findFromHtml(originCatalog, chapterNodePath, showDebug);
			for (Node tnode : nodes) {
				Element ele = (Element) tnode;
				boolean isContinue = false;
				for (Attribute att : ele.attributes()) {
					if (excludeAttrs.contains(att.getName())) {
						isContinue = true;
						break;
					}
				}
				if (isContinue) {
					continue;
				}
				ChapterInfo chapterInfo = new ChapterInfo();

				String title = tnode.getText().trim();
				chapterInfo.setChapterTitle(replace(title));
				
				String chapterUrl = ele.attributeValue(chapterUrlNode);
				String scheme = catalogURI.getScheme();
				// 说明是项目路径，需要处理
				if (!chapterUrl.startsWith(scheme)) {
					// REST风格地址，直接拼接在后面
					if (catalogUrl.endsWith("/")) {
						chapterUrl = catalogUrl + chapterUrl;
					} else {
						logger.error("Unkown chapter url: {}, catalog url: {}", chapterUrl, catalogUrl);
						return;
					}
				}
				chapterInfo.setChapterUrl(chapterUrl);
				chapters.add(chapterInfo);
			}
		} catch (Exception e) {
			logger.error("Load book error: " + catalogUrl, e);
		}
	}
	
	private String loadContent(String chapterUrl) {
		try {
			String originContent = new String(Request.Get(chapterUrl).execute().returnContent().asBytes(), contentCharset);
//			String xml = HtmlTools.html2Xml(originContent);
			List<Node> nodess = HtmlTools.findFromHtml(originContent, contentNodePath, showDebug);
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
		if (replaceKeywords.size() == 0) {
			return str;
		}
		String r = str;
		for (int i = 0; i < replaceKeywords.size(); i += 2) {
			r = r.replaceAll(replaceKeywords.get(i), replaceKeywords.get(i + 1));
		}
		return r;
	}
	
	public DownloadBook setCatalogCharset(String catalogCharset) {
		this.catalogCharset = catalogCharset;
		return this;
	}
	
	public DownloadBook setChapterNodePath(String chapterNodePath) {
		this.chapterNodePath = chapterNodePath;
		return this;
	}
	
	public DownloadBook setChapterUrlNode(String chapterUrlNode) {
		this.chapterUrlNode = chapterUrlNode;
		return this;
	}
	
	public DownloadBook setIgnoreMarkOfChapterAttribute(String...attrs) {
		this.excludeAttrs.addAll(Arrays.asList(attrs));
		return this;
	}
	
	public DownloadBook setContentNodePath(String contentNodePath) {
		this.contentNodePath = contentNodePath;
		return this;
	}
	
	public DownloadBook setContentCharset(String contentCharset) {
		this.contentCharset = contentCharset;
		return this;
	}
	
	public DownloadBook setShowDebug(boolean showDebug) {
		this.showDebug = showDebug;
		return this;
	}
	
	public DownloadBook setReplaceKeywords(String...words) {
		if (words.length % 2 != 0) {
			throw new IllegalArgumentException("Replace key words are not key pairs.");
		}
		this.replaceKeywords.addAll(Arrays.asList(words));
		return this;
	}
	
	public List<ChapterInfo> getChapters() {
		return chapters;
	}
}
