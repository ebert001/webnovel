package com.aswishes.novel.core.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.common.AppConstants;
import com.aswishes.novel.core.common.NovelStatus;
import com.aswishes.novel.core.common.TempFile;
import com.aswishes.novel.core.common.db.PageResult;
import com.aswishes.novel.core.dao.MBookDao;
import com.aswishes.novel.core.dao.MSpiderRuleDao;
import com.aswishes.novel.core.dao.MSpiderWebsiteDao;
import com.aswishes.novel.core.exception.ServiceException;
import com.aswishes.novel.core.model.MBook;
import com.aswishes.novel.core.model.MSpiderRule;
import com.aswishes.novel.core.model.MSpiderWebsite;

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

	public MSpiderWebsite getWebsite(Long id) {
		return spiderWebsiteDao.getById(id);
	}
	
	public MSpiderWebsite getWebsite(String name) {
		return spiderWebsiteDao.getByName(name);
	}
	
	
	public MSpiderRule getRule(Long id) {
		return spiderRuleDao.getById(id);
	}
	
	public PageResult<MSpiderWebsite> getSpiderWebsite(int pageNo, int pageSize) {
		return spiderWebsiteDao.getPage(pageNo, pageSize);
	}
	
	public List<MSpiderWebsite> getOpenedWebsite() {
		return spiderWebsiteDao.getOpenedWebsite();
	}
	
	public List<MBook> getSpiderBook(int pageNo, int pageSize) {
		return bookDao.getList(pageNo, pageSize);
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
		spiderRuleDao.update(rule);
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
		spiderWebsiteDao.deleteById(id);
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
