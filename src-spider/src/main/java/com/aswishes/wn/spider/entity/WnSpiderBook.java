package com.aswishes.wn.spider.entity;

import java.util.Date;

import com.aswishes.spring.mapper.Mapper;

@Mapper(tableName = "wn_spider_book", primaryKey = {"id"})
public class WnSpiderBook {
	private Long id;
	@Mapper(name = "website_id")
	private Long websiteId;
	private String name;
	private String url;
	@Mapper(name = "last_chapter_url")
	private String lastChapterUrl;
	@Mapper(name = "last_retrive_time")
	private Date lastRetriveTime;
	@Mapper(name = "update_time")
	private Date updateTime;
	@Mapper(name = "create_time")
	private Date createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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

}
