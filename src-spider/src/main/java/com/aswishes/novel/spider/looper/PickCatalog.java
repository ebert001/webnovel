package com.aswishes.novel.spider.looper;

import java.net.URI;
import java.util.List;

import org.apache.http.client.fluent.Request;
import org.dom4j.Document;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 爬取网络书籍
 */
public class PickCatalog extends AbstractPicker {
	private static final Logger logger = LoggerFactory.getLogger(PickCatalog.class);
	private String bookName;
	/** 目录页地址 */
	private String catalogUrl;
	private String catalogCharset = "UTF-8";
	/** 目录页标题 */
	private String bookNamePath;
	private String statePath;
	private String authorPath;
	private String lastUpdateTimePath;
	private String lastUpdateChapterPath;
	private String imgPath;
	private String tagPath;
	
	private String catalogChapterNodePath;
	private String catalogChapterUrlPath;
	
	private String chapterCharset;
	private String chapterNodePath;
	
	private WorkState workState = WorkState.RUNNING; 
	
	private IChapterInfo chapterInfo;
	private int lastSerialNo = -1;
	
	public PickCatalog(String bookName, String catalogUrl) {
		this.bookName = bookName;
		this.catalogUrl = catalogUrl;
	}
	
	public void discovery() {
		try {
			logger.debug("Load catalog. book name: {}, url: {}", bookName, catalogUrl);
			URI catalogURI = URI.create(catalogUrl);
			String originCatalog = new String(Request.Get(catalogURI).execute().returnContent().asBytes(), catalogCharset);
			extractBookInfo(originCatalog);
			List<Node> nodes = HtmlTools.findFromHtml(originCatalog, catalogChapterNodePath, showDebug);
			for (int i = 0; i < nodes.size(); i++) {
				if (workState == WorkState.STOP) {
					return;
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
						logger.error("Unkonovel chapter url: {}, catalog url: {}", chapterUrl, catalogUrl);
						return;
					}
				}
				info.setChapterUrl(chapterUrl);
				info.setSerialNo(i + 1);
				if (chapterInfo == null) {
					continue;
				}
				boolean loadChapter = chapterInfo.extract(info);
				if (loadChapter) {
					PickChapter picker = new PickChapter(bookName, title, chapterUrl);
					picker.setShowDebug(showDebug);
					picker.setChapterCharset(chapterCharset);
					picker.setChapterNodePath(chapterNodePath);
					picker.addWeeds(weeds);
					picker.discovery();
					String content = picker.getChapterContent();
					info.setChapterContent(content);
					chapterInfo.extractContent(info, content);
				}
			}
		} catch (Exception e) {
			logger.error("Load book error: " + catalogUrl, e);
		}
		workState = WorkState.STOP;
	}

	private void extractBookInfo(String originCatalog) {
		if (chapterInfo != null) {
			Document doc = HtmlTools.html2Document(originCatalog, showDebug);
			BookInfo bookInfoBean = new BookInfo();
			bookInfoBean.setAuthor(findInfo(doc, authorPath));
			bookInfoBean.setImgUrl(findInfo(doc, imgPath));
			bookInfoBean.setLastUpdateChapter(findInfo(doc, lastUpdateChapterPath));
			bookInfoBean.setLastUpdateTime(findInfo(doc, lastUpdateTimePath));
			bookInfoBean.setState(findInfo(doc, statePath));
			bookInfoBean.setBookName(findInfo(doc, bookNamePath));
			bookInfoBean.setTag(findInfo(doc, tagPath));
			chapterInfo.extractBookInfo(bookInfoBean);
		}
	}
	
	public PickCatalog setCatalogCharset(String catalogCharset) {
		this.catalogCharset = catalogCharset;
		return this;
	}
	
	public PickCatalog setCatalogChapterNodePath(String catalogChapterNodePath) {
		this.catalogChapterNodePath = catalogChapterNodePath;
		return this;
	}
	
	public PickCatalog setCatalogChapterUrlPath(String catalogChapterUrlPath) {
		this.catalogChapterUrlPath = catalogChapterUrlPath;
		return this;
	}
	
	public PickCatalog setWorkState(WorkState workState) {
		this.workState = workState;
		if (this.workState == WorkState.PAUSE) {
			Thread.interrupted();
		}
		return this;
	}
	
	public WorkState getWorkState() {
		return workState;
	}
	
	public PickCatalog setChapterInfo(IChapterInfo chapterInfo) {
		this.chapterInfo = chapterInfo;
		return this;
	}

	public void setCatalogSubjectPath(String catalogSubjectPath) {
		this.bookNamePath = catalogSubjectPath;
	}

	public void setCatalogStatePath(String catalogStatePath) {
		this.statePath = catalogStatePath;
	}

	public void setCatalogAuthorPath(String catalogAuthorPath) {
		this.authorPath = catalogAuthorPath;
	}

	public void setCatalogLastUpdateTimePath(String catalogLastUpdateTimePath) {
		this.lastUpdateTimePath = catalogLastUpdateTimePath;
	}

	public void setCatalogLastUpdateChapterPath(String catalogLastUpdateChapterPath) {
		this.lastUpdateChapterPath = catalogLastUpdateChapterPath;
	}

	public void setCatalogImgPath(String catalogImgPath) {
		this.imgPath = catalogImgPath;
	}
	
	public void setLastSerialNo(int lastSerialNo) {
		this.lastSerialNo = lastSerialNo;
	}

	public void setTagPath(String tagPath) {
		this.tagPath = tagPath;
	}
	
	public void setChapterCharset(String chapterCharset) {
		this.chapterCharset = chapterCharset;
	}
	
	public void setChapterNodePath(String chapterNodePath) {
		this.chapterNodePath = chapterNodePath;
	}
}
