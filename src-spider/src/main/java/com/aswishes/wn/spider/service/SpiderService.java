package com.aswishes.wn.spider.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
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
import com.aswishes.wn.mvc.dao.MBookDao;
import com.aswishes.wn.mvc.model.MBook;
import com.aswishes.wn.mvc.model.MBook.RetriveState;
import com.aswishes.wn.mvc.model.MChapter;
import com.aswishes.wn.mvc.service.ChapterService;
import com.aswishes.wn.spider.dao.MSpiderRuleDao;
import com.aswishes.wn.spider.dao.MSpiderWebsiteDao;
import com.aswishes.wn.spider.entity.MSpiderRule;
import com.aswishes.wn.spider.entity.MSpiderWebsite;
import com.aswishes.wn.spider.looper.BookInfo;
import com.aswishes.wn.spider.looper.ChapterInfo;
import com.aswishes.wn.spider.looper.PickCatalog;
import com.aswishes.wn.spider.looper.PickBooks;
import com.aswishes.wn.spider.looper.IBookInfo;
import com.aswishes.wn.spider.looper.IChapterInfo;
import com.aswishes.wn.spider.looper.WorkState;

@Service
@Transactional
public class SpiderService extends AbstractService {
	private static final Logger logger = LoggerFactory.getLogger(SpiderService.class);
	@Autowired
	private MSpiderWebsiteDao spiderWebsiteDao;
	@Autowired
	private MSpiderRuleDao spiderRuleDao;
	@Autowired
	private MBookDao bookDao;
	@Autowired
	private ChapterService chapterService;
	/** key:网站名称 */
	private Map<String, PickBooks> bookListCache = new ConcurrentHashMap<String, PickBooks>();
	/** key 书记名称 */
	private Map<String, PickCatalog> bookCache = new ConcurrentHashMap<String, PickCatalog>();
	
	private static int spiderThreadCount = 3;
	
	@Override
	public void setDao() {
		this.dao = spiderWebsiteDao;
	}
	
	public MSpiderWebsite getWebsite(Long id) {
		return spiderWebsiteDao.getObjectBy(MapperHelper.getMapper(MSpiderWebsite.class), Restriction.eq("id", id));
	}
	
	public MSpiderWebsite getWebsite(String name) {
		return spiderWebsiteDao.getObjectBy(MapperHelper.getMapper(MSpiderWebsite.class), Restriction.eq("name", name));
	}
	
	
	public MSpiderRule getRule(Long id) {
		return spiderRuleDao.getObjectBy(MapperHelper.getMapper(MSpiderRule.class), Restriction.eq("id", id));
	}
	
	public PageResultWrapper<MSpiderWebsite> getSpiderWebsite(int pageNo, int pageSize) {
		return spiderWebsiteDao.getPage(MapperHelper.getMapper(MSpiderWebsite.class), pageNo, pageSize, Restriction.orderByDesc("id"));
	}
	
	public List<MSpiderWebsite> getOpenedWebsite() {
		return spiderWebsiteDao.getList(MapperHelper.getMapper(MSpiderWebsite.class), 
				Restriction.eq("state", MSpiderWebsite.State.OPENED.getValue()));
	}
	
	public List<MBook> getSpiderBook(int pageNo, int pageSize) {
		return bookDao.getList(MapperHelper.getMapper(MBook.class), pageNo, pageSize);
	}
	
	@Transactional
	public void addSpiderWebsite(MSpiderWebsite website) {
		MSpiderWebsite bean = getWebsite(website.getName());
		if (bean != null) {
			throw new ServiceException(WnStatus.WEBSITE_EXISTS);
		}
		
		Date date = new Date();
		website.setCreateTime(date);
		website.setUpdateTime(date);
		spiderWebsiteDao.save(website);
	}
	
	@Transactional
	public void saveSpiderBook(Long websiteId, BookInfo info) {
		MBook bean = bookDao.getBook(info.getBookName(), websiteId);
		if (bean == null) {
			addSpiderBook(websiteId, info);
			return;
		}
		throw new ServiceException(WnStatus.WEBSITE_BOOK_EXISTS);
	}

	private void addSpiderBook(Long websiteId, BookInfo info) {
		MBook bean = new MBook();
		Date date = new Date();
		
		bean.setWebsiteId(websiteId);
		bean.setName(info.getBookName());
		bean.setUrl(info.getBookUrl());
		bean.setAuthor(info.getAuthor());
		bean.setImg(FileManager.get().storeBookImg(loadBookImg(info.getImgUrl())));
		bean.setIntroduction(info.getIntroduction());
		bean.setUpdateTime(DateUtil.parseDate(info.getLastUpdateTime(), AppConstants.DATE_PATTERNS));
		
		bean.setRetriveCount(1);
		bean.setRetriveStartTime(date);
		bean.setRetriveState(RetriveState.RETRIVING.getValue());
		
		bean.setCreateTime(date);
		bookDao.save(bean);
	}
	
