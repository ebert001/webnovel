package com.aswishes.wn.mvc.model;

import java.util.Date;

/**
 * 书籍评论
 */
public class WnComment {

	/** 评论id */
	private String id;
	
	/** 评论内容 */
	private String content;
	
	/** 状态 */
	private int status;
	
	/** 评论时间 */
	private Date createTime;
	
	/** 引用评论id */
	private String referId;
	
	/** 书籍id */
	private String bookId;
	
	/** 用户id */
	private String userId;
	
	/** 用户别名 */
	private String userAlias;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getReferId() {
		return referId;
	}

	public void setReferId(String referId) {
		this.referId = referId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserAlias() {
		return userAlias;
	}

	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}
	
	
}
