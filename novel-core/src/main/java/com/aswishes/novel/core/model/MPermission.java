package com.aswishes.novel.core.model;

import java.util.Date;

import com.aswishes.spring.mapper.Mapper;

@Mapper(tableName = "m_permission", primaryKey = {"id"})
public class MPermission extends BaseIdAuto {

	private String name;
	private String menu;
	private String url;
	private Integer sequence;
	private String description;
	
	@Mapper(name = "update_time")
	private Date updateTime;
	
	@Mapper(name = "create_time")
	private Date createTime;
	
	public String getName() {
		return name;
	}
	public String getMenu() {
		return menu;
	}
	public String getUrl() {
		return url;
	}
	public Integer getSequence() {
		return sequence;
	}
	public String getDescription() {
		return description;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
