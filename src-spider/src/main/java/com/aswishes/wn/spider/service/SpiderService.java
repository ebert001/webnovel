package com.aswishes.wn.spider.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

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
import com.aswishes.wn.spider.DownloadBook;
import com.aswishes.wn.spider.DownloadBookList;
import com.aswishes.wn.spider.IBookInfo;
import com.aswishes.wn.spider.dao.WnSpiderBookDao;
import com.aswishes.wn.spider.dao.WnSpiderWebsiteDao;
import com.aswishes.wn.spider.entity.WnSpiderBook;
import com.aswishes.wn.spider.entity.WnSpiderWebsite;

@Service
@Transactional
public class SpiderService extends AbstractService {
	private static final Logger logger = LoggerFactory.getLogger(SpiderService.class);
	@Autowired
	private WnSpiderWebsiteDao spiderWebsiteDao;
	@Autowired
	private WnSpiderBookDao spiderBookDao;
	
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
	
	public PageResultWrapper<WnSpiderWebsite> getSpiderWebsite(int pageNo, int pageSize) {
		return spiderWebsiteDao.getPage(MapperHelper.getMapper(WnSpiderWebsite.class), pageNo, pageSize);
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
	public void loopBookList(final Long websiteId, final boolean loopChapters) {
		WnSpiderWebsite website = getWebsite(websiteId);
		String bookListUrlPrefix = website.getBookListUrlPrefix();
		int pageNo = website.getPageNo();
		String bookListUrlSuffix = website.getBookListUrlSuffix();
		DownloadBookList downloadBookList = new DownloadBookList(bookListUrlPrefix, pageNo, bookListUrlSuffix);
		downloadBookList.setBookListCharset(website.getBookListCharset());
		downloadBookList.setBookNodePath(website.getBookNodePath());
		downloadBookList.setBookNamePath(website.getBookNamePath());
		downloadBookList.setBookUrlPath(website.getBookUrlPath());
		downloadBookList.setTotalPagePath(website.getTotalPagePath());
		downloadBookList.setTotalPageExpress(website.getTotalPageExpress());
		downloadBookList.setAuthorPath(website.getAuthorPath());
		downloadBookList.setImgUrlPath(website.getImgPath());
		downloadBookList.setIntroductionPath(website.getIntroductionPath());
		downloadBookList.setLastUpdateTimePath(website.getLastUpdateTimePath());
		
		downloadBookList.discovery(new IBookInfo() {
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
				loopChapters(info);
			}
		});
	}
	
	@Transactional
	public void loopChapters(BookInfo info) {
		DownloadBook downloadBook = new DownloadBook(info.getBookUrl());
//		downloadBook.setCatalogCharset(catalogCharset);
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

}
