package com.aswishes.wn.spider;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.fluent.Request;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadBookList {
	private static final Logger logger = LoggerFactory.getLogger(DownloadBook.class);
	private String bookListCharset = "UTF-8";
	private String bookNodePath;
	private String bookNamePath;
	private String bookUrlPath;
	private String totalPagePath;
	private String totalPageExpress;
	private String imgUrlPath;
	private String authorPath;
	private String introductionPath;
	private String lastUpdateTimePath;
	private boolean showDebug = false;
	
	private String bookListUrlPrefix;
	private String bookListUrlSuffix;
	private int pageNo = 1;
	private int totalPage = 1;
	
	public DownloadBookList(String bookListUrlPrefix, int pageNo, String bookListUrlSuffix) {
		this.pageNo = pageNo;
		this.bookListUrlPrefix = bookListUrlPrefix;
		this.bookListUrlSuffix = bookListUrlSuffix;
	}
	
	public DownloadBookList discovery(IBookInfo bookInfo) {
		do {
			String bookListUrl = getBookListPageUrl(bookListUrlPrefix, pageNo, bookListUrlSuffix);
			try {
				String bookListContent = new String(Request.Get(bookListUrl).execute().returnContent().asBytes(), bookListCharset);
				List<Node> nodes = HtmlTools.findFromHtml(bookListContent, bookNodePath, showDebug);
				logger.debug("node size: {}", nodes.size());
				for (int i = 0; i < nodes.size(); i++) {
					Node tnode = nodes.get(i);
					Node bookNameNode = tnode.selectSingleNode(bookNamePath);
					if (bookNameNode == null) {
						throw new IllegalAccessException("Cant find book name by path: " + bookNamePath);
					}
					Node bookUrlNode = tnode.selectSingleNode(bookUrlPath);
					if (bookUrlNode == null) {
						throw new IllegalAccessException("Cant find book url by path: " + bookUrlPath);
					}
					String bookName = bookNameNode.getText().trim();
					String bookUrl = bookUrlNode.getText().trim();
					logger.debug("{} book name: {}, book url: {}", pageNo, bookName, bookUrl);
					if (bookInfo == null) {
						continue;
					}
					BookInfo info = new BookInfo();
					info.setBookName(bookName);
					info.setBookUrl(bookUrl);
					info.setImgUrl(findInfo(tnode, imgUrlPath));
					info.setAuthor(findInfo(tnode, authorPath));
					info.setIntroduction(findInfo(tnode, introductionPath));
					info.setLastUpdateTime(findInfo(tnode, lastUpdateTimePath));
					bookInfo.extract(info);
				}
				findTotalPage(bookListContent);
			} catch (Exception e) {
				logger.error("Load book list error: " + bookListUrl, e);
			}
		} while ((++pageNo) <= totalPage);
		return this;
	}
	
	private void findTotalPage(String bookListContent) {
		if (totalPagePath == null) {
			return;
		}
		List<Node> nodes = HtmlTools.findFromHtml(bookListContent, totalPagePath, showDebug);
		logger.debug("node size: {}", nodes.size());
		Node node = nodes.get(0);
		String text = node.getText().trim();
		if (totalPageExpress == null) {
			totalPage = Integer.parseInt(text);
		} else {
			Matcher matcher = Pattern.compile(totalPageExpress).matcher(text);
			boolean find = matcher.find();
			if (find) {
				totalPage = Integer.parseInt(matcher.group());
			} else {
				logger.debug("Cant find total page by express: {}, text: {}", totalPageExpress, text);
			}
		}
		logger.debug("total page: {}", totalPage);
	}
	
	private String getBookListPageUrl(String prefix, int pageNo, String suffix) {
		return prefix + pageNo + suffix;
	}
	
	private String findInfo(Node node, String nodePath) {
		if (nodePath == null) {
			return null;
		}
		Node tnode = node.selectSingleNode(nodePath);
		if (tnode == null) {
			return null;
		}
		return tnode.getText().trim();
	}
	
	public DownloadBookList setShowDebug(boolean showDebug) {
		this.showDebug = showDebug;
		return this;
	}
	
	public DownloadBookList setBookListCharset(String bookListCharset) {
		this.bookListCharset = bookListCharset;
		return this;
	}
	
	public DownloadBookList setBookNodePath(String bookNodePath) {
		this.bookNodePath = bookNodePath;
		return this;
	}
	
	public DownloadBookList setBookNamePath(String bookNamePath) {
		this.bookNamePath = bookNamePath;
		return this;
	}
	
	public DownloadBookList setBookUrlPath(String bookUrlPath) {
		this.bookUrlPath = bookUrlPath;
		return this;
	}
	
	public DownloadBookList setImgUrlPath(String imgUrlPath) {
		this.imgUrlPath = imgUrlPath;
		return this;
	}
	
	public DownloadBookList setAuthorPath(String authorPath) {
		this.authorPath = authorPath;
		return this;
	}
	
	public DownloadBookList setLastUpdateTimePath(String lastUpdateTimePath) {
		this.lastUpdateTimePath = lastUpdateTimePath;
		return this;
	}
	
	public DownloadBookList setIntroductionPath(String introductionPath) {
		this.introductionPath = introductionPath;
		return this;
	}
	
	public DownloadBookList setTotalPagePath(String totalPagePath) {
		this.totalPagePath = totalPagePath;
		return this;
	}
	
	public DownloadBookList setTotalPageExpress(String totalPageExpress) {
		this.totalPageExpress = totalPageExpress;
		return this;
	}
	
}
