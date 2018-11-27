package com.aswishes.wn.mvc.model;

import java.util.Date;

/**
 * 论坛跟帖信息
 */
public class WnForum {

	/** 主键 */
	private String id;

	/** 帖子内容 */
	private String content;

	/** 帖子状态。0、关闭，1、审核，2、打开 */
	private Integer status;

	/** 发布时间 */
	private Date createTime;

	/** 主帖id */
	private String subjectId;

	/** 坛员对坛员的回复 */
	private String refer;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
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
