package com.aswishes.novel.core.model;

import java.util.Date;

import com.aswishes.novel.core.common.db.Mapper;

/**
 * 备忘录
 */
@Mapper(tableName = "m_memo", primaryKey = {"id"})
public class MMemo {

	/** 主键 */ 
	private Long id;
	
	/** 标题 */
	private String title;
	
	/** 内容 */
	private String content;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 更新时间 */
	private Date updateTime;
	
	/** 用户id */
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
