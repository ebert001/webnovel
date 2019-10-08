package com.aswishes.novel.spider.service;

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

import com.aswishes.novel.core.common.AppConstants;
import com.aswishes.novel.core.common.DateUtil;
import com.aswishes.novel.core.common.NovelStatus;
import com.aswishes.novel.core.common.TempFile;
import com.aswishes.novel.core.common.file.FileManager;
import com.aswishes.novel.core.dao.MBookDao;
import com.aswishes.novel.core.dao.MSpiderRuleDao;
import com.aswishes.novel.core.dao.MSpiderWebsiteDao;
import com.aswishes.novel.core.exception.ServiceException;
import com.aswishes.novel.core.model.MBook;
import com.aswishes.novel.core.model.MBook.RetriveState;
import com.aswishes.novel.core.service.ChapterService;
import com.aswishes.novel.core.service.SimpleService;
import com.aswishes.novel.core.model.MChapter;
import com.aswishes.novel.core.model.MSpiderRule;
import com.aswishes.novel.core.model.MSpiderWebsite;
import com.aswishes.novel.spider.looper.BookInfo;
import com.aswishes.novel.spider.looper.ChapterInfo;
import com.aswishes.novel.spider.looper.IBookInfo;
import com.aswishes.novel.spider.looper.IChapterInfo;
import com.aswishes.novel.spider.looper.PickBooks;
import com.aswishes.novel.spider.looper.PickCatalog;
import com.aswishes.novel.spider.looper.WorkState;
import com.aswishes.spring.PageResult;
import com.aswishes.spring.Restriction;
import com.aswishes.spring.mapper.MapperHelper;

@Service
@Transactional
public class SpiderService extends SimpleService<MSpiderWebsite> {
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
	
	public PageResult<MSpiderWebsite> getSpiderWebsite(int pageNo, int pageSize) {
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
			throw new ServiceException(NovelStatus.WEBSITE_EXISTS);
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
		throw new ServiceException(NovelStatus.WEBSITE_BOOK_EXISTS);
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
		bean.setState(MBook.State.UNAUDITED.getValue());
		
		bean.setCreateTime(date);
		bookDao.save(bean);
	}
	
	@Transactional
	public void pickBookStart(Long websiteId, BookInfo info) {
		bookDao.startPick(info.getBookName(), websiteId);
	}
	
	@Transactional
	public void pickBookStop(Long websiteId, BookInfo info) {
		bookDao.stopPick(info.getBookName(), websiteId);
	}
	
	@Transactional
	public void saveSpiderChapter(MBook book, ChapterInfo info) {
		MChapter chapter = chapterService.getChapter(book.getId(), info.getChapterTitle());
		if (chapter != null) {
			throw new ServiceException(NovelStatus.BOOK_CHAPTER_EXISTS);
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
			throw new ServiceException(NovelStatus.WEBSITE_NOT_EXISTS);
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
			throw new ServiceException(NovelStatus.BOOK_LIST_CACHE_FULL);
		}
		MSpiderWebsite website = getWebsite(websiteId);
		if (bookListCache.get(website.getName()) != null) {
			throw new ServiceException(NovelStatus.WEBSITE_EXISTS_IN_CACHE);
		}
		MSpiderRule rule = getRule(website.getRuleId());
		if (website.getRuleId() == null || rule == null) {
			logger.warn("Website has no rule. {}", website.getName());
			return;
		}
		PickBooks donovelloadBookList = new PickBooks(rule.getBookListUrlFormat(), Integer.parseInt(rule.getBookListStartPageNo()));
		donovelloadBookList.setBookListCharset(rule.getBookListCharset());
		donovelloadBookList.setTotalPagePath(rule.getBookListTotalPagePath());
		donovelloadBookList.setTotalPageExpress(rule.getBookListTotalPageRegular());
		donovelloadBookList.setBookNodePath(rule.getBookNodePath());
		donovelloadBookList.setBookNodeNamePath(rule.getBookNodeNamePath());
		donovelloadBookList.setBookNodeUrlPath(rule.getBookNodeUrlPath());
		donovelloadBookList.setBookNodeAuthorPath(rule.getBookNodeAuthorPath());
		donovelloadBookList.setBookNodeImgUrlPath(rule.getBookNodeImgPath());
		donovelloadBookList.setBookNodeIntroductionPath(rule.getBookNodeIntroductionPath());
		donovelloadBookList.setBookNodeLastUpdateTimePath(rule.getBookNodeLastUpdateTimePath());
		donovelloadBookList.setBookInfo(new IBookInfo() {
			@Override
			public void extract(BookInfo info) {
				try {
					saveSpiderBook(websiteId, info);
				} catch (ServiceException e) {
					if (e.getStatus() != null && e.getStatus() == NovelStatus.WEBSITE_BOOK_EXISTS) {
						// do nothing.
					} else {
						logger.error("Pick book error", e);
						return;
					}
				}
				pickBookStart(websiteId, info);
				if (!loopChapters) {
					pickBookStop(websiteId, info);
					return;
				}
				loopChapters(info, website, rule, true);
				pickBookStop(websiteId, info);
			}
		});
		bookListCache.put(website.getName(), donovelloadBookList);
		donovelloadBookList.start();
	}
	
