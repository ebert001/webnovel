package com.aswishes.novel.core.entity;

import java.util.Date;

import com.aswishes.novel.common.db.Mapper;

/**
 * 用户反馈表
 */
@Mapper(tableName = "m_feedback", primaryKey = {"id"})
public class MFeedback {
	
	/** id */
	private String id;
	
	/** 标题 */
	private String title;
	
	/** 建议 */
	private String advice;
	
	/** 状态 */
	private Integer status;
	
	/** 是否匿名 */
	private Integer anonymity;
	
	/** 建议时间 */
	private Date createTime;
	
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

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAnonymity() {
		return anonymity;
	}

	public void setAnonymity(Integer anonymity) {
		this.anonymity = anonymity;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
