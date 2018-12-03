package com.aswishes.wn.spider;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.fluent.Request;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 爬取网络书籍
 */
public class DownloadBook {
	private static final Logger logger = LoggerFactory.getLogger(DownloadBook.class);
	private String catalogUrl;
	private String catalogCharset = "UTF-8";
	private String chapterNodePath;
	private String chapterUrlNode;
	private String chapterCharset = "UTF-8";
	private List<String> excludeAttrs = new ArrayList<String>();
	private List<ChapterInfo> chapters = new ArrayList<ChapterInfo>();
	private List<String> replaceKeywords = new ArrayList<String>();
	
	private boolean showDebug = false;
	
	
	public DownloadBook(String catalogUrl) {
		this.catalogUrl = catalogUrl;
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public void discovery() {
		try {
			URI catalogURI = URI.create(catalogUrl);
			String catalog = new String(Request.Get(catalogURI).execute().returnContent().asBytes(), catalogCharset);
			if (showDebug) {
	//			logger.debug("Origin catalog: " + catalog);
			}
			
			TimeUnit.SECONDS.sleep(1);
			
			HtmlCleaner cleaner = new HtmlCleaner();
			CleanerProperties props = cleaner.getProperties();
			TagNode node = cleaner.clean(catalog);
			catalog = new PrettyXmlSerializer(props).getAsString(node);
			if (showDebug) {
				logger.debug("Format catalog: " + catalog);
			}
			Document doc = XmlTools.makeDocument(catalog);
			List<Node> nodes = doc.selectNodes(chapterNodePath);
			
			ChapterInfo chapterInfo = new ChapterInfo();
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

				String chapterContent = new String(Request.Get(chapterInfo.getChapterUrl()).execute().returnContent().asBytes(), chapterCharset);
				chapterInfo.setChapterContent(chapterContent);
				if (showDebug) {
					logger.debug("Chapter Info: \n{}", chapterInfo.toString());
				}
				TimeUnit.SECONDS.sleep(2);
			}
			chapters.add(chapterInfo);
		} catch (Exception e) {
			logger.error("Load book error: " + catalogUrl, e);
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
	
	public DownloadBook setChapterCharset(String chapterCharset) {
		this.chapterCharset = chapterCharset;
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
}