	@Transactional
	public synchronized void loopChapters(BookInfo info, MSpiderWebsite website, MSpiderRule rule, 
			boolean callFromWebsite) {
		// 单独抓取一本书籍内容
		if (!callFromWebsite && isFullOfBookCache()) {
			throw new ServiceException(NovelStatus.BOOK_CACHE_FULL);
		}
		if (bookCache.get(info.getBookName()) != null) {
			throw new ServiceException(NovelStatus.BOOK_EXISTS_IN_CACHE);
		}
		MBook book = bookDao.getBook(info.getBookName(), website.getId());
		PickCatalog donovelloadBook = new PickCatalog(info.getBookName(), info.getBookUrl());
		donovelloadBook.setCatalogCharset(rule.getCatalogCharset());
		donovelloadBook.setCatalogChapterNodePath(rule.getCatalogChapterNodePath());
		donovelloadBook.setCatalogChapterUrlPath(rule.getCatalogChapterUrlPath());
		donovelloadBook.setLastSerialNo(chapterService.getMaxSerialNo(book.getId()));
		
		donovelloadBook.setChapterCharset(rule.getChapterCharset());
		donovelloadBook.setChapterNodePath(rule.getChapterNodePath());
		if (StringUtils.isNotBlank(rule.getChapterWeed())) {
			donovelloadBook.setWeeds(rule.getChapterWeed().split(","));
		}
		donovelloadBook.setChapterInfo(new IChapterInfo() {
			@Override
			public void extractBookInfo(BookInfo bookInfo) {
				
			}
			
			@Override
			public boolean extract(ChapterInfo info) {
				try {
					saveSpiderChapter(book, info);
				} catch (ServiceException e) {
					if (e.getStatus() == NovelStatus.BOOK_CHAPTER_EXISTS) {
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
		if (!callFromWebsite) {
			bookCache.put(book.getName(), donovelloadBook);
			donovelloadBook.start();
		} else {
			donovelloadBook.discovery();
		}
	}
	
	@Transactional
	public synchronized void loopChapters(Long bookId, boolean callFromWebsite) {
		MBook book = bookDao.getBook(bookId);
		MSpiderWebsite website = getWebsite(book.getWebsiteId());
		MSpiderRule rule = getRule(website.getRuleId());
		PickCatalog donovelloadBook = new PickCatalog(book.getName(), book.getUrl());
		donovelloadBook.setCatalogCharset(rule.getCatalogCharset());
		donovelloadBook.setCatalogChapterNodePath(rule.getCatalogChapterNodePath());
		donovelloadBook.setCatalogChapterUrlPath(rule.getCatalogChapterUrlPath());
		donovelloadBook.setLastSerialNo(chapterService.getMaxSerialNo(book.getId()));
		
		donovelloadBook.setChapterCharset(rule.getChapterCharset());
		donovelloadBook.setChapterNodePath(rule.getChapterNodePath());
		if (StringUtils.isNotBlank(rule.getChapterWeed())) {
			donovelloadBook.setWeeds(rule.getChapterWeed().split(","));
		}
		donovelloadBook.setChapterInfo(new IChapterInfo() {
			@Override
			public void extractBookInfo(BookInfo bookInfo) {
				
			}
			
			@Override
			public boolean extract(ChapterInfo info) {
				try {
					saveSpiderChapter(book, info);
				} catch (ServiceException e) {
					if (e.getStatus() == NovelStatus.BOOK_CHAPTER_EXISTS) {
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
		if (!callFromWebsite) {
			bookCache.put(book.getName(), donovelloadBook);
			donovelloadBook.start();
		} else {
			donovelloadBook.discovery();
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
