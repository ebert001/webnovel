package com.aswishes.novel.core.entity;

import java.util.Date;

import com.aswishes.novel.common.db.Mapper;

/**
 * 书架表
 */
@Mapper(tableName = "m_forum", primaryKey = {"id"})
public class MBookshelf extends BaseIdAuto {

	@Mapper(name = "book_id")
	private Long bookId;
	
	/** 书籍名称 */
	@Mapper(name = "book_name")
	private String bookName;
	
	/** 书籍作者 */
	@Mapper(name = "book_author")
	private String bookAuthor;
	
	/** 用户id */
	@Mapper(name = "user_id")
	private Long userId;
	
	@Mapper(name = "update_time")
	private Date updateTime;

	@Mapper(name = "create_time")
	private Date createTime;

	public Long getBookId() {
		return bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public Long getUserId() {
		return userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

}
