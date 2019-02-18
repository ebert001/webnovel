package com.aswishes.wn.spider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.service.AbstractService;
import com.aswishes.wn.spider.dao.WnSpiderBookDao;
import com.aswishes.wn.spider.dao.WnSpiderWebsiteDao;

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
	
	

}
