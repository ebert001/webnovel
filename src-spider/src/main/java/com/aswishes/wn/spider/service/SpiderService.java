package com.aswishes.wn.spider.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.Restriction;
import com.aswishes.spring.mapper.MapperHelper;
import com.aswishes.spring.service.AbstractService;
import com.aswishes.wn.common.WnStatus;
import com.aswishes.wn.exception.ServiceException;
import com.aswishes.wn.spider.dao.WnSpiderBookDao;
import com.aswishes.wn.spider.dao.WnSpiderWebsiteDao;
import com.aswishes.wn.spider.entity.WnSpiderBook;
import com.aswishes.wn.spider.entity.WnSpiderWebsite;

@Service
@Transactional
public class SpiderService extends AbstractService {
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
	
	public List<WnSpiderWebsite> getSpiderWebsite(int pageNo, int pageSize) {
		return spiderWebsiteDao.getList(MapperHelper.getMapper(WnSpiderWebsite.class), pageNo, pageSize);
	}
	
	public List<WnSpiderBook> getSpiderBook(int pageNo, int pageSize) {
		return spiderBookDao.getList(MapperHelper.getMapper(WnSpiderBook.class), pageNo, pageSize);
	}
	
	@Transactional
	public void addSpiderWebsite(String websiteName, String websiteUrl) {
		WnSpiderWebsite bean = getWebsite(websiteName);
		if (bean != null) {
			throw new ServiceException(WnStatus.WEBSITE_EXISTS);
		}
		bean = new WnSpiderWebsite();
		bean.setName(websiteName);
		bean.setUrl(websiteUrl);
		
		Date date = new Date();
		bean.setCreateTime(date);
		bean.setUpdateTime(date);
		spiderWebsiteDao.save(bean);
	}
	
	@Transactional
	public void addSpiderBook(Long websiteId, String name, String url) {
		WnSpiderBook bean = getBook(name, websiteId);
		if (bean != null) {
			throw new ServiceException(WnStatus.WEBSITE_BOOK_EXISTS);
		}
		bean = new WnSpiderBook();
		bean.setWebsiteId(websiteId);
		bean.setName(name);
		bean.setUrl(url);
		
		Date date = new Date();
		bean.setUpdateTime(date);
		bean.setCreateTime(date);
		spiderBookDao.save(bean);
	}
	
	

}
