package com.aswishes.wn.mvc.model;

import java.util.Date;

/**
 * 备忘录
 */
public class WnMemo {

	/** 主键 */ 
	private String id;
	
	/** 标题 */
	private String title;
	
	/** 内容 */
	private String content;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 更新时间 */
	private Date updateTime;
	
	/** 用户id */
	private String userId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
