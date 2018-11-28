package com.aswishes.wn.mvc.model;

import java.util.Date;

import com.aswishes.spring.mapper.Mapper;

/**
 * 书籍表。存储所有的书籍信息，并对书籍进行分类
 */
@Mapper(tableName = "wn_book", primaryKey = {"id"})
public class WnBook {

	/** 书籍id */
	private String id;
	
	/** 书籍名称 */
	private String bookName;
	
	/** 书籍描述 */
	private String desc;
	
	/** 创作时间 */
	private Date createTime;
	
	/** 更新时间 */
	private Date updateTime;
	
	/** 状态：1、写作中，2、已完结 */
	private Integer status;
	
	/** 类型 */
	private Integer typeByTime;
	
	/** 类型细分，按地域 */
	private Integer typeByArea;
	
	/** 类型细分，按领域 */
	private Integer typeByField;
	
	/** 字数 */
	private Integer words;
	
	/** 点击次数 */
	private Integer clickTimes;
	
	/** 评论次数 */
	private Integer commentTimes;
	
	/** 作者id */
	private String authorId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTypeByTime() {
		return typeByTime;
	}

	public void setTypeByTime(Integer typeByTime) {
		this.typeByTime = typeByTime;
	}

	public Integer getTypeByArea() {
		return typeByArea;
	}

	public void setTypeByArea(Integer typeByArea) {
		this.typeByArea = typeByArea;
	}

	public Integer getTypeByField() {
		return typeByField;
	}

	public void setTypeByField(Integer typeByField) {
		this.typeByField = typeByField;
	}

	public Integer getWords() {
		return words;
	}

	public void setWords(Integer words) {
		this.words = words;
	}

	public Integer getClickTimes() {
		return clickTimes;
	}

	public void setClickTimes(Integer clickTimes) {
		this.clickTimes = clickTimes;
	}

	public Integer getCommentTimes() {
		return commentTimes;
	}

	public void setCommentTimes(Integer commentTimes) {
		this.commentTimes = commentTimes;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	
	
}
