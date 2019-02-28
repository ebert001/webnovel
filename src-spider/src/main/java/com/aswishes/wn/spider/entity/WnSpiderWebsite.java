package com.aswishes.wn.spider.entity;

import java.util.Date;

import com.aswishes.spring.mapper.Mapper;
import com.aswishes.wn.mvc.model.BaseIdAuto;

@Mapper(tableName = "wn_spider_website", primaryKey = {"id"})
public class WnSpiderWebsite extends BaseIdAuto {
	
	private String name;
	private String url;

	@Mapper(name = "rule_id")
	private Long ruleId;
	
	@Mapper(name = "last_retrive_name")
	private Date lastRetriveTime;
	
	@Mapper(name = "update_time")
	private Date updateTime;
	
	@Mapper(name = "create_time")
	private Date createTime;

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

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
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
