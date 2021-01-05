package com.aswishes.novel.core.model;

import java.util.Date;

import com.aswishes.novel.common.db.Mapper;

/**
 * 角色表
 * @author lizhou
 */
@Mapper(tableName = "m_role", primaryKey = {"id"})
public class MRole extends BaseIdAuto {

	private String name;
	private String description;
	
	@Mapper(name = "update_time")
	private Date updateTime;
	
	@Mapper(name = "create_time")
	private Date createTime;

	public String getName() {
		return name;
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
