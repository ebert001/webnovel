package com.aswishes.wn.spider.entity;

import java.util.Date;

import com.aswishes.spring.mapper.Mapper;
import com.aswishes.wn.mvc.model.BaseIdAuto;

@Mapper(tableName = "wn_spider_book", primaryKey = {"id"})
public class WnSpiderBook extends BaseIdAuto {
	
	@Mapper(name = "website_id")
	private Long websiteId;
	private String name;
	private String url;
	
	private String author;
	private String img;
	private String introduction;
	
	// 从网站抓取的最近更新时间
	@Mapper(name = "last_update_time")
	private Date lastUpdateTime;
	
	
	@Mapper(name = "last_chapter_url")
	private String lastChapterUrl;
	@Mapper(name = "last_retrive_time")
	private Date lastRetriveTime;
	@Mapper(name = "update_time")
	private Date updateTime;
	@Mapper(name = "create_time")
	private Date createTime;
	
	public Long getWebsiteId() {
		return websiteId;
	}
	public void setWebsiteId(Long websiteId) {
		this.websiteId = websiteId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLastChapterUrl() {
		return lastChapterUrl;
	}
	public void setLastChapterUrl(String lastChapterUrl) {
		this.lastChapterUrl = lastChapterUrl;
	}
	public Date getLastRetriveTime() {
		return lastRetriveTime;
	}
	public void setLastRetriveTime(Date lastRetriveTime) {
		this.lastRetriveTime = lastRetriveTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
