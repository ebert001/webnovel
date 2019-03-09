package com.aswishes.wn.spider.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.PageResultWrapper;
import com.aswishes.spring.Restriction;
import com.aswishes.spring.mapper.MapperHelper;
import com.aswishes.spring.service.AbstractService;
import com.aswishes.wn.common.AppConstants;
import com.aswishes.wn.common.DateUtil;
import com.aswishes.wn.common.TempFile;
import com.aswishes.wn.common.WnStatus;
import com.aswishes.wn.common.file.FileManager;
import com.aswishes.wn.exception.ServiceException;
import com.aswishes.wn.spider.BookInfo;
import com.aswishes.wn.spider.ChapterInfo;
import com.aswishes.wn.spider.DownloadBook;
import com.aswishes.wn.spider.DownloadBookList;
import com.aswishes.wn.spider.IBookInfo;
import com.aswishes.wn.spider.IChapterInfo;
import com.aswishes.wn.spider.WorkState;
import com.aswishes.wn.spider.dao.WnSpiderBookDao;
import com.aswishes.wn.spider.dao.WnSpiderRuleDao;
import com.aswishes.wn.spider.dao.WnSpiderWebsiteDao;
import com.aswishes.wn.spider.entity.WnSpiderBook;
import com.aswishes.wn.spider.entity.WnSpiderRule;
import com.aswishes.wn.spider.entity.WnSpiderWebsite;

@Service
@Transactional
public class SpiderService extends AbstractService {
	private static final Logger logger = LoggerFactory.getLogger(SpiderService.class);
	@Autowired
	private WnSpiderWebsiteDao spiderWebsiteDao;
	@Autowired
	private WnSpiderBookDao spiderBookDao;
	@Autowired
	private WnSpiderRuleDao spiderRuleDao;
	/** key:网站名称 */
	private Map<String, DownloadBookList> bookListCache = new ConcurrentHashMap<String, DownloadBookList>();
	/** key 书记名称 */
	private Map<String, DownloadBook> bookCache = new ConcurrentHashMap<String, DownloadBook>();
	
	private static int spiderThreadCount = 3;
	
	@Override
	public void setDao() {
		this.dao = spiderBookDao;
	}
	
	public WnSpiderWebsite getWebsite(Long id) {
		return spiderWebsiteDao.getObjectBy(MapperHelper.getMapper(WnSpiderWebsite.class), Restriction.eq("id", id));
	}
	
	public WnSpiderWebsite getWebsite(String name) {
		return spiderWebsiteDao.getObjectBy(MapperHelper.getMapper(WnSpiderWebsite.class), Restriction.eq("name", name));
	}
	
	public WnSpiderBook getBook(Long id) {
		return spiderBookDao.getObjectBy(MapperHelper.getMapper(WnSpiderBook.class), 
				Restriction.eq("id", id));
	}
	
	public WnSpiderBook getBook(String name, Long websiteId) {
		return spiderBookDao.getObjectBy(MapperHelper.getMapper(WnSpiderBook.class), 
				Restriction.eq("name", name), Restriction.eq("website_id", websiteId));
	}
	
	public WnSpiderRule getRule(Long id) {
		return spiderRuleDao.getObjectBy(MapperHelper.getMapper(WnSpiderRule.class), Restriction.eq("id", id));
	}
	
	public PageResultWrapper<WnSpiderWebsite> getSpiderWebsite(int pageNo, int pageSize) {
		return spiderWebsiteDao.getPage(MapperHelper.getMapper(WnSpiderWebsite.class), pageNo, pageSize, Restriction.orderByDesc("id"));
	}
	
	public List<WnSpiderBook> getSpiderBook(int pageNo, int pageSize) {
		return spiderBookDao.getList(MapperHelper.getMapper(WnSpiderBook.class), pageNo, pageSize);
	}
	
	@Transactional
	public void addSpiderWebsite(WnSpiderWebsite website) {
		WnSpiderWebsite bean = getWebsite(website.getName());
		if (bean != null) {
			throw new ServiceException(WnStatus.WEBSITE_EXISTS);
		}
		
		Date date = new Date();
		website.setCreateTime(date);
		website.setUpdateTime(date);
		spiderWebsiteDao.save(website);
	}
	
	@Transactional
	public void addSpiderBook(Long websiteId, BookInfo info) {
		WnSpiderBook bean = getBook(info.getBookName(), websiteId);
		if (bean != null) {
			throw new ServiceException(WnStatus.WEBSITE_BOOK_EXISTS);
		}
		bean = new WnSpiderBook();
		bean.setWebsiteId(websiteId);
		bean.setName(info.getBookName());
		bean.setUrl(info.getBookUrl());
		bean.setAuthor(info.getAuthor());
		bean.setImg(FileManager.get().storeBookImg(loadBookImg(info.getImgUrl())));
		bean.setIntroduction(info.getIntroduction());
		bean.setLastUpdateTime(DateUtil.parseDate(info.getLastUpdateTime(), AppConstants.DATE_PATTERNS));
		
		Date date = new Date();
		bean.setUpdateTime(date);
		bean.setCreateTime(date);
		spiderBookDao.save(bean);
	}
	
