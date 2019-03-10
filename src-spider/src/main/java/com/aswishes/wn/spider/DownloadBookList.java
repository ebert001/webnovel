package com.aswishes.wn.spider;

import java.text.MessageFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Request;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadBookList extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(DownloadBook.class);
	private String bookListCharset = "UTF-8";
	private String bookNodePath;
	private String bookNodeNamePath;
	private String bookNodeUrlPath;
	private String totalPagePath;
	private String totalPageExpress;
	private String bookNodeImgUrlPath;
	private String authorPath;
	private String bookNodeIntroductionPath;
	private String bookNodeLastUpdateTimePath;
	private boolean showDebug = false;
	private WorkState workState = WorkState.RUNNING; 
	
	private String bookListUrlFormat;
	private int pageNo = 1;
	private int totalPage = 1;
	
	private IBookInfo bookInfo;
	
	public DownloadBookList(String bookListUrlFormat, int pageNo) {
		this.pageNo = pageNo;
		this.bookListUrlFormat = bookListUrlFormat;
	}
	
	@Override
	public void run() {
		discovery();
	}
	
	public DownloadBookList discovery() {
		do {
			String bookListUrl = getBookListPageUrl(bookListUrlFormat, pageNo);
			try {
				String bookListContent = new String(Request.Get(bookListUrl).execute().returnContent().asBytes(), bookListCharset);
				List<Node> nodes = HtmlTools.findFromHtml(bookListContent, bookNodePath, showDebug);
				logger.debug("node size: {}", nodes.size());
				for (int i = 0; i < nodes.size(); i++) {
					if (workState == WorkState.STOP) {
						return this;
					} else if (workState == WorkState.PAUSE) {
						Thread.sleep(10 * 1000);
					}
					Node tnode = nodes.get(i);
					Node bookNameNode = tnode.selectSingleNode(bookNodeNamePath);
					if (bookNameNode == null) {
						throw new IllegalAccessException("Cant find book name by path: " + bookNodeNamePath);
					}
					Node bookUrlNode = tnode.selectSingleNode(bookNodeUrlPath);
					if (bookUrlNode == null) {
						throw new IllegalAccessException("Cant find book url by path: " + bookNodeUrlPath);
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
					info.setImgUrl(findInfo(tnode, bookNodeImgUrlPath));
					info.setAuthor(findInfo(tnode, authorPath));
					info.setIntroduction(findInfo(tnode, bookNodeIntroductionPath));
					info.setLastUpdateTime(findInfo(tnode, bookNodeLastUpdateTimePath));
					bookInfo.extract(info);
				}
				findTotalPage(bookListContent);
			} catch (Exception e) {
				logger.error("Load book list error: " + bookListUrl, e);
			}
		} while ((++pageNo) <= totalPage);
		workState = WorkState.STOP;
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
		if (StringUtils.isBlank(totalPageExpress)) {
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
	
	private String getBookListPageUrl(String urlFormat, int pageNo) {
		return MessageFormat.format(urlFormat, pageNo);
	}
	
	private String findInfo(Node node, String nodePath) {
		if (StringUtils.isBlank(nodePath)) {
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
	
	public DownloadBookList setBookNodeNamePath(String bookNamePath) {
		this.bookNodeNamePath = bookNamePath;
		return this;
	}
	
	public DownloadBookList setBookNodeUrlPath(String bookUrlPath) {
		this.bookNodeUrlPath = bookUrlPath;
		return this;
	}
	
	public DownloadBookList setBookNodeImgUrlPath(String imgUrlPath) {
		this.bookNodeImgUrlPath = imgUrlPath;
		return this;
	}
	
	public DownloadBookList setBookNodeAuthorPath(String authorPath) {
		this.authorPath = authorPath;
		return this;
	}
	
	public DownloadBookList setBookNodeLastUpdateTimePath(String lastUpdateTimePath) {
		this.bookNodeLastUpdateTimePath = lastUpdateTimePath;
		return this;
	}
	
	public DownloadBookList setBookNodeIntroductionPath(String introductionPath) {
		this.bookNodeIntroductionPath = introductionPath;
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
	
	public DownloadBookList setWorkState(WorkState workState) {
		this.workState = workState;
		if (this.workState == WorkState.PAUSE) {
			Thread.interrupted();
		}
		return this;
	}
	
	public WorkState getWorkState() {
		return workState;
	}
	
	public DownloadBookList setBookInfo(IBookInfo bookInfo) {
		this.bookInfo = bookInfo;
		return this;
	}
}
