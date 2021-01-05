package com.aswishes.novel.core.model;

import java.util.Date;

import com.aswishes.novel.common.db.Mapper;

/**
 * 论坛主帖信息
 */
@Mapper(tableName = "m_forum_object", primaryKey = {"id"})
public class MForumSubject {

	/** 主键 */
	private String id;
	
	/** 帖子标题 */
	private String subject;
	
	/** 帖子类型 */
	private Integer type;
	
	/** 帖子内容 */
	private String content;
	
	/** 帖子状态。0、关闭，1、审核，2、打开 */
	private Integer status;
	
	/** 发布时间 */
	private Date createTime;
	
	/** 最近更新时间 */
	private Date updateTime;
	
	/** 阅读次数 */
	private Integer readTimes;
	
	/** 回复次数 */
	private Integer replyTimes;
	
	/** 用户id */
	private Long userId;
	
	/** 用户别名 */
	private String userAlias;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Integer getReadTimes() {
		return readTimes;
	}

	public void setReadTimes(Integer readTimes) {
		this.readTimes = readTimes;
	}

	public Integer getReplyTimes() {
		return replyTimes;
	}

	public void setReplyTimes(Integer replyTimes) {
		this.replyTimes = replyTimes;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserAlias() {
		return userAlias;
	}

	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public static class Status {
		/** 关闭 */
		public static final Integer CLOSE = 0;
		
		/** 审查 查看 */
		public static final Integer VIEW = 1;
		
		/** 打开 */
		public static final Integer OPEN = 2;
	}
}
