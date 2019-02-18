package com.aswishes.wn.spider.entity;

import java.util.Date;

import com.aswishes.spring.mapper.Mapper;

@Mapper(tableName = "wn_spider_website", primaryKey = {"id"})
public class WnSpiderWebsite {

	private Long id;
	private String name;
	private String url;
	@Mapper(name = "last_retrive_name")
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
