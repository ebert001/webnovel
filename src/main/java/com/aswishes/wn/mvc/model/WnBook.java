package com.aswishes.wn.mvc.model;

import java.util.Date;

import com.aswishes.spring.mapper.Mapper;

/**
 * 书籍表。存储所有的书籍信息，并对书籍进行分类
 */
@Mapper(tableName = "wn_book", primaryKey = {"id"})
public class WnBook {

	/** 书籍id */
	private Long id;
	
	/** 书籍名称 */
	@Mapper(name= "book_name")
	private String bookName;
	
	/** 书籍描述 */
	private String description;
	
	/** 创作时间 */
	@Mapper(name= "create_time")
	private Date createTime;
	
	/** 更新时间 */
	@Mapper(name= "update_time")
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
	@Mapper(name= "click_times")
	private Integer clickTimes;
	
	/** 评论次数 */
	private Integer commentTimes;
	
	/** 作者id */
	@Mapper(name= "author_id")
	private Long authorId;
	
	private String author;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