	@Transactional
	public void saveSpiderRule(Long websiteId, WnSpiderRule rule) {
		WnSpiderWebsite website = getWebsite(websiteId);
		if (website == null) {
			throw new ServiceException(WnStatus.WEBSITE_NOT_EXISTS);
		}
		Date date = new Date();
		if (rule.getId() == null) {
			rule.setUpdateTime(date);
			rule.setCreateTime(date);
			Long ruleId = spiderRuleDao.saveAndGetId(rule);;
			spiderWebsiteDao.updateRule(websiteId, ruleId);
			return;
		}
		rule.setUpdateTime(date);
		spiderRuleDao.updateByPK(rule, true);
	}
	
	/**
	 * 循环所有的网站
	 */
	public synchronized void loopWebsite() {
		
	}
	
	@Transactional
	public synchronized void loopBookList(final Long websiteId, final boolean loopChapters) {
		if (isFullOfBookListCache()) {
			throw new ServiceException(WnStatus.BOOK_LIST_CACHE_FULL);
		}
		WnSpiderWebsite website = getWebsite(websiteId);
		WnSpiderRule rule = getRule(website.getRuleId());
		DownloadBookList downloadBookList = new DownloadBookList(rule.getBookListUrlFormat(), Integer.parseInt(rule.getBookListStartPageNo()));
		downloadBookList.setBookListCharset(rule.getBookListCharset());
		downloadBookList.setTotalPagePath(rule.getBookListTotalPagePath());
		downloadBookList.setTotalPageExpress(rule.getBookListTotalPageRegular());
		downloadBookList.setBookNodePath(rule.getBookNodePath());
		downloadBookList.setBookNodeNamePath(rule.getBookNodeNamePath());
		downloadBookList.setBookNodeUrlPath(rule.getBookNodeUrlPath());
		downloadBookList.setBookNodeAuthorPath(rule.getBookNodeAuthorPath());
		downloadBookList.setBookNodeImgUrlPath(rule.getBookNodeImgPath());
		downloadBookList.setBookNodeIntroductionPath(rule.getBookNodeIntroductionPath());
		downloadBookList.setBookNodeLastUpdateTimePath(rule.getBookNodeLastUpdateTimePath());
		downloadBookList.setBookInfo(new IBookInfo() {
			@Override
			public void extract(BookInfo info) {
				try {
					addSpiderBook(websiteId, info);
				} catch (ServiceException e) {
					if (e.getStatus() != null && e.getStatus() == WnStatus.WEBSITE_BOOK_EXISTS) {
						// do nothing.
					}
				}
				if (!loopChapters) {
					return;
				}
				loopChapters(info, website, rule, true);
			}
		});
		bookListCache.put(website.getName(), downloadBookList);
		downloadBookList.start();
	}
	
	@Transactional
	public synchronized void loopChapters(BookInfo info, WnSpiderWebsite website, WnSpiderRule rule, boolean callFromBook) {
		// 单独抓取一本书籍内容
		if (!callFromBook && isFullOfBookCache()) {
			throw new ServiceException(WnStatus.BOOK_CACHE_FULL);
		}
		WnSpiderBook book = getBook(info.getBookName(), website.getId());
		DownloadBook downloadBook = new DownloadBook(info.getBookUrl());
		downloadBook.setCatalogCharset(rule.getCatalogCharset());
		downloadBook.setCatalogChapterNodePath(rule.getCatalogChapterNodePath());
		downloadBook.setCatalogChapterUrlPath(rule.getCatalogChapterUrlPath());
		
		downloadBook.setChapterCharset(rule.getChapterCharset());
		downloadBook.setChapterNodePath(rule.getChapterNodePath());
		downloadBook.setChapterWeeds(rule.getChapterWeed().split(","));
		downloadBook.setChapterInfo(new IChapterInfo() {
			@Override
			public void extract(ChapterInfo info) {
				
			}
		});
		// 单独抓取一本书籍内容
		if (!callFromBook) {
			bookCache.put(book.getName(), downloadBook);
			downloadBook.start();
		}
	}
	
	private boolean isFullOfBookListCache() {
		if (bookListCache.size() < spiderThreadCount) {
			return false;
		}
		for (Entry<String, DownloadBookList> entry : bookListCache.entrySet()) {
			DownloadBookList bean = entry.getValue();
			if (bean.getWorkState() != WorkState.STOP) {
				continue;
			}
			bookListCache.remove(entry.getKey());
			return false;
		}
		return true;
	}
	
	private boolean isFullOfBookCache() {
		if (bookCache.size() < spiderThreadCount) {
			return false;
		}
		for (Entry<String, DownloadBook> entry : bookCache.entrySet()) {
			DownloadBook bean = entry.getValue();
			if (bean.getWorkState() != WorkState.STOP) {
				continue;
			}
			bookCache.remove(entry.getKey());
			return false;
		}
		return true;
	}
	
	public File loadBookImg(String imgUrl) {
		try {
			byte[] bs = Request.Get(imgUrl)
					.connectTimeout(AppConstants.CONNECT_TIMEOUT)
					.socketTimeout(AppConstants.SO_TIMEOUT)
					.execute().returnContent().asBytes();
			return TempFile.getTempFile(bs);
		} catch (IOException e) {
			logger.error("Load book image error: " + imgUrl, e);
		}
		return null;
	}
	
	@Transactional
	public void deleteWebsite(Long id) {
		spiderWebsiteDao.delete(id);
	}
	
	@Transactional
	public void closeWebsite(Long id) {
		spiderWebsiteDao.updateState(id, WnSpiderWebsite.State.CLOSED.getValue());
	}
	
	@Transactional
	public void openWebsite(Long id) {
		spiderWebsiteDao.updateState(id, WnSpiderWebsite.State.OPENED.getValue());
	}

}
