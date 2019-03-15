package com.aswishes.novel.spider.looper;

import java.util.List;

import org.apache.http.client.fluent.Request;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aswishes.novel.exception.ServiceException;

/**
 * 爬取网络书籍
 */
public class PickChapter extends AbstractPicker {
	private static final Logger logger = LoggerFactory.getLogger(PickChapter.class);
	
	private String bookName;
	private String name;
	private String chapterUrl;
	private String chapterCharset = "UTF-8";
	private String chapterNodePath;
	private String chapterContent;
	
	public PickChapter(String bookName, String name, String chapterUrl) {
		this.bookName = bookName;
		this.name = name;
		this.chapterUrl = chapterUrl;
	}
	
	public void discovery() {
		try {
			logger.debug("Load chapter. book name: {}, name: {}, url: {}", bookName, name, chapterUrl);
			String originContent = new String(Request.Get(chapterUrl).execute().returnContent().asBytes(), chapterCharset);
			List<Node> nodess = HtmlTools.findFromHtml(originContent, chapterNodePath, showDebug);
			if (nodess == null || nodess.isEmpty()) {
				throw new ServiceException("Retrive content xpath error.");
			}
			StringBuilder sb = new StringBuilder();
			for (Node n : nodess) {
				sb.append(n.getStringValue());
			}
			chapterContent = replace(sb.toString());
		} catch (Exception e) {
			throw new ServiceException(e.getCause());
		}
	}
	
	public PickChapter setChapterNodePath(String chapterNodePath) {
		this.chapterNodePath = chapterNodePath;
		return this;
	}
	
	public PickChapter setChapterCharset(String chapterCharset) {
		this.chapterCharset = chapterCharset;
		return this;
	}
	
	public String getChapterContent() {
		return chapterContent;
	}
	
}