	@Transactional
	public void saveSpiderChapter(MBook book, ChapterInfo info) {
		MChapter chapter = chapterService.getChapter(book.getId(), info.getChapterTitle());
		if (chapter != null) {
			throw new ServiceException(WnStatus.BOOK_CHAPTER_EXISTS);
		}
		chapter = new MChapter();
		chapter.setBookId(book.getId());
		chapter.setSubject(info.getChapterTitle());
		chapter.setContent(info.getChapterContent());
		chapter.setWriteTime(DateUtil.parseDate(info.getDeployTime(), AppConstants.DATE_PATTERNS));
		chapter.setState(MChapter.State.UNAUDITED.getValue());
		chapter.setSerialNo(info.getSerialNo());
		chapter.setInputTime(new Date());
		chapterService.addChapter(chapter);
	}
	
	@Transactional
	public void updateChapterContent(MBook book, ChapterInfo info) {
		MChapter chapter = chapterService.getChapter(book.getId(), info.getChapterTitle());
		chapterService.updateContent(chapter.getId(), info.getChapterContent());
	}
	
	
	@Transactional
	public void saveSpiderRule(Long websiteId, MSpiderRule rule) {
		MSpiderWebsite website = getWebsite(websiteId);
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
		 List<MSpiderWebsite> list = getOpenedWebsite();
		 for (MSpiderWebsite website : list) {
			 try {
				 loopBookList(website.getId(), true);
			 } catch (Exception e) {
				 logger.error("Loop website error.", e);
			 }
		 }
	}
	
	public synchronized void loopWebsite(Long websiteId) {
		MSpiderWebsite website = getWebsite(websiteId);
		loopBookList(website.getId(), true);
	}
	
	@Transactional
	public synchronized void loopBookList(final Long websiteId, final boolean loopChapters) {
		if (isFullOfBookListCache()) {
			throw new ServiceException(WnStatus.BOOK_LIST_CACHE_FULL);
		}
		MSpiderWebsite website = getWebsite(websiteId);
		if (bookListCache.get(website.getName()) != null) {
			throw new ServiceException(WnStatus.WEBSITE_EXISTS_IN_CACHE);
		}
		MSpiderRule rule = getRule(website.getRuleId());
		PickBooks downloadBookList = new PickBooks(rule.getBookListUrlFormat(), Integer.parseInt(rule.getBookListStartPageNo()));
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
					saveSpiderBook(websiteId, info);
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
	public synchronized void loopChapters(BookInfo info, MSpiderWebsite website, MSpiderRule rule, boolean callFromBook) {
		// 单独抓取一本书籍内容
		if (!callFromBook && isFullOfBookCache()) {
			throw new ServiceException(WnStatus.BOOK_CACHE_FULL);
		}
		if (bookCache.get(info.getBookName()) != null) {
			throw new ServiceException(WnStatus.BOOK_EXISTS_IN_CACHE);
		}
		MBook book = bookDao.getBook(info.getBookName(), website.getId());
		PickCatalog downloadBook = new PickCatalog(info.getBookUrl());
		downloadBook.setCatalogCharset(rule.getCatalogCharset());
		downloadBook.setCatalogChapterNodePath(rule.getCatalogChapterNodePath());
		downloadBook.setCatalogChapterUrlPath(rule.getCatalogChapterUrlPath());
		downloadBook.setLastSerialNo(bookDao.getMaxSerialNo(book.getId()));
		
		downloadBook.setChapterCharset(rule.getChapterCharset());
		downloadBook.setChapterNodePath(rule.getChapterNodePath());
		if (StringUtils.isNotBlank(rule.getChapterWeed())) {
			downloadBook.setWeeds(rule.getChapterWeed().split(","));
		}
		downloadBook.setChapterInfo(new IChapterInfo() {
			@Override
			public void extractBookInfo(BookInfo bookInfo) {
				
			}
			
			@Override
			public boolean extract(ChapterInfo info) {
				try {
					saveSpiderChapter(book, info);
				} catch (ServiceException e) {
					if (e.getStatus() == WnStatus.BOOK_CHAPTER_EXISTS) {
						logger.error("Book chapter exists. {}", info.getChapterTitle());
					}
					return false;
				}
				return true;
			}
			@Override
			public void extractContent(ChapterInfo info, String content) {
				updateChapterContent(book, info);
			}
		});
		// 单独抓取一本书籍内容
		if (!callFromBook) {
			bookCache.put(book.getName(), downloadBook);
			downloadBook.start();
		} else {
			downloadBook.discovery();
		}
	}
	
	private boolean isFullOfBookListCache() {
		if (bookListCache.size() < spiderThreadCount) {
			return false;
		}
		for (Entry<String, PickBooks> entry : bookListCache.entrySet()) {
			PickBooks bean = entry.getValue();
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
		for (Entry<String, PickCatalog> entry : bookCache.entrySet()) {
			PickCatalog bean = entry.getValue();
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
		} catch (Exception e) {
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
		spiderWebsiteDao.updateState(id, MSpiderWebsite.State.CLOSED.getValue());
	}
	
	@Transactional
	public void openWebsite(Long id) {
		spiderWebsiteDao.updateState(id, MSpiderWebsite.State.OPENED.getValue());
	}

}
