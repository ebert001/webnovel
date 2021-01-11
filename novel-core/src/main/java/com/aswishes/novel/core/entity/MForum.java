package com.aswishes.novel.core.entity;

import java.util.Date;

import com.aswishes.novel.common.db.Mapper;

/**
 * 论坛跟帖信息
 */
@Mapper(tableName = "m_forum", primaryKey = {"id"})
public class MForum {

	/** 主键 */
	private Long id;

	/** 帖子内容 */
	private String content;

	/** 帖子状态。0、关闭，1、审核，2、打开 */
	private Integer status;

	/** 发布时间 */
	private Date createTime;

	/** 主帖id */
	private Long subjectId;

	/** 坛员对坛员的回复 */
	private String refer;

	/** 用户id */
	private Long userId;

	/** 用户别名 */
	private String userAlias;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
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
}
